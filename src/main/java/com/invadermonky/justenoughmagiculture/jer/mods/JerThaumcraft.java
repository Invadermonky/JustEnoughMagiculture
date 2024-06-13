package com.invadermonky.justenoughmagiculture.jer.mods;

import com.invadermonky.justenoughmagiculture.config.ConfigHandlerJEM;
import com.invadermonky.justenoughmagiculture.init.JERIntegration;
import com.invadermonky.justenoughmagiculture.jer.AbstractJerIntegration;
import jeresources.api.conditionals.Conditional;
import jeresources.api.drop.LootDrop;
import jeresources.util.LootTableHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.storage.loot.LootTableList;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.monster.*;
import thaumcraft.common.entities.monster.boss.EntityCultistLeader;
import thaumcraft.common.entities.monster.boss.EntityCultistPortalGreater;
import thaumcraft.common.entities.monster.boss.EntityEldritchGolem;
import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;
import thaumcraft.common.entities.monster.cult.EntityCultist;
import thaumcraft.common.entities.monster.cult.EntityCultistCleric;
import thaumcraft.common.entities.monster.cult.EntityCultistKnight;
import thaumcraft.common.entities.monster.tainted.*;

import java.util.ArrayList;
import java.util.List;

public class JerThaumcraft extends AbstractJerIntegration {
    private static JerThaumcraft instance;

    public static JerThaumcraft getInstance() {
        return instance == null ? instance = new JerThaumcraft() : instance;
    }

    @Override
    public void initJerEntities() {
        if(!ConfigHandlerJEM.jer_entities.thaumcraft)
            return;

        List<LootDrop> drops = new ArrayList<>();

        //=================================================================================
        //Bosses
        //=================================================================================
        LootDrop pearlDrop = new LootDrop(new ItemStack(ItemsTC.primordialPearl));
        LootDrop bagDrop = new LootDrop(new ItemStack(ItemsTC.lootBag, 1, 2));

        //Cultist Leader
        EntityCultistLeader cultistLeader = new EntityCultistLeader(world);
        cultistLeader.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemsTC.crimsonPraetorHelm));
        cultistLeader.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemsTC.crimsonPraetorChest));
        cultistLeader.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ItemsTC.crimsonPraetorLegs));
        cultistLeader.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ItemsTC.crimsonBoots));
        cultistLeader.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ItemsTC.crimsonBlade));

        ArrayList<LootDrop> leaderDrops = new ArrayList<>();
        leaderDrops.add(bagDrop);
        leaderDrops.add(new LootDrop(new ItemStack(ItemsTC.crimsonPraetorHelm), 0, 1, 0.05f, Conditional.affectedByLooting));
        leaderDrops.add(new LootDrop(new ItemStack(ItemsTC.crimsonPraetorChest), 0, 1, 0.05f, Conditional.affectedByLooting));
        leaderDrops.add(new LootDrop(new ItemStack(ItemsTC.crimsonPraetorLegs), 0, 1, 0.05f, Conditional.affectedByLooting));
        leaderDrops.add(new LootDrop(new ItemStack(ItemsTC.crimsonBoots), 0, 1, 0.05f, Conditional.affectedByLooting));
        leaderDrops.add(new LootDrop(new ItemStack(ItemsTC.voidSword), 0, 1, 0.05f,
                Conditional.affectedByLooting, new Conditional(I18n.format(Conditional.gameDifficulty.toString(), EnumDifficulty.EASY.toString()))));
        leaderDrops.add(new LootDrop(new ItemStack(ItemsTC.crimsonBlade), 0, 1, 0.05f,
                Conditional.affectedByLooting, new Conditional(I18n.format(Conditional.notGameDifficulty.toString(), EnumDifficulty.EASY.toString()))));

        JERIntegration.mobRegistry.register(cultistLeader, leaderDrops.toArray(new LootDrop[0]));

        //Greater Crimson Portal
        JERIntegration.mobRegistry.register(new EntityCultistPortalGreater(world), pearlDrop);

        //Eldritch Golem
        JERIntegration.mobRegistry.register(new EntityEldritchGolem(world), pearlDrop, bagDrop);

        //Eldritch Warden
        JERIntegration.mobRegistry.register(new EntityEldritchWarden(world), pearlDrop, bagDrop);


        //=================================================================================
        //Cultists
        //=================================================================================
        //Cleric
        EntityCultistCleric cleric = new EntityCultistCleric(world);
        cleric.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemsTC.crimsonRobeHelm));
        cleric.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemsTC.crimsonRobeChest));
        cleric.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ItemsTC.crimsonRobeLegs));
        cleric.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ItemsTC.crimsonBoots));

        drops.addAll(LootTableHelper.toDrops(world, EntityCultist.LOOT));
        drops.add(new LootDrop(new ItemStack(ItemsTC.crimsonRobeHelm), 0, 1, 0.05f, Conditional.affectedByLooting));
        drops.add(new LootDrop(new ItemStack(ItemsTC.crimsonRobeChest), 0, 1, 0.05f, Conditional.affectedByLooting));
        drops.add(new LootDrop(new ItemStack(ItemsTC.crimsonRobeLegs), 0, 1, 0.05f, Conditional.affectedByLooting));
        drops.add(new LootDrop(new ItemStack(ItemsTC.crimsonBoots), 0, 1, 0.015f, Conditional.affectedByLooting));
        JERIntegration.mobRegistry.register(cleric, drops.toArray(new LootDrop[0]));
        drops.clear();

        //Knight
        EntityCultistKnight knight = new EntityCultistKnight(world);
        knight.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemsTC.crimsonPlateHelm));
        knight.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemsTC.crimsonPlateChest));
        knight.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ItemsTC.crimsonPlateLegs));
        knight.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ItemsTC.crimsonBoots));
        knight.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(ItemsTC.voidSword));

        drops.addAll(LootTableHelper.toDrops(world, EntityCultist.LOOT));
        drops.add(new LootDrop(new ItemStack(ItemsTC.crimsonPlateHelm), 0, 1, 0.05f, Conditional.affectedByLooting));
        drops.add(new LootDrop(new ItemStack(ItemsTC.crimsonPlateChest), 0, 1, 0.05f, Conditional.affectedByLooting));
        drops.add(new LootDrop(new ItemStack(ItemsTC.crimsonPlateLegs), 0, 1, 0.05f, Conditional.affectedByLooting));
        drops.add(new LootDrop(new ItemStack(ItemsTC.crimsonBoots), 0, 1, 0.025f, Conditional.affectedByLooting));
        drops.add(new LootDrop(new ItemStack(ItemsTC.voidSword), 0, 1, 0.005f, Conditional.affectedByLooting));
        drops.add(new LootDrop(new ItemStack(ItemsTC.thaumiumSword), 0, 1, 0.02f, Conditional.affectedByLooting));
        JERIntegration.mobRegistry.register(knight, drops.toArray(new LootDrop[0]));
        drops.clear();

        //=================================================================================
        //Monsters
        //=================================================================================
        //Angry Zombie
        drops.addAll(LootTableHelper.toDrops(world, LootTableList.ENTITIES_ZOMBIE));
        drops.add(new LootDrop(new ItemStack(ItemsTC.brain), 0, 1, 0.5f, Conditional.affectedByLooting));
        JERIntegration.mobRegistry.register(new EntityBrainyZombie(world), drops.toArray(new LootDrop[0]));
        drops.clear();

        //Eldritch Crab
        JERIntegration.mobRegistry.register(new EntityEldritchCrab(world), new LootDrop(new ItemStack(Items.ENDER_PEARL), 1, 1, (1f/3f), Conditional.affectedByLooting));

        //Fire Bat
        JERIntegration.mobRegistry.register(new EntityFireBat(world), new LootDrop(Items.GUNPOWDER, 1, 1, Conditional.affectedByLooting));

        //Giant Angry Zombie
        drops.addAll(LootTableHelper.toDrops(world, LootTableList.ENTITIES_ZOMBIE));
        drops.add(new LootDrop(new ItemStack(ItemsTC.brain), 0, 1, 0.5f, Conditional.affectedByLooting));
        JERIntegration.mobRegistry.register(new EntityGiantBrainyZombie(world), drops.toArray(new LootDrop[0]));
        drops.clear();

        //Pech
        JERIntegration.mobRegistry.register(new EntityPech(world), EntityPech.LOOT);

        //Thaumic Slime
        drops.addAll(LootTableHelper.toDrops(world, LootTableList.ENTITIES_SLIME));
        drops.add(new LootDrop(ConfigItems.FLUX_CRYSTAL.copy()));
        JERIntegration.mobRegistry.register(new EntityThaumicSlime(world), drops.toArray(new LootDrop[0]));
        drops.clear();

        //Wisps
        ArrayList<Aspect> primals = Aspect.getPrimalAspects();
        float chance = 1.0f / (float) primals.size();
        for(Aspect aspect : primals) {
            drops.add(new LootDrop(ThaumcraftApiHelper.makeCrystal(aspect), 1, 1, chance));
        }
        JERIntegration.mobRegistry.register(new EntityWisp(world), drops.toArray(new LootDrop[0]));
        drops.clear();

        //=================================================================================
        //Tainted Mobs
        //=================================================================================
        ArrayList<LootDrop> taintDrops = new ArrayList<>(2);
        taintDrops.add(new LootDrop(ConfigItems.FLUX_CRYSTAL.copy()));

        JERIntegration.mobRegistry.register(new EntityTaintacle(world), taintDrops.toArray(new LootDrop[0]));
        JERIntegration.mobRegistry.register(new EntityTaintacleSmall(world), taintDrops.toArray(new LootDrop[0]));
        JERIntegration.mobRegistry.register(new EntityTaintCrawler(world), new LootDrop(ConfigItems.FLUX_CRYSTAL.copy()));
        JERIntegration.mobRegistry.register(new EntityTaintSeed(world), taintDrops.toArray(new LootDrop[0]));
        JERIntegration.mobRegistry.register(new EntityTaintSeedPrime(world), taintDrops.toArray(new LootDrop[0]));
        JERIntegration.mobRegistry.register(new EntityTaintSwarm(world), taintDrops.toArray(new LootDrop[0]));
    }
}
