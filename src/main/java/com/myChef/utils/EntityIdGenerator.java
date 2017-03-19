package com.myChef.utils;

import java.util.UUID;

/**
 * Created by Mocart
 */


public class EntityIdGenerator {
    public static Long random(){
        Long l = UUID.randomUUID().getLeastSignificantBits();
        if(l < 0){
            l *= (-1);
        }
        return l;
    }
}
