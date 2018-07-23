package org.dimdev.notenoughvanilla;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.ResourceLocation;
import org.dimdev.notenoughvanilla.block.BlockCraftingChest;
import org.dimdev.rift.listener.BlockAdder;
import org.dimdev.rift.listener.ItemAdder;

public class NotEnoughVanilla implements BlockAdder, ItemAdder {
    Block CRAFTING_CHEST = new BlockCraftingChest(Block.Builder.create(Material.WOOD, MapColor.WOOD).hardnessAndResistance(2.5F, 2.5F).soundType(SoundType.WOOD));

    @Override
    public void registerBlocks() {
        Block.registerBlock(new ResourceLocation("notenoughvanilla:crafting_chest"), CRAFTING_CHEST);
    }

    @Override
    public void registerItems() {
        Item.registerItemBlock(CRAFTING_CHEST, ItemGroup.BUILDING_BLOCKS);
    }
}
