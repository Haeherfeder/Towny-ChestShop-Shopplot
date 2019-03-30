package de.haeherfeder.plug.Main;

import org.bukkit.plugin.java.JavaPlugin;

import de.haeherfeder.plug.EventListener.*;


public class Main extends JavaPlugin{
	
	@Override
	public void onEnable() {
		 getServer().getPluginManager().registerEvents(new OnPlace(), this);
	}
}