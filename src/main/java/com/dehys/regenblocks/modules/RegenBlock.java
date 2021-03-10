package com.dehys.regenblocks.modules;

import com.dehys.regenblocks.Plugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class RegenBlock {

    private final World world;
    private final ProtectedRegion protectedRegion;
    private final Location location;
    private final Material material;
    private final Material replacementBlock;
    private final long regenTime;

    public RegenBlock(World world, Location location, Material material, Material replacementBlock, long regenTime) {
        this.world = world;
        this.protectedRegion = null;
        this.location = location;
        this.material = material;
        this.replacementBlock = replacementBlock;
        this.regenTime = regenTime;

        Plugin.regenBlocks.add(this);
    }

    //WorldGuard region
    public RegenBlock(World world, ProtectedRegion region, Location location, Material material, Material replacementBlock, long regenTime) {
        this.world = world;
        this.protectedRegion = region;
        this.location = location;
        this.material = material;
        this.replacementBlock = replacementBlock;
        this.regenTime = regenTime;

        Plugin.regenBlocks.add(this);
    }

    public void regenerate() {
        this.world.getBlockAt(this.location).setType(this.material);
    }

    public World getWorld() {
        return this.world;
    }

    public Location getLocation() {
        return this.location;
    }

    public Material getMaterial() {
        return this.material;
    }

    public Material getReplacementBlock(){
        return this.replacementBlock;
    }

    public long getRegenTime() {
        return this.regenTime;
    }
}
