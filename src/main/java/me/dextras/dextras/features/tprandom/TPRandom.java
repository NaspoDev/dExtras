package me.dextras.dextras.features.tprandom;

import me.dextras.dextras.core.Utils;
import me.dextras.dextras.core.DExtras;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
TPRandom:
Creates the /tpr command. Moderators can use this command to teleport to a random player on the server upon each use.
It's intended usage is for When the server has a high player count.
*/
public class TPRandom implements CommandExecutor {

    DExtras plugin;
    public TPRandom(DExtras plugin) {
        this.plugin = plugin;
    }

    //Command logic for player sender.
    public void commandLogic(Player player) {
        //Permission check.
        if (!(player.hasPermission("dextras.tpr"))) {
            player.sendMessage(Utils.chatColor(plugin.getConfig().getString("messages.general.no-permission")));
            return;
        }

        //Checks if they're they only player online.
        if (plugin.getServer().getOnlinePlayers().size() == 1) {
            player.sendMessage(Utils.chatColor(Utils.prefix + "&7You're the only player online!"));
            return;
        }

        //Gets all online players in a list.
        List<Player> onlinePlayers = new ArrayList<Player>();
        onlinePlayers.addAll(plugin.getServer().getOnlinePlayers());
        Player selected;

        //Selects a random player from the list of all online players.
        boolean loop = true;
        do {
            int rand = new Random().nextInt(onlinePlayers.size());
            selected = onlinePlayers.get(rand);
            if (!(selected.getName().equalsIgnoreCase(player.getName()))) {
                break;
            }
        } while (loop);

        //Teleports them to the selected player.
        player.sendMessage(Utils.chatColor(Utils.prefix + "&7Teleporting to " +
                selected.getDisplayName() + "&7."));
        player.teleport(selected.getLocation());
    }

    //Command logic for console sender.
    public void commandLogic(CommandSender sender) {
        sender.sendMessage("This command can only be used by a player.");
    }

    //Command handling for the alias to "/dextras tpr", "/tpr".
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("tpr")) {

            //Player logic.
            if (sender instanceof Player) {
                Player player = (Player) sender;
                commandLogic(player);
                return true;
            }

            //Console logic.
            commandLogic(sender);
        }
        return false;
    }


}
