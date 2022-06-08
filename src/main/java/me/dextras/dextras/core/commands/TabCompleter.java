/*
Tab completer for DExtrasCmds
*/

package me.dextras.dextras.core.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

//Tab completer for every command.
public class TabCompleter implements org.bukkit.command.TabCompleter {



    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        //Permission check. (Seeing if they have any dextras perm).
        if (!(Commands.hasAnyPermission((Player) sender))) {
            return null;
        }

        //Add args to list.
        List<String> arguments = new ArrayList<>();

        arguments.add("reload");
        arguments.add("help");
        arguments.add("tpr");
        arguments.add("discovery-analytics");

        //Autocomplete logic.
        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (String s : arguments) {
                if (s.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(s);
                }
            }
            return result;
        }

        return null;
    }
}

