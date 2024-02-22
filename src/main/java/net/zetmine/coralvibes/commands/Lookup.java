package net.zetmine.coralvibes.commands;

import net.zetmine.coralvibes.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.*;

public class Lookup implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        Utils utils = new Utils();

        if (!(sender instanceof Player)){
            sender.sendMessage(utils.cvPrefix+ChatColor.RED+"Seul les joueurs peuvent executer cette commande.");
            return false;
        } else {
            if (!(args.length == 1)) {
                sender.sendMessage(ChatColor.RED + "Utilisation: /maintenance <on/off>");
                return false;
            }
            String url = "jdbc:mysql://leather-free.falixserver.net:3306/s1258891_d57c10ef3987";
            String user = "u1258891_3TimrhEXfF";
            String password = "G=7D1Aw5hPOE^cUPMPjaPqkS";

            String selectSql = "SELECT * FROM users WHERE uuid = ?";
            String playerUuid = ((Player) sender).getPlayer().getUniqueId().toString();

            try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement pstmt = conn.prepareStatement(selectSql)) {
                pstmt.setString(1, playerUuid);
                ResultSet rs = pstmt.executeQuery();

                

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}