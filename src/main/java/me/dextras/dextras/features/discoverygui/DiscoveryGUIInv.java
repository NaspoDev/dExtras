package me.dextras.dextras.features.discoverygui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class DiscoveryGUIInv {
    protected static Inventory inv;

    public DiscoveryGUIInv() {
        setupInv();
    }

    private void setupInv() {
        inv = Bukkit.createInventory(null, 9, ChatColor.GOLD + "" + ChatColor.BOLD + "Discovery GUI");

        ItemStack item = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "(Left-Click)");
        meta.setLore(lore);


        //vote sites
        item.setType(Material.PAPER);
        meta.setDisplayName(ChatColor.GREEN + "Vote Sites");
        item.setItemMeta(meta);
        inv.setItem(0, item);

        //instagram
        item.setType(Material.PAINTING);
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Instagram");
        item.setItemMeta(meta);
        inv.setItem(1, item);

        //tiktok
        item.setType(Material.NOTE_BLOCK);
        meta.setDisplayName(ChatColor.DARK_PURPLE + "TikTok");
        item.setItemMeta(meta);
        inv.setItem(2, item);

        //youtube
        item.setType(Material.MUSIC_DISC_PIGSTEP);
        meta.addItemFlags(ItemFlag.values());
        meta.setDisplayName(ChatColor.RED + "You" + ChatColor.WHITE + "Tube");
        item.setItemMeta(meta);
        inv.setItem(3, item);

        //reddit
        item.setType(Material.OBSERVER);
        meta.setDisplayName(ChatColor.GOLD + "Reddit");
        item.setItemMeta(meta);
        inv.setItem(4, item);

        //forums
        item.setType(Material.BOOKSHELF);
        meta.setDisplayName(ChatColor.DARK_GREEN + "Forums");
        item.setItemMeta(meta);
        inv.setItem(5, item);

        //friend
        item.setType(Material.PLAYER_HEAD);
        meta.setDisplayName(ChatColor.YELLOW + "Friend");
        item.setItemMeta(meta);
        inv.setItem(6, item);

        //other
        item.setType(Material.HEART_OF_THE_SEA);
        meta.setDisplayName(ChatColor.BLUE + "Other");
        item.setItemMeta(meta);
        inv.setItem(7, item);

        //filler for last slot
        item.setType(Material.GRAY_STAINED_GLASS_PANE);
        meta.setDisplayName("");
        lore.clear();
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(8, item);
    }
}
