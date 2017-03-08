package com.myChef.utils;

import java.util.UUID;

/**
 * Created by Mocart
 */


public class EntityIdGenerator {
    public static Long random(){
        return UUID.randomUUID().getLeastSignificantBits();
    }
}
