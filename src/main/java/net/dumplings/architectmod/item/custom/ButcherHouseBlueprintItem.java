package net.dumplings.architectmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.npc.VillagerProfession;

public class ButcherHouseBlueprintItem extends BlueprintItemBase {
    public ButcherHouseBlueprintItem(Properties pProperties) {
        super(pProperties);
        professionType = VillagerProfession.BUTCHER;
        structureSize = new BlockPos(14, 14, 15);
        entitySpawnOffset = new BlockPos(7, 2, 4);
        structureFileName = "butcher_home";
    }
}
