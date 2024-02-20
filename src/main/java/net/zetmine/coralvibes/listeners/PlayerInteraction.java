package net.zetmine.coralvibes.listeners;

import net.zetmine.coralvibes.utils.InventoryManager;
import net.zetmine.coralvibes.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class PlayerInteraction implements Listener {

    private final ItemBuilder ib = new ItemBuilder();
    private final InventoryManager im = new InventoryManager();

    @EventHandler
    public void playerInteraction(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack mainMenu = ib.createItem(Material.COMPASS, ChatColor.AQUA.toString()+ChatColor.BOLD+"Menu de Jeu"+ ChatColor.GRAY+" [Clique Droit]",1);
        if (Objects.equals(event.getItem(), mainMenu)) {
            Inventory mainInv = im.mainInv();
            player.openInventory(mainInv);
        }
    }
}
