package net.dumplings.architectmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.npc.VillagerProfession;

public class StoneMasonHouseBlueprintItem extends BlueprintItemBase {
    public StoneMasonHouseBlueprintItem(Properties pProperties) {
        super(pProperties);
        professionType = VillagerProfession.MASON;
        structureSize = new BlockPos(5, 5, 5);
        structureFileName = "testing";
    }
}
