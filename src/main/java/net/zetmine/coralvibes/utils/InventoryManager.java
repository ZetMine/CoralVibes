package net.zetmine.coralvibes.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.checkerframework.checker.units.qual.C;

public class InventoryManager implements Listener {

    private final ItemBuilder ib = new ItemBuilder();
    private final Items items = new Items();

    //
    public Inventory mainInv() {
        Inventory mainInv = Bukkit.createInventory(null, 54, ChatColor.RESET+"Menu de Jeu");

        ItemStack glassPane = items.glassPane;
        ItemStack closeMenu = items.closeMenu;
        ItemStack kitPvp = ib.createGuiItem(Material.IRON_SWORD, ChatColor.AQUA.toString()+ChatColor.BOLD+"KitPVP "+ChatColor.GRAY+"1.20");
        ItemStack faction = ib.createGuiItem(Material.NETHER_STAR, ChatColor.AQUA.toString()+ChatColor.BOLD+"Survie Factions "+ChatColor.GRAY+"1.20");
        ItemStack smp = ib.createGuiItem(Material.GRASS_BLOCK, ChatColor.AQUA.toString()+ChatColor.BOLD+"SMP "+ChatColor.GRAY+"1.20");
        ItemStack parkour = ib.createGuiItem(Material.LIGHT_WEIGHTED_PRESSURE_PLATE, ChatColor.AQUA.toString()+ChatColor.BOLD+"Parkour "+ChatColor.GRAY+"1.20");


        int[] glassPaneSlots = {0, 1, 9, 7, 8, 17, 36, 45, 46, 44, 52, 53};

        for (int slot : glassPaneSlots) {
            mainInv.setItem(slot, glassPane);
        }
        mainInv.setItem(21,kitPvp);
        mainInv.setItem(22,faction);
        mainInv.setItem(23,smp);
        mainInv.setItem(31,parkour);

        mainInv.setItem(49,closeMenu);



        return mainInv;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equals(ChatColor.RESET+"Menu de Jeu")){
            if (event.getSlot() == 49){
                player.closeInventory();
            }

            event.setCancelled(true);
        }
    }
}
