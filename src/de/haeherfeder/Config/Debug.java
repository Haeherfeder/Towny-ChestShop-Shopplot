package de.haeherfeder.Config;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.bukkit.Bukkit;

import de.haeherfeder.plug.Main.Main;

public class Debug {
	private static FileOutputStream fileOut = null; 
	public static boolean getDebug() {
		return Config.getDebug();
	}
	private static DebugType getType() {
		for(DebugType tx : DebugType.values()) {
			if(tx.toString().equals(Config.getProperty("debugType"))) {
				return tx;
			}
		}
		return null;
	}	
	private static OutputStream getDebugStream() throws FileNotFoundException {
		if(getType().equals(DebugType.console)) {
			return System.out;
		}
		if(getType().equals(DebugType.file)) {
			if(fileOut==null) {
				fileOut = new FileOutputStream(Main.getPlugin().getDataFolder()+"/"+Config.getProperty("debugDir")+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_kk:mm"))+".debug");
			}
			return fileOut;
		}
		if(getType().equals(DebugType.bukkitBr)) {
			return null;
		}
		if(getType().equals(DebugType.bukkitCons)) {
			return System.err;
		}
		return System.out;
	}
	public static void sendDebugMsg(String s) {
		if(getDebug()) {
			try {
				if(null==getDebugStream()) {
					Bukkit.broadcastMessage(s);
				}
				try {
					getDebugStream().write((s+System.lineSeparator()).getBytes());
					getDebugStream().flush();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				Bukkit.broadcastMessage(s);
			}
		}
	}
	public static void sendDebugMsg(Object ob) {
		sendDebugMsg(ob.toString());
	}
	public static void sendDebugMsg(Object[] obA) {
		for(Object obj : obA) {
			sendDebugMsg(obj.toString());
		}
	}
	public static void sendMessage(String mes) {
		sendDebugMsg(mes);
	}
}