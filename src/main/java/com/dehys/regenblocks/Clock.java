package com.dehys.regenblocks;

import com.dehys.regenblocks.modules.RegenBlock;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class Clock extends BukkitRunnable {

    private final List<RegenBlock> regenBlocks = Plugin.regenBlocks;

    public static long TICK_TIME;

    public Clock(){
        this.runTaskTimer(Plugin.getPlugin, 0, 1);
    }

    @Override
    public void run() {

        TICK_TIME++;

        for (RegenBlock rb : regenBlocks) {
            if(rb.getRegenTime() == TICK_TIME) {
                rb.regenerate();
            }
        }
    }

    public long getTickTime(){
        return TICK_TIME;
    }
}