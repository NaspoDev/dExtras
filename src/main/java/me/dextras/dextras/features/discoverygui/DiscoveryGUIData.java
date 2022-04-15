package me.dextras.dextras.features.discoverygui;

import me.dextras.dextras.core.DExtras;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;

public class DiscoveryGUIData {
    private static File dir;
    private static File dataFile;
    private static YamlConfiguration dataConfig;

    private static DExtras plugin;
    public DiscoveryGUIData(DExtras plugin) {
        DiscoveryGUIData.plugin = plugin;

        mkdirs();
        saveDefaultConfig();
    }

    private void mkdirs() {
        dir = new File(plugin.getDataFolder(), "DiscoveryGUIData");
        if (!(dir.exists())) {
            try {
                dir.mkdirs();
            } catch (Exception e) {
                plugin.getLogger().log(Level.WARNING, "Could not create DiscoveryGUIData folder!");
                plugin.getLogger().log(Level.WARNING, Arrays.toString(e.getStackTrace()));
            }
        }
    }

    public static void saveDefaultConfig() {
        dataFile = new File(dir.getPath(), "DiscoveryData.yml");
        if (!(dataFile.exists())) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);

        dataConfig.addDefault("vote-sites", 0);
        dataConfig.addDefault("instagram", 0);
        dataConfig.addDefault("tiktok", 0);
        dataConfig.addDefault("youtube", 0);
        dataConfig.addDefault("reddit", 0);
        dataConfig.addDefault("forums", 0);
        dataConfig.addDefault("friend", 0);
        dataConfig.addDefault("other", 0);

        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public static YamlConfiguration getConfig() {
        if (dataConfig == null) {
            saveDefaultConfig();
            return dataConfig;
        }
        return dataConfig;
    }

    public static void saveConfig() {
        try {
            dataConfig.save(dataFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.WARNING, "Could not save DiscoveryData.yml");
            e.printStackTrace();
        }
    }

    public static void reloadConfig() {
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }
}
