package com.dehys.regenblocks.modules;

public class World {

    private String name;
    private Entry[] entries;

    public World(){}

    public World(String name, Entry[] entries){
        this.name = name;
        this.entries = entries;
    }

    public String getName(){
        return name;
    }

    public World setName(String name) {
        this.name = name;
        return this;
    }

    public Entry[] getEntries(){
        return entries;
    }

    public World setEntries(Entry[] entries){
        this.entries = entries;
        return this;
    }

}
