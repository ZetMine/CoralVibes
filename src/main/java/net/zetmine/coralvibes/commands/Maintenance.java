package net.zetmine.coralvibes.commands;

import net.luckperms.api.LuckPerms;
import net.zetmine.coralvibes.utils.ConfigManager;
import net.zetmine.coralvibes.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Maintenance implements CommandExecutor {


    private final ConfigManager configManager;


    public Maintenance(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Utils utils = new Utils();
        boolean maintenanceState = (boolean) configManager.getData("maintenance");

        if (args.length == 0 || !args[0].equalsIgnoreCase("on") && !args[0].equalsIgnoreCase("off")) {
            sender.sendMessage(ChatColor.RED + "Utilisation: /maintenance <on/off>");
            return true;
        }

        boolean newState = args[0].equalsIgnoreCase("on");

        if (maintenanceState == newState) {
            sender.sendMessage(utils.cvPrefix + (newState ? ChatColor.RED : ChatColor.GREEN) +
                    "La Maintenance est " + (newState ? "deja activee" : "deja desactivee") + "! /maintenance " + (newState ? "off" : "on") + " pour " + (newState ? "la desactiver" : "la reactiver") + "!");
        } else {
            sender.sendMessage(utils.cvPrefix + ChatColor.WHITE + "La maintenance a ete " + (newState ? ChatColor.GREEN + "active" : ChatColor.RED + "desactive") + ChatColor.WHITE + "!");
            configManager.setData("maintenance", newState);
            configManager.saveConfig();
        }

        return true;
    }
}
