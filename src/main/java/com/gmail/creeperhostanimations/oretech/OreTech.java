package com.gmail.creeperhostanimations.oretech;

import com.gmail.creeperhostanimations.oretech.blocks.OreTechBlocks;
import com.gmail.creeperhostanimations.oretech.blocks.controller.OreTechControllers;
import com.gmail.creeperhostanimations.oretech.blocks.entity.OreTechBlockEntities;
import com.gmail.creeperhostanimations.oretech.items.OreTechItems;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class OreTech implements ModInitializer {

	//Identifiers
	public static final String MODID = "oretech";

	public static final ItemGroup oreTechItemGroup = FabricItemGroupBuilder.build(new Identifier(MODID,"main_group"), ()->new ItemStack(OreTechBlocks.BATTERY_ITEM));

	@Override
	public void onInitialize() {
		OreTechBlocks.registerAll();
		OreTechBlockEntities.registerAll();
		OreTechControllers.registerAll();
		OreTechItems.registerAll();
	}
}
