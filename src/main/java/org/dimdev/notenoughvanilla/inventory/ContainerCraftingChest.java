package org.dimdev.notenoughvanilla.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;

public class ContainerCraftingChest extends Container {
    public IInventory lowerChestInventory;
    public InventoryCrafting craftMatrix;
    public InventoryCraftResult craftResult;

    private final EntityPlayer player;

    public ContainerCraftingChest(IInventory playerInventory, IInventory chestInventory, EntityPlayer player) {
        this.player = player;
        lowerChestInventory = chestInventory;
        chestInventory.openInventory(player);

        craftMatrix = new InventoryCrafting(this, 3, 3);
        craftResult = new InventoryCraftResult();

        // Crafting result
        addSlotToContainer(new SlotCrafting(player, craftMatrix, craftResult, 0, 124, 35));

        // Crafting matrix
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                addSlotToContainer(new Slot(craftMatrix, col + row * 3, 30 + col * 18, 17 + row * 18));
            }
        }

        // Chest inventory
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                addSlotToContainer(new Slot(chestInventory, col + row * 9, 8 + col * 18, 79 + row * 18));
            }
        }

        // Player inventory
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                addSlotToContainer(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 146 + row * 18));
            }
        }

        // Hotbar
        for (int col = 0; col < 9; ++col) {
            addSlotToContainer(new Slot(playerInventory, col, 8 + col * 18, 204));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return lowerChestInventory.isUsableByPlayer(player);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            result = stack.copy();

            if (index == 0) {
                stack.getItem().onCreated(stack, player.world, player);
                if (!mergeItemStack(stack, 10, 73, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(stack, result);
            } else if (index >= 10 && index < 37) {
                if (!mergeItemStack(stack, 37, 46, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 37 && index < 73) {
                if (!mergeItemStack(stack, 10, 37, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!mergeItemStack(stack, 10, 73, false)) {
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (stack.getCount() == result.getCount()) {
                return ItemStack.EMPTY;
            }

            ItemStack stackTaken = slot.onTake(player, stack);
            if (index == 0) {
                player.dropItem(stackTaken, false);
            }
        }
        return result;
    }

    @Override
    public boolean canMergeSlot(ItemStack stack, Slot slot) {
        return slot.inventory != craftResult && super.canMergeSlot(stack, slot);
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventory) {
        slotChangedCraftingGrid(player.world, player, craftMatrix, craftResult);
    }

    @Override
    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        lowerChestInventory.closeInventory(player);
        if (!player.world.isRemote) {
            clearContainer(player, player.world, craftMatrix);
        }
    }

    public IInventory getLowerChestInventory() {
        return lowerChestInventory;
    }
}
