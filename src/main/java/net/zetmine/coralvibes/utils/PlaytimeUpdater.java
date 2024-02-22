package net.zetmine.coralvibes.utils;

import net.zetmine.coralvibes.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.*;

public class PlaytimeUpdater {

    public void startUpdater() {
        // Schedule the task to run every 5 seconds
        new BukkitRunnable() {
            @Override
            public void run() {
                // Iterate over online players and update their scoreboards
                for (Player player : Bukkit.getOnlinePlayers()) {
                    String url = "jdbc:mysql://leather-free.falixserver.net:3306/s1258891_d57c10ef3987";
                    String user = "u1258891_3TimrhEXfF";
                    String password = "G=7D1Aw5hPOE^cUPMPjaPqkS";

                    String selectSql = "SELECT playtime FROM users WHERE uuid = ?";
                    String updateSql = "UPDATE users SET playtime = ? WHERE uuid = ?";
                    String playerUuid = player.getUniqueId().toString();

                    try (Connection conn = DriverManager.getConnection(url, user, password);
                         PreparedStatement pstmt = conn.prepareStatement(selectSql);
                         PreparedStatement updatePstmt = conn.prepareStatement(updateSql)) {
                        pstmt.setString(1, playerUuid);
                        ResultSet rs = pstmt.executeQuery();

                        if (rs.next()) {
                            java.sql.Time playtime = rs.getTime("playtime");
                            Date playtimeDate = new Date(playtime.getTime());

                            playtimeDate.setTime(playtimeDate.getTime() + 1000);
                            java.sql.Time newPlaytime = new java.sql.Time(playtimeDate.getTime());

                            updatePstmt.setTime(1, newPlaytime);
                            updatePstmt.setString(2, playerUuid);
                            updatePstmt.executeUpdate();

                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.runTaskTimer(Main.getInstance(), 0L, 20L); // 100 ticks = 5 seconds
    }
}
