package com.dehys.regenblocks;

import com.dehys.regenblocks.modules.Entry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonHandler {

    private Gson gson;
    private File file;

    public JsonHandler initialize(){
        gson = new Gson();
        try {
            generate();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*for (Entry e : getEntries()){

            String id = e.getId();
            String material = e.getMaterialConverted() != null ? e.getMaterialConverted().toString() : "null";
            String replacementMaterial = e.getReplacementMaterialConverted() != null ? e.getReplacementMaterialConverted().toString() : "null";
            int delay = e.getDelay();

            System.out.println("\t"+id);
            System.out.println("\t"+e.getMaterial()+": "+material);
            System.out.println("\t"+e.getReplacementMaterial()+": "+replacementMaterial);
            System.out.println("\t"+delay);
            System.out.println(" ");
        }
*/
        return this;
    }

    public List<Entry> getEntries() {
        try{
            Reader reader = Files.newBufferedReader(Paths.get("plugins/RegenBlocks/Entries.json"));
            List<Entry> entries = gson.fromJson(reader, new TypeToken<List<Entry>>() {}.getType());
            reader.close();

            return entries;
        }catch (IOException e){
            System.out.println("[RegenBlocks:ERROR] Entries.json file not found! Check directory permissions");
            return null;
        }
    }

    // Will get *:! (* being all worlds and :! being with no regions)
    public List<Entry> getWorlds() {
        try{
            Reader reader = Files.newBufferedReader(Paths.get("plugins/RegenBlocks/Entries.json"));
            List<Entry> worlds = new ArrayList<>();
            for (Entry entry : (List<Entry>) gson.fromJson(reader, new TypeToken<List<Entry>>() {}.getType())) {
                if (!entry.getId().contains(":")){
                    worlds.add(entry);
                }
            }

            reader.close();

            return worlds;
        }catch (IOException e){
            System.out.println("[RegenBlocks:ERROR] Entries.json file not found! Check directory permissions");
            return null;
        }
    }

    // Will get world:* (world: being the prefix and * being all regions)
    public List<Entry> getRegions() {
        try{
            Reader reader = Files.newBufferedReader(Paths.get("plugins/RegenBlocks/Entries.json"));
            List<Entry> regions = new ArrayList<>();
            for (Entry entry : (List<Entry>) gson.fromJson(reader, new TypeToken<List<Entry>>() {}.getType())) {
                if (entry.getId().contains(":")){
                    regions.add(entry);
                }
            }

            reader.close();

            return regions;
        }catch (IOException e){
            System.out.println("[RegenBlocks:ERROR] Entries.json file not found! Check directory permissions");
            return null;
        }
    }

    public File getFile() {
        return file;
    }

    public JsonHandler generate() throws IOException {

        String dirPath = "plugins/RegenBlocks";
        if (!(Files.exists(Paths.get(dirPath)) && Files.isDirectory(Paths.get(dirPath)))){
            if (new File(dirPath).mkdir()){
                System.out.println("[RegenBlocks] Generated configuration directory successfully.");
            }else{
                System.out.println("[RegenBlocks] Failed to generate configuration directory!");
                System.out.println("[RegenBlocks] Falling back...");
                Plugin.getPlugin.getServer().getPluginManager().disablePlugin(Plugin.getPlugin);
                return null;
            }
        }

        Entry[] entries = {
                new Entry("world", "stone", "air", 100),
                new Entry("world1", "diamond_ore", "netherrite", 5000),
                new Entry("world:spawn", "gold_ore", "bedrock", 400),
                new Entry("world1:shop", "diamond_block", "bedrock", 150)
        };

        gson = new GsonBuilder().setPrettyPrinting().create();

        String path = "plugins/RegenBlocks/Entries.json";
        File file = new File(path);
        this.file = file;
        if(file.createNewFile()){
            Writer writer = Files.newBufferedWriter(Paths.get(path));
            gson.toJson(entries, writer);
            System.out.println("[RegenBlocks] Generated new file: Entries.json");
            writer.close();
            return this;
        }
        return this;
    }

}
