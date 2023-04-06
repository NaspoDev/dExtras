package me.dextras.dextras.core.commands;

import me.dextras.dextras.core.DExtras;
import me.dextras.dextras.core.Utils;
import me.dextras.dextras.features.discoveryanalytics.DiscoveryAnalyticsCmd;
import me.dextras.dextras.features.tprandom.TPRandom;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

//Command executor for all commands, except aliases.
//Logic for core commands (help, reload) can be found in the CoreCommandLogic class.
//Logic for any other command is re-distributed to its own class.
public class Commands implements CommandExecutor {

    private DExtras plugin;
    private CoreCommandLogic coreCommandLogic;
    private TPRandom tpRandom;
    private DiscoveryAnalyticsCmd discoveryAnalyticsCmd;
    public Commands(DExtras plugin, CoreCommandLogic coreCommandLogic,
                    TPRandom tpRandom, DiscoveryAnalyticsCmd discoveryAnalyticsCmd) {
        this.plugin = plugin;
        this.coreCommandLogic = coreCommandLogic;
        this.tpRandom = tpRandom;
        this.discoveryAnalyticsCmd = discoveryAnalyticsCmd;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("dextras")) {

            // --- PLAYER COMMAND STUFF ---

            if (sender instanceof Player) {
                Player player = (Player) sender;

                //Help command.
                if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
                    coreCommandLogic.helpCommand(player);
                    return true;
                }

                //Reload command.
                if (args[0].equalsIgnoreCase("reload")) {
                    coreCommandLogic.reloadCommand(player);
                    return true;
                }

                //tpr command.
                if (args[0].equalsIgnoreCase("tpr")) {
                    tpRandom.commandLogic(player);
                    return true;
                }

                //discovery-analytics command.
                if (args[0].equalsIgnoreCase("discovery-analytics")) {
                    discoveryAnalyticsCmd.commandLogic(player);
                    return true;
                }
                return true;
            }

            // --- CONSOLE COMMAND STUFF ---

            //Help command.
            if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
                coreCommandLogic.helpCommand(sender);
                return true;
            }

            //Reload command.
            if (args[0].equalsIgnoreCase("reload")) {
                coreCommandLogic.helpCommand(sender);
                return true;
            }

            //tpr command.
            if (args[0].equalsIgnoreCase("tpr")) {
                tpRandom.commandLogic(sender);
                return true;
            }

            //discovery-analytics command.
            if (args[0].equalsIgnoreCase("discovery-analytics")) {
                discoveryAnalyticsCmd.commandLogic(sender);
                return true;
            }
        }
        return false;
    }

    //Checks if the player has any dextras permission. (Mainly used for help command and tab completer).
    public static boolean hasAnyPermission(Player player) {
        String[] permissions = {
                "dextras.reload",
                "dextras.tpr",
                "dextras.discovery-analytics"
        };

        boolean[] permissionValues = new boolean[permissions.length];

        //Checking if they have any of the permissions.
        for (int i = 0; i < permissions.length; i++) {
            permissionValues[i] = player.hasPermission(permissions[i]);
        }

        //Deciding the return value.
        for (boolean b : permissionValues) {
            if (b) {
                return true;
            }
        }
        return false;
    }
}
