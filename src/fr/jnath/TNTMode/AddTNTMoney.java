package fr.jnath.TNTMode;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddTNTMoney implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0 instanceof Player) {
			Player player = (Player) arg0;
			PlayerData.getPlayerData(player).addCoins(5000);
			return true;
		}
		return false;
	}

}
