package com.dehys.regenblocks;

import java.util.ArrayList;
import java.util.List;

import com.dehys.regenblocks.events.BlockBreakEvent;
import com.dehys.regenblocks.hooks.GriefPreventionHook;
import com.dehys.regenblocks.hooks.WorldGuardHook;
import com.dehys.regenblocks.modules.RegenBlock;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin{

    //RegenBlock Public list
    public static List<RegenBlock> regenBlocks;

    //Bukkit Variables
    private static PluginManager pluginManager;
    public static Plugin getPlugin;
    public static Clock getClock;

    //Module Variables
    public static WorldGuardHook worldGuardHook;
    public static GriefPreventionHook griefPreventionHook;

    public void onEnable() {
        getPlugin = this;
        getClock = initializeClock();
        pluginManager = getServer().getPluginManager();
        regenBlocks = new ArrayList<>();

        worldGuardHook = (WorldGuardHook) initializeHooks();
        griefPreventionHook = (GriefPreventionHook) initializeHooks();

        initializeEvents();
    }

    public void onDisable() {
        getClock.cancel();
        for (RegenBlock rb : regenBlocks) {
            rb.regenerate();
        }
    }

    public Object initializeHooks(){
        if (pluginManager.getPlugin("WorldGuard") != null){
            return new WorldGuardHook().Initialize();
        } else if (pluginManager.getPlugin("GriefPrevention") != null){
            return new GriefPreventionHook().Initialize();
        } else {
            System.out.println("[RegenBlocks] Not using WorldGuard nor GriefPrevention.");
            return null;
        }
    }

    public Clock initializeClock(){
        return new Clock();
    }

    public void initializeEvents(){
        pluginManager.registerEvents(new BlockBreakEvent(this), this);
    }

}
