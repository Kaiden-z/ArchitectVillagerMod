package net.dumplings.architectmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.npc.VillagerProfession;

public class FarmerHouseBlueprintItem extends BlueprintItemBase {
    public FarmerHouseBlueprintItem(Properties pProperties) {
        super(pProperties);
        professionType = VillagerProfession.FARMER;
        structureSize = new BlockPos(14, 8, 13);
        entitySpawnOffset = new BlockPos(6, 5, 7);
        structureFileName = "farmer_home";
    }
}
