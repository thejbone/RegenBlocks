package com.dehys.regenblocks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.UUID;

public class PutBlockBack {

    private World world;
    private Location location;
    private long regenTime;
    private UUID claim;
    private byte data;
    private Material material;

    PutBlockBack(World world, UUID claim, Block block, long regenTime) {
        this.world = world;
        this.claim = claim;
        this.location = block.getLocation();
        this.data = block.getData();
        this.material = block.getType();
        this.regenTime = regenTime;

        RegenBlocks.regenBlocks.add(this);
    }

    void regenerate() {
        world.getBlockAt(this.location).setType(material);
        world.getBlockAt(this.location).setData(data);
//        Bukkit.broadcastMessage(material + " " +  data  + " has been replaced!");
    }

    World getWorld() { return this.world; }

    UUID getClaim() { return this.claim; }

    Location getLocation() {
        return this.location;
    }

    Material getMaterial() {
        return this.material;
    }

    byte getByte(){
        return this.data;
    }

    long getRegenTime() {
        return this.regenTime;
    }
}
