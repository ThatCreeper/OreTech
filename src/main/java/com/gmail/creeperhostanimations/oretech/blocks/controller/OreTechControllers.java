package com.gmail.creeperhostanimations.oretech.blocks.controller;

import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.container.BlockContext;
import net.minecraft.util.Identifier;

public class OreTechControllers {

	public static final Identifier BATTERY_CONTROLLER = new Identifier("oretech:battery");
	public static final Identifier ELECTRIC_FURNACE_CONTROLLER = new Identifier("oretech:electric_furnace");
    
    public static void registerAll() {
        ContainerProviderRegistry.INSTANCE.registerFactory(BATTERY_CONTROLLER, (syncId, id, player, buf) -> new BatteryController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));
	    ContainerProviderRegistry.INSTANCE.registerFactory(ELECTRIC_FURNACE_CONTROLLER, (syncId, id, player, buf) -> new ElectricFurnaceController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));
    }
}