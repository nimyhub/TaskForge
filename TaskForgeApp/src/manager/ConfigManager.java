package manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import data.Config;

public class ConfigManager {
	private File configFile;
	
	public ConfigManager() {
		String userHome = (System.getProperty("user.home") + File.separator + "TaskForge");
		System.out.println("ConfigManager::Path test " + userHome);
		configFile = new File(userHome, "config.dat");
		File directory = configFile.getParentFile();
        if (directory != null && !directory.exists()) {
            directory.mkdirs();
        }
	}
	
	public Config loadConfig() {
		if(!configFile.exists()) return getDefaultConfig();
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(configFile))) {
			return (Config) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return getDefaultConfig();
		}
	}
	
	public void saveConfig(Config config) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(configFile))) {
			out.writeObject(config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void defaultOnError() {
		Config config = getDefaultConfig();
		saveConfig(config);
	}
	
	private Config getDefaultConfig() {
		Config config = new Config();
		String userHome = System.getProperty("user.home");
		config.setLastDir(userHome + File.separator + "TaskForge");
		config.setLastFileName("tasks");
		config.setLastFormat(".dat");
		saveConfig(config);
		return config;
	}
}
