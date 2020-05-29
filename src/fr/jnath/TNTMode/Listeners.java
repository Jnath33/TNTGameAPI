package fr.jnath.TNTMode;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;

import fr.jnath.Utils.Utils;

public class Listeners implements Listener {
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		if(event.getInventory().getName().contains("§cBuy kit")) {
			event.setCancelled(true);
			if(event.getCurrentItem().hasItemMeta()) {
				String iName = event.getCurrentItem().getItemMeta().getDisplayName();
				for (Kit kit : Kit.getAllKit()) {
					if(iName.contains(kit.getItemName())) {
						if(PlayerData.getPlayerData(player).getCoins()>=kit.getPrix()) {
							Inventory bInv = Bukkit.createInventory(null, 9, kit.getItemName());
							bInv.setItem(3, Utils.createItem(ChatColor.GREEN+"Achetez", Material.EMERALD_BLOCK, 1));
							bInv.setItem(5, Utils.createItem("§c Ne pas achetez", Material.REDSTONE_BLOCK, 1));
							player.openInventory(bInv);
							
						}else {
							player.sendMessage("Vous n'avez pas assez d'argent pour acheter ceci.");
							player.closeInventory();
						}
						break;
					}
				}
			}
		}else {
			String iName = event.getInventory().getName();
			for (Kit kit : Kit.getAllKit()) {
				if(iName.contains(kit.getItemName())) {
					event.setCancelled(true);
					if(event.getCurrentItem().getType()==Material.EMERALD_BLOCK) {
						PlayerData.getPlayerData(player).addCoins(-kit.getPrix());
						player.closeInventory();
						PlayerData.getPlayerData(player).addKit(kit);
						player.sendMessage("Vous venez d'acheter le kit "+kit.getName()+" il coutait "+String.valueOf(kit.getPrix())+" il vous reste maitenans "+String.valueOf(PlayerData.getPlayerData(player).getCoins()));
					}else if(event.getCurrentItem().getType()==Material.REDSTONE_BLOCK){
						player.closeInventory();
						player.sendMessage("Achat abandoner");
					}
				}
			}
		}
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		try {
			PlayerData.getPlayerData(player).save();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PlayerData.rmPlayerData(player);
	}
}
