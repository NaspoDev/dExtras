package me.dextras.dextras.core;

import me.dextras.dextras.features.FirstJoin;
import me.dextras.dextras.features.GravesFix;
import me.dextras.dextras.features.TPRandom;
import me.dextras.dextras.features.discoverygui.DiscoveryGUI;
import me.dextras.dextras.features.discoverygui.DiscoveryGUICmd;
import me.dextras.dextras.features.discoverygui.DiscoveryGUIData;
import me.dextras.dextras.features.discoverygui.DiscoveryGUIInv;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class DExtras extends JavaPlugin {
    //core
    Constants constants = new Constants(this);
    FirstJoin firstJoin = new FirstJoin(this);
    DExtrasCmds dExtrasCmds = new DExtrasCmds(this);
    TabCompleter tabCompleter = new TabCompleter();

    //features
    TPRandom tpRandom = new TPRandom(this);

    DiscoveryGUI discoveryGUI = new DiscoveryGUI(this);
    DiscoveryGUIInv discoveryGUIInv = new DiscoveryGUIInv();
    DiscoveryGUICmd discoveryGUICmd = new DiscoveryGUICmd();
    DiscoveryGUIData discoveryGUIData = new DiscoveryGUIData(this);

    //GravesFix gravesFix = new GravesFix();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getLogger().info("dExtras has been enabled!");

        //DExtrasCmds
        Objects.requireNonNull(this.getCommand("dextras")).setExecutor(dExtrasCmds);
        //TPRandom
        Objects.requireNonNull(this.getCommand("tpr")).setExecutor(tpRandom);
        //TabCompleter
        Objects.requireNonNull(this.getCommand("dextras")).setTabCompleter(tabCompleter);
        //FirstJoin
        if (this.getConfig().getBoolean("FirstJoin")) {
            this.getServer().getPluginManager().registerEvents(firstJoin, this);
            if (this.getServer().getPluginManager().getPlugin("BetterRTP") == null ||
            this.getServer().getPluginManager().getPlugin("Essentials") == null) {
                this.getLogger().warning("Cannot find BetterRTP or Essentials which are softdepends of " +
                        "this plugin!");
            }
        }
        //DiscoveryGUI
        if (this.getConfig().getBoolean("DiscoveryGUI")) {
            this.getServer().getPluginManager().registerEvents(discoveryGUI, this);
            Objects.requireNonNull(this.getCommand("discoverygui")).setExecutor(discoveryGUICmd);
        }

        //GravesFix
        //this.getServer().getPluginManager().registerEvents(gravesFix, this);
    }

    @Override
    public void onDisable() {
        this.getLogger().info("dExtras has been disabled!");
    }
}
