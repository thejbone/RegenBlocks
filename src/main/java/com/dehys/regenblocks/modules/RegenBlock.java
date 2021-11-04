package com.dehys.regenblocks.modules;

import com.dehys.regenblocks.Plugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class RegenBlock {

    private final World world;
    private final Location location;
    private final Material material;
    private final long regenTime;

    public RegenBlock(World world, Location location, Material material, long regenTime) {
        this.world = world;
        this.location = location;
        this.material = material;
        this.regenTime = regenTime;

        Plugin.regenBlocks.add(this);
    }

    public void regenerate() {
        this.world.getBlockAt(this.location).setType(this.material);
    }

    public long getRegenTime() {
        return this.regenTime;
    }
}
