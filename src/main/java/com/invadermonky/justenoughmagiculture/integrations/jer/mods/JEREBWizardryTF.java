package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import electroblob.tfspellpack.entity.living.EntityDruidMage;
import jeresources.api.conditionals.LightLevel;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import twilightforest.biomes.RegistryBiomeEvent;

public class JEREBWizardryTF extends JERBase implements IJERIntegration {
    public JEREBWizardryTF(boolean enableJERMobs) {
        if(enableJERMobs) registerModEntities();
    }

    @Override
    public void registerModEntities() {
        if(JEMConfig.EB_WIZARDRY_TF.JUST_ENOUGH_RESOURCES.enableDruidMage) {
            registerMob(new EntityDruidMage(world), LightLevel.hostile, BiomeHelper.getBiomeNamesForTypes(RegistryBiomeEvent.TWILIGHT), new ResourceLocation(ModIds.EB_WIZARDRY_TF.MOD_ID, "entities/druid_mage"));
            registerRenderHook(EntityDruidMage.class, ((renderInfo, e) -> {
                GlStateManager.translate(-0.05,-0.4,0);
                return renderInfo;
            }));
        }
    }
}
