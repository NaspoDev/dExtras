package me.dextras.dextras.core;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class DExtrasCmds implements CommandExecutor {

    DExtras plugin;

    DExtrasCmds(DExtras plugin) {
        this.plugin = plugin;
    }

    String prefix = plugin.getConfig().getString("messages.general.prefix");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("dextras")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!(player.hasPermission("dextras.use"))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.
                            requireNonNull(plugin.getConfig().getString("messages.general.no-permission"))));
                    return true;
                }
                if (args.length == 0) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix +
                    "&7Did you mean " + "&6/dextras reload " + "&7?"));
                    return true;
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    plugin.reloadConfig();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix +
                            Objects.requireNonNull(plugin.getConfig().getString("messages.general.reload"))));
                    return true;
                }
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix +
                        "&7Did you mean &6/dextras reload&7?"));
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage("Did you mean /dextras reload?");
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                plugin.reloadConfig();
                sender.sendMessage("dExtras has been reloaded.");
                return true;
            }
            sender.sendMessage("Did you mean /dextras reload?");
        }
        return false;
    }
}
