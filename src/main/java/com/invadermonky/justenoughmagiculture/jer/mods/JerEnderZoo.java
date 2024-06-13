package com.invadermonky.justenoughmagiculture.jer.mods;

import com.invadermonky.justenoughmagiculture.config.ConfigHandlerJEM;
import com.invadermonky.justenoughmagiculture.init.JERIntegration;
import com.invadermonky.justenoughmagiculture.jer.AbstractJerIntegration;
import crazypants.enderzoo.EnderZoo;
import crazypants.enderzoo.config.Config;
import crazypants.enderzoo.entity.*;
import crazypants.enderzoo.potion.BrewingUtil;
import jeresources.api.conditionals.Conditional;
import jeresources.api.drop.LootDrop;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.storage.loot.LootTableList;

public class JerEnderZoo extends AbstractJerIntegration {
    private static JerEnderZoo instance;

    public static JerEnderZoo getInstance() {
        return instance == null ? instance = new JerEnderZoo() : instance;
    }

    @Override
    public void initJerEntities() {
        if(!ConfigHandlerJEM.jer_entities.ender_zoo)
            return;

        //=================================================================================
        //Ender Zoo

        //Concussion Creeper
        if(Config.concussionCreeperEnabled) {
            LootDrop[] drops = new LootDrop[3];
            drops[0] = new LootDrop(new ItemStack(Items.GUNPOWDER), 0, 1, 0.33f, Conditional.affectedByLooting);
            drops[1] = new LootDrop(new ItemStack(EnderZoo.itemConfusingDust), 0, 1, 0.33f, Conditional.affectedByLooting);
            drops[2] = new LootDrop(new ItemStack(EnderZoo.itemEnderFragment), 0, 1, 0.33f, Conditional.affectedByLooting);
            JERIntegration.mobRegistry.register(new EntityConcussionCreeper(world), drops);
        }
        //Dire Slime (Khndrel Knight)
        if(Config.direSlimeEnabled) {
            JERIntegration.mobRegistry.register(new EntityDireSlime(world), new LootDrop(new ItemStack(Items.CLAY_BALL)));
        }
        //Dire Wolf
        if(Config.direWolfEnabled) {
            JERIntegration.mobRegistry.register(new EntityDireWolf(world));
        }
        //Enderminy
        if(Config.enderminyEnabled) {
            LootDrop[] drops = new LootDrop[2];
            drops[0] = new LootDrop(new ItemStack(Items.ENDER_PEARL), 1, 1, Conditional.affectedByLooting);
            drops[1] = new LootDrop(new ItemStack(EnderZoo.itemEnderFragment), 0, 1, 0.5f, Conditional.affectedByLooting);
            JERIntegration.mobRegistry.register(new EntityEnderminy(world), drops);
        }
        //Fallen Knight
        if(Config.fallenKnightEnabled) {
            LootDrop[] drops = new LootDrop[2];
            drops[0] = new LootDrop(new ItemStack(Items.ROTTEN_FLESH), 0, 1, 0.5f, Conditional.affectedByLooting);
            drops[1] = new LootDrop(new ItemStack(Items.BONE), 0, 1, 0.5f, Conditional.affectedByLooting);
            JERIntegration.mobRegistry.register(new EntityFallenKnight(world), drops);
        }
        //Fallen Mount
        if(Config.fallenMountEnabled) {
            JERIntegration.mobRegistry.register(new EntityFallenMount(world), LootTableList.ENTITIES_HORSE);
        }
        //Owl
        if(Config.owlEnabled) {
            JERIntegration.mobRegistry.register(new EntityOwl(world), new LootDrop(new ItemStack(Items.FEATHER), 1, 1, Conditional.affectedByLooting));
        }
        //Wither Cat
        if(Config.witherCatEnabled) {
            JERIntegration.mobRegistry.register(new EntityWitherCat(world));
        }
        //Wither Witch
        if(Config.witherWitchEnabled) {
            LootDrop[] drops = new LootDrop[4];
            drops[0] = new LootDrop(new ItemStack(EnderZoo.itemWitheringDust), 0, 1, 0.43f, Conditional.affectedByLooting);
            drops[1] = new LootDrop(BrewingUtil.createWitherPotion(false, true), 0, 1, 0.29f, Conditional.affectedByLooting);
            drops[2] = new LootDrop(BrewingUtil.createHealthPotion(false, false, true), 0, 1, 0.14f, Conditional.affectedByLooting);
            drops[3] = new LootDrop(BrewingUtil.createRegenerationPotion(false, false, true), 0, 1, 0.14f, Conditional.affectedByLooting);
            JERIntegration.mobRegistry.register(new EntityWitherWitch(world), drops);
        }
    }
}
