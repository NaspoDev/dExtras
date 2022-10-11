package me.dextras.dextras.features.discoveryanalytics;

import me.dextras.dextras.core.DExtras;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class DiscoveryAnalytics implements Listener {

    // playerName and hostname variables used for sharing their data between loginEvent and joinEvent methods.
    private String uuid;
    private String hostname;

    private DExtras plugin;
    public DiscoveryAnalytics(DExtras plugin) {
        this.plugin = plugin;
    }


    // Get the player's name and hostname.
    @EventHandler
    private void loginEvent(PlayerLoginEvent event) {
        uuid = event.getPlayer().getUniqueId().toString();
        hostname = event.getHostname();
    }

    // If the player hasn't played before, update the analytics for their hostname.
    @EventHandler
    private void joinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        // Checks that the uuid and hostname between events match.
        if (!(player.getUniqueId().toString().equalsIgnoreCase(uuid))) {
            return;
        }

        if (!(player.hasPlayedBefore())) {
            plugin.getConfig().getConfigurationSection("DiscoveryAnalytics.sub-domains")
                    .getKeys(false)
                    .forEach(key -> {
                        if (hostname.toLowerCase().startsWith(key.toLowerCase())) {
                            int temp = (plugin.getConfig().getInt("DiscoveryAnalytics.sub-domains." + key)) + 1;
                            plugin.getConfig().set("DiscoveryAnalytics.sub-domains." + key, temp);
                            plugin.saveConfig();
                        }
                    });
        }
    }
}
