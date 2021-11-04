package com.dehys.regenblocks.hooks;

import com.dehys.regenblocks.Plugin;
import com.dehys.regenblocks.modules.Entry;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import org.bukkit.block.Block;

import java.util.List;

public class GriefPreventionHook implements Hook{

    private GriefPrevention griefPrevention;
    private boolean isInitialized = false;

    @Override
    public GriefPreventionHook Initialize() {
        return null;
    }

    @Override
    public boolean isInitialized() {
        return false;
    }

    @Override
    public Object getInstance() {
        return null;
    }

    public Entry getRegionalBlockEntry(Block block){
        /*List<Entry> entries = Plugin.jsonHandler.getRegions();
        for (Entry entry : entries){
            if (
                    blockIsInRegion(block, getRegion(entry)) &&
                            entry.getMaterial().toString().equalsIgnoreCase(block.getType().toString())
            ){
                return entry;
            }
        }*/
        return null;
    }
}
