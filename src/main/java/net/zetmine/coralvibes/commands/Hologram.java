package net.zetmine.coralvibes.commands;

import net.zetmine.coralvibes.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

public class Hologram implements CommandExecutor {



    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED+"Seul les joueurs peuvent executer cette commande.");

            return true;
        }

        if (args.length == 0)
            return false;

        Player player = (Player) sender;
        HologramBuilder hologram = new HologramBuilder(String.join(" ", args).split("\\|"));

        hologram.spawn(player.getLocation());

        return true;
    }

    public final class HologramBuilder {

        private final String[] lines;

        public HologramBuilder(String... lines) {
            this.lines = lines;
        }

        public void spawn(Location originLocation) {
            for (String line : lines) {
                ArmorStand stand = originLocation.getWorld().spawn(originLocation, ArmorStand.class);

                stand.setVisible(false);
                stand.setGravity(false);
                stand.setInvulnerable(true);

                stand.setCustomNameVisible(true);
                stand.setCustomName(Utils.colorize(line));

                originLocation.subtract(0, 0.25, 0);
            }
        }
    }
}