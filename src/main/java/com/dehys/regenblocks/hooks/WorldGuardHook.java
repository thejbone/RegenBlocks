package com.dehys.regenblocks.hooks;

import com.dehys.regenblocks.Plugin;
import com.dehys.regenblocks.modules.Entry;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.List;

public class WorldGuardHook implements Hook{

    private WorldGuard worldGuard;
    private boolean isInitialized = false;

    private RegionContainer container;

    @Override
    public WorldGuardHook Initialize() {
        try{
            worldGuard = WorldGuard.getInstance();
            this.isInitialized = true;

            container = worldGuard.getPlatform().getRegionContainer();

            return this;
        }catch (NoClassDefFoundError e){
            this.isInitialized = false;
            return null;
        }
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public Object getInstance() {
        return this.isInitialized ? this.worldGuard : null;
    }

    public ProtectedRegion getRegion(World world, String id){
        RegionManager regions = container.get(BukkitAdapter.adapt(world));
        return regions != null ? regions.getRegion(id) : null;
    }

    public ProtectedRegion getRegion(Entry entry){
        RegionManager regions = container.get(BukkitAdapter.adapt(entry.getWorld()));
        return regions != null ? regions.getRegion(entry.getRegionName()) : null;
    }

    public boolean blockIsInRegion(Block block, ProtectedRegion region){
        return region.contains(block.getX(), block.getY(), block.getZ());
    }

    public Entry getRegionalBlockEntry(Block block){
        List<Entry> entries = Plugin.jsonHandler.getRegions();
        for (Entry entry : entries){
            if (
                    blockIsInRegion(block, getRegion(entry)) &&
                    entry.getMaterial().equalsIgnoreCase(block.getType().toString())
            ){
                return entry;
            }
        }
        return null;
    }


}
