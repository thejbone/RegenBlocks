package com.dehys.regenblocks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Plugin extends JavaPlugin{

    static List<RegenBlock> regenBlocks;

    List<String> recordedMaterials;
    List<World> worlds;
    Material replacementBlock;

    private static long TICK_TIME;
    private Timer timer;

    public static Plugin getPlugin;

    public void onEnable() {

        new JsonHandler();

        getPlugin = this;



        //Timer (BukkitRunnable but more specific)
        timer = new Timer();
        timer.runTaskTimer(this, 0, 1);

        //Regenerating blocks list
        regenBlocks = new ArrayList<>();


        //Configuration
        recordedMaterials = new ArrayList<>();
        worlds = new ArrayList<>();

        getConfig().options().copyDefaults(true);
        saveConfig();

        getConfig().getStringList("recordedMaterials").stream().forEach(
                material -> recordedMaterials.add(material.toString()));



        getConfig().getStringList("worlds").stream().forEach(
                world -> worlds.add(
                        Bukkit.getWorld(world.toString())
                ));


        replacementBlock = Material.matchMaterial(getConfig().getString("replacementBlock"));
        if(replacementBlock == null) {
            replacementBlock = Material.BEDROCK;
        }


        //Events
        getServer().getPluginManager().registerEvents(new BlockBroke(this), this);
    }

    public void onDisable() {

        timer.cancel();
        timer = null;

        for (RegenBlock rb : regenBlocks) {
            rb.regenerate();
        }
    }

    static long currentTickTime() {
        return TICK_TIME;
    }

    private class Timer extends BukkitRunnable {

        @Override
        public void run() {

            TICK_TIME++;

            for (RegenBlock rb : regenBlocks) {
                if(rb.getRegenTime() == currentTickTime()) {
                    rb.regenerate();
                }
            }
        }

    }

}
