package com.gmail.creeperhostanimations.oretech.blocks.entity;

import com.gmail.creeperhostanimations.oretech.OreTech;
import com.gmail.creeperhostanimations.oretech.blocks.OreTechBlocks;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class OreTechBlockEntities {

	public static BlockEntityType<BatteryEntity> BATTERY_ENTITY;
	public static BlockEntityType<CrankEntity> CRANK_ENTITY;
	public static BlockEntityType<ElectricFurnaceEntity> ELECTRIC_FURNACE_ENTITY;

    public static void registerAll() {
        BATTERY_ENTITY = Registry.register(Registry.BLOCK_ENTITY, new Identifier(OreTech.MODID, "battery"), BlockEntityType.Builder.create(BatteryEntity::new, OreTechBlocks.BATTERY).build(null));
	    CRANK_ENTITY = Registry.register(Registry.BLOCK_ENTITY, new Identifier(OreTech.MODID, "crank"), BlockEntityType.Builder.create(CrankEntity::new, OreTechBlocks.CRANK).build(null));
	    ELECTRIC_FURNACE_ENTITY = Registry.register(Registry.BLOCK_ENTITY, new Identifier(OreTech.MODID,"electric_furnace"), BlockEntityType.Builder.create(ElectricFurnaceEntity::new, OreTechBlocks.ELECTRIC_FURNACE).build(null));
    }

}