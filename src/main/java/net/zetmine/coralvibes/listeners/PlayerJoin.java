package net.zetmine.coralvibes.listeners;

import net.luckperms.api.LuckPerms;
import net.zetmine.coralvibes.commands.Vanish;
import net.zetmine.coralvibes.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PlayerJoin implements Listener {

    private final Items items = new Items();
    private final ConfigManager configManager;





    public PlayerJoin(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
        Player player = event.getPlayer();
        Utils utils = new Utils();
        Vanish vanish = new Vanish();
        ScoreboardUpdater scoreboardUpdater = new ScoreboardUpdater();

        if ((boolean)configManager.getData("maintenance") && !player.isOp()){
            //player.kickPlayer(utils.cvPrefix+ChatColor.RED+"Vous avez ete explusé de CoralVibes!" + "\n\n" +ChatColor.WHITE+ChatColor.BOLD+"Raison: "+ChatColor.RED+"Le serveur est en maintenance! Revenez plus tard!");
            player.kickPlayer(utils.kickMessage("Le serveur est en maintenance! Revenez plus tard!"));
            return;
        }

        scoreboardUpdater.update(event.getPlayer().getUniqueId().toString());

        event.setJoinMessage(ChatColor.GRAY+"["+ ChatColor.GREEN+"+"+ChatColor.GRAY+"] "+player.getName());
        ItemStack mainMenu = items.mainMenu;
        ItemStack shop = items.shop;
        ItemStack profile = items.profile;
        ItemStack lobby = items.lobby;
        player.getInventory().setItem(4, mainMenu);
        player.getInventory().setItem(5, shop);
        player.getInventory().setItem(3, profile);
        player.getInventory().setItem(8, lobby);
        player.getInventory().setHeldItemSlot(4);

        String url = "jdbc:mysql://leather-free.falixserver.net:3306/s1258891_d57c10ef3987";
        String user = "u1258891_3TimrhEXfF";
        String password = "G=7D1Aw5hPOE^cUPMPjaPqkS";

        String selectSql = "SELECT * FROM users WHERE uuid = ?";
        String playerName = player.getDisplayName();
        String playerUuid = player.getUniqueId().toString();

        try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement pstmt = conn.prepareStatement(selectSql)) {
            pstmt.setString(1, playerUuid);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                updateLastLogDate(conn, playerUuid);
                player.sendMessage("Welcome Back (a changer)");
            } else {
                insertNewUser(conn, playerName, playerUuid);
                player.sendMessage("Welcome (a changer)");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        for ( UUID vanishPlayerUUID : vanish.vanishedPlayers) {
            if (player.equals(Bukkit.getPlayer(vanishPlayerUUID)))
                continue;
            Player vanishPlayer = Bukkit.getPlayer(vanishPlayerUUID);
            player.hidePlayer(vanishPlayer);
        }

    }

    private void insertNewUser(Connection conn, String playerName, String playerUuid) throws SQLException {
        String insertSql = "INSERT INTO users (name, uuid, rankId, coins, gems, firstLogDate, lastLogDate, playtime) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        int rankId = 1;
        int coins = 0;
        int gems = 0;
        Time playtime = Time.valueOf("00:00:00");
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = sdf.format(currentDate);

        try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {
            pstmt.setString(1, playerName);
            pstmt.setString(2, playerUuid);
            pstmt.setInt(3, rankId);
            pstmt.setInt(4, coins);
            pstmt.setInt(5, gems);
            pstmt.setString(6, formattedDateTime);
            pstmt.setString(7, formattedDateTime);
            pstmt.setTime(8, playtime);

            int rowsAffected = pstmt.executeUpdate();

        }
    }

    private void updateLastLogDate(Connection conn, String playerUuid) throws SQLException {
        String updateSql = "UPDATE users SET lastLogDate = ? WHERE uuid = ?";

        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = sdf.format(currentDate);

        try (PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
            pstmt.setString(1, formattedDateTime);
            pstmt.setString(2, playerUuid);

            int rowsAffected = pstmt.executeUpdate();
        }
    }

}
