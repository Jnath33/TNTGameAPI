package fr.jnath.TNTMode;

import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;



public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		saveDefaultConfig();
		
		FichierPlayerData.setDefauldRepertory(getConfig().getString("TNTMode.relativeFolder"));
		if(!new File(getConfig().getString("TNTMode.relativeFolder")).exists()) {
			new File(getConfig().getString("TNTMode.relativeFolder")).mkdir();
		}
	}
}