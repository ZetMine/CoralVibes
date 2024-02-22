package net.zetmine.coralvibes.commands;

import net.zetmine.coralvibes.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public final class Vanish implements CommandExecutor {

    public final Set<UUID> vanishedPlayers = new HashSet<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Utils utils = new Utils();
        Player targetPlayer = null;

        // /vanish -> toggle status of self
        if (args.length == 0) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(utils.cvPrefix+ChatColor.RED+"Veuillez indiquer un joueur.");

                return true;
            }

            targetPlayer = (Player) sender;

        } else {
            // /vanish <player> -> toggle status of other player
            targetPlayer = Bukkit.getPlayer(args[0]);

            if (targetPlayer == null) {
                sender.sendMessage(utils.cvPrefix+ChatColor.RED + "Le joueur " + args[0] + " n'a pas été trouvé!");

                return true;
            }
        }

        UUID uniqueId = targetPlayer.getUniqueId();
        boolean isVanished = vanishedPlayers.contains(uniqueId);

        for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
            if (otherPlayer.equals(targetPlayer))
                continue;

            if (isVanished)
                otherPlayer.showPlayer(targetPlayer);
            else
                otherPlayer.hidePlayer(targetPlayer);
        }

        sender.sendMessage(utils.cvPrefix+ChatColor.GREEN
                + targetPlayer.getName() + " est maintenant " + (isVanished ? "visible" : "en vanish") + ".");

        if (isVanished)
            vanishedPlayers.remove(uniqueId);

        else
            vanishedPlayers.add(uniqueId);

        return true;
    }
}