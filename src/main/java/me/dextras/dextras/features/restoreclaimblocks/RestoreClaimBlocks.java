package me.dextras.dextras.features.restoreclaimblocks;

import me.dextras.dextras.core.DExtras;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
Restore Claimblocks Feature:
Automatically restores claim blocks of ranked players on first join (upon season turnover).

Does not restore for Netherite rank players!
This is because Netherite is currently a temporary rank, with each duration of the rank
having different amount of claim blocks. Therefore, making it impossible to tell which duration a player had purchased.
Instead, the plugin notifies the player to make a support ticket, and a server administrator can restore them manually.
 */

public class RestoreClaimBlocks implements Listener {

    private DExtras plugin;
    private List<Rank> ranks;

    public RestoreClaimBlocks(DExtras plugin) {
        this.plugin = plugin;

        // List of rank objects (DeltaRealms ranks) excluding netherite.
        // Rank objects have the name, priority, and allocated claim blocks.
        // (Priority is in ascending order. Ex. 2 is higher than 1).
        ranks = new ArrayList<>();
        ranks.add(new Rank("coal", 1, 1000));
        ranks.add(new Rank("iron", 2, 2000));
        ranks.add(new Rank("gold", 3, 3500));
        ranks.add(new Rank("diamond", 4, 6000));
        ranks.add(new Rank("emerald", 5, 8000));
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
        for (String rank : ranksAndBlocks.keySet()) {
            if ()
        }
    }

}
