package net.zetmine.coralvibes.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Items {

    private final ItemBuilder ib = new ItemBuilder();

    // Hotbar Items
    public ItemStack mainMenu = ib.createItem(Material.COMPASS, ChatColor.AQUA.toString()+ChatColor.BOLD+"Menu de Jeu"+ ChatColor.GRAY+" [Clique Droit]",1);
    public ItemStack shop = ib.createItem(Material.EMERALD, ChatColor.AQUA.toString()+ChatColor.BOLD+"Boutique"+ChatColor.GRAY+" [Clique Droit]",1);
    public ItemStack profile = ib.createItem(Material.PLAYER_HEAD, ChatColor.AQUA.toString()+ChatColor.BOLD+"Profil"+ChatColor.GRAY+" [Clique Droit]",1);
    public ItemStack lobby = ib.createItem(Material.BEACON,ChatColor.AQUA.toString()+ChatColor.BOLD+"Changer de Lobby"+ChatColor.GRAY+" [Clique Droit]",1);

    public ItemStack playerSetOn = ib.createItem(Material.LIME_DYE,ChatColor.AQUA.toString()+ChatColor.BOLD+"Afficher les Joueurs"+ChatColor.GRAY+" [Clique Droit]",1);
    public ItemStack playerSetOff = ib.createItem(Material.GRAY_DYE,ChatColor.AQUA.toString()+ChatColor.BOLD+"Cacher les Joueurs"+ChatColor.GRAY+" [Clique Droit]",1);

    // GUI Items
    public ItemStack glassPane = ib.createGuiItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE," ");
    public ItemStack closeMenu = ib.createGuiItem(Material.BARRIER, ChatColor.RED.toString()+ChatColor.BOLD+"Fermer le Menu");
}
