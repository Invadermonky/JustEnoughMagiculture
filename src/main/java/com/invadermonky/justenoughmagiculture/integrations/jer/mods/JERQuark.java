package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.google.common.collect.ImmutableSet;
import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigQuark;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.villager.BasicTrade;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.villager.CustomVillagerEntry;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.registry.CustomVillagerRegistry;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import com.invadermonky.justenoughmagiculture.util.StringHelper;
import jeresources.api.conditionals.Conditional;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.entry.MobEntry;
import jeresources.registry.MobRegistry;
import jeresources.util.LootTableHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Biomes;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.BiomeDictionary.Type;
import vazkii.quark.base.module.Feature;
import vazkii.quark.base.module.ModuleLoader;
import vazkii.quark.misc.feature.BlackAsh;
import vazkii.quark.vanity.feature.WitchHat;
import vazkii.quark.world.entity.*;
import vazkii.quark.world.feature.*;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JERQuark extends JERBase implements IJERIntegration {
    private static final JEMConfigQuark.JER jerConfig = JEMConfig.QUARK.JUST_ENOUGH_RESOURCES;
    private static JERQuark instance;

    private JERQuark() {}

    public JERQuark(boolean enableJERDungeons, boolean enableJERMobs, boolean enableJERVillagers) {
        if(enableJERDungeons) registerModDungeons();
        if(enableJERMobs) registerModEntities();
        if(enableJERVillagers) registerModVillagers();
    }

    public static JERQuark getInstance() {
        return instance == null ? instance = new JERQuark() : instance;
    }

    @Override
    public void registerModDungeons() {
        if(isFeatureEnabled(Archaeologist.class)) registerDungeonLoot("archaeologist");
        if(isFeatureEnabled(BuriedTreasure.class)) registerDungeonLoot("quark:buried_treasure", "dungeon.jem:quark.buried_treasure", LootTableList.CHESTS_SIMPLE_DUNGEON);
        if(isFeatureEnabled(PirateShips.class)) registerDungeonLoot("pirate_chest");
    }

    @Override
    public void registerModEntities() {
        if(isFeatureEnabled(Archaeologist.class) && Archaeologist.enableHat && jerConfig.JER_MOBS.enableArchaeologist) {
            EntityArchaeologist archaeologist = new EntityArchaeologist(world);
            registerMob(archaeologist, LightLevel.any, new LootDrop(Archaeologist.archaeologist_hat, 0, 1, 0.025f, Conditional.affectedByLooting));
            registerRenderHook(archaeologist.getClass(), ((renderInfo, e) -> {
                GlStateManager.translate(-0.05, -0.45, 0);
                return renderInfo;
            }));
        }

        if(isFeatureEnabled(Crabs.class) && jerConfig.JER_MOBS.enableCrab) {
            registerMob(new EntityCrab(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(Type.BEACH.getName()), EntityCrab.CRAB_LOOT_TABLE);
        }

        if(isFeatureEnabled(DepthMobs.class)) {
            if(DepthMobs.enableAshen && jerConfig.JER_MOBS.enableAshen) {
                EntityAshen ashen = new EntityAshen(world);
                ItemStack stack = new ItemStack(Items.TIPPED_ARROW);
                PotionUtils.appendEffects(stack, ImmutableSet.of(new PotionEffect(MobEffects.BLINDNESS, 50, 0)));
                ashen.setHeldItem(EnumHand.OFF_HAND, stack);
                registerMob(ashen, LightLevel.hostile, LootTableList.ENTITIES_SKELETON);
            }

            if(DepthMobs.enableDweller && jerConfig.JER_MOBS.enableDweller) {
                registerMob(new EntityDweller(world), LightLevel.hostile, LootTableList.ENTITIES_ZOMBIE);
            }
        }

        if(isFeatureEnabled(Foxhounds.class) && jerConfig.JER_MOBS.enableFoxhound) {
            registerMob(new EntityFoxhound(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(Biomes.HELL), EntityFoxhound.FOXHOUND_LOOT_TABLE);
        }

        if(isFeatureEnabled(Frogs.class) && jerConfig.JER_MOBS.enableFrog) {
            registerMob(new EntityFrog(world), LightLevel.any, EntityFrog.FROG_LOOT_TABLE);
        }

        if(isFeatureEnabled(PirateShips.class) && !PirateShips.onlyHat && jerConfig.JER_MOBS.enablePirate) {
            EntityPirate pirate = new EntityPirate(world);
            pirate.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
            pirate.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(PirateShips.pirate_hat));
            List<LootDrop> drops = LootTableHelper.toDrops(world, LootTableList.ENTITIES_SKELETON);
            drops.add(new LootDrop(PirateShips.pirate_hat, 0, 1, 0.085f, Conditional.affectedByLooting));
            registerMob(pirate, LightLevel.any, BiomeHelper.getBiomeNamesForTypes(Type.OCEAN.getName()), drops.toArray(new LootDrop[0]));
        }

        if(isFeatureEnabled(Stonelings.class) && jerConfig.JER_MOBS.enableStoneling) {
            List<LootDrop> drops = LootTableHelper.toDrops(world, EntityStoneling.LOOT_TABLE);
            drops.addAll(LootTableHelper.toDrops(world, EntityStoneling.CARRY_LOOT_TABLE));
            registerMob(new EntityStoneling(world), LightLevel.hostile, drops.toArray(new LootDrop[0]));
        }

        if(isFeatureEnabled(Wraiths.class) && jerConfig.JER_MOBS.enableWraith) {
            registerMob(new EntityWraith(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForTypes(Type.NETHER.getName()), EntityWraith.LOOT_TABLE);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void injectLoot() {
        if(JEMConfig.QUARK.enableJERInjectedLoot) {
            MobRegistry registry = MobRegistry.getInstance();
            for (MobEntry mobEntry : MobRegistry.getInstance().getMobs()) {
                if (isFeatureEnabled(WitchHat.class) && WitchHat.dropRate > 0 && mobEntry.getEntity() instanceof EntityWitch) {
                    try {
                        Field entryField = MobRegistry.getInstance().getClass().getDeclaredField("registry");
                        entryField.setAccessible(true);
                        Set<MobEntry> mobEntries = (Set<MobEntry>) entryField.get(MobRegistry.getInstance());

                        LootDrop hatDrop = new LootDrop(WitchHat.witch_hat, 0, 1, (float) WitchHat.dropRate, Conditional.affectedByLooting);
                        if(WitchHat.verifyTruePlayer)
                            hatDrop.addConditional(Conditional.playerKill);

                        mobEntries.remove(mobEntry);
                        mobEntry.addDrop(hatDrop);
                        mobEntries.add(mobEntry);
                        continue;
                    } catch(Exception ignored) {}
                }

                if(isFeatureEnabled(BlackAsh.class) && mobEntry.getEntity() instanceof EntityWither) {
                    try {
                        Field entryField = MobRegistry.getInstance().getClass().getDeclaredField("registry");
                        entryField.setAccessible(true);
                        Set<MobEntry> mobEntries = (Set<MobEntry>) entryField.get(MobRegistry.getInstance());

                        if(BlackAsh.witherMax > 0) {
                            LootDrop ashDrop = new LootDrop(Item.getItemFromBlock(BlackAsh.black_ash), BlackAsh.witherMin, BlackAsh.witherMax);
                            mobEntries.remove(mobEntry);
                            mobEntry.addDrop(ashDrop);
                            mobEntries.add(mobEntry);
                        }
                    } catch(Exception ignored){}
                }

                if (isFeatureEnabled(BlackAsh.class) && mobEntry.getEntity() instanceof EntityWitherSkeleton) {
                    try {
                        Field entryField = MobRegistry.getInstance().getClass().getDeclaredField("registry");
                        entryField.setAccessible(true);
                        Set<MobEntry> mobEntries = (Set<MobEntry>) entryField.get(MobRegistry.getInstance());

                        mobEntries.remove(mobEntry);

                        Field dropsField = mobEntry.getClass().getDeclaredField("drops");
                        dropsField.setAccessible(true);
                        Set<LootDrop> drops = (Set<LootDrop>) dropsField.get(mobEntry);

                        if(BlackAsh.removeCoalDrops)
                            drops.removeIf(drop -> (drop.item.getItem().equals(Items.COAL)));
                        mobEntry.addDrop(new LootDrop(Item.getItemFromBlock(BlackAsh.black_ash), BlackAsh.witherSkeletonMin, BlackAsh.witherSkeletonMax, Conditional.affectedByLooting));
                        mobEntries.add(mobEntry);
                    } catch(Exception ignored) {}
                }
            }
        }
    }

    @Override
    public void registerModVillagers() {
        if(jerConfig.JER_VILLAGERS.enableArchaeologist && isFeatureEnabled(Archaeologist.class)) {
            CustomVillagerRegistry registry = CustomVillagerRegistry.getInstance();

            List<List<EntityVillager.ITradeList>> allTrades = new ArrayList<>();
            List<EntityVillager.ITradeList> trades = new ArrayList<>();

            trades.add(new BasicTrade(new ItemStack(Items.BONE, 4), new ItemStack(Items.EMERALD, 3)));
            trades.add(new BasicTrade(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.BONE, 12)));
            trades.add(new BasicTrade(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.GUNPOWDER, 12)));
            trades.add(new BasicTrade(new ItemStack(Items.EMERALD, 1), new ItemStack(Items.COAL, 20)));
            trades.add(new BasicTrade(new ItemStack(Items.DIAMOND, 1), new ItemStack(Items.EMERALD, 16)));
            trades.add(new BasicTrade(new ItemStack(Items.IRON_PICKAXE), new ItemStack(Items.EMERALD, 6)));
            trades.add(new BasicTrade(new ItemStack(Items.IRON_SHOVEL), new ItemStack(Items.EMERALD, 5)));
            if (Archaeologist.enableHat && Archaeologist.sellHat)
                trades.add(new BasicTrade(new ItemStack(Archaeologist.archaeologist_hat), new ItemStack(Items.EMERALD, 5)));

            allTrades.add(trades);

            registry.addVillagerEntry(new CustomVillagerEntry("quark:archaeologist", 0, allTrades) {
                @Override
                public EntityLivingBase getEntity(@Nonnull Minecraft minecraft) throws IllegalAccessException, InvocationTargetException, InstantiationException {
                    return new EntityArchaeologist(world);
                }

                @Override
                public String getDisplayName() {
                    return "entity." + getName() + ".name";
                }
            });
        }
    }

    private void registerDungeonLoot(String name) {
        JERDungeonStrings dungeon = new JERDungeonStrings(name);
        registerDungeonLoot(dungeon.category, dungeon.unlocName, dungeon.lootTable);
    }

    private static class JERDungeonStrings {
        public final String category;
        public final String unlocName;
        public final ResourceLocation lootTable;

        public JERDungeonStrings(String name) {
            this.category = String.format("%s:chests/%s", ModIds.QUARK.MOD_ID, name);
            this.unlocName = StringHelper.getDungeonTranslationKey(ModIds.QUARK.MOD_ID, name);
            this.lootTable = new ResourceLocation(this.category);
        }
    }

    private boolean isFeatureEnabled(Class<? extends Feature> clazz) {
        Feature feature = ModuleLoader.featureInstances.get(clazz);
        return feature != null && feature.isEnabled();
    }
}
