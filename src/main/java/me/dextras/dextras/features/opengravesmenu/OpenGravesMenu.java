package me.dextras.dextras.features.opengravesmenu;

import me.dextras.dextras.core.DExtras;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

// When a player clicks on the grave compass, it opens the graves menu for them.
public class OpenGravesMenu implements Listener {

    private DExtras plugin;

    public OpenGravesMenu(DExtras plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (player.getInventory().getItemInMainHand().getType() == Material.RECOVERY_COMPASS) {
            ItemMeta compassItemMeta = player.getInventory().getItemInMainHand().getItemMeta();
            if (compassItemMeta.getDisplayName().contains("'s Grave")) {
                if (compassItemMeta.hasLore()) {
                    player.performCommand("graves");
                }
            }
        }
    }
}
