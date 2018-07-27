package org.dimdev.notenoughvanilla.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.dimdev.notenoughvanilla.inventory.ContainerCraftingChest;

public class GuiCraftingChest extends GuiContainer {
    private static final ResourceLocation CRAFTING_CHEST_GUI_TEXTURE = new ResourceLocation("notenoughvanilla:textures/gui/container/crafting_chest.png");

    private final IInventory lowerChestInventory;
    private final IInventory upperChestInventory;

    public GuiCraftingChest(IInventory lowerInventory, IInventory upperInventory) {
        super(new ContainerCraftingChest(lowerInventory, upperInventory, Minecraft.getMinecraft().player));
        lowerChestInventory = lowerInventory;
        upperChestInventory = upperInventory;
        xSize = 176;
        ySize = 228;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        fontRenderer.func_211126_b(upperChestInventory.getDisplayName().getString(), 8, 6, 0x404040);
        fontRenderer.func_211126_b(lowerChestInventory.getDisplayName().getString(), 8, ySize - 96 + 3, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        mc.getTextureManager().bindTexture(CRAFTING_CHEST_GUI_TEXTURE);
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
