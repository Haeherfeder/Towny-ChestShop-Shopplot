package de.haeherfeder.Config;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
				fileOut = new FileOutputStream(Config.getProperty("debugDir")+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_kk:mm"))+".debug");
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
				getDebugStream().write((s+System.lineSeparator()).getBytes());
				getDebugStream().flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
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
}