package com.dehys.regenblocks.hooks;

import me.ryanhamshire.GriefPrevention.GriefPrevention;

public class GriefPreventionHook implements Hook{

    private GriefPrevention griefPrevention;
    private boolean isInitialized = false;

    @Override
    public GriefPreventionHook Initialize() {
        return null;
    }

    @Override
    public boolean isInitialized() {
        return false;
    }

    @Override
    public Object getInstance() {
        return null;
    }
}
