package de.haeherfeder.plug.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.plugin.java.JavaPlugin;

import com.Acrobot.ChestShop.Events.PreShopCreationEvent;
import com.palmergames.bukkit.towny.Towny;
import com.palmergames.bukkit.towny.object.TownBlockType;
import com.palmergames.bukkit.towny.object.TownyUniverse;

import de.haeherfeder.plug.EventListener.*;


public class Main extends JavaPlugin implements Listener{
	
	@Override
	public void onEnable() {
		 getServer().getPluginManager().registerEvents(new OnPlace(), this);
	}
	
	@EventHandler
    public boolean onShopCreate(PreShopCreationEvent event)
    {
		Player p = event.getPlayer();
		Sign s = event.getSign();
		Location l = s.getLocation();
		if(p.hasPermission("Chestshop.CreateOutsideShops")) {
			return true;
		}
		Towny t = new Towny();
		t.getTownyUniverse();
		if(TownBlockType.COMMERCIAL==TownyUniverse.getTownBlock(l).getType()) {
			p.sendMessage("Du kannst auf diesem Plot kein Shop errichten.");
			event.setOutcome(PreShopCreationEvent.CreationOutcome.OTHER);
			Player p2 =  Bukkit.getPlayer("none");
			event.setCreator(p2);
		}
		return false;
    }	
}