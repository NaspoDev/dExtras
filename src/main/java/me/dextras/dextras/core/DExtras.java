package me.dextras.dextras.core;

import me.dextras.dextras.features.FirstJoin;
import me.dextras.dextras.features.NewPlayerPingNaspo;
import me.dextras.dextras.features.TPRandom;
import me.dextras.dextras.features.discoverygui.DiscoveryGUI;
import me.dextras.dextras.features.discoverygui.DiscoveryGUICmd;
import me.dextras.dextras.features.discoverygui.DiscoveryGUIData;
import me.dextras.dextras.features.discoverygui.DiscoveryGUIInv;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class DExtras extends JavaPlugin {
    //core
    private Utils utils;
    private DExtrasCmds dExtrasCmds;
    private TabCompleter tabCompleter;

    //features
    private FirstJoin firstJoin;
    private TPRandom tpRandom;

    private DiscoveryGUI discoveryGUI;
    private DiscoveryGUIInv discoveryGUIInv;
    private DiscoveryGUICmd discoveryGUICmd;
    private DiscoveryGUIData discoveryGUIData;

    private NewPlayerPingNaspo newPlayerPingNaspo;

    //private GravesFix gravesFix;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getLogger().info("dExtras has been enabled!");

        softDependencyCheck();
        instantiateClasses();
        registerEvents();
        registerCommands();


        //GravesFix
        //this.getServer().getPluginManager().registerEvents(gravesFix, this);
    }

    @Override
    public void onDisable() {
        this.getLogger().info("dExtras has been disabled!");
    }

    private void softDependencyCheck() {
        //FirstJoin
        if (this.getConfig().getBoolean("FirstJoin")) {
            if (this.getServer().getPluginManager().getPlugin("Essentials") == null) {
                this.getLogger().log(Level.WARNING, "Essentials plugin could not be location which is a " +
                        "soft-dependency of this plugin. " +
                        "The FirstJoin feature will not be fully functional without it!");
            }
            if (this.getServer().getPluginManager().getPlugin("BetterRTP") == null) {
                this.getLogger().log(Level.WARNING, "BetterRTP plugin could not be location which is a " +
                        "soft-dependency of this plugin. " +
                        "The FirstJoin feature will not be fully functional without it!");
            }
        }
    }

    private void instantiateClasses() {
        //core
        utils = new Utils(this);
        dExtrasCmds = new DExtrasCmds(this);
        tabCompleter = new TabCompleter();

        //features
        firstJoin = new FirstJoin(this);
        tpRandom = new TPRandom(this);

        discoveryGUI = new DiscoveryGUI(this);
        discoveryGUIInv = new DiscoveryGUIInv();
        discoveryGUICmd = new DiscoveryGUICmd();
        discoveryGUIData = new DiscoveryGUIData(this);

        newPlayerPingNaspo = new NewPlayerPingNaspo(this);
        //gravesFix = new GravesFix();
    }

    // --- Constants to register ---

    private void registerEvents() {
        //FirstJoin
        if (this.getConfig().getBoolean("FirstJoin")) {
            this.getServer().getPluginManager().registerEvents(firstJoin, this);
        }
        //DiscoveryGUI
        if (this.getConfig().getBoolean("DiscoveryGUI")) {
            this.getServer().getPluginManager().registerEvents(discoveryGUI, this);
        }
        //NewPlayerPingNaspo
        this.getServer().getPluginManager().registerEvents(newPlayerPingNaspo, this);
    }

    private void registerCommands() {
        //DExtrasCmds
        this.getCommand("dextras").setExecutor(dExtrasCmds);
        //TPRandom
        this.getCommand("tpr").setExecutor(tpRandom);
        //TabCompleter
        this.getCommand("dextras").setTabCompleter(tabCompleter);
        //DiscoveryGUI
        if (this.getConfig().getBoolean("DiscoveryGUI")) {
            this.getCommand("discoverygui").setExecutor(discoveryGUICmd);
        }
    }
}
