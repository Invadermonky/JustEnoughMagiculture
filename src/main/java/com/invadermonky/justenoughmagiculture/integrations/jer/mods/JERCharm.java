package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigCharm;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.integrations.jer.conditionals.JEMLightLevel;
import com.invadermonky.justenoughmagiculture.util.Reference;
import com.invadermonky.justenoughmagiculture.util.StringHelper;
import jeresources.api.conditionals.Conditional;
import jeresources.api.drop.LootDrop;
import jeresources.entry.MobEntry;
import jeresources.registry.MobRegistry;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import svenhjol.charm.Charm;
import svenhjol.charm.brewing.potion.DecayPotion;
import svenhjol.charm.tweaks.feature.WitchesDropDecay;
import svenhjol.charm.tweaks.feature.WitchesDropLuck;
import svenhjol.charm.world.entity.EntitySpectre;
import svenhjol.charm.world.feature.AbandonedCrates;
import svenhjol.charm.world.feature.Spectre;
import svenhjol.charm.world.feature.SwampHutDecorations;
import svenhjol.charm.world.feature.VillageDecorations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.invadermonky.justenoughmagiculture.util.ModIds.CHARM;

public class JERCharm extends JERBase implements IJERIntegration {
    private static final JEMConfigCharm.JER jerConfig = JEMConfig.CHARM.JUST_ENOUGH_RESOURCES;
    private static JERCharm instance;

    public JERCharm(boolean enableJERDungeons, boolean enableJERMobs) {
        if(enableJERDungeons) registerModDungeons();
        if(enableJERMobs) registerModEntities();
    }

    private JERCharm() {}

    public static JERCharm getInstance() {
        return instance == null ? instance = new JERCharm() : instance;
    }

    @Override
    public void registerModDungeons() {
        //Abandoned Crates
        if(Charm.hasFeature(AbandonedCrates.class) && AbandonedCrates.generateChance > 0) {
            JERDungeonStrings crate = new JERDungeonStrings("abandoned_crate", false);
            registerDungeonLoot(crate.category, crate.unlocName, Reference.FakeTables.CHARM_CRATE_FAKE_TABLE);
        }

        //Swamp Hut Decorations
        if(Charm.hasFeature(SwampHutDecorations.class)) {
            registerCharmDungeonLoot("common_potions", false);
        }

        //Village Decorations
        if(Charm.hasFeature(VillageDecorations.class) && VillageDecorations.storage) {
            registerCharmDungeonLoot("butcher", true);
            registerCharmDungeonLoot("carpenter", true);
            registerCharmDungeonLoot("farmer", true);
            registerCharmDungeonLoot("fisherman", true);
            registerCharmDungeonLoot("librarian", true);
            registerCharmDungeonLoot("priest", true);
            registerCharmDungeonLoot("shepherd", true);
            registerCharmDungeonLoot("smith", true);
        }
    }

    @Override
    public void registerModEntities() {
        if(Charm.hasFeature(Spectre.class) && jerConfig.enableSpectre) {
            registerMob(new EntitySpectre(world), JEMLightLevel.custom(Spectre.despawnLight, false), EntitySpectre.LOOT_TABLE);
            registerRenderHook(EntitySpectre.class, ((renderInfo, e) -> {
                GlStateManager.translate(-0.05,-0.4,0);
                return renderInfo;
            }));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void injectLoot() {
        if (JEMConfig.CHARM.enableJERInjectedLoot) {
            for (MobEntry mobEntry : MobRegistry.getInstance().getMobs()) {
                if(mobEntry.getEntity() instanceof EntityWitch) {
                    try {
                        Field entryField = MobRegistry.getInstance().getClass().getDeclaredField("registry");
                        entryField.setAccessible(true);
                        Set<MobEntry> mobEntries = (Set<MobEntry>) entryField.get(MobRegistry.getInstance());
                        mobEntries.remove(mobEntry);

                        List<LootDrop> dropsToAdd = new ArrayList<>();

                        if(Charm.hasFeature(WitchesDropDecay.class) && WitchesDropDecay.dropChance > 0) {
                            ItemStack potion = new ItemStack(Items.POTIONITEM);
                            PotionUtils.addPotionToItemStack(potion, DecayPotion.type);
                            LootDrop decayDrop = new LootDrop(potion, 0, 1, (float) WitchesDropDecay.dropChance, Conditional.affectedByLooting);
                            dropsToAdd.add(decayDrop);
                        }

                        if(Charm.hasFeature(WitchesDropLuck.class) && WitchesDropLuck.dropChance > 0) {
                            ItemStack potion = new ItemStack(Items.POTIONITEM);
                            PotionUtils.addPotionToItemStack(potion, PotionType.REGISTRY.getObject(new ResourceLocation("luck")));
                            LootDrop luckDrop = new LootDrop(potion, 0, 1, (float) WitchesDropLuck.dropChance, Conditional.affectedByLooting);
                            dropsToAdd.add(luckDrop);
                        }

                        mobEntry.addDrops(dropsToAdd.toArray(new LootDrop[0]));
                        mobEntries.add(mobEntry);
                    } catch (Exception ignored) {}
                }
            }
        }
    }

    private void registerCharmDungeonLoot(String name, boolean isVillage) {
        JERDungeonStrings dungeon = new JERDungeonStrings(name, isVillage);
        registerDungeonLoot(dungeon.category, dungeon.unlocName, dungeon.lootTable);
    }

    private static class JERDungeonStrings {
        public final String category;
        public final String unlocName;
        public final ResourceLocation lootTable;

        public JERDungeonStrings(String dungeon, boolean isVillage) {
            this.category = isVillage ? String.format("%s:%s/%s", CHARM.MOD_ID, "village", dungeon) : String.format("%s:%s/%s", CHARM.MOD_ID, "treasure", dungeon);
            this.unlocName = StringHelper.getDungeonTranslationKey(CHARM.MOD_ID, dungeon);
            this.lootTable = new ResourceLocation(category);
        }
    }
}
