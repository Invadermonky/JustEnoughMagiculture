package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigEBWizardry;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.util.LogHelper;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import com.invadermonky.justenoughmagiculture.util.StringHelper;
import electroblob.wizardry.constants.Element;
import electroblob.wizardry.entity.living.EntityEvilWizard;
import electroblob.wizardry.entity.living.EntityRemnant;
import electroblob.wizardry.registry.WizardryItems;
import jeresources.api.conditionals.LightLevel;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.lang.reflect.Method;

public class JEREBWizardry extends JERBase implements IJERIntegration {
    JEMConfigEBWizardry.JER jerConfig = JEMConfig.EB_WIZARDRY.JUST_ENOUGH_RESOURCES;

    public JEREBWizardry(boolean enableJERDungeons, boolean enableJERMobs) {
        if(enableJERDungeons) registerModDungeons();
        if(enableJERMobs) registerModEntities();
    }

    @Override
    public void registerModDungeons() {
        registerEBDungeon("library_ruins_bookshelf");
        registerEBDungeon("obelisk");
        registerEBDungeon("shrine");
        registerEBDungeon("wizard_tower");
    }

    @Override
    public void registerModEntities() {
        if(jerConfig.enableEvilWizard) {
            try {
                EntityEvilWizard evilWizard = new EntityEvilWizard(world);
                evilWizard.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(WizardryItems.wizard_hat));
                evilWizard.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(WizardryItems.wizard_robe));
                evilWizard.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(WizardryItems.wizard_leggings));
                evilWizard.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(WizardryItems.wizard_boots));
                evilWizard.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(WizardryItems.magic_wand));
                Method getLootMethod = evilWizard.getClass().getDeclaredMethod("getLootTable");
                getLootMethod.setAccessible(true);
                registerMob(evilWizard, LightLevel.hostile, (ResourceLocation) getLootMethod.invoke(evilWizard));
                registerRenderHook(evilWizard.getClass(), ((renderInfo, e) -> {
                    GlStateManager.translate(-0.05,-0.4,0);
                    return renderInfo;
                }));
            } catch(Exception e) {
                LogHelper.warn("Failed to register Evil Wizard.");
                e.printStackTrace();
            }
        }

        if(jerConfig.enableRemnantEarth) {
            try {
                EntityRemnant earthRemnant = new EntityRemnant(world);
                earthRemnant.setElement(Element.EARTH);
                Method getLootMethod = earthRemnant.getClass().getDeclaredMethod("getLootTable");
                getLootMethod.setAccessible(true);
                registerMob(earthRemnant, LightLevel.hostile, (ResourceLocation) getLootMethod.invoke(earthRemnant));
            } catch(Exception e) {
                LogHelper.warn("Failed to register Earth Remnant.");
                e.printStackTrace();
            }
        }

        if(jerConfig.enableRemnantFire) {
            try {
                EntityRemnant fireRemnant = new EntityRemnant(world);
                fireRemnant.setElement(Element.FIRE);
                Method getLootMethod = fireRemnant.getClass().getDeclaredMethod("getLootTable");
                getLootMethod.setAccessible(true);
                registerMob(fireRemnant, LightLevel.hostile, (ResourceLocation) getLootMethod.invoke(fireRemnant));
            } catch(Exception e) {
                LogHelper.warn("Failed to register Fire Remnant.");
                e.printStackTrace();
            }
        }

        if(jerConfig.enableRemnantHealing) {
            try {
                EntityRemnant healingRemnant = new EntityRemnant(world);
                healingRemnant.setElement(Element.HEALING);
                Method getLootMethod = healingRemnant.getClass().getDeclaredMethod("getLootTable");
                getLootMethod.setAccessible(true);
                registerMob(healingRemnant, LightLevel.hostile, (ResourceLocation) getLootMethod.invoke(healingRemnant));
            } catch(Exception e) {
                LogHelper.warn("Failed to register Healing Remnant.");
                e.printStackTrace();
            }
        }

        if(jerConfig.enableRemnantIce) {
            try {
                EntityRemnant iceRemanant = new EntityRemnant(world);
                iceRemanant.setElement(Element.ICE);
                Method getLootMethod = iceRemanant.getClass().getDeclaredMethod("getLootTable");
                getLootMethod.setAccessible(true);
                registerMob(iceRemanant, LightLevel.hostile, (ResourceLocation) getLootMethod.invoke(iceRemanant));
            } catch(Exception e) {
                LogHelper.warn("Failed to register Ice Remnant.");
                e.printStackTrace();
            }
        }

        if(jerConfig.enableRemnantLightning) {
            try {
                EntityRemnant lightningRemnant = new EntityRemnant(world);
                lightningRemnant.setElement(Element.LIGHTNING);
                Method getLootMethod = lightningRemnant.getClass().getDeclaredMethod("getLootTable");
                getLootMethod.setAccessible(true);
                registerMob(lightningRemnant, LightLevel.hostile, (ResourceLocation) getLootMethod.invoke(lightningRemnant));
            } catch(Exception e) {
                LogHelper.warn("Failed to register Lightning Remnant.");
                e.printStackTrace();
            }
        }

        if(jerConfig.enableRemnantNecromancy) {
            try {
                EntityRemnant necromancyRemnant = new EntityRemnant(world);
                necromancyRemnant.setElement(Element.NECROMANCY);
                Method getLootMethod = necromancyRemnant.getClass().getDeclaredMethod("getLootTable");
                getLootMethod.setAccessible(true);
                registerMob(necromancyRemnant, LightLevel.hostile, (ResourceLocation) getLootMethod.invoke(necromancyRemnant));
            } catch(Exception e) {
                LogHelper.warn("Failed to register Necromancy Remnant.");
                e.printStackTrace();
            }
        }

        if(jerConfig.enableRemnantSorcery) {
            try {
                EntityRemnant sorceryRemnant = new EntityRemnant(world);
                sorceryRemnant.setElement(Element.SORCERY);
                Method getLootMethod = sorceryRemnant.getClass().getDeclaredMethod("getLootTable");
                getLootMethod.setAccessible(true);
                registerMob(sorceryRemnant, LightLevel.hostile, (ResourceLocation) getLootMethod.invoke(sorceryRemnant));
            } catch(Exception e) {
                LogHelper.warn("Failed to register Sorcery Remnant.");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void registerModVillagers() {
        //TODO: Everything is generated dynamically. I may come back to this later.
    }

    private void registerEBDungeon(String name) {
        JERDungeonStrings dungeon = new JERDungeonStrings(name);
        registerDungeonLoot(dungeon.category, dungeon.unlocName, dungeon.lootTable);
    }

    private class JERDungeonStrings {
        public final String category;
        public final String unlocName;
        public final ResourceLocation lootTable;

        public JERDungeonStrings(String name) {
            this.category = ModIds.EBWIZARDRY.MOD_ID + ":" + name;
            this.unlocName = StringHelper.getDungeonTranslationKey(ModIds.EBWIZARDRY.MOD_ID, name);
            this.lootTable = new ResourceLocation(ModIds.EBWIZARDRY.MOD_ID, "chests/" + name);
        }
    }
}
