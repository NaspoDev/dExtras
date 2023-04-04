package me.dextras.dextras.features.restoreclaimblocks;

import me.dextras.dextras.core.DExtras;
import net.luckperms.api.LuckPerms;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.HashMap;

/* Allows administrators to restore the claim blocks of all ranked players during
 season turnover via one simple command.*/
public class RestoreClaimBlocks implements Listener {

    private DExtras plugin;
    private HashMap<String, Integer> ranksAndBlocks;

    public RestoreClaimBlocks(DExtras plugin) {
        this.plugin = plugin;

        // String array of DeltaRealms rank names.
        ranksAndBlocks = new HashMap<>();
        ranksAndBlocks.put("coal", 1000);
        ranksAndBlocks.put("iron", 2000);
        ranksAndBlocks.put("gold", 3500);
        ranksAndBlocks.put("diamond", 6000);
        ranksAndBlocks.put("emerald", 8000);
        ranksAndBlocks.put("netherite", 1000);
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!(player.hasPlayedBefore())) {
            if (hasRank(player)) {

            }
        }
    }

    private boolean hasRank(Player player) {
        for ()
    }
}
