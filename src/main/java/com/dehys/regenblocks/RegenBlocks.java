package com.dehys.regenblocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.griefdefender.api.GriefDefender;
import com.griefdefender.api.claim.Claim;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class RegenBlocks extends JavaPlugin {

    static List<PutBlockBack> regenBlocks;

    List<String> recordedMaterials;
    List<World> worlds;
    HashMap<UUID,List<String>> claims;
    Material replacementBlock;

    FileConfiguration config;

    private static long TICK_TIME;
    private Timer timer;

    public void onEnable() {

        //Timer (BukkitRunnable but more specific)
        timer = new Timer();
        timer.runTaskTimer(this, 0, 1);

        //Regenerating blocks list
        regenBlocks = new ArrayList<>();
        claims = new HashMap<>();

        //Configuration
        getConfig().options().copyDefaults(false);
        saveConfig();

        for(String key : getConfig().getConfigurationSection("claimsUUIDs").getKeys(false))
        {
            List<String> blocks = new ArrayList<>(getConfig().getStringList("claimsUUIDs." + key + ".recordedMaterials"));
            claims.put(UUID.fromString(key), blocks);
        }

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

        for (PutBlockBack rb : regenBlocks) {
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

            for (PutBlockBack rb : regenBlocks) {
                if(rb.getRegenTime() == currentTickTime()) {
                    rb.regenerate();
                }
            }
        }

    }

}
