package com.dehys.regenblocks.modules;

public class Entry{

    private String blockName;
    private String replacementBlock;
    private int delay;

    public Entry(){}

    public Entry(String blockName, String replacementBlock, int delay){
        this.blockName = blockName;
        this.replacementBlock = replacementBlock;
        this.delay = delay;
    }

    public String getBlockName() {
        return blockName;
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
