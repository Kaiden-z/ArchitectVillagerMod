package net.dumplings.architectmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.npc.VillagerProfession;

public class CartographerHouseBlueprintItem extends BlueprintItemBase {
    public CartographerHouseBlueprintItem(Properties pProperties) {
        super(pProperties);
        professionType = VillagerProfession.CARTOGRAPHER;
        structureSize = new BlockPos(11, 10, 9);
        entitySpawnOffset = new BlockPos(1, 1, 1);
        structureFileName = "cartography_home";
    }
}
