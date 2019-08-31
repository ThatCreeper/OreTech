package com.gmail.creeperhostanimations.oretech;

import com.gmail.creeperhostanimations.oretech.blocks.Battery;
import com.gmail.creeperhostanimations.oretech.blocks.controller.BatteryController;
import com.gmail.creeperhostanimations.oretech.blocks.entity.BatteryEntity;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.container.BlockContext;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class OreTech implements ModInitializer {

	public static final String MODID = "oretech";
	public static final Identifier BATTERY_CONTROLLER = new Identifier("modid:battery");

	public static final Block BATTERY = new Battery();
	public static BlockEntityType<BatteryEntity> BATTERY_ENTITY;

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, new Identifier(MODID, "battery"), BATTERY);
		BATTERY_ENTITY = Registry.register(Registry.BLOCK_ENTITY, new Identifier(MODID,"battery"), BlockEntityType.Builder.create(BatteryEntity::new, BATTERY).build(null));
		ContainerProviderRegistry.INSTANCE.registerFactory(BATTERY_CONTROLLER, (syncId, id, player, buf) -> new BatteryController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));
	}
}
