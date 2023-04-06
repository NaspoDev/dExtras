package me.dextras.dextras.features.restoreclaimblocks;

import me.dextras.dextras.core.DExtras;
import me.dextras.dextras.core.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.ArrayList;
import java.util.List;

/*
Restore Claim Blocks Feature:
Automatically restores claim blocks of ranked players on first join (upon season turnover).
 */

public class RestoreClaimBlocks implements Listener {

    private DExtras plugin;
    private List<Rank> ranks;

    public RestoreClaimBlocks(DExtras plugin) {
        this.plugin = plugin;

        // List of rank objects (DeltaRealms ranks).
        // Rank objects have the name, priority, and allocated claim blocks.
        // (Priority is in ascending order. Ex. 2 is higher than 1).
        ranks = new ArrayList<>();
        ranks.add(new Rank("coal", 1, 1000));
        ranks.add(new Rank("iron", 2, 2000));
        ranks.add(new Rank("gold", 3, 3500));
        ranks.add(new Rank("diamond", 4, 6000));
        ranks.add(new Rank("emerald", 5, 8000));
        ranks.add(new Rank("netherite", 6, 10000));
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Rank playerDonorRank = null;

        // If they haven't played before.
        if (!(player.hasPlayedBefore())) {

            // If they have a rank, store it. (Stores the highest priority rank if they have multiple).
            for (Rank rank : ranks) {
                if (player.hasPermission("group." + rank.getName())) {
                    if (playerDonorRank != null) {
                        if (rank.getPriorityInAscendingOrder() > playerDonorRank.getPriorityInAscendingOrder()) {
                            playerDonorRank = rank;
                        }
                    } else {
                        playerDonorRank = rank;
                    }
                }
            }

            // If they have a donor rank, restore their claim blocks after 30 seconds.
            if (playerDonorRank != null) {
                Rank localPlayerDonorRank = playerDonorRank; // need to define local var for this in the runnable.
                Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                    @Override
                    public void run() {
                        restoreClaimBlocks(player, localPlayerDonorRank.getAllocatedClaimblocks());
                    }
                }, 1200L);
            }
        }
    }

    // Restores the ranked players claim blocks.
    private void restoreClaimBlocks(Player player, int claimBlocks) {
        // Send console command to restore the claim blocks.
        plugin.getServer().dispatchCommand(
                plugin.getServer().getConsoleSender(),
                "acb " + player.getName() + " " + claimBlocks);

        // Send a message and play a sound to notify player that claim blocks have been restored.
        player.sendMessage(Utils.chatColor("&a" + claimBlocks + " &aclaim blocks for your rank have " +
                "been restored."));
        player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1.0f, 1.0f);
    }

}
