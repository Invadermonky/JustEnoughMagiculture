package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigEBWizardry;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import com.invadermonky.justenoughmagiculture.util.ReflectionHelper;
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
            EntityEvilWizard evilWizard = new EntityEvilWizard(world);
            evilWizard.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(WizardryItems.wizard_hat));
            evilWizard.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(WizardryItems.wizard_robe));
            evilWizard.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(WizardryItems.wizard_leggings));
            evilWizard.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(WizardryItems.wizard_boots));
            evilWizard.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(WizardryItems.magic_wand));
            registerMob(evilWizard, LightLevel.hostile, ReflectionHelper.getLootTable(evilWizard));
            registerRenderHook(evilWizard.getClass(), ((renderInfo, e) -> {
                GlStateManager.translate(-0.05,-0.4,0);
                return renderInfo;
            }));
        }

        if(jerConfig.enableRemnantEarth) {
            EntityRemnant earthRemnant = new EntityRemnant(world);
            earthRemnant.setElement(Element.EARTH);
            registerMob(earthRemnant, LightLevel.hostile, ReflectionHelper.getLootTable(earthRemnant));
        }

        if(jerConfig.enableRemnantFire) {
            EntityRemnant fireRemnant = new EntityRemnant(world);
            fireRemnant.setElement(Element.FIRE);
            registerMob(fireRemnant, LightLevel.hostile, ReflectionHelper.getLootTable(fireRemnant));
        }

        if(jerConfig.enableRemnantHealing) {
            EntityRemnant healingRemnant = new EntityRemnant(world);
            healingRemnant.setElement(Element.HEALING);
            registerMob(healingRemnant, LightLevel.hostile, ReflectionHelper.getLootTable(healingRemnant));
        }

        if(jerConfig.enableRemnantIce) {
            EntityRemnant iceRemanant = new EntityRemnant(world);
            iceRemanant.setElement(Element.ICE);
            registerMob(iceRemanant, LightLevel.hostile, ReflectionHelper.getLootTable(iceRemanant));
        }

        if(jerConfig.enableRemnantLightning) {
            EntityRemnant lightningRemnant = new EntityRemnant(world);
            lightningRemnant.setElement(Element.LIGHTNING);
            registerMob(lightningRemnant, LightLevel.hostile, ReflectionHelper.getLootTable(lightningRemnant));
        }

        if(jerConfig.enableRemnantNecromancy) {
            EntityRemnant necromancyRemnant = new EntityRemnant(world);
            necromancyRemnant.setElement(Element.NECROMANCY);
            registerMob(necromancyRemnant, LightLevel.hostile, ReflectionHelper.getLootTable(necromancyRemnant));
        }

        if(jerConfig.enableRemnantSorcery) {
            EntityRemnant sorceryRemnant = new EntityRemnant(world);
            sorceryRemnant.setElement(Element.SORCERY);
            registerMob(sorceryRemnant, LightLevel.hostile, ReflectionHelper.getLootTable(sorceryRemnant));
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

    private static class JERDungeonStrings {
        public final String category;
        public final String unlocName;
        public final ResourceLocation lootTable;

        public JERDungeonStrings(String name) {
            this.category = ModIds.EB_WIZARDRY.MOD_ID + ":" + name;
            this.unlocName = StringHelper.getDungeonTranslationKey(ModIds.EB_WIZARDRY.MOD_ID, name);
            this.lootTable = new ResourceLocation(ModIds.EB_WIZARDRY.MOD_ID, "chests/" + name);
        }
    }
}
