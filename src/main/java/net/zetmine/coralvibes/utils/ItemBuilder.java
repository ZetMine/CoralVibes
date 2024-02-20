package net.zetmine.coralvibes.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ItemBuilder {
    public ItemStack createGuiItem(final Material material, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, 1);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_DESTROYS);

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack createItem(Material material, String name, Integer amount, List<String> lore, Boolean unbreakable, List<Enchantment> enchants, List<Integer> enchantsLevel){
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        if(name!=null){meta.setDisplayName(name);}
        if(lore!=null){meta.setLore(lore);}
        meta.setUnbreakable(unbreakable);
        if(enchants != null && enchantsLevel != null && enchants.size() == enchantsLevel.size()){
            for (int i = 0; i < enchants.size(); i++) {
                meta.addEnchant(enchants.get(i),enchantsLevel.get(i),true);
            }
        }
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack createItem(Material material, String name, Integer amount){
        List<String> defaultLore = null;
        Boolean defaultUnbreakable = true;
        List<Enchantment> defaultEnchants = null;
        List<Integer> defaultEnchantsLevel = null;

        return createItem(material, name, amount, defaultLore, defaultUnbreakable, defaultEnchants, defaultEnchantsLevel);
    }

    public ItemStack createHead(String name, Integer amount, UUID uuid, List<String> lore, Boolean unbreakable) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, amount);
        SkullMeta meta = (SkullMeta) item.getItemMeta(); // Use the same meta object for both general and skull-specific metadata

        if (name != null) {
            meta.setDisplayName(name);
        }
        if (lore != null) {
            meta.setLore(lore);
        }
        meta.setUnbreakable(unbreakable);

        GameProfile profile = new GameProfile(uuid, "");
        profile.getProperties().put("textures", new Property("textures", "FuzeIII"));

        Field profileField;
        try {
            profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            Bukkit.getLogger().warning("Failed to set base64 skull value!");
        }

        item.setItemMeta(meta); // Set the SkullMeta object with the custom texture to the item

        return item;
    }

}