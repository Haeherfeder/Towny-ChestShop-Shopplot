package de.haeherfeder.Config;
/**
 * 
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author haeherfeder
 *
 */
public class Config {
	private static Properties p = new Properties();
	private static File fileConf = new File("MrX-Server/config.xml");
	protected static void loadConf() {
		if(!fileConf.exists()) {
			resetConf();
			Debug.sendDebugMsg(p.toString());
			fileConf.getParentFile().mkdirs();
			try {
				fileConf.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				FileInputStream in = new FileInputStream(fileConf);
				p.loadFromXML(in);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	protected static void resetConf() {
		DefaultConf[] keys = DefaultConf.values();
		for(int i = 0;i<keys.length;i++) {
			p.setProperty(keys[i].getName(),keys[i].getob().toString());
		}
	}
	public static void saveConf() {
		FileOutputStream os;
		try {
			os = new FileOutputStream(fileConf);
			p.storeToXML(os, "");
			os.flush();
			Debug.sendDebugMsg("stored");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getProperty(String key) {
		return p.getProperty(key);
	}
	public static void setProperty(String key,String value) {
		p.setProperty(key, value);
	}
	protected static void setProperty(String key,Object value) {
		p.setProperty(key, value.toString());
	}
	public static int getPort() {
		return Integer.parseInt(p.getProperty("port"));
	}
	public static boolean getDebug() {
		return Boolean.parseBoolean(p.getProperty("debug"));
	}
	public static int getMaxPlayer() {
		return Integer.parseInt(p.getProperty("maxPlayers"));
	}
	public static int getMaxAcc() {
		return Integer.parseInt(p.getProperty("maxAccounts"));
	}
}