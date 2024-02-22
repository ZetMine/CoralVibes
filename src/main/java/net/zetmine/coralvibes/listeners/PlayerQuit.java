package net.zetmine.coralvibes.listeners;

import net.zetmine.coralvibes.commands.Vanish;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();
        event.setQuitMessage(ChatColor.GRAY+"["+ ChatColor.RED+"-"+ChatColor.GRAY+"] "+player.getName());

        Vanish vanish = new Vanish();
        if (vanish.vanishedPlayers.contains(player.getUniqueId())) {
            vanish.vanishedPlayers.remove(player.getUniqueId());
        }



    }


}
