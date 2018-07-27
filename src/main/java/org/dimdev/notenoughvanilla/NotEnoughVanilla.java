package org.dimdev.notenoughvanilla;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.IInteractionObject;
import org.dimdev.notenoughvanilla.block.BlockCraftingChest;
import org.dimdev.notenoughvanilla.block.BlockLantern;
import org.dimdev.notenoughvanilla.block.BlockLight;
import org.dimdev.notenoughvanilla.gui.GuiCraftingChest;
import org.dimdev.notenoughvanilla.item.ItemLantern;
import org.dimdev.notenoughvanilla.item.ItemPortableCraftingTable;
import org.dimdev.notenoughvanilla.tileentity.TileEntityCraftingChest;
import org.dimdev.rift.listener.BlockAdder;
import org.dimdev.rift.listener.ItemAdder;
import org.dimdev.rift.listener.TileEntityTypeAdder;
import org.dimdev.rift.listener.client.GameGuiAdder;

public class NotEnoughVanilla implements BlockAdder, ItemAdder, GameGuiAdder, TileEntityTypeAdder { // TODO: split this
    public static Block CRAFTING_CHEST_BLOCK = new BlockCraftingChest(Block.Builder.create(Material.WOOD, MapColor.WOOD).hardnessAndResistance(2.5F, 2.5F).soundType(SoundType.WOOD));
    public static BlockLight LIGHT_BLOCK = new BlockLight(Block.Builder.create(Material.AIR));
    public static BlockLantern LANTERN_BLOCK = new BlockLantern(Block.Builder.create(Material.GLASS).doesNotBlockMovement().lightValue(15));

    public static Item PORTABLE_CRAFTING_TABLE_ITEM = new ItemPortableCraftingTable(new Item.Builder().maxStackSize(1).group(ItemGroup.TOOLS));
    public static ItemLantern LANTERN_ITEM = new ItemLantern(new Item.Builder().maxStackSize(1).maxDamage(32).group(ItemGroup.TOOLS));

    public static TileEntityType<TileEntityCraftingChest> CRAFTING_CHEST_TILE_ENTITY;

    @Override
    public void registerBlocks() {
        Block.registerBlock("notenoughvanilla:crafting_chest", CRAFTING_CHEST_BLOCK);
        Block.registerBlock("notenoughvanilla:light", LIGHT_BLOCK);
        Block.registerBlock("notenoughvanilla:lantern", LANTERN_BLOCK);
    }

    @Override
    public void registerItems() {
        Item.registerItem("notenoughvanilla:portable_crafting_table", PORTABLE_CRAFTING_TABLE_ITEM);
        Item.registerItemBlock(CRAFTING_CHEST_BLOCK, ItemGroup.DECORATIONS);
        Item.registerItemBlock(LANTERN_BLOCK, LANTERN_ITEM);
    }

    @Override
    public void displayGui(EntityPlayerSP player, String id, IInteractionObject interactionObject) {}

    @Override
    public void displayContainerGui(EntityPlayerSP player, String id, IInventory inventory) {
        if (id.equals("notenoughvanilla:crafting_chest")) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiCraftingChest(player.inventory, inventory));
        }
    }

    @Override
    public void registerTileEntityTypes() {
        CRAFTING_CHEST_TILE_ENTITY = TileEntityType.registerTileEntityType("notenoughvanilla:crafting_chest", TileEntityType.Builder.create(TileEntityCraftingChest::new));
    }
}
