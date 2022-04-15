package me.dextras.dextras.features;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

//UPCOMING FEATURE
/*
GravesFix:
Fixes a bug where if a graves compass is stored after the grave has been collected, and that compass
is then used on a lodestone, it immediately crashes the server.
 */
public class GravesFix implements Listener {
    private String name;

    public GravesFix() {
        File graveFile = new File(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("Graves"))
                .getDataFolder() + "/config/", "grave.yml");
        if (!(graveFile.exists())) {
            return;
        }
        YamlConfiguration graveYaml = YamlConfiguration.loadConfiguration(graveFile);
        name = Objects.requireNonNull(graveYaml.getString("settings.default.default.compass.name"));
        if (name.contains("%")) {
            name = name.substring(name.lastIndexOf('%') + 1);
        }
    }

    private ItemStack item;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        itemCheck(player.getInventory().getItemInMainHand());

        if (player.getInventory().getItemInMainHand().equals(item)) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                event.setCancelled(true);
            }
        }
    }



    /*@EventHandler
    public void onInventoryMove(InventoryClickEvent event) {
        if (Objects.requireNonNull(event.getCurrentItem()).getType().equals(Material.COMPASS)) {
            if (Objects.requireNonNull(event.getCurrentItem().getItemMeta()).getDisplayName().contains(name)) {
                if (event.getCurrentItem().getItemMeta().hasLore()) {
                    item = event.getCurrentItem();
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void offHandCheck(PlayerSwapHandItemsEvent event) {
        itemCheck(Objects.requireNonNull(event.getMainHandItem()));
        if (event.getOffHandItem().equals(item)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        itemCheck(event.getItemDrop().getItemStack());
        if (event.getItemDrop().equals(item)) {
            event.setCancelled(true);
        }
    }*/

    public void itemCheck(ItemStack item) {
        if (item.getType().equals(Material.COMPASS)) {
            if (Objects.requireNonNull(item.getItemMeta()).getDisplayName().contains(name)) {
                if (item.getItemMeta().hasLore()) {
                    this.item = item;
                }
            }
        }
    }
}
