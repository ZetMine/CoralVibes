package net.zetmine.coralvibes.listeners;

import net.zetmine.coralvibes.utils.ItemBuilder;
import net.zetmine.coralvibes.utils.Items;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerSlotChange implements Listener {

    private final Items items = new Items();

    @EventHandler
    public void onPlayerItemHeld(InventoryMoveItemEvent event) {
        ItemStack mainMenu = items.mainMenu;
        ItemStack shop = items.shop;

        if (event.getItem().equals(mainMenu) || event.getItem().equals(shop)){
            event.setCancelled(true);
        }
    }

}
