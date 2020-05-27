package fr.jnath.TNTMode;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.jnath.Utils.Utils;

public class PlayerData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7098619538188328744L;
	private static Map<String, PlayerData> s_all_player_data = new HashMap<String, PlayerData>();
	private List<String> _myKit;
	private int _coins;
	private int _xpTotal;
	private String _playerName;
	private String _playerUID;
	
	public PlayerData(List<String> kits, int coin, int xpTotal, Player player) {
		_myKit=kits;
		_coins=coin;
		_xpTotal=xpTotal;
		_playerName=player.getName();
		_playerUID = player.getUniqueId().toString();
		s_all_player_data.put(_playerName, this);
	}
	public PlayerData(PlayerData pData) {
		_myKit=pData._myKit;
		_coins=pData._coins;
		_xpTotal=pData._xpTotal;
		_playerName=pData._playerName;
		_playerUID=pData._playerUID;
		s_all_player_data.put(_playerName, this);
		
	}
	public boolean haveKit(Kit kit) {
		return _myKit.contains(kit.getName()); 
	}
	public int getCoins() {
		return _coins;
	}
	public void addCoins(int coins) {
		_coins+=coins;
	}
	public int getTotalXP() {
		return _xpTotal;
	}
	public int getLevel() {
		boolean end = false;
		int lvl=0;
		int totalXpRenward = _xpTotal;
		while(end){
			if(totalXpRenward-(lvl*50+500)<=0) {
				lvl+=1;
			}else end = true;
		}
		return lvl;
	}
	public void addKit(Kit kit) {
		_myKit.add(kit.getName());
	}
	public Inventory buyKitInventory() {
		Inventory inv = Bukkit.createInventory(null, 54, "§cBuy kit");
		Collection<Kit> allKit = Kit.getAllKit();
		int curentSet=-1;
		for(Kit kit : allKit) {
			curentSet++;
			if(this.haveKit(kit)) {
				inv.setItem(curentSet, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5));
			}else {
				int prix=kit.getPrix();
				inv.setItem(curentSet, Utils.createItem(kit.getItemName()+" §c("+ChatColor.GOLD+String.valueOf(prix)+"$§c)", kit.getMaterial(), 1));
			}
			if(!(curentSet<44)) {
				break;
			}
		}
		return inv;
		
	}
	public List<Kit> getKit(){
		List<Kit> myKit = new ArrayList<Kit>();
		for(String kitS : _myKit) {
			myKit.add(Kit.getKit(kitS));
		}
		return myKit;
	}
	public void save() throws IOException {
		FichierPlayerData file = new FichierPlayerData(_playerUID);
		file.ouvrir("E");
		file.ecrire(this);
		file.fermer();
	}
	public static PlayerData getPlayerData(Player player) {
		return s_all_player_data.get(player.getName());
	}
	public static void rmPlayerData(Player player) {
		s_all_player_data.remove(player.getName());
	}
}
