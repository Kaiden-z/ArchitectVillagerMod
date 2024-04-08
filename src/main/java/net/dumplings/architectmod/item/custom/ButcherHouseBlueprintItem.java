package net.dumplings.architectmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.npc.VillagerProfession;

public class ButcherHouseBlueprintItem extends BlueprintItemBase {
    public ButcherHouseBlueprintItem(Properties pProperties) {
        super(pProperties);
        professionType = VillagerProfession.BUTCHER;
        structureSize = new BlockPos(5, 5, 5);
        structureFileName = "testing";
    }
}
