package net.dumplings.architectmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.npc.VillagerProfession;

public class FarmerHouseBlueprintItem extends BlueprintItemBase {
    public FarmerHouseBlueprintItem(Properties pProperties) {
        super(pProperties);
        professionType = VillagerProfession.FARMER;
        structureSize = new BlockPos(5, 5, 5);
        structureFileName = "testing";
    }
}
