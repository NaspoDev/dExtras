package me.dextras.dextras.features.packprompt;

import me.dextras.dextras.core.DExtras;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.logging.Level;

// Prompts players to download the texture pack listed in the config.
// Does not require a server resource pack to be set in the server.properties (allows more freedom).
public class PackPrompt implements Listener {

    private DExtras plugin;
    public PackPrompt(DExtras plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void joinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        try {
            player.setResourcePack(plugin.getConfig().getString("pack-prompt.pack-url"),
                    null, plugin.getServer().getResourcePackPrompt());
        } catch (Exception e) {
            plugin.getLogger().log(Level.WARNING, "Could not load resource pack!");
        }
    }
}
