package com.dehys.regenblocks.modules;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class Entry{

    private String id;
    private String blockName;
    private String replacementBlock;
    private int delay;

    public Entry(){}

    public Entry(String id, String blockName, String replacementBlock, int delay){
        this.id = id;
        this.blockName = blockName;
        this.replacementBlock = replacementBlock;
        this.delay = delay;
    }

    public String getId(){
        return id;
    }

    public World getWorld(){
        return Bukkit.getWorld(id.replaceAll("\\b:.+\\b", ""));
    }

    public String getRegionName(){
        return id.replaceAll("\\b.+:\\b", "") == "" ? null : id.replaceAll("\\b.+:\\b", "");
    }

    public Entry setId(String id){
        this.id = id;
        return this;
    }

    public String getBlockName() {
        return blockName;
    }

    public Material getBlock(){
        return Material.getMaterial(blockName.toUpperCase());
    }

    public Entry setBlockName(String blockName) {
        this.blockName = blockName;
        return this;
    }

    public String getReplacementBlock(){
        return replacementBlock;
    }

    public Entry setReplacementBlock(String replacementBlock){
        this.replacementBlock = replacementBlock;
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
