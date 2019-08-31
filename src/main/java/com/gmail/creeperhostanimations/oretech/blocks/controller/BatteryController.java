package com.gmail.creeperhostanimations.oretech.blocks.controller;

import io.github.cottonmc.cotton.gui.CottonScreenController;
import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public class BatteryController extends CottonScreenController {
    public BatteryController(int syncId, PlayerInventory playerInventory, BlockContext context) {
		super(RecipeType.SMELTING, syncId, playerInventory, new PlayerInv(), getBlockPropertyDelegate(context));
		
		WGridPanel rootPanel = (WGridPanel) getRootPanel();

		rootPanel.add(new WLabel(new TranslatableText("block.oretech.battery"), WLabel.DEFAULT_TEXT_COLOR), 0, 0);
		
		WBar energyLevel = new WBar(new Identifier("oretech", "textures/gui/energy_bg.png"),new Identifier("oretech", "textures/gui/energy_full.png"),0,1);
		rootPanel.add(energyLevel, 4, 1, 1, 4);
		
		rootPanel.validate(this);
	}

	@Override
	public int getCraftingResultSlotIndex() {
		return -1; //There's no real result slot
    }
}