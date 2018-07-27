package org.dimdev.notenoughvanilla.item;

import net.minecraft.block.BlockWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemPortableCraftingTable extends Item {
    public ItemPortableCraftingTable(Builder group) {
        super(group);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        if (world.isRemote || !hand.equals(EnumHand.MAIN_HAND)) {
            return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItemMainhand());
        }

        player.displayGui(new BlockWorkbench.InterfaceCraftingTable(world, new BlockPos(player.posX, player.posY, player.posZ)) {
            @Override
            public Container createContainer(InventoryPlayer inventory, EntityPlayer player) {
                return new ContainerWorkbench(inventory, world, new BlockPos(player.posX, player.posY, player.posZ)) {
                    @Override
                    public boolean canInteractWith(EntityPlayer player) {
                        return true;
                    }
                };
            }
        });

        return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItemMainhand());
    }
}