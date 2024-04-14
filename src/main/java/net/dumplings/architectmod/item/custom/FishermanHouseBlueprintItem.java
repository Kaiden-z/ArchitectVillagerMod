package net.dumplings.architectmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.npc.VillagerProfession;

public class FishermanHouseBlueprintItem extends BlueprintItemBase {
    public FishermanHouseBlueprintItem(Properties pProperties) {
        super(pProperties);
        professionType = VillagerProfession.FISHERMAN;
        structureSize = new BlockPos(5, 5, 5);
        structureFileName = "testing";
    }
}
