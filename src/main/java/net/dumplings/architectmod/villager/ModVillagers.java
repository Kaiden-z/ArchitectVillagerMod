package net.dumplings.architectmod.villager;

import com.google.common.collect.ImmutableSet;
import net.dumplings.architectmod.ArchitectVillagerMod;
import net.dumplings.architectmod.block.ModBlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, ArchitectVillagerMod.MODID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, ArchitectVillagerMod.MODID);

    public static final RegistryObject<PoiType> SAWMILL_POI = POI_TYPES.register("sawmill_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.SAWMILL_BLOCK.get().getStateDefinition().getPossibleStates()),
                    1, 1));

    public static final RegistryObject<VillagerProfession> ARCHITECT =
            VILLAGER_PROFESSIONS.register("architect", () -> new VillagerProfession("Architect",
                    holder -> holder.get() == SAWMILL_POI.get(), holder -> holder.get() == SAWMILL_POI.get(),
                    ImmutableSet.of(), ImmutableSet.of(), SoundEvents.UI_STONECUTTER_TAKE_RESULT));

    public static void register(IEventBus eventBus)
        {
            POI_TYPES.register(eventBus);
            VILLAGER_PROFESSIONS.register(eventBus);
        }
}
