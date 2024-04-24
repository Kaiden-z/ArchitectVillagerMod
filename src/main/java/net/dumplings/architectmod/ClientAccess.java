package net.dumplings.architectmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;

import java.util.List;

public class ClientAccess {
    public static void expandBlueprintItemToolTip(List<Component> components, BlockPos structureSize) {
        assert Minecraft.getInstance().player != null;
        if (!Screen.hasShiftDown()) {
            components.add(Component.literal("Press \u00A75SHIFT\u00A7f for more information!"));
        } else {
            components.add(Component.literal(
                    "This building requires a \u00A75" + structureSize.getX() + "x" + structureSize.getY() + "x" + structureSize.getZ() + "\u00A7f area to build.\n\n" +
                    "Right-click any block to mark the bottom left corner of your construction site.\n\n" +
                    "Right-block the same block again to confirm your site location and begin construction."));
        }
    }

}
