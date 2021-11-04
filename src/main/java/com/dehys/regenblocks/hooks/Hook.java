package com.dehys.regenblocks.hooks;

public interface Hook {

    Hook Initialize();
    boolean isInitialized();
    Object getInstance();

}
