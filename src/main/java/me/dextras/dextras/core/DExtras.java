package me.dextras.dextras.core;

import me.dextras.dextras.features.FirstJoin;
import me.dextras.dextras.features.TPRandom;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class DExtras extends JavaPlugin {

    FirstJoin firstJoin = new FirstJoin(this);
    DExtrasCmds dExtrasCmds = new DExtrasCmds(this);
    TabCompleter tabCompleter = new TabCompleter();
    TPRandom tpRandom = new TPRandom(this);

    @Override
    public void onEnable() {
        super.onEnable();
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
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.getLogger().info("dExtras has been disabled!");
    }


}
