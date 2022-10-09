package me.dextras.dextras.features.huskdrops;

import me.dextras.dextras.core.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

//Custom drops for husks. 50% chance to drop sand. (Also affected by looting).
public class HuskDrops implements Listener {

    @EventHandler
    private void onEntityKill(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();

        //If the entity is a husk and has a killer
        if (entity.getType().equals(EntityType.HUSK)) {
            if (entity.getKiller() != null) {
                Player player = entity.getKiller();

                //If the killer didn't use a looting sword, drop sand at normal chance.
                if (!(hasLooting(player))){
                    if (Utils.getRandomChance() > 0.49) {
                        dropSand(entity.getWorld(), entity.getLocation(), 1);
                    }
                }
                //Otherwise, drop sand via looting chances.
                dropSand(entity.getWorld(), entity.getLocation(),
                        getLootingDrop(
                        player.getInventory().getItemInMainHand().getItemMeta()
                                .getEnchantLevel(Enchantment.LOOT_BONUS_MOBS)));
            }
        }
    }

    //Checks if the player is holding a sword, that has looting.
    private boolean hasLooting(Player player) {
        if (!(player.getInventory().getItemInMainHand().getType().equals(Material.AIR))) {
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item.getType().toString().contains("sword")) {
                if (item.getItemMeta().hasEnchant(Enchantment.LOOT_BONUS_MOBS)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Looting drop calculation algorithm:
    // 1. Calculates a base drop chance for each looting level.
    // 2. If there will be ad drop, it will calculate the chance of increasing the drop amount.
    private int getLootingDrop(int lootingLevel) {
        switch (lootingLevel) {
            case 1: {
                if (Utils.getRandomChance() > 0.44) { // Chance to drop 55%
                    if (Utils.getRandomChance() > 0.96) { // Chance to drop 2 3%
                        return 2;
                    }
                    return 1;
                }
            }
            case 2: {
                if (Utils.getRandomChance() > 0.39) { // Chance to drop 60%
                    if (Utils.getRandomChance() > 0.95) { // Chance to drop 2 4%
                        return 2;
                    }
                    return 1;
                }
            }
            case 3: {
                if (Utils.getRandomChance() > 0.34) { // Chance to drop 65%
                    if (Utils.getRandomChance() > 0.94) { // Chance to drop 2 5%
                        return 2;
                    }
                    return 1;
                }
            }
        }

        return 0;
    }

    //Drops one sand naturally at the location of the husk's death.
    private void dropSand(World world, Location loc, int amount) {
        ItemStack sand = new ItemStack(Material.SAND, amount);
        world.dropItemNaturally(loc, sand);
    }
}
