package com.zhgd.controller.controllerUtile;

import java.util.UUID;

public class UUIDUtile {
    public static String uuid(){
        UUID u = UUID.randomUUID();
        String uuid = u.toString().replace("-","");
        return uuid;
    }
}
