package com.gmail.creeperhostanimations.oretech.blocks.controller;

import io.github.cottonmc.cotton.gui.CottonScreenController;
import io.github.cottonmc.cotton.gui.widget.WBar;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WPlayerInvPanel;
import io.github.cottonmc.cotton.gui.widget.WToggleButton;
import io.github.cottonmc.cotton.gui.widget.WBar.Direction;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;

public class ElectricFurnaceController extends CottonScreenController {
    
    public ElectricFurnaceController(int syncId, PlayerInventory playerInventory, BlockContext context) {
        super(RecipeType.SMELTING, syncId, playerInventory, getBlockInventory(context), getBlockPropertyDelegate(context));
        
		WGridPanel rootPanel = (WGridPanel) getRootPanel();

		rootPanel.add(new WLabel(new TranslatableText("block.oretech.electric_furnace"), WLabel.DEFAULT_TEXT_COLOR), 0, 0);
        
        rootPanel.add(WItemSlot.of(blockInventory, 0), 2, 3);

        WBar energyLevel = new WBar(new Identifier("oretech", "textures/gui/energy_bg.png"),new Identifier("oretech", "textures/gui/energy_full.png"),2,3);
		rootPanel.add(energyLevel, 0, 1, 1, 4);

        WBar progressBar = new WBar(new Identifier("oretech","textures/gui/furnace_bar_bg.png"), new Identifier("oretech","textures/gui/furnace_bar.png"), 0, 1, Direction.RIGHT);
        rootPanel.add(progressBar, 4, 3,3,1);

        rootPanel.add(WItemSlot.of(blockInventory, 1), 8, 3);

        rootPanel.add(new WPlayerInvPanel(playerInventory), 0, 6);

		rootPanel.validate(this);
    }

    @Override
    public int getCraftingSlotCount() {
        return 1;
    }

}