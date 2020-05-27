package fr.jnath.TNTMode;

import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;



public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		saveDefaultConfig();
		SetKit.set();
		FichierPlayerData.setDefauldRepertory(getConfig().getString("TNTMode.relativeFolder"));
		if(!new File(getConfig().getString("TNTModeApi.relativeFolder")).exists()) {
			new File(getConfig().getString("TNTModeApi.relativeFolder")).mkdir();
		}
	}
}
