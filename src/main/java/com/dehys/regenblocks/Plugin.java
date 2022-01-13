package com.dehys.regenblocks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.claim.Claim;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Plugin extends JavaPlugin{

    static List<RegenBlock> regenBlocks;

    List<String> recordedMaterials;
    List<World> worlds;
    List<UUID> claims;
    Material replacementBlock;

    private static long TICK_TIME;
    private Timer timer;

    public void onEnable() {

        //Timer (BukkitRunnable but more specific)
        timer = new Timer();
        timer.runTaskTimer(this, 0, 1);

        //Regenerating blocks list
        regenBlocks = new ArrayList<>();
        claims = new ArrayList<>();

        //Configuration
        recordedMaterials = new ArrayList<>();
        worlds = new ArrayList<>();

        getConfig().options().copyDefaults(true);
        saveConfig();

        getConfig().getStringList("recordedMaterials").forEach(
                material -> recordedMaterials.add(material.toString()));

        getConfig().getStringList("claimsUUIDs").forEach(
                claim -> claims.add(UUID.fromString(claim)));


        replacementBlock = Material.matchMaterial(getConfig().getString("replacementBlock"));
        if(replacementBlock == null) {
            replacementBlock = Material.AIR;
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

    public Claim getClaim(Block block)
    {
        if(block == null) return null;
        final Claim c = GriefDefender.getCore().getClaimAt(block.getLocation());
        return c;
    }


    private static class Timer extends BukkitRunnable {

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
