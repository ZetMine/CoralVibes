package net.zetmine.coralvibes.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Broadcast implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        // Check if there are arguments
        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Utilisation: /broadcast <message>");
            return true;
        }

        // Concatenate the arguments into a single string
        String message = ChatColor.RED.toString() + ChatColor.BOLD + "Broadcast" + ChatColor.WHITE + ChatColor.BOLD + " >>> " +ChatColor.WHITE+ String.join(" ", args);

        // Broadcast the message
        Bukkit.broadcastMessage(message);

        return true;
    }
}
