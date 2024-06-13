package com.invadermonky.justenoughmagiculture.jer.mods;

import com.invadermonky.justenoughmagiculture.config.ConfigHandlerJEM;
import com.invadermonky.justenoughmagiculture.init.JERIntegration;
import com.invadermonky.justenoughmagiculture.jer.AbstractJerIntegration;
import com.superdextor.adinferos.entity.monster.*;
import com.superdextor.adinferos.entity.other.EntityInfernalChicken;
import com.superdextor.adinferos.entity.type.CurseType;
import com.superdextor.adinferos.init.NetherItems;
import jeresources.api.conditionals.Conditional;
import jeresources.api.drop.LootDrop;
import jeresources.api.drop.PlantDrop;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IPlantable;

import java.util.ArrayList;
import java.util.List;

public class JerAdInferos extends AbstractJerIntegration {
    private static JerAdInferos instance;

    public static JerAdInferos getInstance() {
        return instance == null ? instance = new JerAdInferos() : instance;
    }

    @Override
    public void initJerDungeons() {
        //There are dungeons but I'm lazy.
    }

    @Override
    public void initJerEntities() {
        if(!ConfigHandlerJEM.jer_entities.ad_inferos)
            return;

        //=================================================================================
        //Ad Infernos
        List<LootDrop> drops = new ArrayList<>();

        //Black Widow
        drops.add(new LootDrop(NetherItems.INFERNAL_STRING, 1, 5, Conditional.affectedByLooting));
        drops.add(new LootDrop(Items.SPIDER_EYE, 1, 1));
        JERIntegration.mobRegistry.register(new EntityBlackWidow(world), drops.toArray(new LootDrop[0]));
        drops.clear();

        //Curse (Flame)
        EntityCurse curseFlame = new EntityCurse(world);
        curseFlame.setType(CurseType.FLAME);
        JERIntegration.mobRegistry.register(curseFlame, new LootDrop(Items.REDSTONE, 0, 1, Conditional.affectedByLooting));

        //Curse (Decay)
        EntityCurse curseDecay = new EntityCurse(world);
        curseDecay.setType(CurseType.DECAY);
        JERIntegration.mobRegistry.register(curseDecay, new LootDrop(NetherItems.WITHER_DUST, 0, 1, Conditional.affectedByLooting));

        //Curse (Venom)
        EntityCurse curseVenom = new EntityCurse(world);
        curseVenom.setType(CurseType.VENOM);
        JERIntegration.mobRegistry.register(curseVenom, new LootDrop(Items.SPIDER_EYE, 0, 1, Conditional.affectedByLooting));

        //Curse (Nausea)
        EntityCurse curseDefault = new EntityCurse(world);
        curseDefault.setType(CurseType.FLAME);
        JERIntegration.mobRegistry.register(curseDefault, new LootDrop(Items.GUNPOWDER, 0, 1, Conditional.affectedByLooting));

        //Ghost
        JERIntegration.mobRegistry.register(new EntityGhost(world), new LootDrop(NetherItems.SOUL_FRAGMENT, 0, 1));

        //Glowstone Skeleton (Dimstone)
        EntityGlowstoneSkeleton dimstoneSkeleton = new EntityGlowstoneSkeleton(world);
        dimstoneSkeleton.setDimstoneSkeleton(true);
        drops.add(new LootDrop(Items.BONE, 0, 1, Conditional.affectedByLooting));
        drops.add(new LootDrop(NetherItems.DIMSTONE_DUST, 0, 1, Conditional.affectedByLooting));
        JERIntegration.mobRegistry.register(dimstoneSkeleton, drops.toArray(new LootDrop[0]));
        drops.clear();

        //Glowstone Skeleton (Glowstone)
        EntityGlowstoneSkeleton glowstoneSkeleton = new EntityGlowstoneSkeleton(world);
        glowstoneSkeleton.setDimstoneSkeleton(false);
        drops.add(new LootDrop(Items.BONE, 0, 1, Conditional.affectedByLooting));
        drops.add(new LootDrop(Items.GLOWSTONE_DUST, 0, 1, Conditional.affectedByLooting));
        JERIntegration.mobRegistry.register(glowstoneSkeleton, drops.toArray(new LootDrop[0]));
        drops.clear();

        //Infernal chicken
        drops.add(new LootDrop(NetherItems.INFERNAL_FEATHER, 0, 1, Conditional.affectedByLooting));
        drops.add(new LootDrop(NetherItems.INFERNAL_CHICKEN, 1f));
        JERIntegration.mobRegistry.register(new EntityInfernalChicken(world), drops.toArray(new LootDrop[0]));
        drops.clear();

        //Infernium Avis
        drops.add(new LootDrop(NetherItems.COOKED_FLESH, 8, 16, Conditional.affectedByLooting));
        drops.add(new LootDrop(NetherItems.WITHER_DUST, 8, 16, Conditional.affectedByLooting));
        drops.add(new LootDrop(Item.getItemFromBlock(Blocks.GOLD_BLOCK), 2, 4));
        drops.add(new LootDrop(NetherItems.AVIS_WING, 2, 2));
        JERIntegration.mobRegistry.register(new EntityInfernumAvis(world), drops.toArray(new LootDrop[0]));
        drops.clear();

        //Obsidian Sheepman
        drops.add(new LootDrop(Items.ROTTEN_FLESH, 0, 1, Conditional.affectedByLooting));
        drops.add(new LootDrop(NetherItems.OBSIDIAN_NUGGET, 1, 3, Conditional.affectedByLooting));
        JERIntegration.mobRegistry.register(new EntityObsidianSheepman(world), drops.toArray(new LootDrop[0]));
        drops.clear();

        //Phantom
        JERIntegration.mobRegistry.register(new EntityPhantom(world), new LootDrop(NetherItems.ECTOPLASM, 0,1,Conditional.affectedByLooting));

        //Reaper
        EntityReaper reaper = new EntityReaper(world);
        reaper.setCloaking(false);
        reaper.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(NetherItems.SCYTHE));
        drops.add(new LootDrop(Items.ENDER_PEARL, 0, 1, Conditional.affectedByLooting));
        drops.add(new LootDrop(new ItemStack(NetherItems.GOLDEN_BUCKET_BLOOD, 1, 1)));
        drops.add(new LootDrop(NetherItems.NETHERITE_NUGGET, 3, 7, Conditional.affectedByLooting));
        drops.add(new LootDrop(NetherItems.WITHER_DUST, 4, 10, Conditional.affectedByLooting));
        JERIntegration.mobRegistry.register(reaper, drops.toArray(new LootDrop[0]));
        drops.clear();

        //Skeleton Horse (Normal)
        EntitySkeletonHorse skeletonHorse = new EntitySkeletonHorse(world);
        skeletonHorse.setWither(false);
        JERIntegration.mobRegistry.register(skeletonHorse, new LootDrop(Items.BONE, 0, 2, Conditional.affectedByLooting));

        //Skeleton Horse (Wither)
        EntitySkeletonHorse witherHorse = new EntitySkeletonHorse(world);
        witherHorse.setWither(true);
        JERIntegration.mobRegistry.register(witherHorse, new LootDrop(NetherItems.WITHER_DUST, 0, 2, Conditional.affectedByLooting));
    }

    @Override
    public void initJerPlants() {
        if(!ConfigHandlerJEM.jer_crops.ad_inferos)
            return;

        JERIntegration.plantRegistry.register((Item & IPlantable) NetherItems.INFERNAL_SEEDS,
                new PlantDrop(new ItemStack(NetherItems.INFERNAL_WHEAT), 1, 1),
                new PlantDrop(new ItemStack(NetherItems.INFERNAL_SEEDS), 0, 2));
    }
}
