package net.zetmine.coralvibes.listeners;

import net.luckperms.api.LuckPerms;
import net.zetmine.coralvibes.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class PlayerChat implements Listener {

    private final LuckPerms lpApi;
    public PlayerChat(LuckPerms lpApi) {
        this.lpApi = lpApi;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Utils utils = new Utils();

        Player player = event.getPlayer();
        String message = event.getMessage();
        List<Object> playerData = utils.getPlayer(player.getName(),lpApi);
        String prefix = (String) playerData.get(1);

        if (utils.isRanked(player.getName(),lpApi)){
            event.setFormat(ChatColor.DARK_GRAY+player.getName()+": "+message);
        } else {
            event.setFormat(prefix+" "+player.getName()+": "+message);
        }
    }
}