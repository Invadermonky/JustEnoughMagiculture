package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigBotania;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import jeresources.api.conditionals.LightLevel;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.common.entity.EntityDoppleganger;

import static com.invadermonky.justenoughmagiculture.util.ModIds.BOTANIA;

public class JERBotania extends JERBase implements IJERIntegration {
    private static final JEMConfigBotania.JER jerConfig = JEMConfig.BOTANIA.JUST_ENOUGH_RESOURCES;

    public JERBotania(boolean enableJERMobs) {
        if(enableJERMobs) registerModEntities();
    }

    @Override
    public void registerModEntities() {
        boolean adjusted = false;
        if(jerConfig.enableGaiaGuardian) {
            EntityDoppleganger gaiaGuardian = new EntityDoppleganger(world);
            gaiaGuardian.setCustomNameTag(I18n.format("entity.botania:doppleganger.name"));
            registerMob(gaiaGuardian, LightLevel.any, new ResourceLocation(BOTANIA.MOD_ID, "gaia_guardian"));
            registerRenderHook(gaiaGuardian.getClass(), ((renderInfo, e) -> {
                GlStateManager.translate(-0.05, -0.4, 0);
                return renderInfo;
            }));
            adjusted = true;
        }

        if(jerConfig.enableGaiaGuardianII) {
            EntityDoppleganger gaiaGuardianII = new EntityDoppleganger(world);
            gaiaGuardianII.setCustomNameTag(I18n.format("entity.botania:doppleganger.name") + " 2");
            registerMob(gaiaGuardianII, LightLevel.any, new ResourceLocation(BOTANIA.MOD_ID, "gaia_guardian_2"));
            if(!adjusted) {
                registerRenderHook(gaiaGuardianII.getClass(), ((renderInfo, e) -> {
                    GlStateManager.translate(-0.05, -0.4, 0);
                    return renderInfo;
                }));
            }
        }

        if(jerConfig.enableFelBlaze) {
            EntityBlaze felBlaze = new EntityBlaze(world);
            felBlaze.setCustomNameTag(I18n.format("entity.jem:botania.fel_blaze.name"));
            registerMob(felBlaze, LightLevel.any, new ResourceLocation(BOTANIA.MOD_ID, "fel_blaze"));
        }
    }
}
