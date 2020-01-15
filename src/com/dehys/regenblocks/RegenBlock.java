package com.dehys.regenblocks;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class RegenBlock {
	
	private World world;
	private Location location;
	private Material material;
	private long regenTime;
	
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
	
	public World getWorld() {
		return this.world;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public Material getMaterial() {
		return this.material;
	}
	
	public long getRegenTime() {
		return this.regenTime;
	}
}
