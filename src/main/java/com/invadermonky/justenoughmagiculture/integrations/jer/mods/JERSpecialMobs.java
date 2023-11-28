package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.google.common.collect.Sets;
import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.util.LogHelper;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import fathertoast.specialmobs.bestiary.EnumMobFamily;
import gnu.trove.set.hash.THashSet;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.util.LootTableHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class JERSpecialMobs extends JERBase implements IJERIntegration {
    public JERSpecialMobs(boolean enableJERMobs) {
        if(enableJERMobs) registerModEntities();
    }

    @Override
    public void registerModEntities() {
        THashSet<String> disabledMobs = new THashSet<>(Sets.newHashSet(JEMConfig.SPECIAL_MOBS.JUST_ENOUGH_RESOURCES.disableMobs));

        for(EnumMobFamily mobFamily : EnumMobFamily.values()) {
            for(EnumMobFamily.Species variant : mobFamily.variants) {
                try {
                    if(!disabledMobs.contains(variant.unlocalizedName)) {
                        EntityLiving entity = variant.constructor.newInstance(world);
                        ResourceLocation lootTable = new ResourceLocation(ModIds.SPECIALMOBS.MOD_ID, "entities/" + variant.family.name.toLowerCase() + "/" + variant.name.toLowerCase());
                        List<LootDrop> drops = LootTableHelper.toDrops(world, lootTable);
                        if (variant.bestiaryInfo.weight > 0) {
                            registerMob(entity, LightLevel.hostile, drops.toArray(new LootDrop[0]));
                        }

                        if(variant.name.equals("Mini")) {
                            registerRenderHook(variant.variantClass, ((renderInfo, e) -> {
                                GlStateManager.scale(0.4,0.4,0.4);
                                return renderInfo;
                            }));
                        } else if(variant.name.equals("Giant")) {
                            registerRenderHook(variant.variantClass, ((renderInfo, e) -> {
                                GlStateManager.scale(1.6,1.6,1.6);
                                return renderInfo;
                            }));
                        } else if(variant.unlocalizedName.equals("FighterGhast") || variant.unlocalizedName.equals("UnholyGhast")) {
                            registerRenderHook(variant.variantClass, ((renderInfo, e) -> {
                                GlStateManager.scale(0.4,0.4,0.4);
                                return renderInfo;
                            }));
                        }
                    }
                } catch (Exception e) {
                    LogHelper.warn(String.format("Failed to register %s %s.", variant.family, variant.name));
                    e.printStackTrace();
                }
            }
        }
    }
}
