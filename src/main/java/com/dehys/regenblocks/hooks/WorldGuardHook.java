package com.dehys.regenblocks.hooks;

import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;

public class WorldGuardHook implements Hook{

    private WorldGuard worldGuard;
    private boolean isInitialized = false;

    private RegionContainer container;

    @Override
    public Hook Initialize() {
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
        RegionManager regions = container.get(world);
        return regions != null ? regions.getRegion(id) : null;
    }
}
