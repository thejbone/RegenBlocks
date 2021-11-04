package com.dehys.regenblocks.exceptions;

public class MaterialNotFound extends Exception {

    public MaterialNotFound(Object o){
        super("[RegenBlocks] MaterialNotFound exception in "+o.toString());
    }

    public Object returnNull(){
        return null;
    }

}
