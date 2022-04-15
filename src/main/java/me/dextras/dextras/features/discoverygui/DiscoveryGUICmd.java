package me.dextras.dextras.features.discoverygui;

import me.dextras.dextras.core.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DiscoveryGUICmd implements CommandExecutor {
    protected static List<Player> hasUsed = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("discoverygui")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can use this command.");
                return true;
            }
            Player player = (Player) sender;
            if (hasUsed.contains(player)) {
                player.sendMessage(Constants.chatColor("&cYou've already submitted your response."));
                return true;
            }
            if (!(player.hasPlayedBefore())) {
                player.openInventory(DiscoveryGUIInv.inv);
            }
        }
        return false;
    }
}
