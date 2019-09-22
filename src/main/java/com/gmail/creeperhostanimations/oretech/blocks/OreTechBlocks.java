package com.gmail.creeperhostanimations.oretech.blocks;

import com.gmail.creeperhostanimations.oretech.OreTech;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class OreTechBlocks {

	public static final Block BATTERY = new Battery();
	public static BlockItem BATTERY_ITEM;
	public static final Block CRANK = new Crank();
	public static final Block ELECTRIC_FURNACE = new ElectricFurnace();

    public static void registerAll() {
		Registry.register(Registry.BLOCK, new Identifier(OreTech.MODID, "battery"), BATTERY);
		BATTERY_ITEM = new BlockItem(BATTERY, new Item.Settings().group(OreTech.oreTechItemGroup));
		Registry.register(Registry.ITEM, new Identifier(OreTech.MODID, "battery"), BATTERY_ITEM);
		register("crank", CRANK);
		register("electric_furnace", ELECTRIC_FURNACE);
    }

	public static void register(String name, Block block) {
		Registry.register(Registry.BLOCK, new Identifier(OreTech.MODID, name), block);
		Registry.register(Registry.ITEM, new Identifier(OreTech.MODID, name), new BlockItem(block, new Item.Settings().group(OreTech.oreTechItemGroup)));
	}
}