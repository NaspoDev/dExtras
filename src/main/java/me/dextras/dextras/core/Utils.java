package me.dextras.dextras.core;

import org.bukkit.ChatColor;

import java.text.DecimalFormat;
import java.util.Random;

public class Utils {
    private static DExtras plugin;
    public static String prefix;

    private static Random rand;
    private static DecimalFormat df;

    Utils(DExtras plugin) {
        Utils.plugin = plugin;
        rand = new Random();
        df = new DecimalFormat();

        reloadVars();
    }

    public static void reloadConfigs() {
        plugin.reloadConfig();

        reloadVars();
    }

    // Reloads any variables in the plugin that get their values from values in the config.
    private static void reloadVars() {
        prefix = plugin.getConfig().getString("messages.general.prefix");
    }

    // Returns a Bukkit formatted chat color string.
    public static String chatColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    //Returns a random double, formatted to 2 decimal points to represent percentage.
    public static double getRandomChance(){
        df.setMaximumFractionDigits(2);
        return Double.parseDouble(df.format(rand.nextDouble()));
    }
}
