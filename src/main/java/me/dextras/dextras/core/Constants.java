package me.dextras.dextras.core;

import me.dextras.dextras.features.discoverygui.DiscoveryGUIData;
import org.bukkit.ChatColor;

public class Constants {
    private static DExtras plugin;

    public static String prefix;

    Constants(DExtras plugin) {
        Constants.plugin = plugin;

        reloadVars();
    }

    public static void reloadConfigs() {
        plugin.reloadConfig();
        DiscoveryGUIData.reloadConfig();

        reloadVars();
    }

    private static void reloadVars() {
        prefix = plugin.getConfig().getString("messages.general.prefix");
    }

    public static String chatColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
