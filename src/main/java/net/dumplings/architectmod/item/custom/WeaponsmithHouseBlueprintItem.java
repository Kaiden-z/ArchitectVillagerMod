package net.dumplings.architectmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.npc.VillagerProfession;

public class WeaponsmithHouseBlueprintItem extends BlueprintItemBase {
    public WeaponsmithHouseBlueprintItem(Properties pProperties) {
        super(pProperties);
        professionType = VillagerProfession.WEAPONSMITH;
        structureSize = new BlockPos(5, 5, 5);
        entitySpawnOffset = new BlockPos(1, 1, 1);
        structureFileName = "testing";
    }
}
