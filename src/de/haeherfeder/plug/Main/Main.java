
package de.haeherfeder.plug.Main;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import de.haeherfeder.plug.EventListener.*;
import de.haeherfeder.Config.*;


public class Main extends JavaPlugin{
	private static Plugin pl;
	@Override
	public void onEnable() {
		saveDefaultConfig();
		pl = this;
		Config.loadConf(this.getConfig());
		 getServer().getPluginManager().registerEvents(new OnPlace(), this);
	}
	public static Plugin getPlugin() {
		return pl;
	}
}