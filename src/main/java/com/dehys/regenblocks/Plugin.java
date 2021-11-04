package com.dehys.regenblocks;

import java.util.ArrayList;
import java.util.List;

import com.dehys.regenblocks.events.BlockBreak;
import com.dehys.regenblocks.hooks.GriefPreventionHook;
import com.dehys.regenblocks.hooks.WorldGuardHook;
import com.dehys.regenblocks.modules.RegenBlock;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin{

    public static List<RegenBlock> regenBlocks;

    public static PluginManager pluginManager;
    public static Plugin getPlugin;
    public static Clock getClock;
    public static JsonHandler jsonHandler;

    public static WorldGuardHook worldGuardHook;
    public static GriefPreventionHook griefPreventionHook;

    public void onEnable() {
        jsonHandler = new JsonHandler().initialize();
        getPlugin = this;
        pluginManager = getServer().getPluginManager();
        regenBlocks = new ArrayList<>();

        initializeEvents();
        initializeHooks();

        getClock = initializeClock();
    }

    public void onDisable() {
        getClock.cancel();
        for (RegenBlock rb : regenBlocks) {
            rb.regenerate();
        }
    }

    public void initializeHooks(){
        if (pluginManager.getPlugin("WorldGuard") != null){
            worldGuardHook = new WorldGuardHook().Initialize();
        } else if (pluginManager.getPlugin("GriefPrevention") != null){
            griefPreventionHook = new GriefPreventionHook().Initialize();
        } else {
            System.out.println("[RegenBlocks] Not using WorldGuard nor GriefPrevention.");
        }
    }

    public Clock initializeClock(){
        return new Clock();
    }

    public void initializeEvents(){
        pluginManager.registerEvents(new BlockBreak(this), this);
    }

}
