package com.dehys.regenblocks.events;

import com.dehys.regenblocks.Plugin;
import com.dehys.regenblocks.hooks.WorldGuardHook;
import com.dehys.regenblocks.modules.Entry;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.List;

public class BlockBreak implements Listener{


    private final Plugin plugin;
    private final List<Entry> entries;
    private final List<Entry> worlds;
    private final List<Entry> regions;

    private WorldGuardHook WGH;

    public BlockBreak(Plugin plugin) {
        this.plugin = plugin;
        this.entries = Plugin.jsonHandler.getEntries();
        this.worlds = Plugin.jsonHandler.getWorlds();
        this.regions = Plugin.jsonHandler.getRegions();

        this.WGH = Plugin.worldGuardHook;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        World world = e.getBlock().getWorld();
        Block block = e.getBlock();
        Location location = block.getLocation();
        Material material = block.getType();

        e.getPlayer().sendMessage("broke");

        if (entries.stream().anyMatch(entry -> entry.getId().startsWith(world.getName()))){
            if (entries.stream().anyMatch(entry -> entry.getBlock() == material)){
                if (Plugin.pluginManager.isPluginEnabled("WorldGuard")){
                    for (Entry entry : regions){
                        if (WGH.blockIsInRegion(block, WGH.getRegion(entry)) && entry.getBlock() == material){
                            e.getPlayer().sendMessage("Block is a valid regional block.");
                        }
                    }
                }


                //do
            }
        }

    }

}
