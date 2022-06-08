package me.dextras.dextras.features.discoveryanalytics;

import me.dextras.dextras.core.DExtras;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class DiscoveryAnalytics implements Listener {

    private DExtras plugin;
    public DiscoveryAnalytics(DExtras plugin) {
        this.plugin = plugin;
    }

    //If the player hasn't played before, update the analytics for their hostname.
    @EventHandler
    private void loginEvent(PlayerLoginEvent event) {
        Player player = event.getPlayer();

        if (!(player.hasPlayedBefore())) {
            plugin.getConfig().getConfigurationSection("DiscoveryAnalytics.sub-domains")
                    .getKeys(false)
                    .forEach(key -> {
                        if (event.getHostname().toLowerCase().startsWith(key.toLowerCase())) {
                            int temp = (plugin.getConfig().getInt("DiscoveryAnalytics.sub-domains." + key)) + 1;
                            plugin.getConfig().set("DiscoveryAnalytics.sub-domains." + key, temp);
                            plugin.saveConfig();
                        }
                    });
        }
    }
}
