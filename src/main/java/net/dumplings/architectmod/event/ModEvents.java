package net.dumplings.architectmod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.dumplings.architectmod.ArchitectVillagerMod;
import net.dumplings.architectmod.item.ModItems;
import net.dumplings.architectmod.villager.ModVillagers;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = ArchitectVillagerMod.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == ModVillagers.ARCHITECT.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            // Level 1 trades
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.IRON_INGOT, 16),
                    new ItemStack(ModItems.ARMORER_HOUSE_BLUEPRINT.get(), 1),
                    5, 8, 0.02f
            ));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.PORKCHOP, 30),
                    new ItemStack(ModItems.BUTCHER_HOUSE_BLUEPRINT.get(), 1),
                    5, 8, 0.02f
            ));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.NETHER_WART, 15),
                    new ItemStack(ModItems.CLERIC_HOUSE_BLUEPRINT.get(), 1),
                    5, 8, 0.02f
            ));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.WHEAT, 32),
                    new ItemStack(ModItems.FARMER_HOUSE_BLUEPRINT.get(), 1),
                    5, 8, 0.02f
            ));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.SALMON, 20),
                    new ItemStack(ModItems.FISHERMAN_HOUSE_BLUEPRINT.get(), 1),
                    5, 8, 0.02f
            ));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.ARROW, 32),
                    new ItemStack(ModItems.FLETCHER_HOUSE_BLUEPRINT.get(), 1),
                    5, 8, 0.02f
            ));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.LEATHER, 20),
                    new ItemStack(ModItems.LEATHERWORKER_HOUSE_BLUEPRINT.get(), 1),
                    5, 8, 0.02f
            ));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.BOOK, 12),
                    new ItemStack(ModItems.LIBRARIAN_HOUSE_BLUEPRINT.get(), 1),
                    5, 8, 0.02f
            ));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.COBBLESTONE, 64),
                    new ItemStack(ModItems.STONE_MASON_HOUSE_BLUEPRINT.get(), 1),
                    5, 8, 0.02f
            ));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.WHITE_WOOL, 20),
                    new ItemStack(ModItems.SHEPERD_HOUSE_BLUEPRINT.get(), 1),
                    5, 8, 0.02f
            ));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.IRON_INGOT, 16),
                    new ItemStack(ModItems.TOOLSMITH_HOUSE_BLUEPRINT.get(), 1),
                    5, 8, 0.02f
            ));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.IRON_INGOT, 16),
                    new ItemStack(ModItems.WEAPONSMITH_HOUSE_BLUEPRINT.get(), 1),
                    5, 8, 0.02f
            ));
        }
    }
}
