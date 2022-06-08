package me.dextras.dextras.core.commands;

import me.dextras.dextras.core.DExtras;
import me.dextras.dextras.core.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

//Command logic for all core commands.
public class CoreCommandLogic {

    private final String[] helpTextPlayer;
    private final String[] helpTextConsole;

    private DExtras plugin;
    public CoreCommandLogic(DExtras plugin) {
        this.plugin = plugin;

        //Text to send for help command logic for player command.
        helpTextPlayer = new String[] {
                "&7&l----------[ &6&ldExtras &7&l]----------",
                "&a/dextras reload &7- Reload the plugin.",
                "&a/dextras tpr &7(or use alias &a/tpr&7) - Teleport to a random person on the server.",
                "&a/dextras discovery-analytics &7- Display the discovery analytics."
        };

        //Text to send for help command logic for console command.
        helpTextConsole = new String[] {
                "----------[ dExtras ]----------",
                "/dextras reload - Reload the plugin.",
                "/dextras tpr (or use alias /tpr) - Teleport to a random person on the server.",
                "/dextras discovery-analytics - Display the discovery analytics."
        };
    }

    // --- Help Command ---

    void helpCommand(Player player) {
        //Permission check. (Seeing if they have any dextras perm).
        if (!(Commands.hasAnyPermission(player))) {
            player.sendMessage(Utils.chatColor(
                    plugin.getConfig().getString("messages.general.no-permission")));
            return;
        }

        //Sending the help message.
        for (String s : helpTextPlayer) {
            player.sendMessage(Utils.chatColor(s));
        }
    }

    void helpCommand(CommandSender sender) {
        //Sending the help message.
        for (String s : helpTextConsole) {
            sender.sendMessage(s);
        }
    }

    // --- Reload Command ---

    void reloadCommand(Player player) {
        if (!(player.hasPermission("dextras.reload"))) {
            player.sendMessage(Utils.chatColor(
                    plugin.getConfig().getString("messages.general.no-permission")));
            return;
        }

        Utils.reloadConfigs();
        player.sendMessage(Utils.chatColor(Utils.prefix +
                plugin.getConfig().getString("messages.general.reload")));
    }

    void reloadCommand(CommandSender sender) {
        Utils.reloadConfigs();
        sender.sendMessage("All configurations reloaded.");
    }
}
