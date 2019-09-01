package com.gmail.creeperhostanimations.oretech;

import com.gmail.creeperhostanimations.oretech.blocks.Battery;
import com.gmail.creeperhostanimations.oretech.blocks.Crank;
import com.gmail.creeperhostanimations.oretech.blocks.ElectricFurnace;
import com.gmail.creeperhostanimations.oretech.blocks.controller.BatteryController;
import com.gmail.creeperhostanimations.oretech.blocks.controller.ElectricFurnaceController;
import com.gmail.creeperhostanimations.oretech.blocks.entity.BatteryEntity;
import com.gmail.creeperhostanimations.oretech.blocks.entity.CrankEntity;
import com.gmail.creeperhostanimations.oretech.blocks.entity.ElectricFurnaceEntity;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.container.BlockContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class OreTech implements ModInitializer {

	//Identifiers
	public static final String MODID = "oretech";
	public static final Identifier BATTERY_CONTROLLER = new Identifier("oretech:battery");
	public static final Identifier ELECTRIC_FURNACE_CONTROLLER = new Identifier("oretech:electric_furnace");
	
	//Blocks
	public static final Block BATTERY = new Battery();
	public static final Block CRANK = new Crank();
	public static final Block ELECTRIC_FURNACE = new ElectricFurnace();

	//BlockEntities
	public static BlockEntityType<BatteryEntity> BATTERY_ENTITY;
	public static BlockEntityType<CrankEntity> CRANK_ENTITY;
	public static BlockEntityType<ElectricFurnaceEntity> ELECTRIC_FURNACE_ENTITY;

	@Override
	public void onInitialize() {
		//Blocks
		register("battery", BATTERY, ItemGroup.MISC);
		register("crank", CRANK, ItemGroup.MISC);
		register("electric_furnace", ELECTRIC_FURNACE, ItemGroup.MISC);
		
		//BlockEntities
		BATTERY_ENTITY = Registry.register(Registry.BLOCK_ENTITY, new Identifier(MODID,"battery"), BlockEntityType.Builder.create(BatteryEntity::new, BATTERY).build(null));
		CRANK_ENTITY = Registry.register(Registry.BLOCK_ENTITY, new Identifier(MODID, "crank"), BlockEntityType.Builder.create(CrankEntity::new, CRANK).build(null));

		//Containers
		ContainerProviderRegistry.INSTANCE.registerFactory(BATTERY_CONTROLLER, (syncId, id, player, buf) -> new BatteryController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));
		ContainerProviderRegistry.INSTANCE.registerFactory(ELECTRIC_FURNACE_CONTROLLER, (syncId, id, player, buf) -> new ElectricFurnaceController(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));
	}

	public void register(String name, Block block, ItemGroup itemGroup) {
		Registry.register(Registry.BLOCK, new Identifier(MODID, name), block);
		Registry.register(Registry.ITEM, new Identifier(MODID, name), new BlockItem(block, new Item.Settings().group(itemGroup)));
	}
}
