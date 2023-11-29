package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.github.alexthe666.rats.RatsMod;
import com.github.alexthe666.rats.server.entity.*;
import com.github.alexthe666.rats.server.items.RatsItemRegistry;
import com.github.alexthe666.rats.server.world.RatsWorldRegistry;
import com.github.alexthe666.rats.server.world.village.RatsVillageRegistry;
import com.github.alexthe666.rats.server.world.village.WorldGenPetShop;
import com.github.alexthe666.rats.server.world.village.WorldGenPlagueDoctor;
import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigRats;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.jer.villager.CustomVanillaVillagerEntry;
import com.invadermonky.justenoughmagiculture.integrations.jei.categories.jer.villager.CustomVillagerEntry;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.registry.CustomVillagerRegistry;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import com.invadermonky.justenoughmagiculture.util.LogHelper;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import com.invadermonky.justenoughmagiculture.util.StringHelper;
import jeresources.api.conditionals.LightLevel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.VillagerRegistry.VillagerCareer;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class JERRats extends JERBase implements IJERIntegration {
    private static JERRats instance;
    JEMConfigRats.JER jerConfig = JEMConfig.RATS.JUST_ENOUGH_RESOURCES;

    public JERRats(boolean enableJERDungeons, boolean enableJERMobs) {
        if(enableJERDungeons) registerModDungeons();
        if(enableJERMobs) registerModEntities();
        registerModVillagers();
    }

    @Override
    public void registerModDungeons() {
        registerRatsDungeon("pet_shop", WorldGenPetShop.LOOT);
        registerRatsDungeon("pet_shop_upstairs", WorldGenPetShop.UPSTAIRS_LOOT);
        registerRatsDungeon("plague_doctor_house", WorldGenPlagueDoctor.LOOT);
    }

    @Override
    public void registerModEntities() {
        String[] ratlantisBiome = !RatsMod.CONFIG_OPTIONS.disableRatlantis ? BiomeHelper.getBiomeNamesForBiomes(RatsWorldRegistry.RATLANTIS_BIOME) : new String[] {"None"};

        if(jerConfig.enableBlackDeath) {
            registerMob(new EntityBlackDeath(world), LightLevel.hostile, EntityBlackDeath.LOOT);
            adjustHumanoidRenderHook(EntityBlackDeath.class);
        }

        if(jerConfig.enableFeralRatlantean) {
            registerMob(new EntityFeralRatlantean(world), LightLevel.hostile, ratlantisBiome, EntityFeralRatlantean.LOOT);
        }

        if(jerConfig.enableIllagerPiper) {
            EntityIllagerPiper piper = new EntityIllagerPiper(world);
            piper.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(RatsItemRegistry.RAT_FLUTE));
            registerMob(piper, LightLevel.hostile, EntityIllagerPiper.LOOT);
            adjustHumanoidRenderHook(piper.getClass());
        }

        if(jerConfig.enableRatlanteanAutomation) {
            registerMob(new EntityMarbleCheeseGolem(world), LightLevel.any, EntityMarbleCheeseGolem.LOOT);
            registerRenderHook(EntityMarbleCheeseGolem.class, ((renderInfo, e) -> {
                GlStateManager.translate(-0.05,-1.1,0);
                GlStateManager.scale(0.8,0.8,0.8);
                return renderInfo;
            }));
        }

        if(jerConfig.enableNeoRatlantean) {
            registerMob(new EntityNeoRatlantean(world), LightLevel.any, EntityNeoRatlantean.LOOT);
        }

        if(jerConfig.enablePirat) {
            EntityPirat pirat = new EntityPirat(world);
            pirat.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(RatsItemRegistry.PIRAT_CUTLASS));
            pirat.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(RatsItemRegistry.PIRAT_HAT));
            registerMob(pirat, LightLevel.any, EntityPirat.LOOT);
        }

        if(jerConfig.enablePlagueBeast) {
            registerMob(new EntityPlagueBeast(world), LightLevel.any, EntityPlagueBeast.LOOT);
        }

        if(jerConfig.enablePlagueDoctor) {
            registerMob(new EntityPlagueDoctor(world), LightLevel.any, EntityPlagueDoctor.LOOT);
            adjustHumanoidRenderHook(EntityPlagueDoctor.class);
        }

        if(jerConfig.enableRat) {
            EntityRat rat = new EntityRat(world);
            registerMob(rat, LightLevel.any, EntityRat.LOOT);
        }

        if(jerConfig.enableRatlanteanSpirit) {
            registerMob(new EntityRatlanteanSpirit(world), LightLevel.hostile, ratlantisBiome,EntityRatlanteanSpirit.LOOT);
        }
    }

    @Override
    public void registerModVillagers() {
        if(JEMConfig.RATS.fixJERVillagers) {
            registerPetShopOwner();
            registerPlagueDoctorVillager();
        }
    }

    private void registerPetShopOwner() {
        try {
            EntityVillager villager = new EntityVillager(world);
            villager.setProfession(RatsVillageRegistry.PET_SHOP_OWNER);
            villager.getProfession();

            VillagerCareer career = RatsVillageRegistry.PET_SHOP_OWNER.getCareer(0);
            Field tradesField = career.getClass().getDeclaredField("trades");
            tradesField.setAccessible(true);
            List<List<EntityVillager.ITradeList>> trades = (List<List<EntityVillager.ITradeList>>) tradesField.get(career);

            CustomVillagerRegistry.getInstance().addVillagerEntry(new CustomVanillaVillagerEntry(RatsVillageRegistry.PET_SHOP_OWNER.getRegistryName().toString(), 0, trades) {
                @Override
                public EntityLivingBase getEntity(@Nonnull Minecraft minecraft) throws IllegalAccessException, InvocationTargetException, InstantiationException {
                    return villager;
                }
            });
        } catch (Exception e) {
            LogHelper.warn("Failed to register Pet Shop Owner villager.");
            e.printStackTrace();
        }
    }

    private void registerPlagueDoctorVillager() {
        try {
            VillagerCareer career = RatsVillageRegistry.PLAGUE_DOCTOR.getCareer(0);
            Field tradesField = career.getClass().getDeclaredField("trades");
            tradesField.setAccessible(true);
            List<List<EntityVillager.ITradeList>> trades = (List<List<EntityVillager.ITradeList>>) tradesField.get(career);

            CustomVillagerRegistry.getInstance().addVillagerEntry(new CustomVillagerEntry(RatsVillageRegistry.PLAGUE_DOCTOR.getRegistryName().toString(), 0, trades) {
                @Override
                public EntityLivingBase getEntity(@Nonnull Minecraft minecraft) throws IllegalAccessException, InvocationTargetException, InstantiationException {
                    return new EntityPlagueDoctor(world);
                }
            });
        } catch (Exception e) {
            LogHelper.warn("Failed to register Plague Doctor villager.");
            e.printStackTrace();
        }
    }

    private void adjustHumanoidRenderHook(Class<? extends EntityLiving> clazz) {
        registerRenderHook(clazz, ((renderInfo, e) -> {
            GlStateManager.translate(-0.05,-0.45,0);
            return renderInfo;
        }));
    }

    private void registerRatsDungeon(String name, ResourceLocation lootTable) {
        JERDungeonStrings dungeon = new JERDungeonStrings(name);
        registerDungeonLoot(dungeon.category, dungeon.unlocName, lootTable);
    }

    private static class JERDungeonStrings {
        public final String category;
        public final String unlocName;

        public JERDungeonStrings(String name) {
            this.category = ModIds.RATS.MOD_ID + ":" + name;
            this.unlocName = StringHelper.getDungeonTranslationKey(ModIds.RATS.MOD_ID, name);
        }
    }
}
