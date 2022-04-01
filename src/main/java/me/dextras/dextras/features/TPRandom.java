package me.dextras.dextras.features;

import me.dextras.dextras.core.DExtras;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/*
This class' feature:
Creates the /tpr command. Moderators can use this command to teleport to a random player on the server upon each use.
It's intended usage is for When the server has a high player count.
*/
public class TPRandom implements CommandExecutor {

    DExtras plugin;
    public TPRandom(DExtras plugin) {
        this.plugin = plugin;
    }

    String prefix = plugin.getConfig().getString("messages.general.prefix");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("tpr")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("This command can only be used by a player.");
                return true;
            }
            Player player = (Player) sender;
            if (!(player.hasPermission("dextras.tpr"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull
                        (plugin.getConfig().getString("messages.general.no-permission"))));
                return true;
            }

            if (plugin.getServer().getOnlinePlayers().size() == 1) {
                player.sendMessage(prefix + "&7You're the only player online!");
                return true;
            }

            List<Player> onlinePlayers = new ArrayList<Player>();
            onlinePlayers.addAll(plugin.getServer().getOnlinePlayers());
            Player selected;

            boolean loop = true;
            do {
                int rand = new Random().nextInt(onlinePlayers.size());
                selected = onlinePlayers.get(rand);
                if (!(selected.getName().equalsIgnoreCase(player.getName()))) {
                    break;
                }
            } while (loop);

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix +
                    "&7Teleporting to " + selected.getDisplayName() + "&7."));
            player.teleport(selected.getLocation());
        }

        return false;
    }
}
