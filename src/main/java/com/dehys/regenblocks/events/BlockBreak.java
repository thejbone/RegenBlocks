package com.dehys.regenblocks.events;

import com.dehys.regenblocks.Clock;
import com.dehys.regenblocks.Plugin;
import com.dehys.regenblocks.exceptions.MaterialNotFound;
import com.dehys.regenblocks.hooks.GriefPreventionHook;
import com.dehys.regenblocks.hooks.WorldGuardHook;
import com.dehys.regenblocks.modules.Entry;
import com.dehys.regenblocks.modules.RegenBlock;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.ArrayList;
import java.util.List;

public class BlockBreak implements Listener{


    private final Plugin plugin;
    private final List<Entry> entries;

    public static List<Block> ignoredBlocks;

    private final WorldGuardHook worldGuardHook;
    private final GriefPreventionHook griefPreventionHook;

    public BlockBreak(Plugin plugin) {
        this.plugin = plugin;
        this.entries = Plugin.jsonHandler.getEntries();
        this.worldGuardHook = Plugin.worldGuardHook;
        this.griefPreventionHook = Plugin.griefPreventionHook;
        ignoredBlocks = new ArrayList<>();
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e){
        Block block = e.getBlock();
        World world = block.getWorld();

        if (entries.stream().anyMatch(entry -> entry.getMaterial().toString().equalsIgnoreCase(block.getType().toString()) && entry.getWorld().equals(world))){
            ignoredBlocks.add(block);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block block = e.getBlock();
        World world = block.getWorld();
        Location location = block.getLocation();
        Material material = block.getType();

        if (ignoredBlocks.remove(block)) return;

        if (entries.stream().anyMatch(entry -> entry.getId().startsWith(world.getName()))) {
            if (entries.stream().anyMatch(entry -> entry.getMaterialConverted().equals(material))) {
                Entry entry = null;

                if (worldGuardHook !=null){
                    entry = worldGuardHook.getRegionalBlockEntry(block);
                } else if (griefPreventionHook != null){
                    entry = griefPreventionHook.getRegionalBlockEntry(block);
                }

                if (entry == null) { entry = getBlockEntry(block); }

                assert entry != null;
                System.out.println(entry.getMaterial()+" -> "+entry.getWorld().getName()+" (in "+entry.getRegionName()+")"); //DEBUG


                new RegenBlock(
                        block.getWorld(),
                        block.getLocation(),
                        block.getType(),
                        Clock.TICK_TIME+entry.getDelay()
                );
                System.out.println("new regenblock created");
                block.setType(entry.getReplacementMaterialConverted());
                e.setCancelled(true);

            }
        }

    }


    private Entry getBlockEntry(Block block){
        List<Entry> entries = this.entries;
        for (Entry entry : entries){
            if (entry.getMaterial().equalsIgnoreCase(block.getType().toString()) && entry.getWorld().equals(block.getWorld())){
                return entry;
            }
        }
        return null;
    }

}
