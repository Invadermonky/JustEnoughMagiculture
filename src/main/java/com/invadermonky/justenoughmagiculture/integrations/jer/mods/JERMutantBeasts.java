package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import chumbanotz.mutantbeasts.entity.CreeperMinionEntity;
import chumbanotz.mutantbeasts.entity.EndersoulCloneEntity;
import chumbanotz.mutantbeasts.entity.mutant.*;
import chumbanotz.mutantbeasts.util.EntityUtil;
import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigMutantBeasts;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import jeresources.api.conditionals.LightLevel;
import net.minecraft.client.renderer.GlStateManager;

public class JERMutantBeasts extends JERBase implements IJERIntegration {
    JEMConfigMutantBeasts.JER jerConfig = JEMConfig.MUTANT_BEASTS.JUST_ENOUGH_RESOURCES;

    public JERMutantBeasts(boolean enableJERMobs) {
        if(enableJERMobs) registerModEntities();
    }

    @Override
    public void registerModEntities() {
        if(jerConfig.enableCreeperMinion) {
            CreeperMinionEntity creeperMinion = new CreeperMinionEntity(world);
            registerMob(creeperMinion, LightLevel.hostile, EntityUtil.getLootTable(creeperMinion));
        }

        if(jerConfig.enableEndersoulClone) {
            EndersoulCloneEntity endersoulClone = new EndersoulCloneEntity(world);
            registerMob(endersoulClone, LightLevel.hostile, EntityUtil.getLootTable(endersoulClone));
            registerRenderHook(endersoulClone.getClass(), ((renderInfo, e) -> {
                GlStateManager.translate(-0.05,-0.5,0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableMutantCreeper) {
            MutantCreeperEntity mutantCreeper = new MutantCreeperEntity(world);
            registerMob(mutantCreeper, LightLevel.hostile, EntityUtil.getLootTable(mutantCreeper));
            registerRenderHook(mutantCreeper.getClass(), ((renderInfo, e) -> {
                GlStateManager.scale(0.9,0.9,0.9);
                return renderInfo;
            }));
        }

        if(jerConfig.enableMutantEnderman) {
            MutantEndermanEntity mutantEnderman = new MutantEndermanEntity(world);
            registerMob(mutantEnderman, LightLevel.hostile, EntityUtil.getLootTable(mutantEnderman));
            registerRenderHook(mutantEnderman.getClass(), ((renderInfo, e) -> {
                GlStateManager.scale(1.6,1.6,1.6);
                GlStateManager.translate(-0.1,-0.9,0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableMutantSkeleton) {
            MutantSkeletonEntity mutantSkeleton = new MutantSkeletonEntity(world);
            registerMob(mutantSkeleton, LightLevel.hostile, EntityUtil.getLootTable(mutantSkeleton));
            registerRenderHook(mutantSkeleton.getClass(), ((renderInfo, e) -> {
                GlStateManager.translate(-0.1,-0.8,0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableMutantSnowGolem) {
            MutantSnowGolemEntity mutantSnowGolem = new MutantSnowGolemEntity(world);
            registerMob(mutantSnowGolem, LightLevel.hostile, EntityUtil.getLootTable(mutantSnowGolem));
            registerRenderHook(mutantSnowGolem.getClass(), ((renderInfo, e) -> {
                GlStateManager.scale(1.1,1.1,1.1);
                return renderInfo;
            }));
        }

        if(jerConfig.enableMutantZombie) {
            MutantZombieEntity mutantZombie = new MutantZombieEntity(world);
            registerMob(mutantZombie, LightLevel.hostile, EntityUtil.getLootTable(mutantZombie));
            registerRenderHook(mutantZombie.getClass(), ((renderInfo, e) -> {
                GlStateManager.translate(-0.1,-0.8,0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableSpiderPig) {
            SpiderPigEntity spiderPig = new SpiderPigEntity(world);
            registerMob(spiderPig, LightLevel.hostile, EntityUtil.getLootTable(spiderPig));
            registerRenderHook(spiderPig.getClass(), ((renderInfo, e) -> {
                GlStateManager.translate(-0,0.2,0);
                return renderInfo;
            }));
        }
    }
}
