package net.zetmine.coralvibes.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;


public class ConfigManager {

    private FileConfiguration config;
    private File configFile;


    public void loadConfig(JavaPlugin plugin) {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
        }
        configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
            config = YamlConfiguration.loadConfiguration(configFile);
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public Object getData(String key){
        Object data = config.get(key);
        if (Objects.equals(data, "true")){
            data = true;
        }
        if (Objects.equals(data, "false")){
            data = false;
        }

        return data;

    }

    public void setData(String key, Object value){
        config.set(key, value);
    }


    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
