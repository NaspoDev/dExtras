package me.dextras.dextras.features.discoverygui;

import me.dextras.dextras.core.Utils;
import me.dextras.dextras.core.DExtras;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import net.md_5.bungee.api.chat.TextComponent;

/*
DiscoveryGUI:
Sends a hoverable clickable message to a new player after 3 minutes of playtime. Upon clicking, the message
will open a GUI for the user where the user can select where they discovered DeltaRealms.
*/
public class DiscoveryGUI implements Listener {

    DExtras plugin;
    public DiscoveryGUI(DExtras plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        TextComponent message = new TextComponent();

        if (!(player.hasPlayedBefore())) {
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
                @Override
                public void run() {
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1.0f);
                    message.setText("Spare a few seconds? Click here to tell us how you found us!");
                    message.setColor(ChatColor.YELLOW);
                    message.setBold(true);
                    message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/discoverygui"));
                    message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                            new Text("Click me!")));
                    player.spigot().sendMessage(message);
                }
            }, 3600L);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getInventory().equals(DiscoveryGUIInv.inv))) {
            return;
        }
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();

        if (player.hasPermission("dextras.discoverygui")) {
            player.closeInventory();
            player.sendMessage(Utils.chatColor(Utils.prefix + "&7No response was recorded. GUI was opened " +
                    "in admin mode."));
            return;
        }

        switch (event.getSlot()) {
            case 0 -> DiscoveryGUIData.getConfig().set("vote-sites",
                    (DiscoveryGUIData.getConfig().getInt("vote-sites") + 1));
            case 1 -> DiscoveryGUIData.getConfig().set("instagram",
                    (DiscoveryGUIData.getConfig().getInt("instagram") + 1));
            case 2 -> DiscoveryGUIData.getConfig().set("tiktok",
                    (DiscoveryGUIData.getConfig().getInt("tiktok") + 1));
            case 3 -> DiscoveryGUIData.getConfig().set("youtube",
                    (DiscoveryGUIData.getConfig().getInt("youtube") + 1));
            case 4 -> DiscoveryGUIData.getConfig().set("reddit",
                    (DiscoveryGUIData.getConfig().getInt("reddit") + 1));
            case 5 -> DiscoveryGUIData.getConfig().set("forums",
                    (DiscoveryGUIData.getConfig().getInt("forums") + 1));
            case 6 -> DiscoveryGUIData.getConfig().set("friend",
                    (DiscoveryGUIData.getConfig().getInt("friend") + 1));
            case 7 -> DiscoveryGUIData.getConfig().set("other",
                    (DiscoveryGUIData.getConfig().getInt("other") + 1));
        }
        DiscoveryGUIData.saveConfig();
        player.closeInventory();
        DiscoveryGUICmd.hasUsed.add(player);
        player.sendMessage(Utils.chatColor("&eResponse recorded, thank you!"));
    }
}
