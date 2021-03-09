package com.dehys.regenblocks.objects;

public class Region {

    private String name;
    private Entry[] entries;

    public Region(){}

    public Region(String name, Entry[] entries){
        this.name = name;
        this.entries = entries;
    }

    public String getName(){
        return name;
    }

    public Region setName(String name) {
        this.name = name;
        return this;
    }

    public Entry[] getEntries(){
        return entries;
    }

    public Region setEntries(Entry[] entries){
        this.entries = entries;
        return this;
    }

}
