package com.dehys.regenores;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBroke implements Listener{
	
	
	//private RegenOres plugin;
	
	public BlockBroke(RegenOres plugin) {
		//this.plugin = plugin;
	}
	
	
	 @EventHandler
	 public void onBlockBreak(BlockBreakEvent e) {
		 Block b = e.getBlock();
		 
		 for (World world : RegenOres.worlds) {
			 if(world == b.getWorld()) {
				 for (String material : RegenOres.recordedMaterials) {
					 
					 String materialName = material.split(":")[0];
					 long regenTime = Long.parseLong(material.split(":")[1]);
					 
					 if(materialName.equalsIgnoreCase(b.getType().name())){
						 new RegenBlock(b);
						 return;
					 }
				 } 
				 return;
			 }
		 }
	 }
	
}
