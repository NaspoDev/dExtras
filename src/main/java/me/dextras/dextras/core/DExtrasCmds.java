package me.dextras.dextras.core;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DExtrasCmds implements CommandExecutor {

    DExtras plugin;
    DExtrasCmds(DExtras plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("dextras")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (!(player.hasPermission("dextras.use"))) {
                    player.sendMessage(Utils.chatColor(plugin.getConfig().getString("messages.general.no-permission")));
                    return true;
                }
                if (args.length == 0) {
                    player.sendMessage(Utils.chatColor(Utils.prefix +
                    "&7Did you mean " + "&6/dextras reload " + "&7?"));
                    return true;
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    Utils.reloadConfigs();
                    player.sendMessage(Utils.chatColor(Utils.prefix +
                            plugin.getConfig().getString("messages.general.reload")));
                    return true;
                }
                player.sendMessage(Utils.chatColor(Utils.prefix +
                        "&7Did you mean &6/dextras reload&7?"));
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage("Did you mean /dextras reload?");
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                Utils.reloadConfigs();
                sender.sendMessage("All configurations reloaded.");
                return true;
            }
            sender.sendMessage("Did you mean /dextras reload?");
        }
        return false;
    }
}
