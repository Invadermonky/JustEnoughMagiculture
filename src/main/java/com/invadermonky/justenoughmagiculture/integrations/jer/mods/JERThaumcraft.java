package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigThaumcraft;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.lootbag.LootBagEntry;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.registry.LootBagRegistry;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import com.invadermonky.justenoughmagiculture.util.StringHelper;
import jeresources.api.conditionals.Conditional;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.util.LootTableHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.minecraft.world.storage.loot.functions.SetNBT;
import net.minecraftforge.common.BiomeDictionary.Type;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.internal.WeightedRandomLoot;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.blocks.world.BlockLoot;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.monster.*;
import thaumcraft.common.entities.monster.boss.*;
import thaumcraft.common.entities.monster.cult.EntityCultist;
import thaumcraft.common.entities.monster.cult.EntityCultistCleric;
import thaumcraft.common.entities.monster.cult.EntityCultistKnight;
import thaumcraft.common.entities.monster.tainted.*;
import thecodex6824.thaumicaugmentation.api.TALootTables;

import java.util.ArrayList;

public class JERThaumcraft extends JERBase implements IJERIntegration {
    private static JERThaumcraft instance;
    private static final JEMConfigThaumcraft.JER jerConfig = JEMConfig.THAUMCRAFT.JUST_ENOUGH_RESOURCES;
    private static final boolean forceLoad = JEMConfig.THAUMCRAFT.forceLoadJERMobs;

    public JERThaumcraft(boolean enableJERDungeons, boolean enableJERMobs) {
        if(enableJERDungeons) registerModDungeons();
        if(enableJERMobs) registerModEntities();
    }

    private JERThaumcraft() {}

    public static JERThaumcraft getInstance() {
        return instance != null ? instance : (instance = new JERThaumcraft());
    }

    public void registerLootBagEntries() {
        if(JEMConfig.THAUMCRAFT.JUST_ENOUGH_ITEMS.enableJEILootBags) {
            LootBagRegistry registry = LootBagRegistry.getInstance();
            registry.registerLootBag(new LootBagEntry(new ItemStack(ItemsTC.lootBag, 1, 0), getCommonLoot()));
            registry.registerLootBag(new LootBagEntry(new ItemStack(ItemsTC.lootBag, 1, 1), getUncommonLoot()));
            registry.registerLootBag(new LootBagEntry(new ItemStack(ItemsTC.lootBag, 1, 2), getRareLoot()));
            registry.registerLootBag(new LootBagEntry(new ItemStack(BlocksTC.lootCrateCommon), getCommonLoot()));
            registry.registerLootBag(new LootBagEntry(new ItemStack(BlocksTC.lootCrateUncommon), getUncommonLoot()));
            registry.registerLootBag(new LootBagEntry(new ItemStack(BlocksTC.lootCrateRare), getRareLoot()));
            registry.registerLootBag(new LootBagEntry(new ItemStack(BlocksTC.lootUrnCommon), getCommonLoot()));
            registry.registerLootBag(new LootBagEntry(new ItemStack(BlocksTC.lootUrnUncommon), getUncommonLoot()));
            registry.registerLootBag(new LootBagEntry(new ItemStack(BlocksTC.lootUrnRare), getRareLoot()));
        }
    }

    @Override
    public void registerModDungeons() {
        registerDungeonLoot("thaumcraft:mound", StringHelper.getDungeonTranslationKey(ModIds.THAUMCRAFT.MOD_ID, "mound"), LootTableList.CHESTS_SIMPLE_DUNGEON);
    }

    @Override
    public void registerModEntities() {
        registerBosses();
        registerCultists();
        registerMonsters();
        registerTaintMobs();

        ItemStack output = FurnaceRecipes.instance().getSmeltingResult(new ItemStack(Items.PORKCHOP));
    }

    private void registerBosses() {
        if(JEMConfig.THAUMCRAFT.forceLoadJERMobs || ModIds.CRIMSON_WARFARE.isLoaded) {

            LootDrop pearlDrop = new LootDrop(new ItemStack(ItemsTC.primordialPearl));
            LootDrop bagDrop = new LootDrop(new ItemStack(ItemsTC.lootBag, 1, 2));

            if (jerConfig.enableBossCultistPraetor) {
                EntityCultistLeader cultistLeader = new EntityCultistLeader(world);
                cultistLeader.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemsTC.crimsonPraetorHelm));
                cultistLeader.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemsTC.crimsonPraetorChest));
                cultistLeader.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ItemsTC.crimsonPraetorLegs));
                cultistLeader.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ItemsTC.crimsonBoots));
                cultistLeader.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ItemsTC.crimsonBlade));

                ArrayList<LootDrop> leaderDrops = new ArrayList<>();
                leaderDrops.add(bagDrop);
                leaderDrops.add(new LootDrop(new ItemStack(ItemsTC.crimsonPraetorHelm), 0, 1, 0.05f, Conditional.affectedByLooting));
                leaderDrops.add(new LootDrop(new ItemStack(ItemsTC.crimsonPraetorChest), 0, 1, 0.05f, Conditional.affectedByLooting));
                leaderDrops.add(new LootDrop(new ItemStack(ItemsTC.crimsonPraetorLegs), 0, 1, 0.05f, Conditional.affectedByLooting));
                leaderDrops.add(new LootDrop(new ItemStack(ItemsTC.crimsonBoots), 0, 1, 0.05f, Conditional.affectedByLooting));
                leaderDrops.add(new LootDrop(new ItemStack(ItemsTC.voidSword), 0, 1, 0.05f,
                        Conditional.affectedByLooting, new Conditional(I18n.format(Conditional.gameDifficulty.toString(), EnumDifficulty.EASY.toString()))));
                leaderDrops.add(new LootDrop(new ItemStack(ItemsTC.crimsonBlade), 0, 1, 0.05f,
                        Conditional.affectedByLooting, new Conditional(I18n.format(Conditional.notGameDifficulty.toString(), EnumDifficulty.EASY.toString()))));

                registerMob(cultistLeader, LightLevel.any, leaderDrops.toArray(new LootDrop[0]));
                registerRenderHook(EntityCultistLeader.class, ((renderInfo, e) -> {
                    GlStateManager.translate(-0.05, -0.65, 0);
                    GlStateManager.scale(1.35, 1.35, 1.35);
                    return renderInfo;
                }));
            }

            if (jerConfig.enableBossGreaterCrimsonPortal) {
                registerMob(new EntityCultistPortalGreater(world), LightLevel.any, pearlDrop);
            }

            if (jerConfig.enableBossEldritchGolem) {
                registerMob(new EntityEldritchGolem(world), LightLevel.any, pearlDrop, bagDrop);
                registerRenderHook(EntityEldritchGolem.class, ((renderInfo, e) -> {
                    GlStateManager.translate(-0.05, -0.65, 0);
                    GlStateManager.scale(1.15, 1.15, 1.15);
                    return renderInfo;
                }));
            }

            if (jerConfig.enableBossEldritchWarden) {
                registerMob(new EntityEldritchWarden(world), LightLevel.any, pearlDrop, bagDrop);
                registerRenderHook(EntityEldritchWarden.class, ((renderInfo, e) -> {
                    GlStateManager.translate(-0.05, -0.8, 0);
                    GlStateManager.scale(1.5, 1.5, 1.5);
                    return renderInfo;
                }));
            }

            if (jerConfig.enableBossTaintacleGiant) {
                ArrayList<LootDrop> taintacleDrops = new ArrayList<>();
                taintacleDrops.add(pearlDrop);
                if (ModIds.THAUMIC_AUGMENTATION.isLoaded) {
                    taintacleDrops.addAll(LootTableHelper.toDrops(world, TALootTables.TAINT_MOB));
                }
                registerMob(new EntityTaintacleGiant(world), LightLevel.any, taintacleDrops.toArray(new LootDrop[0]));
            }
        }
    }

    private void registerCultists() {
        if(jerConfig.enableCultistCleric) {
            EntityCultistCleric cleric = new EntityCultistCleric(world);
            cleric.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemsTC.crimsonRobeHelm));
            cleric.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemsTC.crimsonRobeChest));
            cleric.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ItemsTC.crimsonRobeLegs));
            cleric.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ItemsTC.crimsonBoots));

            ArrayList<LootDrop> drops = new ArrayList<>(LootTableHelper.toDrops(world, EntityCultist.LOOT));
            drops.add(new LootDrop(new ItemStack(ItemsTC.crimsonRobeHelm), 0, 1, 0.05f, Conditional.affectedByLooting));
            drops.add(new LootDrop(new ItemStack(ItemsTC.crimsonRobeChest), 0, 1, 0.05f, Conditional.affectedByLooting));
            drops.add(new LootDrop(new ItemStack(ItemsTC.crimsonRobeLegs), 0, 1, 0.05f, Conditional.affectedByLooting));
            drops.add(new LootDrop(new ItemStack(ItemsTC.crimsonBoots), 0, 1, 0.015f, Conditional.affectedByLooting));

            registerMob(cleric, LightLevel.any, drops.toArray(new LootDrop[0]));
        }

        if(jerConfig.enableCultistKnight) {
            EntityCultistKnight knight = new EntityCultistKnight(world);
            knight.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemsTC.crimsonPlateHelm));
            knight.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemsTC.crimsonPlateChest));
            knight.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ItemsTC.crimsonPlateLegs));
            knight.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ItemsTC.crimsonBoots));
            knight.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ItemsTC.voidSword));

            ArrayList<LootDrop> drops = new ArrayList<>(LootTableHelper.toDrops(world, EntityCultist.LOOT));
            drops.add(new LootDrop(new ItemStack(ItemsTC.crimsonPlateHelm), 0, 1, 0.05f, Conditional.affectedByLooting));
            drops.add(new LootDrop(new ItemStack(ItemsTC.crimsonPlateChest), 0, 1, 0.05f, Conditional.affectedByLooting));
            drops.add(new LootDrop(new ItemStack(ItemsTC.crimsonPlateLegs), 0, 1, 0.05f, Conditional.affectedByLooting));
            drops.add(new LootDrop(new ItemStack(ItemsTC.crimsonBoots), 0, 1, 0.025f, Conditional.affectedByLooting));

            drops.add(new LootDrop(new ItemStack(ItemsTC.voidSword), 0, 1, 0.005f, Conditional.affectedByLooting));
            drops.add(new LootDrop(new ItemStack(ItemsTC.thaumiumSword), 0, 1, 0.02f, Conditional.affectedByLooting));

            registerMob(knight, LightLevel.any, drops.toArray(new LootDrop[0]));
        }

        if(jerConfig.enableCultistCleric || jerConfig.enableCultistKnight) {
            registerRenderHook(EntityCultist.class, ((renderInfo, e) -> {
                GlStateManager.translate(0,-0.45,0);
                return renderInfo;
            }));
        }
    }

    private void registerMonsters() {
        if(jerConfig.enableAngryZombie) {
            ArrayList<LootDrop> drops = new ArrayList<>(LootTableHelper.toDrops(world, LootTableList.ENTITIES_ZOMBIE));
            drops.add(new LootDrop(new ItemStack(ItemsTC.brain), 0, 1, 0.5f, Conditional.affectedByLooting));
            registerMob(new EntityBrainyZombie(world), LightLevel.hostile, drops.toArray(new LootDrop[0]));
        }

        if(jerConfig.enableEldrichCrab && (ModIds.THAUMIC_AUGMENTATION.isLoaded || forceLoad)) {
            registerMob(new EntityEldritchCrab(world), LightLevel.hostile, new LootDrop(new ItemStack(Items.ENDER_PEARL), 1, 1, (1f/3f), Conditional.affectedByLooting));
        }

        if(jerConfig.enableFireBat) {
            registerMob(new EntityFireBat(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForTypes(Type.NETHER.toString()), new LootDrop(Items.GUNPOWDER, 1, 1, Conditional.affectedByLooting));
        }

        if(jerConfig.enableFuriousZombie) {
            ArrayList<LootDrop> drops = new ArrayList<>(LootTableHelper.toDrops(world, LootTableList.ENTITIES_ZOMBIE));
            drops.add(new LootDrop(new ItemStack(ItemsTC.brain), 0, 1, 0.5f, Conditional.affectedByLooting));
            registerMob(new EntityGiantBrainyZombie(world), LightLevel.hostile, drops.toArray(new LootDrop[0]));
        }

        if(jerConfig.enablePech) {
            registerMob(new EntityPech(world), LightLevel.hostile, EntityPech.LOOT);
            registerRenderHook(EntityPech.class, ((renderInfo, e) -> {
                GlStateManager.translate(0, -0.4, 0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableThaumicSlime) {
            ArrayList<LootDrop> drops = new ArrayList<>(LootTableHelper.toDrops(world, LootTableList.ENTITIES_SLIME));
            drops.add(new LootDrop(ConfigItems.FLUX_CRYSTAL.copy()));
            registerMob(new EntityThaumicSlime(world), LightLevel.any, drops.toArray(new LootDrop[0]));
        }

        if(jerConfig.enableWisp) {
            ArrayList<Aspect> primals = Aspect.getPrimalAspects();
            ArrayList<LootDrop> drops = new ArrayList<>(primals.size());
            float chance = 1.0f / (float) primals.size();
            for(Aspect aspect : primals) {
                drops.add(new LootDrop(ThaumcraftApiHelper.makeCrystal(aspect), 1, 1, chance));
            }
            registerMob(new EntityWisp(world), LightLevel.any, drops.toArray(new LootDrop[0]));
        }
    }

    private void registerTaintMobs() {
        ArrayList<LootDrop> taintDrops = new ArrayList<>(2);
        taintDrops.add(new LootDrop(ConfigItems.FLUX_CRYSTAL.copy()));
        if(ModIds.THAUMIC_AUGMENTATION.isLoaded) {
            taintDrops.addAll(LootTableHelper.toDrops(world, TALootTables.TAINT_MOB));
        }

        if(jerConfig.enableTaintacle || jerConfig.enableTaintacleSmall) {
            if (jerConfig.enableTaintacle) {
                registerMob(new EntityTaintacle(world), LightLevel.any, taintDrops.toArray(new LootDrop[0]));
                registerRenderHook(EntityTaintacle.class, ((renderInfo, e) -> {
                    GlStateManager.translate(0, 2.2, 0);
                    return renderInfo;
                }));
            }

            if (jerConfig.enableTaintacleSmall) {
                registerMob(new EntityTaintacleSmall(world), LightLevel.any, taintDrops.toArray(new LootDrop[0]));
                registerRenderHook(EntityTaintacleSmall.class, ((renderInfo, e) -> {
                    GlStateManager.translate(0, jerConfig.enableTaintacle ? -1.6 : 0.6, 0);
                    return renderInfo;
                }));
            }
        }

        if(jerConfig.enableTaintCrawler) {
            registerMob(new EntityTaintCrawler(world), LightLevel.any, new LootDrop(ConfigItems.FLUX_CRYSTAL.copy()));
        }

        if(jerConfig.enableTaintSeed) {
            registerMob(new EntityTaintSeed(world), LightLevel.any, taintDrops.toArray(new LootDrop[0]));
            registerRenderHook(EntityTaintSeed.class, ((renderInfo, e) -> {
                GlStateManager.translate(0, 0.6, 0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableTaintSeedPrime) {
            registerMob(new EntityTaintSeedPrime(world), LightLevel.any, taintDrops.toArray(new LootDrop[0]));
            registerRenderHook(EntityTaintSeedPrime.class, ((renderInfo, e) -> {
                GlStateManager.translate(0, jerConfig.enableTaintSeed ? 0.6 : 1.2, 0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableTaintSwarm) {
            registerMob(new EntityTaintSwarm(world), LightLevel.any, taintDrops.toArray(new LootDrop[0]));
        }
    }

    private static LootTable getCommonLoot() {
        int minRolls = BlockLoot.LootType.COMMON.ordinal() + 1;
        return getLoot(WeightedRandomLoot.lootBagCommon, minRolls);
    }

    private static LootTable getUncommonLoot() {
        int minRolls = BlockLoot.LootType.UNCOMMON.ordinal() + 1;
        return getLoot(WeightedRandomLoot.lootBagUncommon, minRolls);
    }

    private static LootTable getRareLoot() {
        int minRolls = BlockLoot.LootType.RARE.ordinal() + 1;
        return getLoot(WeightedRandomLoot.lootBagRare, minRolls);
    }

    private static LootTable getLoot(ArrayList<WeightedRandomLoot> lootList, int minRolls) {
        ArrayList<LootEntry> entries = new ArrayList<>();
        int maxRolls = minRolls + 2;
        int potionWeight = 0;
        int splashWeight = 0;
        int lingeringWeight = 0;
        for(WeightedRandomLoot randomLoot : lootList) {
            ItemStack stack = randomLoot.item;

            if(JEMConfig.THAUMCRAFT.JUST_ENOUGH_ITEMS.removePotionLoot) {
                if(stack.getItem() == Items.POTIONITEM) {
                    potionWeight += randomLoot.itemWeight;
                    continue;
                } else if(stack.getItem() == Items.SPLASH_POTION) {
                    splashWeight += randomLoot.itemWeight;
                    continue;
                } else if(stack.getItem() == Items.LINGERING_POTION) {
                    lingeringWeight += randomLoot.itemWeight;
                    continue;
                }
            }

            ArrayList<LootFunction> functions = new ArrayList<>();
            functions.add(new SetMetadata(new LootCondition[0], new RandomValueRange((float) stack.getItemDamage(), (float) stack.getItemDamage())));
            functions.add(new SetCount(new LootCondition[0], new RandomValueRange(stack.getCount(), stack.getCount())));
            if(stack.hasTagCompound())
                functions.add(new SetNBT(new LootCondition[0], stack.getTagCompound()));

            entries.add(new LootEntryItem(
                    stack.getItem(),
                    randomLoot.itemWeight,
                    0,
                    functions.toArray(new LootFunction[0]),
                    new LootCondition[0],
                    stack.getItem().getRegistryName().toString()
            ));
        }

        if(JEMConfig.THAUMCRAFT.JUST_ENOUGH_ITEMS.removePotionLoot) {
            entries.add(new LootEntryItem(Items.POTIONITEM, potionWeight, 0, new LootFunction[0], new LootCondition[0], Items.POTIONITEM.toString()));
            entries.add(new LootEntryItem(Items.SPLASH_POTION, splashWeight, 0, new LootFunction[0], new LootCondition[0], Items.SPLASH_POTION.toString()));
            entries.add(new LootEntryItem(Items.LINGERING_POTION, lingeringWeight, 0, new LootFunction[0], new LootCondition[0], Items.LINGERING_POTION.toString()));
        }

        return new LootTable(new LootPool[] {new LootPool(
                entries.toArray(new LootEntry[0]),
                new LootCondition[0],
                new RandomValueRange((float) minRolls, (float) maxRolls),
                new RandomValueRange(0, 0),
                "thaumcraft:loot_block"
        )});
    }

    private boolean canSmelt(ItemStack stack) {
        return !FurnaceRecipes.instance().getSmeltingResult(stack).isEmpty();
    }
}
