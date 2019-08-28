package com.gmail.creeperhostanimations.oretech.blocks.controller;

import io.github.cottonmc.cotton.gui.CottonScreenController;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.text.TranslatableText;

public class BatteryController extends CottonScreenController {
    public BatteryController(int syncId, PlayerInventory playerInventory, BlockContext context) {
		super(RecipeType.SMELTING, syncId, playerInventory, getBlockInventory(context), getBlockPropertyDelegate(context));
		
		WGridPanel rootPanel = (WGridPanel) getRootPanel();

		rootPanel.add(new WLabel(new TranslatableText("block.examplemod.drama_generator"), WLabel.DEFAULT_TEXT_COLOR), 0, 0);
		
		WItemSlot inputSlot = WItemSlot.of(blockInventory, 0);
		rootPanel.add(inputSlot, 4, 1);
		
		rootPanel.add(this.createPlayerInventoryPanel(), 0, 3);
		
		rootPanel.validate(this);
	}

	@Override
	public int getCraftingResultSlotIndex() {
		return -1; //There's no real result slot
    }
}