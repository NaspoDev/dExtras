package me.dextras.dextras.features.restoreclaimblocks;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

/* Allows administrators to restore the claim blocks of all ranked players during
 season turnover via one simple command.*/
public class RestoreClaimBlocks implements Listener {

    private String[] ranks;

    public RestoreClaimBlocks() {

        // String array of DeltaRealms rank names.
        ranks = new String[]{
                "coal",
                "iron",
                "gold",
                "diamond",
                "emerald",
                "netherite"
        };
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {

    }
}
