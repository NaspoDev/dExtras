package me.dextras.dextras.core;

import org.bukkit.ChatColor;

public class Utils {
    private static DExtras plugin;
    public static String prefix;

    Utils(DExtras plugin) {
        Utils.plugin = plugin;

        reloadVars();
    }

    public static void reloadConfigs() {
        plugin.reloadConfig();

        reloadVars();
    }

    private static void reloadVars() {
        prefix = plugin.getConfig().getString("messages.general.prefix");
    }

    public static String chatColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
