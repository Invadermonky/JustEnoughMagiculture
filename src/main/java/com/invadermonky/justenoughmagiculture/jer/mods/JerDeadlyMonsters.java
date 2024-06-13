package com.invadermonky.justenoughmagiculture.jer.mods;

import com.dmonsters.entity.*;
import com.dmonsters.main.ModConfig;
import com.invadermonky.justenoughmagiculture.config.ConfigHandlerJEM;
import com.invadermonky.justenoughmagiculture.init.JERIntegration;
import com.invadermonky.justenoughmagiculture.jer.AbstractJerIntegration;

public class JerDeadlyMonsters extends AbstractJerIntegration {
    private static JerDeadlyMonsters instance;

    public static JerDeadlyMonsters getInstance() {
        return instance == null ? instance = new JerDeadlyMonsters() : instance;
    }

    @Override
    public void initJerEntities() {
        if(!ConfigHandlerJEM.jer_entities.deadly_monsters)
            return;

        //=================================================================================
        //Deadly Monsters
        if(!ModConfig.babyDisabled) {
            JERIntegration.mobRegistry.register(new EntityBaby(world), EntityBaby.LOOT);
        }
        if(!ModConfig.climberDisabled) {
            JERIntegration.mobRegistry.register(new EntityClimber(world), EntityClimber.LOOT);
        }
        if(!ModConfig.entrailDisabled) {
            JERIntegration.mobRegistry.register(new EntityEntrail(world), EntityEntrail.LOOT);
        }
        if(!ModConfig.freezerDisabled) {
            JERIntegration.mobRegistry.register(new EntityFreezer(world), EntityFreezer.LOOT);
        }
        if(!ModConfig.hauntedCowDisabled) {
            JERIntegration.mobRegistry.register(new EntityHauntedCow(world), EntityHauntedCow.LOOT);
        }
        if(!ModConfig.mutantSteveDisabled) {
            JERIntegration.mobRegistry.register(new EntityMutantSteve(world), EntityMutantSteve.LOOT);
        }
        if(!ModConfig.presentDisabled) {
            JERIntegration.mobRegistry.register(new EntityPresent(world), EntityPresent.LOOT);
        }
        if(!ModConfig.strangerDisabled) {
            JERIntegration.mobRegistry.register(new EntityStranger(world), EntityStranger.LOOT);
        }
        if(!ModConfig.topielecDisabled) {
            JERIntegration.mobRegistry.register(new EntityTopielec(world), EntityTopielec.LOOT);
        }
        if(!ModConfig.fallenLeaderDisabled) {
            JERIntegration.mobRegistry.register(new EntityWideman(world), EntityWideman.LOOT);
        }
        if(!ModConfig.bloodyMaidenDisabled) {
            JERIntegration.mobRegistry.register(new EntityWoman(world), EntityWoman.LOOT);
        }
        if(!ModConfig.zombieChickenDisabled) {
            JERIntegration.mobRegistry.register(new EntityZombieChicken(world), EntityZombieChicken.LOOT);
        }
    }
}
