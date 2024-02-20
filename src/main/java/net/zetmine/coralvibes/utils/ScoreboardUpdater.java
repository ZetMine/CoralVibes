package net.zetmine.coralvibes.utils;

import net.zetmine.coralvibes.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ScoreboardUpdater {

    public void startUpdater() {
        // Schedule the task to run every 5 seconds
        new BukkitRunnable() {
            @Override
            public void run() {
                // Iterate over online players and update their scoreboards
                for (Player player : Bukkit.getOnlinePlayers()) {
                    update(player.getUniqueId().toString());
                }
            }
        }.runTaskTimer(Main.getInstance(), 0L, 60L); // 100 ticks = 5 seconds
    }

    public void update(String uuid) {
        String url = "jdbc:mysql://leather-free.falixserver.net:3306/s1258891_d57c10ef3987";
        String user = "u1258891_3TimrhEXfF";
        String password = "G=7D1Aw5hPOE^cUPMPjaPqkS";

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        final Scoreboard board = manager.getNewScoreboard();
        final Objective objective = board.registerNewObjective("mainBoard", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.WHITE.toString() + ChatColor.BOLD + ">>> " + ChatColor.AQUA.toString() + ChatColor.BOLD + "CoralVibes " + ChatColor.GRAY + "0.5" + ChatColor.WHITE.toString() + ChatColor.BOLD + " <<<");

        try {
            // Get player-specific data from the database
            Connection conn = DriverManager.getConnection(url, user, password);; // Implement your database connection method
            String sql = "SELECT name, coins, gems FROM users WHERE uuid = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, uuid);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String playerName = rs.getString("name");
                int coins = rs.getInt("coins");
                int gems = rs.getInt("gems");
                java.util.Date currentDate = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDateTime = sdf.format(currentDate);

                // Add player-specific data to scoreboard
                Score date = objective.getScore(ChatColor.GRAY+formattedDateTime);
                date.setScore(20);
                Score empty = objective.getScore(" ");
                empty.setScore(16);
                Score playerNameScore = objective.getScore(ChatColor.WHITE+"  - "+ChatColor.AQUA+playerName+ChatColor.WHITE+" -");
                playerNameScore.setScore(15);
                Score rank = objective.getScore(ChatColor.WHITE + "    Grade: " + ChatColor.GRAY +"GRADE");
                rank.setScore(14);
                Score coinsScore = objective.getScore(ChatColor.WHITE + "    Pièces: " + ChatColor.GOLD + coins);
                coinsScore.setScore(13);
                Score gemsScore = objective.getScore(ChatColor.WHITE + "    Gemmes: " + ChatColor.GREEN + gems);
                gemsScore.setScore(12);
                Score empty2 = objective.getScore("  ");
                empty2.setScore(11);
                Score online = objective.getScore(ChatColor.WHITE + "Connectés: " + ChatColor.AQUA + Bukkit.getOnlinePlayers().size());
                online.setScore(10);
                Score empty3 = objective.getScore("   ");
                empty3.setScore(9);
                Score ip = objective.getScore(ChatColor.AQUA+"mc.coralvibes.net");
                ip.setScore(8);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        }

        // Set the scoreboard for the player
        Player player = Bukkit.getPlayer(UUID.fromString(uuid));
        if (player != null) {
            player.setScoreboard(board);
        }
    }
}
