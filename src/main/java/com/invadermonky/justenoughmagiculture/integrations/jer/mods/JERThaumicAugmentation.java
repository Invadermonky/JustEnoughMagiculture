package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigThaumicAugmentation;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.lootbag.LootBagEntry;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.registry.LootBagRegistry;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import jeresources.api.conditionals.LightLevel;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.ItemStack;
import thecodex6824.thaumicaugmentation.api.TABlocks;
import thecodex6824.thaumicaugmentation.api.TALootTables;
import thecodex6824.thaumicaugmentation.api.world.TABiomes;
import thecodex6824.thaumicaugmentation.common.entity.*;

public class JERThaumicAugmentation extends JERBase implements IJERIntegration {
    private static JERThaumicAugmentation instance;
    private static final JEMConfigThaumicAugmentation.JER jerConfig = JEMConfig.THAUMIC_AUGMENTATION.JUST_ENOUGH_RESOURCES;

    public JERThaumicAugmentation(boolean enableJERMobs) {
        if(enableJERMobs) registerModEntities();
    }

    private JERThaumicAugmentation() {}

    public static JERThaumicAugmentation getInstance() {
        return instance != null ? instance : (instance = new JERThaumicAugmentation());
    }

    public void registerLootBagEntries() {
        if(JEMConfig.THAUMIC_AUGMENTATION.JUST_ENOUGH_ITEMS.enableJEILootBags) {

            LootBagRegistry registry = LootBagRegistry.getInstance();
            registry.registerLootBag(new LootBagEntry(new ItemStack(TABlocks.URN, 1, 0), manager.getLootTableFromLocation(TALootTables.LOOT_COMMON)));
            registry.registerLootBag(new LootBagEntry(new ItemStack(TABlocks.URN, 1, 1), manager.getLootTableFromLocation(TALootTables.LOOT_UNCOMMON)));
            registry.registerLootBag(new LootBagEntry(new ItemStack(TABlocks.URN, 1, 2), manager.getLootTableFromLocation(TALootTables.LOOT_RARE)));
        }
    }

    @Override
    public void registerModEntities() {
        if(jerConfig.enableAutocaster) {
            registerMob(new EntityAutocaster(world), LightLevel.any, TALootTables.AUTOCASTER);
        }

        if(jerConfig.enableEldritchAutocaster) {
            registerMob(new EntityAutocasterEldritch(world), LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(TABiomes.EMPTINESS), TALootTables.AUTOCASTER_ELDRITCH);
        }

        if(jerConfig.enableEldritchGolem) {
            registerMob(new EntityTAEldritchGolem(world), LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(TABiomes.EMPTINESS), TALootTables.ELDRITCH_GOLEM);
            registerRenderHook(EntityTAEldritchGolem.class, ((renderInfo, e) -> {
                GlStateManager.scale(0.9,0.9,0.9);
                GlStateManager.translate(-0.05,-0.4,0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableEldritchGuardian) {
            registerMob(new EntityTAEldritchGuardian(world), LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(TABiomes.EMPTINESS), TALootTables.ELDRITCH_GUARDIAN);
        }

        if(jerConfig.enableEldritchWarden) {
            registerMob(new EntityTAEldritchWarden(world), LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(TABiomes.EMPTINESS), TALootTables.ELDRITCH_WARDEN);
        }

        if(jerConfig.enablePrimalWisp) {
            registerMob(new EntityPrimalWisp(world), LightLevel.any, TALootTables.PRIMAL_WISP);
        }
    }
}
