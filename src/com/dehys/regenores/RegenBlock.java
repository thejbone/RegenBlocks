package com.dehys.regenores;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;

public class RegenBlock {
	
	public static List<RegenBlock> regenBlocks;
	
	private Location loc;
	private Material mat;
	private long regenTime;
	
	public RegenBlock(Location loc, Material mat, long regenTime) {
		this.loc = loc;
		this.mat = mat;
		
	}
}
