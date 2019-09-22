package com.gmail.creeperhostanimations.oretech.items;

import com.gmail.creeperhostanimations.oretech.OreTech;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class OreTechItems {

    public static Item CABLE = new Item(new Item.Settings().group(OreTech.oreTechItemGroup));
    public static Item POWER_CABLE = new Item(new Item.Settings().group(OreTech.oreTechItemGroup));
    public static Item CRANK_HANDLE = new Item(new Item.Settings().group(OreTech.oreTechItemGroup));

    public static void registerAll() {
        register("cable", CABLE);
        register("power_cable", POWER_CABLE);
        register("crank_handle", CRANK_HANDLE);
    }

    public static void register(String name, Item item) {
        Registry.register(Registry.ITEM, new Identifier(OreTech.MODID), item);
    }

}