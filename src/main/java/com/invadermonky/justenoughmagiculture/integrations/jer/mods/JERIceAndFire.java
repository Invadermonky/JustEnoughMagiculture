package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.github.alexthe666.iceandfire.entity.*;
import com.github.alexthe666.iceandfire.enums.EnumTroll;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.github.alexthe666.iceandfire.world.gen.*;
import com.github.alexthe666.rats.server.entity.EntityPirat;
import com.github.alexthe666.rats.server.entity.EntityRat;
import com.google.common.collect.Sets;
import com.invadermonky.justenoughmagiculture.client.model.entity.mods.iceandfire.JERRenderHippocampus;
import com.invadermonky.justenoughmagiculture.client.render.entity.mods.rats.JERRenderPirat;
import com.invadermonky.justenoughmagiculture.client.render.entity.mods.rats.JERRenderRat;
import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigIceAndFire;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.jer.villager.CustomVanillaVillagerEntry;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.registry.CustomVillagerRegistry;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import com.invadermonky.justenoughmagiculture.util.LogHelper;
import com.invadermonky.justenoughmagiculture.util.ReflectionHelper;
import com.invadermonky.justenoughmagiculture.util.StringHelper;
import com.invadermonky.justenoughmagiculture.util.modhelpers.IaFLootHelper;
import gnu.trove.map.hash.THashMap;
import gnu.trove.set.hash.THashSet;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.entry.MobEntry;
import jeresources.registry.MobRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerProfession;
import org.apache.commons.lang3.reflect.FieldUtils;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JERIceAndFire extends JERBase implements IJERIntegration {
    private static JERIceAndFire instance;
    JEMConfigIceAndFire.JER jerConfig = JEMConfig.ICE_AND_FIRE.JUST_ENOUGH_RESOURCES;

    private JERIceAndFire() {}

    public JERIceAndFire(boolean enableJERDungeons, boolean enableJERMobs, boolean enableJERVillagers) {
        if(enableJERDungeons) registerModDungeons();
        if(enableJERMobs) registerModEntities();
        if(enableJERVillagers) registerModVillagers();
        getInstance();
    }

    public static JERIceAndFire getInstance() {
        return instance != null ? instance : (instance = new JERIceAndFire());
    }

    /**
     * Ice and fire needs to register render overrides here because they don't work if registered normally.
     */
    public void lateInit() {
        registerRenderOverrides();
        injectLoot();
    }

    @Override
    public void registerModDungeons() {
        registerIAFDungeon(WorldGenCyclopsCave.CYCLOPS_CHEST);
        registerIAFDungeon(WorldGenFireDragonCave.FIREDRAGON_CHEST);
        registerIAFDungeon(WorldGenFireDragonCave.FIREDRAGON_MALE_CHEST);
        registerIAFDungeon(WorldGenHydraCave.HYDRA_CHEST);
        registerIAFDungeon(WorldGenIceDragonCave.ICEDRAGON_CHEST);
        registerIAFDungeon(WorldGenIceDragonCave.ICEDRAGON_MALE_CHEST);
        registerIAFDungeon(WorldGenMyrmexDecoration.DESERT_MYRMEX_FOOD_CHEST);
        registerIAFDungeon(WorldGenMyrmexDecoration.JUNGLE_MYRMEX_FOOD_CHEST);
        registerIAFDungeon(WorldGenMyrmexDecoration.MYRMEX_GOLD_CHEST);
        registerIAFDungeon(WorldGenMyrmexDecoration.MYRMEX_TRASH_CHEST);
    }

    @Override
    public void registerModEntities() {
        registerDragons();
        registerDeathworms();
        registerDread();
        registerMyrmex();
        registerSeaSerpents();
        registerMiscMobs();

    }

    @Override
    @SuppressWarnings("unchecked")
    public void injectLoot() {
        if(JEMConfig.ICE_AND_FIRE.enableJERInjectedLoot) {
            for (MobEntry mobEntry : MobRegistry.getInstance().getMobs()) {
                if (mobEntry.getEntity() instanceof EntityWitherSkeleton) {
                    try {
                        Field entryField = MobRegistry.getInstance().getClass().getDeclaredField("registry");
                        entryField.setAccessible(true);
                        Set<MobEntry> mobEntries = (Set<MobEntry>) entryField.get(MobRegistry.getInstance());

                        mobEntries.remove(mobEntry);

                        Field dropsField = mobEntry.getClass().getDeclaredField("drops");
                        dropsField.setAccessible(true);

                        mobEntry.addDrop(new LootDrop(IafItemRegistry.witherbone, 0, 1));
                        mobEntries.add(mobEntry);
                    } catch(Exception ignored) {}
                }
            }
        }
    }

    private void registerDragons() {
        THashSet<Type> validFireTypes = new THashSet<>(Sets.newHashSet(Type.HILLS, Type.MOUNTAIN));
        THashSet<Type> invalidForeTypes = new THashSet<>(Sets.newHashSet(Type.COLD, Type.SNOWY));
        THashSet<Biome> validIceBiomes = getIaFLinkedBiomes(Type.COLD, Type.SNOWY);
        THashSet<Type> invalidIceTypes = new THashSet<>(Sets.newHashSet(Type.BEACH));

        if(jerConfig.JER_MOBS.enableFireDragonMale) {
            for (int i = 0; i < 3; i++) {
                EntityFireDragon fireDragon = new EntityFireDragon(world);
                fireDragon.setVariant(i);
                fireDragon.setCustomNameTag("entity.jem:iaf_fire_dragon_male.name");
                List<LootDrop> drops = IaFLootHelper.toDrops(fireDragon, manager.getLootTableFromLocation(EntityFireDragon.MALE_LOOT));
                if (jerConfig.JER_MOBS.enableFireDragonMale) {
                    registerMob(fireDragon, LightLevel.any, getIaFSpawnBiomesFromTypes(validFireTypes, invalidForeTypes), drops);
                }
            }
        }

        if(jerConfig.JER_MOBS.enableIceDragonMale) {
            for (int i = 0; i < 3; i++) {
                EntityIceDragon iceDragon = new EntityIceDragon(world);
                iceDragon.setVariant(i);
                iceDragon.setCustomNameTag("entity.jem:iaf_ice_dragon_male.name");
                THashSet<Biome> validBiomes = getIaFLinkedBiomes(Type.COLD, Type.SNOWY);
                List<LootDrop> drops = IaFLootHelper.toDrops(iceDragon, manager.getLootTableFromLocation(EntityIceDragon.MALE_LOOT));
                if (jerConfig.JER_MOBS.enableFireDragonMale) {
                    registerMob(iceDragon, LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(validBiomes.toArray(new Biome[0])), drops);
                }
            }
        }

        if(jerConfig.JER_MOBS.enableFireDragonFemale) {
            for (int i = 0; i < 3; i++) {
                EntityFireDragon fireDragon = new EntityFireDragon(world);
                fireDragon.setVariant(i);
                fireDragon.setCustomNameTag("entity.jem:iaf_fire_dragon_female.name");
                List<LootDrop> femaleDrops = IaFLootHelper.toDrops(fireDragon, manager.getLootTableFromLocation(EntityFireDragon.FEMALE_LOOT));
                registerMob(fireDragon, LightLevel.any, getIaFSpawnBiomesFromTypes(validFireTypes, invalidForeTypes), femaleDrops);
            }
        }

        if(jerConfig.JER_MOBS.enableIceDragonFemale) {
            for(int i = 0; i < 3; i++) {
                EntityIceDragon iceDragon = new EntityIceDragon(world);
                iceDragon.setVariant(i);
                iceDragon.setCustomNameTag("entity.jem:iaf_ice_dragon_female.name");
                List<LootDrop> femaleDrops = IaFLootHelper.toDrops(iceDragon, manager.getLootTableFromLocation(EntityIceDragon.FEMALE_LOOT));
                registerMob(iceDragon, LightLevel.any, getIaFSpawnBiomes(validIceBiomes, invalidIceTypes), femaleDrops);
            }
        }

        registerRenderHook(EntityDragonBase.class, ((renderInfo, e) -> {
            GlStateManager.scale(3.0,3.0,3.0);
            GlStateManager.translate(-0.005,-0.05,0);
            return renderInfo;
        }));
    }

    private void registerDeathworms() {
        if(jerConfig.JER_MOBS.enableDeathWormRed) {
            EntityDeathWorm deathWorm = new EntityDeathWorm(world);
            deathWorm.setVariant(2);    //0 = Tan, 1 = White, 2 = Red
            deathWorm.setWormAge(5);    //Worm Age of 20 creates giant worms.
            THashSet<Type> validTypes = new THashSet<>(Sets.newHashSet(Type.SANDY, Type.DRY));
            THashSet<Type> invalidTypes = new THashSet<>(Sets.newHashSet(Type.BEACH, Type.MESA));
            List<LootDrop> drops = IaFLootHelper.toDrops(deathWorm, manager.getLootTableFromLocation(EntityDeathWorm.RED_LOOT));
            registerMob(deathWorm, LightLevel.any, getIaFSpawnBiomesFromTypes(validTypes, invalidTypes), drops);
        }

        if(jerConfig.JER_MOBS.enableDeathWormRedGiant) {
            EntityDeathWorm deathWorm = new EntityDeathWorm(world);
            deathWorm.setVariant(2);    //0 = Tan, 1 = White, 2 = Red
            deathWorm.setWormAge(10);   //Worm Age of 20 creates giant worms.
            THashSet<Type> validTypes = new THashSet<>(Sets.newHashSet(Type.SANDY, Type.DRY));
            THashSet<Type> invalidTypes = new THashSet<>(Sets.newHashSet(Type.BEACH, Type.MESA));
            List<LootDrop> drops = IaFLootHelper.toDrops(deathWorm, manager.getLootTableFromLocation(EntityDeathWorm.RED_GIANT_LOOT));
            registerMob(deathWorm, LightLevel.any, getIaFSpawnBiomesFromTypes(validTypes, invalidTypes), drops);
        }

        if(jerConfig.JER_MOBS.enableDeathWormTan) {
            EntityDeathWorm deathWorm = new EntityDeathWorm(world);
            deathWorm.setVariant(0);    //0 = Tan, 1 = White, 2 = Red
            deathWorm.setWormAge(5);    //Worm Age of 20 creates giant worms.
            THashSet<Type> validTypes = new THashSet<>(Sets.newHashSet(Type.SANDY, Type.DRY));
            THashSet<Type> invalidTypes = new THashSet<>(Sets.newHashSet(Type.BEACH, Type.MESA));
            List<LootDrop> drops = IaFLootHelper.toDrops(deathWorm, manager.getLootTableFromLocation(EntityDeathWorm.TAN_LOOT));
            registerMob(deathWorm, LightLevel.any, getIaFSpawnBiomesFromTypes(validTypes, invalidTypes), drops);
        }

        if(jerConfig.JER_MOBS.enableDeathWormTanGiant) {
            EntityDeathWorm deathWorm = new EntityDeathWorm(world);
            deathWorm.setVariant(0);    //0 = Tan, 1 = White, 2 = Red
            deathWorm.setWormAge(10);   //Worm Age of 20 creates giant worms.
            THashSet<Type> validTypes = new THashSet<>(Sets.newHashSet(Type.SANDY, Type.DRY));
            THashSet<Type> invalidTypes = new THashSet<>(Sets.newHashSet(Type.BEACH, Type.MESA));
            List<LootDrop> drops = IaFLootHelper.toDrops(deathWorm, manager.getLootTableFromLocation(EntityDeathWorm.TAN_GIANT_LOOT));
            registerMob(deathWorm, LightLevel.any, getIaFSpawnBiomesFromTypes(validTypes, invalidTypes), drops);
        }

        if(jerConfig.JER_MOBS.enableDeathWormWhite) {
            EntityDeathWorm deathWorm = new EntityDeathWorm(world);
            deathWorm.setVariant(1);    //0 = Tan, 1 = White, 2 = Red
            deathWorm.setWormAge(5);    //Worm Age of 20 creates giant worms.
            THashSet<Type> validTypes = new THashSet<>(Sets.newHashSet(Type.SANDY, Type.DRY));
            THashSet<Type> invalidTypes = new THashSet<>(Sets.newHashSet(Type.BEACH, Type.MESA));
            List<LootDrop> drops = IaFLootHelper.toDrops(deathWorm, manager.getLootTableFromLocation(EntityDeathWorm.WHITE_LOOT));
            registerMob(deathWorm, LightLevel.any, getIaFSpawnBiomesFromTypes(validTypes, invalidTypes), drops);
        }

        if(jerConfig.JER_MOBS.enableDeathWormWhiteGiant) {
            EntityDeathWorm deathWorm = new EntityDeathWorm(world);
            deathWorm.setVariant(1);    //0 = Tan, 1 = White, 2 = Red
            deathWorm.setWormAge(10);   //Worm Age of 20 creates giant worms.
            THashSet<Type> validTypes = new THashSet<>(Sets.newHashSet(Type.SANDY, Type.DRY));
            THashSet<Type> invalidTypes = new THashSet<>(Sets.newHashSet(Type.BEACH, Type.MESA));
            List<LootDrop> drops = IaFLootHelper.toDrops(deathWorm, manager.getLootTableFromLocation(EntityDeathWorm.WHITE_GIANT_LOOT));
            registerMob(deathWorm, LightLevel.any, getIaFSpawnBiomesFromTypes(validTypes, invalidTypes), drops);
        }
    }

    private void registerDread() {
        if(jerConfig.JER_MOBS.enableDreadBeast) {
            EntityDreadBeast dreadBeast = new EntityDreadBeast(world);
            THashSet<Biome> validBiomes = getIaFLinkedBiomes(Type.COLD, Type.SNOWY);
            registerMob(dreadBeast, LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(validBiomes.toArray(new Biome[0])), EntityDreadBeast.LOOT);
            adjustHumanoidRenderHook(EntityDreadBeast.class);
        }

        if(jerConfig.JER_MOBS.enableDreadGhoul) {
            EntityDreadGhoul dreadGhoul = new EntityDreadGhoul(world);
            THashSet<Biome> validBiomes = getIaFLinkedBiomes(Type.COLD, Type.SNOWY);
            registerMob(dreadGhoul, LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(validBiomes.toArray(new Biome[0])), EntityDreadGhoul.LOOT);
            adjustHumanoidRenderHook(EntityDreadGhoul.class);
        }

        if(jerConfig.JER_MOBS.enableDreadKnight) {
            EntityDreadKnight dreadKnight = new EntityDreadKnight(world);
            dreadKnight.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(IafItemRegistry.dread_knight_sword));
            THashSet<Biome> validBiomes = getIaFLinkedBiomes(Type.COLD, Type.SNOWY);
            registerMob(dreadKnight, LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(validBiomes.toArray(new Biome[0])), EntityDreadKnight.LOOT);
            registerRenderHook(EntityDreadKnight.class, ((renderInfo, e) -> {
                GlStateManager.translate(-0.05,0.2,0);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableDreadLich) {
            EntityDreadLich lich = new EntityDreadLich(world);
            lich.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(IafItemRegistry.lich_staff));
            THashSet<Biome> validBiomes = getIaFLinkedBiomes(Type.COLD, Type.SNOWY);
            registerMob(lich, LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(validBiomes.toArray(new Biome[0])), EntityDreadLich.LOOT);
            adjustHumanoidRenderHook(EntityDreadLich.class);
        }

        if(jerConfig.JER_MOBS.enableDreadScuttler) {
            EntityDreadScuttler dreadScuttler = new EntityDreadScuttler(world);
            THashSet<Biome> validBiomes = getIaFLinkedBiomes(Type.COLD, Type.SNOWY);
            registerMob(dreadScuttler, LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(validBiomes.toArray(new Biome[0])), EntityDreadScuttler.LOOT);
            registerRenderHook(EntityDreadScuttler.class, ((renderInfo, e) -> {
                GlStateManager.scale(0.9,0.9,0.9);
                GlStateManager.translate(0,-0.2,0);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableDreadThrall) {
            EntityDreadThrall dreadThrall = new EntityDreadThrall(world);
            dreadThrall.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(IafItemRegistry.dread_sword));
            dreadThrall.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(Items.CHAINMAIL_HELMET));
            dreadThrall.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.CHAINMAIL_CHESTPLATE));
            dreadThrall.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.CHAINMAIL_LEGGINGS));
            dreadThrall.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(Items.CHAINMAIL_BOOTS));
            THashSet<Biome> validBiomes = getIaFLinkedBiomes(Type.COLD, Type.SNOWY);
            registerMob(dreadThrall, LightLevel.hostile, BiomeHelper.getBiomeNamesForBiomes(validBiomes.toArray(new Biome[0])), EntityDreadThrall.LOOT);
            adjustHumanoidRenderHook(dreadThrall.getClass());
        }
    }

    private void registerMyrmex() {
        if(jerConfig.JER_MOBS.enableMyrmexDesertQueen) {
            THashSet<Biome> validBiomes = getIaFLinkedBiomes(Type.HOT, Type.DRY, Type.SANDY);
            registerMob(setMyrmexTexture(new EntityMyrmexQueen(world), false), LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(validBiomes.toArray(new Biome[0])), EntityMyrmexQueen.DESERT_LOOT);
        }

        if(jerConfig.JER_MOBS.enableMyrmexDesertRoyal) {
            THashSet<Biome> validBiomes = getIaFLinkedBiomes(Type.HOT, Type.DRY, Type.SANDY);
            registerMob(setMyrmexTexture(new EntityMyrmexRoyal(world), false), LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(validBiomes.toArray(new Biome[0])), EntityMyrmexRoyal.DESERT_LOOT);
        }

        if(jerConfig.JER_MOBS.enableMyrmexDesertSentinel) {
            THashSet<Biome> validBiomes = getIaFLinkedBiomes(Type.HOT, Type.DRY, Type.SANDY);
            registerMob(setMyrmexTexture(new EntityMyrmexSentinel(world), false), LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(validBiomes.toArray(new Biome[0])), EntityMyrmexSentinel.DESERT_LOOT);
        }

        if(jerConfig.JER_MOBS.enableMyrmexDesertSoldier) {
            THashSet<Biome> validBiomes = getIaFLinkedBiomes(Type.HOT, Type.DRY, Type.SANDY);
            registerMob(setMyrmexTexture(new EntityMyrmexSoldier(world), false), LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(validBiomes.toArray(new Biome[0])), EntityMyrmexSoldier.DESERT_LOOT);
        }

        if(jerConfig.JER_MOBS.enableMyrmexDesertWorker) {
            THashSet<Biome> validBiomes = getIaFLinkedBiomes(Type.HOT, Type.DRY, Type.SANDY);
            registerMob(setMyrmexTexture(new EntityMyrmexWorker(world), false), LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(validBiomes.toArray(new Biome[0])), EntityMyrmexWorker.DESERT_LOOT);
        }

        if(jerConfig.JER_MOBS.enableMyrmexJungleQueen) {
            registerMob(setMyrmexTexture(new EntityMyrmexQueen(world), true), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(Type.JUNGLE), EntityMyrmexQueen.JUNGLE_LOOT);
        }

        if(jerConfig.JER_MOBS.enableMyrmexJungleRoyal) {
            registerMob(setMyrmexTexture(new EntityMyrmexRoyal(world), true), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(Type.JUNGLE), EntityMyrmexRoyal.JUNGLE_LOOT);
        }

        if(jerConfig.JER_MOBS.enableMyrmexJungleSentinel) {
            registerMob(setMyrmexTexture(new EntityMyrmexSentinel(world), true), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(Type.JUNGLE), EntityMyrmexSentinel.JUNGLE_LOOT);
        }

        if(jerConfig.JER_MOBS.enableMyrmexJungleSoldier) {
            registerMob(setMyrmexTexture(new EntityMyrmexSoldier(world), true), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(Type.JUNGLE), EntityMyrmexSoldier.JUNGLE_LOOT);
        }

        if(jerConfig.JER_MOBS.enableMyrmexJungleWorker) {
            registerMob(setMyrmexTexture(new EntityMyrmexWorker(world), true), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(Type.JUNGLE), EntityMyrmexWorker.JUNGLE_LOOT);
        }

        registerRenderHook(EntityMyrmexQueen.class, ((renderInfo, entityLivingBase) -> {
            GlStateManager.scale(1.7,1.7,1.7);
            return renderInfo;
        }));
    }

    private void registerSeaSerpents() {
        if(jerConfig.JER_MOBS.enableSeaSerpent) {
            for(int i = 0; i < 6; i++) {
                EntitySeaSerpent seaSerpent = new EntitySeaSerpent(world);
                NBTTagCompound tag = new NBTTagCompound();
                tag.setFloat("Scale", 1f);
                seaSerpent.readEntityFromNBT(tag);
                seaSerpent.setVariant(i);
                List<LootDrop> drops = IaFLootHelper.toDrops(seaSerpent, manager.getLootTableFromLocation(EntitySeaSerpent.LOOT));
                registerMob(seaSerpent, LightLevel.any, BiomeHelper.getBiomeNamesForTypes(Type.OCEAN), drops);
            }
        }
    }

    private void registerMiscMobs() {
        if(jerConfig.JER_MOBS.enableAmphithere) {
            registerMob(new EntityAmphithere(world), LightLevel.any, BiomeHelper.getTypeNamesForTypes(Type.JUNGLE), EntityAmphithere.LOOT);
            registerRenderHook(EntityAmphithere.class, ((renderInfo, entityLivingBase) -> {
                GlStateManager.translate(-0.05,1.4,0);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableCockatrice) {
            registerMob(new EntityCockatrice(world), LightLevel.any, BiomeHelper.getTypeNamesForTypes(Type.SAVANNA, Type.SPARSE), EntityCockatrice.LOOT);
        }

        if(jerConfig.JER_MOBS.enableCyclops) {
            registerMob(new EntityCyclops(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(Type.BEACH, Type.PLAINS), EntityCyclops.LOOT);
            registerRenderHook(EntityCyclops.class, ((renderInfo, entityLivingBase) -> {
                GlStateManager.scale(0.9,0.9,0.9);
                GlStateManager.translate(-0.05,-1.75,0);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableGorgon) {
            registerMob(new EntityGorgon(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(Type.BEACH), EntityGorgon.LOOT);
            adjustHumanoidRenderHook(EntityGorgon.class);
        }

        if(jerConfig.JER_MOBS.enableHippocampus) {
            registerMob(new EntityHippocampus(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(Type.OCEAN), EntityHippocampus.LOOT);
        }

        if(jerConfig.JER_MOBS.enableHippogryph) {
            registerMob(new EntityHippogryph(world), LightLevel.any, BiomeHelper.getTypeNamesForTypes(Type.HILLS), EntityHippogryph.LOOT);
        }

        if(jerConfig.JER_MOBS.enableHydra) {
            registerMob(new EntityHydra(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(Type.SWAMP), EntityHydra.LOOT);
            registerRenderHook(EntityHydra.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.75,1.75,1.75);
                GlStateManager.translate(-0.05,-0.4,0);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enablePixie) {
            THashSet<Biome> validBiomes = getIaFLinkedBiomes(Type.FOREST, Type.SPOOKY);
            validBiomes.addAll(BiomeDictionary.getBiomes(Type.MAGICAL));
            registerMob(new EntityPixie(world), LightLevel.any, BiomeHelper.getBiomeNamesForBiomes(validBiomes.toArray(new Biome[0])), EntityPixie.LOOT);
        }

        if(jerConfig.JER_MOBS.enableSiren) {
            THashSet<Type> validTypes = new THashSet<>(Sets.newHashSet(Type.OCEAN));
            THashSet<Type> invalidTypes = new THashSet<>(Sets.newHashSet(Type.COLD));
            registerMob(new EntitySiren(world), LightLevel.any, getIaFSpawnBiomesFromTypes(validTypes, invalidTypes), EntitySiren.LOOT);
        }

        if(jerConfig.JER_MOBS.enableStymphalianBird) {
            registerMob(new EntityStymphalianBird(world), LightLevel.any, BiomeHelper.getBiomeNamesForTypes(Type.SWAMP), EntityStymphalianBird.LOOT);
            registerRenderHook(EntityStymphalianBird.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.75,1.75,1.75);
                GlStateManager.translate(-0.05,-0.3,0);
                return renderInfo;
            }));
        }

        if(jerConfig.JER_MOBS.enableTrollForest) {
            EntityTroll troll = new EntityTroll(world);
            troll.setType(EnumTroll.FOREST);
            registerMob(troll, LightLevel.hostile, BiomeHelper.getTypeNamesForTypes(troll.getType().spawnBiome), EntityTroll.FOREST_LOOT);
        }

        if(jerConfig.JER_MOBS.enableTrollFrost) {
            EntityTroll troll = new EntityTroll(world);
            troll.setType(EnumTroll.FROST);
            registerMob(troll, LightLevel.hostile, BiomeHelper.getTypeNamesForTypes(troll.getType().spawnBiome), EntityTroll.FROST_LOOT);
        }

        if(jerConfig.JER_MOBS.enableTrollMountain) {
            EntityTroll troll = new EntityTroll(world);
            troll.setType(EnumTroll.MOUNTAIN);
            registerMob(troll, LightLevel.hostile, BiomeHelper.getTypeNamesForTypes(troll.getType().spawnBiome), EntityTroll.MOUNTAIN_LOOT);
        }

        //Generic Render Hooks
        registerRenderHook(EntityTroll.class, ((renderInfo, e) -> {
            GlStateManager.translate(-0.05,-0.8,0);
            return renderInfo;
        }));
    }

    public void registerRenderOverrides() {
        if(JEMConfig.ICE_AND_FIRE.enableRenderFixes) {
            //Ice and Fire uses the depreciated method to register entity renders.
            RenderingRegistry.registerEntityRenderingHandler(EntityHippocampus.class, new JERRenderHippocampus(Minecraft.getMinecraft().getRenderManager()));
        }
    }

    private THashSet<Biome> getIaFLinkedBiomes(Type... types) {
        THashSet<Biome> biomes = new THashSet<>();
        for(Type type : types) {
            if(biomes.isEmpty()) {
                biomes.addAll(BiomeDictionary.getBiomes(type));
            } else {
                biomes.removeIf(biome -> (!BiomeDictionary.getBiomes(type).contains(biome)));
            }
        }
        return biomes;
    }

    private String[] getIaFSpawnBiomesFromTypes(THashSet<Type> validTypes, THashSet<Type> invalidTypes) {
        THashSet<Biome> validBiomes = new THashSet<>();

        for(Type validType : validTypes) {
            validBiomes.addAll(BiomeDictionary.getBiomes(validType));
        }

        for(Type invalidType : invalidTypes) {
            validBiomes.removeAll(BiomeDictionary.getBiomes(invalidType));
        }

        return validBiomes.isEmpty() ? new String[] {"jer.any"} : BiomeHelper.getBiomeNamesForBiomes(validBiomes.toArray(new Biome[0]));
    }

    private String[] getIaFSpawnBiomes(THashSet<Biome> biomes, THashSet<Type> invalidTypes) {
        for(Type type : invalidTypes) {
            biomes.removeAll(BiomeDictionary.getBiomes(type));
        }
        return biomes.isEmpty() ? new String[] {"jer.any"} : BiomeHelper.getBiomeNamesForBiomes(biomes.toArray(new Biome[0]));
    }

    private void adjustHumanoidRenderHook(Class<? extends EntityLivingBase> clazz) {
        registerRenderHook(clazz, ((renderInfo, e) -> {
            GlStateManager.translate(-0.05,-0.4,0);
            return renderInfo;
        }));
    }

    @Override
    public void registerModVillagers() {
        CustomVillagerRegistry registry = CustomVillagerRegistry.getInstance();

        THashMap<VillagerProfession,EntityMyrmexBase> desertMyrmexVillagers = new THashMap<>(5);
        THashMap<VillagerProfession,EntityMyrmexBase> jungleMyrmexVillagers = new THashMap<>(5);

        if(jerConfig.JER_VILLAGERS.enableMyrmexQueen) {
            desertMyrmexVillagers.put(IafVillagerRegistry.INSTANCE.desertMyrmexQueen, setMyrmexTexture(new EntityMyrmexQueen(world), false));
            jungleMyrmexVillagers.put(IafVillagerRegistry.INSTANCE.jungleMyrmexQueen, setMyrmexTexture(new EntityMyrmexQueen(world), true));
        }

        if(jerConfig.JER_VILLAGERS.enableMyrmexRoyal) {
            desertMyrmexVillagers.put(IafVillagerRegistry.INSTANCE.desertMyrmexRoyal, setMyrmexTexture(new EntityMyrmexRoyal(world), false));
            jungleMyrmexVillagers.put(IafVillagerRegistry.INSTANCE.jungleMyrmexRoyal, setMyrmexTexture(new EntityMyrmexRoyal(world), true));
        }

        if(jerConfig.JER_VILLAGERS.enableMyrmexSentinel) {
            desertMyrmexVillagers.put(IafVillagerRegistry.INSTANCE.desertMyrmexSentinel, setMyrmexTexture(new EntityMyrmexSentinel(world), false));
            jungleMyrmexVillagers.put(IafVillagerRegistry.INSTANCE.jungleMyrmexSentinel, setMyrmexTexture(new EntityMyrmexSentinel(world), true));
        }

        if(jerConfig.JER_VILLAGERS.enableMyrmexSoldier) {
            desertMyrmexVillagers.put(IafVillagerRegistry.INSTANCE.desertMyrmexSoldier, setMyrmexTexture(new EntityMyrmexSoldier(world), false));
            jungleMyrmexVillagers.put(IafVillagerRegistry.INSTANCE.jungleMyrmexSoldier, setMyrmexTexture(new EntityMyrmexSoldier(world), true));
        }

        if(jerConfig.JER_VILLAGERS.enableMyrmexWorker) {
            desertMyrmexVillagers.put(IafVillagerRegistry.INSTANCE.desertMyrmexWorker, setMyrmexTexture(new EntityMyrmexWorker(world), false));
            jungleMyrmexVillagers.put(IafVillagerRegistry.INSTANCE.jungleMyrmexWorker, setMyrmexTexture(new EntityMyrmexWorker(world), true));
        }

        registerMyrmexVillagers(registry, desertMyrmexVillagers);
        registerMyrmexVillagers(registry, jungleMyrmexVillagers);

        if(jerConfig.JER_VILLAGERS.enableSnowVillager) {
            registerSnowVillagers(registry);
        }
    }

    private EntityMyrmexBase setMyrmexTexture(EntityMyrmexBase entity, boolean isJungle) {
        entity.setJungleVariant(isJungle);
        return entity;
    }

    private void registerMyrmexVillagers(CustomVillagerRegistry registry, THashMap<VillagerProfession,EntityMyrmexBase> myrmexVillagers) {
        myrmexVillagers.forEach((profession, villager) -> {
            try {
                VillagerCareer career = profession.getCareer(0);
                List<List<EntityVillager.ITradeList>> trades = (List<List<EntityVillager.ITradeList>>) ReflectionHelper.getFieldObject(career, "trades");
                registry.addVillagerEntry(new CustomVanillaVillagerEntry(career.getName(), 0, trades) {
                    @Override
                    public EntityLivingBase getEntity(@Nonnull Minecraft minecraft) throws IllegalAccessException, InvocationTargetException, InstantiationException {
                        return villager;
                    }

                    @Override
                    public String getDisplayName() {
                        return villager.getDisplayName().getFormattedText();
                    }

                    @Override
                    public float getRenderScale() {
                        if(villager instanceof EntityMyrmexQueen) return 20.0f;
                        else if(villager instanceof EntityMyrmexRoyal) return 20.0f;
                        else if(villager instanceof EntityMyrmexSentinel) return 30.0f;
                        else if(villager instanceof EntityMyrmexSoldier) return 36.0f;
                        else return 36.0f;
                    }
                });
            } catch(Exception e) {
                LogHelper.warn(String.format("Failed to register %s villager.", villager.getName()));
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void registerSnowVillagers(CustomVillagerRegistry registry) {
        Map<Integer,VillagerProfession> iafProfessions = IafVillagerRegistry.INSTANCE.professions;

        iafProfessions.forEach((id, profession) -> {
            try {
                EntitySnowVillager snowVillager = new EntitySnowVillager(world);

                //Ice and fire is a butt, so I have to set this manually.
                FieldUtils.writeDeclaredField(snowVillager, "prof", profession, true);

                VillagerCareer career = profession.getCareer(0);
                List<List<EntityVillager.ITradeList>> trades = (List<List<EntityVillager.ITradeList>>) ReflectionHelper.getFieldObject(career, "trades");

                registry.addVillagerEntry(new CustomVanillaVillagerEntry(career.getName(), id, trades) {
                    @Override
                    public EntityLivingBase getEntity(@Nonnull Minecraft minecraft) throws IllegalAccessException, InvocationTargetException, InstantiationException {
                        return snowVillager;
                    }

                    @Override
                    public String getDisplayName() {
                        return snowVillager.getDisplayName().getFormattedText();
                    }
                });
            } catch (Exception e) {
                LogHelper.warn(String.format("Failed to register Snow Villager %s", profession.getRegistryName().toString()));
            }
        });
    }

    private void registerIAFDungeon(ResourceLocation lootTable) {
        JERDungeonStrings dungeon = new JERDungeonStrings(lootTable);
        registerDungeonLoot(dungeon.category, dungeon.unlocName, lootTable);
    }

    public static class JERDungeonStrings {
        public final String category;
        public final String unlocName;

        public JERDungeonStrings(ResourceLocation lootTable) {
            this.category = lootTable.toString();
            this.unlocName = StringHelper.getDungeonTranslationKey(lootTable);
        }
    }
}
