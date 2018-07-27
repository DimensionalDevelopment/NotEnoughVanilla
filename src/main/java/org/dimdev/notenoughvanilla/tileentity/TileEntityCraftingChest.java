package org.dimdev.notenoughvanilla.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import org.dimdev.notenoughvanilla.NotEnoughVanilla;
import org.dimdev.notenoughvanilla.inventory.ContainerCraftingChest;

public class TileEntityCraftingChest extends TileEntityLockableLoot {
    private NonNullList<ItemStack> contents;

    protected TileEntityCraftingChest(TileEntityType<?> type) {
        super(type);
        contents = NonNullList.withSize(27, ItemStack.EMPTY);
    }

    public TileEntityCraftingChest() {
        this(NotEnoughVanilla.CRAFTING_CHEST_TILE_ENTITY);
    }

    @Override
    public int getSizeInventory() {
        return 27;
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : contents) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ITextComponent getName() {
        return new TextComponentTranslation("container.crafting_chest");
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        contents = NonNullList.withSize(getSizeInventory(), ItemStack.EMPTY);
        if (!checkLootAndRead(compound)) {
            ItemStackHelper.loadAllItems(compound, contents);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if (!checkLootAndWrite(compound)) {
            ItemStackHelper.saveAllItems(compound, contents);
        }
        return compound;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public String getGuiID() {
        return "notenoughvanilla:crafting_chest";
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer player) {
        fillWithLoot(player);
        return new ContainerCraftingChest(playerInventory, this, player);
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return contents;
    }

    @Override
    protected void func_199721_a(NonNullList<ItemStack> contents) {
        this.contents = contents;
    }
}
