package me.dextras.dextras.features.newplayerpingnaspo;

import me.dextras.dextras.core.DExtras;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

//Plays a firework sounds to Naspo when a new player joins the server.
//Purpose: When Naspo is tabbed out he can still welcome new players via audio queue.
public class NewPlayerPingNaspo implements Listener {

    DExtras plugin;
    public NewPlayerPingNaspo(DExtras plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!(plugin.getConfig().getBoolean("NewPlayerPingNaspo"))) {
            return;
        }

        Player player = event.getPlayer();

        if (!(player.hasPlayedBefore())) {
            if (getNaspo() != null) {
                Player naspo = getNaspo();

                naspo.playSound(naspo.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 0.5f, 1.0f);
                Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                    @Override
                    public void run() {
                        naspo.playSound(naspo.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 0.5f, 1.0f);
                    }
                }, 30L);

            }
        }
    }

    private Player getNaspo() {
        return Bukkit.getPlayerExact("Naspo_");
    }
}
