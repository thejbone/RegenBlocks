package com.dehys.regenblocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class RegenBlock {

    private World world;
    private Location location;
    private Material material;
    private long regenTime;

    RegenBlock(World world, Location location, Material material, long regenTime) {
        this.world = world;
        this.location = location;
        this.material = material;
        this.regenTime = regenTime;

        Plugin.regenBlocks.add(this);
    }

    void regenerate() {
        this.world.getBlockAt(this.location).setType(this.material);
    }

    World getWorld() {
        return this.world;
    }

    Location getLocation() {
        return this.location;
    }

    Material getMaterial() {
        return this.material;
    }

    long getRegenTime() {
        return this.regenTime;
    }
}
