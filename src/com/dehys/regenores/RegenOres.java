package com.dehys.regenores;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class RegenOres extends JavaPlugin{
	
	public static List<String> recordedMaterials;
	public static List<World> worlds;
	
	public void onEnable() {
		
		RegenBlock.regenBlocks = new ArrayList<>();
		
		recordedMaterials = new ArrayList<>();
		worlds = new ArrayList<>();
		
		getConfig().options().copyDefaults(true);
        saveConfig();
		
        getConfig().getStringList("recordedMaterials").stream().forEach(
        		material -> recordedMaterials.add(material.toString()));
        
        
        
        getConfig().getStringList("worlds").stream().forEach(
        		world -> worlds.add(
        					Bukkit.getWorld(world.toString())
        				));
        
		
        
        for (String m : recordedMaterials) {
        	System.out.println(m);
        }
        
        for (World w : worlds) {
        	System.out.println(w.getName());
        }
        
        
        
		getServer().getPluginManager().registerEvents(new BlockBroke(this), this);
	}
	
	public void onDisable() {
		
	}
	
	public static void main(String[] args) {
		System.out.println(Material.DIAMOND_ORE.getKey());
	}

}
