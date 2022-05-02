/*
Tab completer for DExtrasCmds
*/

package me.dextras.dextras.core;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.ArrayList;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        if (label.equalsIgnoreCase("dextras")) {
            if (!(sender.hasPermission("dextras.use"))) {
                return null;
            }
        }

        List<String> arguments = new ArrayList<String>();

        arguments.add("reload");

        List<String> result = new ArrayList<String>();
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

