package me.dextras.dextras.features.huskdrops;

import me.dextras.dextras.core.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

//Custom drops for husks. 50% chance to drop sand. (Also affected by looting).
public class HuskDrops implements Listener {

    @EventHandler
    private void onEntityKill(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();

        //If the entity is a husk and has a killer
        if (entity.getType().equals(EntityType.HUSK)) {
            if (entity.getKiller() != null) {
                Player player = entity.getKiller();

                if (!(hasLooting(player))){
                    if (Utils.getRandomChance() > 0.49) {
                        dropSand(entity.getWorld(), entity.getLocation(), 1);
                    }
                }
            }
        }
    }

    //Checks if the player is holding a sword, that has looting.
    private boolean hasLooting(Player player) {
        if (!(player.getInventory().getItemInMainHand().getType().equals(Material.AIR))) {
            ItemStack item = player.getInventory().getItemInMainHand();

            if (item.getType().toString().contains("sword")) {
                if (item.getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_MOBS)) {
                    int level = item.getItemMeta().getEnchantLevel(Enchantment.LOOT_BONUS_MOBS);

                    switch (level) {
                        case 1: {
                            if (Utils.getRandomChance() > 0.44) {
                                if (Utils.getRandomChance() > 0.94)
                                    dropSand();
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    private int g

    //Drops one sand naturally at the location of the husk's death.
    private void dropSand(World world, Location loc, int amount) {
        ItemStack sand = new ItemStack(Material.SAND, amount);
        world.dropItemNaturally(loc, sand);
    }

    //Creates a drop % based on the value of the looting enchant.
    //Then drops sand accordingly.
    private void dropSand(World world, Location loc, boolean looting, Player killer) {

    }
}
