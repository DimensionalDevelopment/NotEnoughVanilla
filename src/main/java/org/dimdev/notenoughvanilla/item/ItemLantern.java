package org.dimdev.notenoughvanilla.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.dimdev.notenoughvanilla.NotEnoughVanilla;

public class ItemLantern extends Item {
    public ItemLantern() {
        super(new Builder().maxStackSize(1).maxDamage(32).group(ItemGroup.MISC));
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean par5) {
        BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ).up();
        setBlock(pos, world);
    }

    public static void setBlock(BlockPos pos, World world) {
        if ((world.isAirBlock(pos) || world.getBlockState(pos).equals(NotEnoughVanilla.LANTERN_LIGHT)) && !world.isRemote) {
            world.setBlockState(pos, NotEnoughVanilla.LANTERN_LIGHT.getDefaultState());
        }
    }

    @Override
    public EnumActionResult func_195939_a(ItemUseContext context) {
        context.func_195991_k().setBlockState(context.func_195995_a().offset(context.func_196000_l()), NotEnoughVanilla.LANTERN_BLOCK.getDefaultState());

        context.func_195999_j().setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);

        return EnumActionResult.SUCCESS;
    }
}
