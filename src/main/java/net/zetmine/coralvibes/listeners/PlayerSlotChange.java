package net.zetmine.coralvibes.listeners;

import net.zetmine.coralvibes.utils.Items;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class PlayerSlotChange implements Listener {

    private final Items items = new Items();

    @EventHandler
    public void onPlayerItemHeld(InventoryClickEvent event) {
        event.setCancelled(true);
    }
    @EventHandler
    public void onSwapItemHand(PlayerSwapHandItemsEvent event) {
        event.setCancelled(true);
    }

}
