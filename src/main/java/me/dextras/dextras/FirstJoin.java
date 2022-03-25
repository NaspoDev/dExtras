package me.dextras.dextras;

import com.earth2me.essentials.Essentials;
import me.SuperRonanCraft.BetterRTP.references.customEvents.RTP_TeleportPostEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

/*
This class' feature:
Gives "betterrtp.bypass.cooldown" for 10 minutes to new players so that they won't quit the server
if they die off the bat, didn't set a home, and can't rtp again.
 */
public class FirstJoin implements Listener {

    DExtras plugin;
    FirstJoin(DExtras plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (!player.hasPlayedBefore()) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " permission settemp" +
                    " betterrtp.bypass.cooldown true 10m");
        }
    }

    Essentials ess = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");

    @EventHandler
    public void afterRTP(RTP_TeleportPostEvent event) {
        Player player = event.getPlayer();

        int homes = ess.getUser(player).getHomes().size();
        if (homes == 0) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.
                    getConfig().getString("messages.first-join.home-reminder"))));
        }
    }
}
