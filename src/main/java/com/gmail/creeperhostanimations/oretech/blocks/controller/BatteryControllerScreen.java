package com.gmail.creeperhostanimations.oretech.blocks.controller;

import io.github.cottonmc.cotton.gui.client.CottonScreen;
import net.minecraft.entity.player.PlayerEntity;

public class BatteryControllerScreen extends CottonScreen<BatteryController> {
    public BatteryControllerScreen(BatteryController container, PlayerEntity player) {
        super(container, player);
    }
}