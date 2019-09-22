package com.gmail.creeperhostanimations.oretech.blocks.controller;

import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.container.BlockContext;

public class RegisterControllersClient {

    public static void registerAll() {
        ScreenProviderRegistry.INSTANCE.registerFactory(OreTechControllers.BATTERY_CONTROLLER, (syncId, identifier, player, buf) -> new BatteryControllerScreen(new BatteryController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())), player));
        ScreenProviderRegistry.INSTANCE.registerFactory(OreTechControllers.ELECTRIC_FURNACE_CONTROLLER, (syncId, identifier, player, buf) -> new ElectricFurnaceControllerScreen(new ElectricFurnaceController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())), player));
    }
}