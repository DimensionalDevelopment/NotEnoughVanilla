package org.dimdev.notenoughvanilla.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.dimdev.notenoughvanilla.NotEnoughVanilla;

public class ItemLantern extends ItemBlock {
    public ItemLantern(Builder builder) {
        super(NotEnoughVanilla.LANTERN_BLOCK, builder);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
        if (world.isRemote || !isHoldingLantern(entity)) {
            return;
        }

        BlockPos pos = new BlockPos(entity.posX, entity.posY + entity.getEyeHeight(), entity.posZ);
        if (world.getBlockState(pos).isAir()) {
            world.setBlockState(pos, NotEnoughVanilla.LIGHT_BLOCK.getDefaultState());
        }
    }

    public boolean isHoldingLantern(Entity entity) {
        if (!(entity instanceof EntityLivingBase)) {
            return false;
        }

        EntityLivingBase living = (EntityLivingBase) entity;
        return living.getHeldItemMainhand().getItem() == this ||
               living.getHeldItemOffhand().getItem() == this;
    }
}
