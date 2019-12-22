package de.haeherfeder.Config;
/**
 * 
 */

import org.bukkit.configuration.file.FileConfiguration;

/**
 * @author haeherfeder
 *
 */
public class Config {
	private static FileConfiguration fileConf = null;
	public static void loadConf(FileConfiguration fileConf) {
		Config.fileConf = fileConf;
	}
	protected static void resetConf() {
		DefaultConf[] keys = DefaultConf.values();
		for(int i = 0;i<keys.length;i++) {
			fileConf.set(keys[i].getName(),keys[i].getob());
		}
	}
	public static String getProperty(String key) {
		return fileConf.getString(key);
	}
	public static void setProperty(String key,String value) {
		fileConf.set(key, value);
	}
	public static void setProperty(String key,Object value) {
		fileConf.set(key, value);
	}
	public static boolean getDebug() {
		return fileConf.getBoolean("debug");
	}
}