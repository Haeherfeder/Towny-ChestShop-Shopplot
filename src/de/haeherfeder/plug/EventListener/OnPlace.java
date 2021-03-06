package de.haeherfeder.plug.EventListener;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.Acrobot.ChestShop.Events.PreShopCreationEvent;
import com.palmergames.bukkit.towny.object.TownBlockType;
import com.palmergames.bukkit.towny.object.TownyUniverse;

import de.haeherfeder.Config.Debug;

public class OnPlace implements Listener{
    public boolean allowShopCreate(PreShopCreationEvent event)
    {
		Player p = event.getPlayer();
		Debug.sendMessage("Event erkannt");
		Sign s = event.getSign();
		Block b = s.getBlock();
		Location l = s.getLocation();
		if(p.hasPermission("Chestshop.CreateOutsideShops")) {
			Debug.sendMessage("Have Perm");
			return true;
		}
		p.sendMessage("have no perm.");
//		p.sendMessage("no perm");
//		Towny t = new Towny();
		if (TownyUniverse.isWilderness(b)) {
			Debug.sendMessage("Wilderness");
			p.sendMessage("you aren't allowed to create a shop in the Wilderness");
			return false;
		} 
		TownBlockType we;
		try {
			we = TownyUniverse.getTownBlock(l).getType();
		} catch (Exception e) {
			Debug.sendMessage("Exeption");
			e.printStackTrace();
			return false;
		}
		Debug.sendDebugMsg(we);
		if(we.equals(TownBlockType.COMMERCIAL)||we.equals(TownBlockType.EMBASSY)) {
			Debug.sendMessage("Plot Allowed.");
			return true;
		}
		p.sendMessage("Du kannst auf diesem Plot kein Shop errichten.");
		return false;
    }
    @EventHandler
    public void onShopCreate(PreShopCreationEvent event) {
    	if(!allowShopCreate(event)) {
    	String[] message = {"ChestShop arent","allowed","outside","Shop Plots"};
    	event.setSignLines(message);
    	event.setOutcome(PreShopCreationEvent.CreationOutcome.OTHER);
    	}
    }
}