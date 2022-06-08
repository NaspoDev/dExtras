package me.dextras.dextras.features.discoveryanalytics;

import me.dextras.dextras.core.DExtras;
import me.dextras.dextras.core.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// Command logic for "/dextras discovery-analytics" command.
public class DiscoveryAnalyticsCmd {

    private DExtras plugin;
    public DiscoveryAnalyticsCmd(DExtras plugin) {
        this.plugin = plugin;
    }

    //Command logic for player sender.
    public void commandLogic(Player player) {
        //Permission check.
        if (!(player.hasPermission("dextras.discovery-analytics"))) {
            player.sendMessage(Utils.chatColor(
                    plugin.getConfig().getString("messages.general.no-permission")));
            return;
        }

        //Display logic.
        player.sendMessage(Utils.chatColor("&7&l----------[ &6&lDiscovery Analytics &7&l]----------"));
        player.sendMessage(Utils.chatColor("&aStarted Tracking&7: &f" +
                plugin.getConfig().getString("DiscoveryAnalytics.started-tracking")) );

        plugin.getConfig().getConfigurationSection("DiscoveryAnalytics.sub-domains")
                .getKeys(false)
                .forEach(key -> {
                    int temp = plugin.getConfig().getInt("DiscoveryAnalytics.sub-domains." + key);
                    player.sendMessage(Utils.chatColor("&a" + key.toLowerCase() + "&7: &f" + temp));
                });
    }

    //Command logic for console sender.
    public void commandLogic(CommandSender sender) {
        //Display logic.
        sender.sendMessage("----------[ Discovery Analytics ]----------");
        plugin.getConfig().getConfigurationSection("DiscoveryAnalytics.sub-domains")
                .getKeys(false)
                .forEach(key -> {
                    int temp = plugin.getConfig().getInt("DiscoveryAnalytics.sub-domains." + key);
                    sender.sendMessage(key.toLowerCase() + ": " + temp);
                });
    }
}
