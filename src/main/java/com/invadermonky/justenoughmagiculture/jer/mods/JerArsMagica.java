package com.invadermonky.justenoughmagiculture.jer.mods;

import am2.api.ArsMagicaAPI;
import am2.api.affinity.Affinity;
import am2.bosses.*;
import am2.defs.ItemDefs;
import am2.defs.LootTablesArsMagica;
import am2.entity.*;
import com.invadermonky.justenoughmagiculture.config.ConfigHandlerJEM;
import com.invadermonky.justenoughmagiculture.init.JERIntegration;
import com.invadermonky.justenoughmagiculture.jer.AbstractJerIntegration;
import com.invadermonky.justenoughmagiculture.util.DungeonHelper;
import jeresources.api.conditionals.Conditional;
import jeresources.api.drop.LootDrop;
import jeresources.util.LootTableHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

import java.util.ArrayList;
import java.util.List;

public class JerArsMagica extends AbstractJerIntegration {
    private static JerArsMagica instance;

    public static JerArsMagica getInstance() {
        return instance == null ? instance = new JerArsMagica() : instance;
    }

    @Override
    public void initJerDungeons() {
        if(!ConfigHandlerJEM.jer_dungeons.ars_magica_2)
            return;

        ResourceLocation loc = LootTablesArsMagica.MAGE_TOWER_LOOT;
        DungeonHelper.registerDungeon(loc.toString(), DungeonHelper.getDungeonLocalKey("arsmagica2", "mage_tower"), loc);

        loc = LootTablesArsMagica.MAGE_TOWER_RARE_LOOT;
        DungeonHelper.registerDungeon(loc.toString(), DungeonHelper.getDungeonLocalKey("arsmagica2", "mage_tower_rare"), loc);
    }

    @Override
    public void initJerEntities() {
        if(!ConfigHandlerJEM.jer_entities.ars_magica_2)
            return;

        LootDrop[] bossDrops = new LootDrop[3];

        //Dark Mage
        JERIntegration.mobRegistry.register(new EntityDarkMage(world), LootTablesArsMagica.DARK_MAGE_LOOT);
        //Dryad
        JERIntegration.mobRegistry.register(new EntityDryad(world), new LootDrop(new ItemStack(ItemDefs.essence, 1, ArsMagicaAPI.getAffinityRegistry().getId(Affinity.LIFE)), 0.1f));
        //Earth Elemental
        JERIntegration.mobRegistry.register(new EntityEarthElemental(world));
        //Fire Elemental
        JERIntegration.mobRegistry.register(new EntityFireElemental(world));
        //Hecate
        List<LootDrop> drops = new ArrayList<>(LootTableHelper.toDrops(world, LootTableList.ENTITIES_ZOMBIE));
        drops.add(new LootDrop(new ItemStack(ItemDefs.essence, 1, ArsMagicaAPI.getAffinityRegistry().getId(Affinity.ENDER)), 0.1f));
        JERIntegration.mobRegistry.register(new EntityHecate(world), drops.toArray(new LootDrop[0]));
        drops.clear();
        //Hell Cow
        JERIntegration.mobRegistry.register(new EntityHellCow(world), LootTablesArsMagica.HELL_COW_LOOT);
        //Light Mage
        JERIntegration.mobRegistry.register(new EntityLightMage(world), LootTablesArsMagica.LIGHT_MAGE_LOOT);
        //Mana Creeper
        JERIntegration.mobRegistry.register(new EntityManaCreeper(world), LootTablesArsMagica.MANA_CREEPER_LOOT);
        //Mana Elemental
        JERIntegration.mobRegistry.register(new EntityManaElemental(world), new LootDrop(new ItemStack(ItemDefs.essence, 1, ArsMagicaAPI.getAffinityRegistry().getId(Affinity.ARCANE)), 0.1f));
        //Water Elemental
        JERIntegration.mobRegistry.register(new EntityWaterElemental(world), new LootDrop(new ItemStack(ItemDefs.essence, 1, ArsMagicaAPI.getAffinityRegistry().getId(Affinity.WATER))));


        //Air Guardian
        bossDrops[0] = new LootDrop(new ItemStack(ItemDefs.infinityOrb, 1, 1));
        bossDrops[1] = new LootDrop(new ItemStack(ItemDefs.essence, 1, ArsMagicaAPI.getAffinityRegistry().getId(Affinity.AIR)), 0, 3, new Conditional[0]);
        bossDrops[2] = new LootDrop(ItemDefs.airSledEnchanted, 0.3f);
        JERIntegration.mobRegistry.register(new EntityAirGuardian(world), bossDrops);
        //Arcane Guardian
        bossDrops[0] = new LootDrop(new ItemStack(ItemDefs.infinityOrb, 1, 1));
        bossDrops[1] = new LootDrop(new ItemStack(ItemDefs.essence, 1, ArsMagicaAPI.getAffinityRegistry().getId(Affinity.ARCANE)), 0, 3, new Conditional[0]);
        bossDrops[2] = new LootDrop(ItemDefs.arcaneSpellBookEnchanted, 0.3f);
        JERIntegration.mobRegistry.register(new EntityArcaneGuardian(world), bossDrops);
        //Earth Guardian
        bossDrops[0] = new LootDrop(new ItemStack(ItemDefs.infinityOrb, 1, 0));
        bossDrops[1] = new LootDrop(new ItemStack(ItemDefs.essence, 1, ArsMagicaAPI.getAffinityRegistry().getId(Affinity.EARTH)), 0, 3, new Conditional[0]);
        bossDrops[2] = new LootDrop(ItemDefs.earthArmorEnchanted, 0.3f);
        JERIntegration.mobRegistry.register(new EntityEarthGuardian(world), bossDrops);
        //Ender Guardian
        bossDrops[0] = new LootDrop(new ItemStack(ItemDefs.infinityOrb, 1, 2));
        bossDrops[1] = new LootDrop(new ItemStack(ItemDefs.essence, 1, ArsMagicaAPI.getAffinityRegistry().getId(Affinity.ENDER)), 0, 3, new Conditional[0]);
        bossDrops[2] = new LootDrop(ItemDefs.enderBootsEnchanted, 0.3f);
        JERIntegration.mobRegistry.register(new EntityEnderGuardian(world), bossDrops);
        //Fire Guardian
        bossDrops[0] = new LootDrop(new ItemStack(ItemDefs.infinityOrb, 1, 2));
        bossDrops[1] = new LootDrop(new ItemStack(ItemDefs.essence, 1, ArsMagicaAPI.getAffinityRegistry().getId(Affinity.FIRE)), 0, 3, new Conditional[0]);
        bossDrops[2] = new LootDrop(ItemDefs.fireEarsEnchanted, 0.3f);
        JERIntegration.mobRegistry.register(new EntityFireGuardian(world), bossDrops);
        //Life Guardian
        bossDrops[0] = new LootDrop(new ItemStack(ItemDefs.infinityOrb, 1, 1));
        bossDrops[1] = new LootDrop(new ItemStack(ItemDefs.essence, 1, ArsMagicaAPI.getAffinityRegistry().getId(Affinity.LIFE)), 0, 3, new Conditional[0]);
        bossDrops[2] = new LootDrop(ItemDefs.lifeWardEnchanted, 0.3f);
        JERIntegration.mobRegistry.register(new EntityLifeGuardian(world), bossDrops);
        //Lightning Guardian
        bossDrops[0] = new LootDrop(new ItemStack(ItemDefs.infinityOrb, 1, 1));
        bossDrops[1] = new LootDrop(new ItemStack(ItemDefs.essence, 1, ArsMagicaAPI.getAffinityRegistry().getId(Affinity.LIGHTNING)), 0, 3, new Conditional[0]);
        bossDrops[2] = new LootDrop(ItemDefs.lightningCharmEnchanted, 0.3f);
        JERIntegration.mobRegistry.register(new EntityLightningGuardian(world), bossDrops);
        //Nature Guardian
        bossDrops[0] = new LootDrop(new ItemStack(ItemDefs.infinityOrb, 1, 2));
        bossDrops[1] = new LootDrop(new ItemStack(ItemDefs.essence, 1, ArsMagicaAPI.getAffinityRegistry().getId(Affinity.NATURE)), 0, 3, new Conditional[0]);
        bossDrops[2] = new LootDrop(ItemDefs.natureScytheEnchanted, 0.3f);
        JERIntegration.mobRegistry.register(new EntityNatureGuardian(world), bossDrops);
        //Water Guardian
        bossDrops[0] = new LootDrop(new ItemStack(ItemDefs.infinityOrb, 1, 0));
        bossDrops[1] = new LootDrop(new ItemStack(ItemDefs.essence, 1, ArsMagicaAPI.getAffinityRegistry().getId(Affinity.WATER)), 0, 3, new Conditional[0]);
        bossDrops[2] = new LootDrop(ItemDefs.waterOrbsEnchanted, 0.3f);
        JERIntegration.mobRegistry.register(new EntityWaterGuardian(world), bossDrops);
        //Winter Guardian
        bossDrops[0] = new LootDrop(new ItemStack(ItemDefs.infinityOrb, 1, 2));
        bossDrops[1] = new LootDrop(new ItemStack(ItemDefs.essence, 1, ArsMagicaAPI.getAffinityRegistry().getId(Affinity.ICE)), 0, 3, new Conditional[0]);
        bossDrops[2] = new LootDrop(ItemDefs.winterArmEnchanted, 0.3f);
        JERIntegration.mobRegistry.register(new EntityWinterGuardian(world), bossDrops);
    }
}
