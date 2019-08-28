package com.gmail.creeperhostanimations.oretech;

import com.gmail.creeperhostanimations.oretech.blocks.controller.BatteryController;
import com.gmail.creeperhostanimations.oretech.blocks.controller.BatteryControllerScreen;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.container.BlockContext;

public class OreTechClient implements ClientModInitializer  {

    @Override
    public void onInitializeClient() {
        ScreenProviderRegistry.INSTANCE.registerFactory(OreTech.BATTERY_CONTROLLER, (syncId, identifier, player, buf) -> new BatteryControllerScreen(new BatteryController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())), player));
    }
    
}