package de.haeherfeder.plug.EventListener;

import org.bukkit.Location;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.Acrobot.ChestShop.Events.PreShopCreationEvent;
import com.palmergames.bukkit.towny.object.TownBlockType;
import com.palmergames.bukkit.towny.object.TownyUniverse;

public class OnPlace implements Listener{
    public boolean allowShopCreate(PreShopCreationEvent event)
    {
		Player p = event.getPlayer();
		p.sendMessage("Event erkannt");
		Sign s = event.getSign();
		Location l = s.getLocation();
		if(p.hasPermission("Chestshop.CreateOutsideShops")) {
			p.sendMessage("Have Perm");
			return true;
		}
		p.sendMessage("have no perm.");
//		p.sendMessage("no perm");
//		Towny t = new Towny();
		if (TownyUniverse.isWilderness(p.getLocation().getBlock())) {
			p.sendMessage("Wilderness");
			return false;
		} 
		TownBlockType we;
		try {
			we = TownyUniverse.getTownBlock(l).getType();
		} catch (Exception e) {
			p.sendMessage("Exeption");
			e.printStackTrace();
			return false;
		}
		System.out.println(we);
		if(we.equals(TownBlockType.COMMERCIAL)) {
			p.sendMessage("Plot Allowed.");
			return true;
		}
		p.sendMessage("Du kannst auf diesem Plot kein Shop errichten.");
		return false;
    }
    @EventHandler
    public void onShopCreate(PreShopCreationEvent event) {
    	if(!allowShopCreate(event)) {
    	String[] test = {"Test","Test","Test","Test"};
    	event.setSignLines(test);
    	event.setOutcome(PreShopCreationEvent.CreationOutcome.OTHER);
    	}
    }
}