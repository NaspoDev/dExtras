package me.dextras.dextras.core;

import me.dextras.dextras.core.commands.CoreCommandLogic;
import me.dextras.dextras.core.commands.Commands;
import me.dextras.dextras.core.commands.TabCompleter;
import me.dextras.dextras.features.discoveryanalytics.DiscoveryAnalyticsCmd;
import me.dextras.dextras.features.firstjoin.FirstJoin;
import me.dextras.dextras.features.discoveryanalytics.DiscoveryAnalytics;
import me.dextras.dextras.features.huskdrops.HuskDrops;
import me.dextras.dextras.features.newplayerpingnaspo.NewPlayerPingNaspo;
import me.dextras.dextras.features.packprompt.PackPrompt;
import me.dextras.dextras.features.tprandom.TPRandom;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class DExtras extends JavaPlugin {
    //core
    private Utils utils;
    private Commands commands;
    private TabCompleter tabCompleter;
    private CoreCommandLogic coreCommandLogic;

    //features
    private FirstJoin firstJoin;
    private TPRandom tpRandom;
    private NewPlayerPingNaspo newPlayerPingNaspo;
    private HuskDrops huskDrops;
    private PackPrompt packPrompt;

    private DiscoveryAnalytics discoveryAnalytics;
    private DiscoveryAnalyticsCmd discoveryAnalyticsCmd;


    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getLogger().info("dExtras has been enabled!");

        softDependencyCheck();
        instantiateClasses();
        registerEvents();
        registerCommands();
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
        //commands initiated below to receive instances of many classes.
        tabCompleter = new TabCompleter();
        coreCommandLogic = new CoreCommandLogic(this);

        //features
        firstJoin = new FirstJoin(this);
        tpRandom = new TPRandom(this);
        newPlayerPingNaspo = new NewPlayerPingNaspo(this);
        huskDrops = new HuskDrops();
        packPrompt = new PackPrompt(this);

        discoveryAnalytics = new DiscoveryAnalytics(this);
        discoveryAnalyticsCmd = new DiscoveryAnalyticsCmd(this);

        //commands
        commands = new Commands(this, coreCommandLogic, tpRandom, discoveryAnalyticsCmd);
    }

    // --- Constants to Register ---

    private void registerEvents() {
        //FirstJoin
        if (this.getConfig().getBoolean("FirstJoin")) {
            this.getServer().getPluginManager().registerEvents(firstJoin, this);
        }
        //NewPlayerPingNaspo
        this.getServer().getPluginManager().registerEvents(newPlayerPingNaspo, this);
        //DiscoveryAnalytics
        this.getServer().getPluginManager().registerEvents(discoveryAnalytics, this);
        //HuskDrops
        this.getServer().getPluginManager().registerEvents(huskDrops, this);
        //PackPrompt
        this.getServer().getPluginManager().registerEvents(packPrompt, this);
    }

    private void registerCommands() {
        //CoreCommands
        this.getCommand("dextras").setExecutor(commands);
        //TabCompleter
        this.getCommand("dextras").setTabCompleter(tabCompleter);
        //TPRandom (for alias)
        this.getCommand("tpr").setExecutor(tpRandom);
    }
}
