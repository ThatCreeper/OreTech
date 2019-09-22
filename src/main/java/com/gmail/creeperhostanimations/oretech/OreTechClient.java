package com.gmail.creeperhostanimations.oretech;

import com.gmail.creeperhostanimations.oretech.blocks.controller.RegisterControllersClient;

import net.fabricmc.api.ClientModInitializer;

public class OreTechClient implements ClientModInitializer  {

    @Override
    public void onInitializeClient() {
        RegisterControllersClient.registerAll();
    }
    
}