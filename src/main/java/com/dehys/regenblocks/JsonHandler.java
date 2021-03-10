package com.dehys.regenblocks;

import com.dehys.regenblocks.enums.ConfigType;
import com.dehys.regenblocks.modules.Entry;
import com.dehys.regenblocks.modules.Region;
import com.dehys.regenblocks.modules.World;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JsonHandler {

    private Gson gson;

    public JsonHandler() {
        this.gson = new Gson();
        try {
            generateConfigFile(ConfigType.WORLDS);
            generateConfigFile(ConfigType.REGIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<World> getWorlds() {
        try{
            Reader reader = Files.newBufferedReader(Paths.get("../RegenBlocks/Worlds.json"));
            List<World> worlds = gson.fromJson(reader, new TypeToken<List<World>>() {}.getType());

            for (World w : worlds) System.out.println(w);

            reader.close();

            return worlds;
        }catch (IOException e){
            Plugin.getPlugin.getLogger().warning("Worlds.json file not found! Check directory permissions");
            return null;
        }
    }

    public List<Region> getRegions() {
        try{
            Reader reader = Files.newBufferedReader(Paths.get("../RegenBlocks/Regions.json"));
            List<Region> regions = gson.fromJson(reader, new TypeToken<List<Region>>() {}.getType());

            for (Region r : regions) System.out.println(r);

            reader.close();

            return regions;
        }catch (IOException e){
            Plugin.getPlugin.getLogger().warning("Region.json file not found! Check directory permissions");
            return null;
        }
    }

    public File generateConfigFile(ConfigType type) throws IOException {

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
                new Entry("stone", "air", 100),
                new Entry("diamond_ore", "netherrite", 5000),
                new Entry("gold_ore", "bedrock", 400),
                new Entry("diamond_block", "bedrock", 150)
        };

        World[] worlds = {
                new World("world1", new Entry[]{entries[0], entries[1]}),
                new World("world2", new Entry[]{entries[2], entries[3]}),
        };

        Region[] regions = {
                new Region("region1", new Entry[]{entries[0], entries[1]}),
                new Region("region2", new Entry[]{entries[2], entries[3]})
        };

        gson = new GsonBuilder().setPrettyPrinting().create();

        if (type == ConfigType.WORLDS){
            String path = "plugins/RegenBlocks/Worlds.json";
            File file = new File(path);
            if(file.createNewFile()){
                Writer writer = Files.newBufferedWriter(Paths.get(path));
                gson.toJson(worlds, writer);
                System.out.println("[RegenBlocks] Generated new file: Worlds.json");
                writer.close();
                return file;
            }
            return file;
        }else{
            String path = "plugins/RegenBlocks/Regions.json";
            File file = new File(path);
            if(file.createNewFile()){
                Writer writer = Files.newBufferedWriter(Paths.get(path));
                gson.toJson(regions, writer);
                System.out.println("[RegenBlocks] Generated new file: Regions.json");
                writer.close();
                return file;
            }
            return file;
        }
    }

}
