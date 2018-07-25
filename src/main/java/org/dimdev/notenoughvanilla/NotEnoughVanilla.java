package org.dimdev.notenoughvanilla;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.dimdev.notenoughvanilla.block.BlockCraftingChest;
import org.dimdev.notenoughvanilla.block.BlockLantern;
import org.dimdev.notenoughvanilla.block.BlockLight;
import org.dimdev.notenoughvanilla.item.ItemLantern;
import org.dimdev.rift.listener.BlockAdder;
import org.dimdev.rift.listener.ItemAdder;

public class NotEnoughVanilla implements BlockAdder, ItemAdder {
    public static Block CRAFTING_CHEST = new BlockCraftingChest(Block.Builder.create(Material.WOOD, MapColor.WOOD).hardnessAndResistance(2.5F, 2.5F).soundType(SoundType.WOOD));

    public static Item PORTABLE_CRAFTING_TABLE = new Item(new Item.Builder().maxStackSize(1).group(ItemGroup.HOTBAR)) {
        @Override
        public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
            if (!world.isRemote && hand.equals(EnumHand.MAIN_HAND)) {
                player.displayGui(new BlockWorkbench.InterfaceCraftingTable(world, new BlockPos(player.posX, player.posY, player.posZ)) {
                    public Container createContainer(InventoryPlayer p_createContainer_1_, EntityPlayer p_createContainer_2_) {
                        return new ContainerWorkbench(p_createContainer_1_, world, new BlockPos(player.posX, player.posY, player.posZ)) {
                            public boolean canInteractWith(EntityPlayer p_canInteractWith_1_) {
                                return true;
                            }
                        };
                    }
                });
                player.func_195066_a(StatList.CRAFTING_TABLE_INTERACTION);
            }

            return super.onItemRightClick(world, player, hand);
        }
    };

    public static BlockLight LANTERN_LIGHT = new BlockLight();
    public static BlockLantern LANTERN_BLOCK = new BlockLantern();

    public static ItemLantern LANTERN_ITEM = new ItemLantern();

    @Override
    public void registerBlocks() {
        Block.registerBlock("notenoughvanilla:crafting_chest", CRAFTING_CHEST);
        Block.registerBlock("notenoughvanilla:light", LANTERN_LIGHT);
        Block.registerBlock("notenoughvanilla:lantern", LANTERN_BLOCK);
    }

    @Override
    public void registerItems() {
        Item.registerItemBlock(CRAFTING_CHEST, ItemGroup.BUILDING_BLOCKS);
        Item.registerItem(new ResourceLocation("notenoughvanilla:portable_crafting_table"), PORTABLE_CRAFTING_TABLE);
        Item.registerItemBlock(LANTERN_BLOCK, LANTERN_ITEM);
    }
}
