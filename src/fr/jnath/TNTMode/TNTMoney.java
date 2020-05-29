package fr.jnath.TNTMode;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TNTMoney implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0 instanceof Player) {
			Player player = (Player) arg0;
			player.sendMessage(ChatColor.GREEN+"Vous avez "+ChatColor.GOLD+String.valueOf(PlayerData.getPlayerData(player).getCoins())+"$");
			return true;
		}
		return false;
	}

}
