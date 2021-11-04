package com.dehys.regenblocks.modules;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.Locale;

public class Entry{

    private String id;
    private String material;
    private String replacementMaterial;
    private int delay;

    public Entry(){}

    public Entry(String id, String material, String replacementMaterial, int delay){
        this.id = id;
        this.material = material;
        this.replacementMaterial = replacementMaterial;
        this.delay = delay;
    }

    public String getId(){
        return id;
    }

    public World getWorld(){
        return Bukkit.getWorld(id.replaceAll("\\b:.+\\b", ""));
    }

    //move this to WGH class?
    public String getRegionName(){
        String regionName = id.replaceAll("\\b.+:\\b", "");
        return  regionName.isEmpty() || regionName.equalsIgnoreCase(getWorld().getName()) ? null : regionName;
    }

    public Entry setId(String id){
        this.id = id;
        return this;
    }

    public String getMaterial(){
        return this.material;
    }

    public Material getMaterialConverted() {
        return Material.matchMaterial(this.material.toUpperCase(Locale.ENGLISH));
    }

    public Entry setMaterial(String material) {
        this.material = material;
        return this;
    }

    public String getReplacementMaterial(){
        return this.replacementMaterial;
    }

    public Material getReplacementMaterialConverted() {
        return Material.matchMaterial(this.replacementMaterial.toUpperCase(Locale.ENGLISH));
    }

    public Entry setReplacementMaterial(String replacementMaterial){
        this.replacementMaterial = replacementMaterial;
        return this;
    }

    public int getDelay() {
        return delay;
    }

    public Entry setDelay(int delay){
        this.delay = delay;
        return this;
    }
}