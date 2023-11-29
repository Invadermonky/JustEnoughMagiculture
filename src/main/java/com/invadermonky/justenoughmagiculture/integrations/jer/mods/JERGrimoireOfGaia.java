package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigGrimoireOfGaia;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.integrations.jer.conditionals.JEMConditional;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import gaia.GaiaConfig;
import gaia.entity.monster.*;
import gaia.init.GaiaBlocks;
import gaia.init.GaiaItems;
import gaia.init.GaiaLootTables;
import jeresources.api.conditionals.Conditional;
import jeresources.api.conditionals.LightLevel;
import jeresources.api.drop.LootDrop;
import jeresources.util.LootTableHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class JERGrimoireOfGaia extends JERBase implements IJERIntegration {
    private final JEMConfigGrimoireOfGaia.JER jerConfig = JEMConfig.GRIMOIRE_OF_GAIA.JUST_ENOUGH_RESOURCES;

    private String[] validUnderground = new String[] {"CONIFEROUS", "DENSE", "FOREST", "JUNGLE", "MESA", "MOUNTAIN", "PLAINS", "SANDY", "SAVANNA", "SNOWY", "SPOOKY", "SWAMP"};
    private String[] invalidUnderground = new String[] {"BEACH", "COLD", "HOT", "OCEAN", "RIVER", "SPARSE"};

    public JERGrimoireOfGaia(boolean enableJERMobs) {
        if(enableJERMobs) registerModEntities();
    }

    @Override
    public void registerModEntities() {
        registerAssistMobs();
        registerAssistDayMobs();
        registerHostileMobs();
        registerHostileDayMobs();
    }

    /*
    General Registry
*/
    private void registerAssistMobs() {
        if(jerConfig.enableEnderDragonGirl) { registerEnderDragonGirl(); }
        if(jerConfig.enableEnderEye) { registerEnderEye(); }
        if(jerConfig.enableHarpyWizard) { registerHarpyWizard(); }
        if(jerConfig.enableMermaid) { registerMermaid(); }
    }

    private void registerAssistDayMobs() {
        if(jerConfig.enableBee) { registerBee(); }
        if(jerConfig.enableCentaur) { registerCentaur(); }
        if(jerConfig.enableCobbleGolem) { registerCobbleGolem(); }
        if(jerConfig.enableDryad) { registerDryad(); }
        if(jerConfig.enableDwarf) { registerDwarf(); }
        if(jerConfig.enableDwarfArcher) { registerDwarfArcher(); }
        if(jerConfig.enableDwarfMiner) { registerDwarfMiner(); }
        if(jerConfig.enableGoblin) { registerGoblin(); }
        if(jerConfig.enableGoblinArcher) { registerGoblinArcher(); }
        if(jerConfig.enableGryphon) { registerGryphon(); }
        if(jerConfig.enableHunter) { registerHunter(); }
        if(jerConfig.enableKikimora) { registerKikimora(); }
        if(jerConfig.enableCyclops) { registerMonoeye(); }
        if(jerConfig.enableSatyress) { registerSatyress(); }
        if(jerConfig.enableValkyrie) { registerValkyrie(); }
        if(jerConfig.enableYukiOnna) { registerYukiOnna(); }
    }

    private void registerHostileMobs() {
        if(jerConfig.enableAnubis) { registerAnubis(); }
        if(jerConfig.enableArachne) { registerArachne(); }
        if(jerConfig.enableBanshee) { registerBanshee(); }
        if(jerConfig.enableBaphomet) { registerBaphomet(); }
        if(jerConfig.enableBeholder) { registerBeholder(); }
        if(jerConfig.enableBoneKnight) { registerBoneKnight(); }
        if(jerConfig.enableCecaelia) { registerCecaelia(); }
        if(jerConfig.enableCobblestoneGolem) { registerCobblestoneGolem(); }
        if(jerConfig.enableCreep) { registerCreep(); }
        if(jerConfig.enableDeathword) { registerDeathword(); }
        if(jerConfig.enableDhampir) { registerDhampir(); }
        if(jerConfig.enableDullahan) { registerDullahan(); }
        if(jerConfig.enableFleshLich) { registerFleshLich(); }
        if(jerConfig.enableGelatinousSlime) { registerGelatinousSlime(); }
        if(jerConfig.FERAL_GOBLIN.enableMobJER) { registerGoblinFeral(); }
        if(jerConfig.FERAL_GOBLIN_ARCHER.enableMobJER) { registerGoblinFeralArcher(); }
        if(jerConfig.FERAL_GOBLIN_BOMBER.enableMobJER) { registerGoblinFeralBomber(); }
        if(jerConfig.enableGravemite) { registerGravemite(); }
        if(jerConfig.enableHarpy) { registerHarpy(); }
        if(jerConfig.ILLAGER_BUTLER.enableMobJER) { registerIllagerButler(); }
        if(jerConfig.ILLAGER_INCINERATOR.enableMobJER) { registerIllagerFire(); }
        if(jerConfig.ILLAGER_INQUISITOR.enableMobJER) { registerIllagerInquisitor(); }
        if(jerConfig.enableKobold) { registerKobold(); }
        if(jerConfig.enableMimic) { registerMimic(); }
        if(jerConfig.enableMinotaur) { registerMinotaur(); }
        if(jerConfig.enableMinotaurus) { registerMinotaurus(); }
        if(jerConfig.enableMinotaurusArcher) { registerMinotaurusArcher(); }
        if(jerConfig.enableMummy) { registerMummy(); }
        if(jerConfig.enableNineTails) { registerNineTails(); }
        if(jerConfig.enableOni) { registerOni(); }
        if(jerConfig.enableOrc) { registerOrc(); }
        if(jerConfig.enableOrcWizard) { registerOrcRanged(); }
        if(jerConfig.enableSHAMAN) { registerShaman(); }
        if(jerConfig.enableSHARKO) { registerSharko(); }
        if(jerConfig.enableSludgeGirl) { registerSludgeGirl(); }
        if(jerConfig.enableSphinx) { registerSphinx(); }
        if(jerConfig.enableSporeling) { registerSporeling(); }
        if(jerConfig.enableSpriggan) { registerSpriggan(); }
        if(jerConfig.enableSuccubus) { registerSuccubus(); }
        if(jerConfig.enableVampire) { registerVampire(); }
        if(jerConfig.enableWerecat) { registerWerecat(); }
        if(jerConfig.enableWitch) { registerWitch(); }
        if(jerConfig.enableWitherCow) { registerWitherCow(); }
        if(jerConfig.enableYeti) { registerYeti(); }
    }

    private void registerHostileDayMobs() {
        if(jerConfig.enableAntWorker) { registerAnt(); }
        if(jerConfig.enableAntScavenger) { registerAntRanger(); }
        if(jerConfig.enableMandragora) { registerMandragora(); }
        if(jerConfig.enableMatango) { registerMatango(); }
        if(jerConfig.enableNaga) { registerNaga(); }
        if(jerConfig.enableSelkie) { registerSelkie(); }
        if(jerConfig.enableSiren) { registerSiren(); }
        if(jerConfig.enableToad) { registerToad(); }
    }

    /*
    Assist Mob Registry
*/
    private void registerEnderDragonGirl() {
        EntityGaiaEnderDragonGirl entity = new EntityGaiaEnderDragonGirl(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"END"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_ENDER_DRAGON_GIRL;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(Items.ENDER_PEARL, 0, 2, getDropChance(2, 3),true));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX, 2));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));
            loot.add(getRareDrop(GaiaItems.SPAWN_ENDER_GIRL));
            loot.add(getRareDrop(GaiaItems.MISC_ELYTRA));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
    }

    private void registerEnderEye() {
        EntityGaiaEnderEye entity = new EntityGaiaEnderEye(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(validUnderground, invalidUnderground);
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_ENDER_EYE;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(Items.ENDER_PEARL, 0, 1, getDropChance(1, 2), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX, 0));
            loot.add(getRareDecoDrop(GaiaBlocks.DOLL_ENDER_GIRL));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
    }

    private void registerHarpyWizard() {
        EntityGaiaHarpyWizard entity = new EntityGaiaHarpyWizard(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[] {"SPOOKY"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_HARPY_WIZARD;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));
            loot.add(getCommonDrop(GaiaItems.MISC_SOUL_FIRE, 0, 2, getDropChance(2, 3), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));
            loot.add(getUncommonDrop(GaiaItems.MISC_BOOK));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerMermaid() {
        EntityGaiaMermaid entity = new EntityGaiaMermaid(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"BEACH", "OCEAN", "RIVER"}, new String[]{"MUSHROOM"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_MERMAID;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.MISC_PEARL, 0, 2, getDropChance(2, 3), true));
            loot.add(getCommonDrop(Items.PRISMARINE_SHARD, 0, 1, getDropChance(1, 4), true));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX, 0));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));
            loot.add(getRareDrop(GaiaItems.BOX_OLD));
            loot.add(getRareDecoDrop(GaiaBlocks.DOLL_MERMAID));
            loot.add(getRareDrop(GaiaItems.ACCESSORY_TRINKET_WATER_BREATHING));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
    }

    /*
        Assist Day Mob Registry
    */
    private void registerBee() {
        EntityGaiaBee entity = new EntityGaiaBee(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"DENSE", "FOREST", "RARE"}, new String[]{"CONIFEROUS", "COLD", "HOT", "SPARSE", "SPOOKY"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_BEE;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(Items.DYE, 15, 0, 1, getDropChance(1, 2), true));
            loot.add(getCommonDrop(GaiaItems.FOOD_HONEY, 0, 1, getDropChance(1, 2), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerCentaur() {
        EntityGaiaCentaur entity = new EntityGaiaCentaur(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"PLAINS", "MESA"}, new String[]{"SAVANNA"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_CENTAUR;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(Items.LEATHER, 0, 1, getDropChance(1, 2), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));
            loot.add(getRareDrop(GaiaItems.BAG_ARROW));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
        registerRenderHook(entity.getClass(), (renderInfo, e) -> {
            GlStateManager.translate(0, -0.2, 0);
            return renderInfo;
        });
    }

    private void registerCobbleGolem() {
        EntityGaiaCobbleGolem entity = new EntityGaiaCobbleGolem(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"JUNGLE"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_COBBLE_GOLEM;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerDryad() {
        EntityGaiaDryad entity = new EntityGaiaDryad(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"DENSE", "FOREST", "RARE"}, new String[]{"CONIFEROUS", "COLD", "HOT", "SPARSE", "SPOOKY"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_DRYAD;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.FOOD_ROOT, 0, 1, getDropChance(1, 2), true));
            loot.add(getCommonDrop(Item.getItemFromBlock(Blocks.LOG), 0, 1, getDropChance(1, 2), false, JEMConditional.killedWithAxe));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));
            loot.add(getRareDecoDrop(GaiaBlocks.DOLL_DRYAD));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerDwarf() {
        EntityGaiaDwarf entity = new EntityGaiaDwarf(world);
        entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(GaiaItems.WEAPON_PROP_AXE_STONE));
        entity.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(GaiaItems.SHIELD_PROP, 1, 1));
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"MOUNTAIN"}, new String[]{"COLD", "DENSE", "HOT"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_DWARF_MELEE;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.FOOD_MEAT, 0, 2, getDropChance(2, 3), true));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX, 0));
            loot.add(getUncommonDrop(GaiaItems.BOX_GOLD));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));
            loot.add(getRareDrop(GaiaItems.BAG_ARROW));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerDwarfArcher() {
        EntityGaiaDwarf entity = new EntityGaiaDwarf(world);
        entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"MOUNTAIN"}, new String[]{"COLD", "DENSE", "HOT"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_DWARF_RANGED;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(Items.ARROW, 0, 2, getDropChance(2, 3), true));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX, 0));
            loot.add(getUncommonDrop(GaiaItems.BOX_GOLD));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));
            loot.add(getRareDrop(GaiaItems.BAG_ARROW));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
    }

    private void registerDwarfMiner() {
        EntityGaiaDwarf entity = new EntityGaiaDwarf(world);
        entity.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.STONE_PICKAXE));
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"MOUNTAIN"}, new String[]{"COLD", "DENSE", "HOT"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_DWARF_MINER;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(Items.IRON_NUGGET, 0, 2, getDropChance(2, 3), true));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX, 0));
            loot.add(getUncommonDrop(GaiaItems.BOX_GOLD));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));
            loot.add(getRareDrop(GaiaItems.BAG_ARROW));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
    }

    private void registerGoblin() {
        EntityGaiaGoblin entity = new EntityGaiaGoblin(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"SAVANNA"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_GOBLIN_MELEE;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.FOOD_MEAT, 0, 1, getDropChance(1, 2), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerGoblinArcher() {
        EntityGaiaGoblin entity = new EntityGaiaGoblin(world);
        entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(GaiaItems.WEAPON_PROJECTILE_BOMB));
        entity.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(Items.FLINT_AND_STEEL));
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"SAVANNA"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_GOBLIN_RANGED;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.FOOD_MEAT, 0, 1, getDropChance(1, 2), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));
            loot.add(getRareDrop(GaiaItems.BAG_ARROW));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
    }

    private void registerGryphon() {
        EntityGaiaGryphon entity = new EntityGaiaGryphon(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"MOUNTAIN"}, new String[]{"COLD", "DENSE", "HOT"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_GRYPHON;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(Items.FEATHER, 0, 1, getDropChance(1, 2), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerHunter() {
        EntityGaiaHunter entity = new EntityGaiaHunter(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"JUNGLE"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_HUNTER;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.FOOD_ROTTEN_HEART, 0, 1, getDropChance(1, 2), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));
            loot.add(getRareDrop(GaiaItems.BAG_ARROW));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerKikimora() {
        EntityGaiaKikimora entity = new EntityGaiaKikimora(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[] {"CONIFEROUS", "SNOWY"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_KIKIMORA;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.MISC_FUR, 0, 1, getDropChance(1, 2), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerMonoeye() {
        EntityGaiaMonoeye entity = new EntityGaiaMonoeye(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[] {"CONIFEROUS"}, new String[] {"SNOWY"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_MONOEYE;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.MISC_FUR, 0, 1, getDropChance(1, 2), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));
            if(Enchantments.SHARPNESS != null) {
                ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
                ItemEnchantedBook.addEnchantment(enchantedBook, new EnchantmentData(Enchantments.SHARPNESS, 1));
                loot.add(getRareDrop(enchantedBook));
            }

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerSatyress() {
        EntityGaiaSatyress entity = new EntityGaiaSatyress(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"PLAINS", "MESA"}, new String[]{"SAVANNA"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_SATYRESS;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.FOOD_MEAT, 0, 1, getDropChance(1, 2), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerValkyrie() {
        EntityGaiaValkyrie entity = new EntityGaiaValkyrie(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"MOUNTAIN"}, new String[]{"COLD", "DENSE", "HOT"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_VALKYRIE;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.FOOD_SMALL_APPLE_GOLD, 0, 1, getDropChance(1, 4), true));
            loot.add(getCommonDrop(unifyNugget(3), 1, 3, 1.0f, false));
            loot.add(getUncommonDrop(GaiaItems.BOX_DIAMOND));
            loot.add(getRareDecoDrop(GaiaBlocks.BUST_VALKYRIE));
            loot.add(getRareDrop(GaiaItems.MISC_RING, 0));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
        registerRenderHook(entity.getClass(), (renderInfo, e) -> {
            GlStateManager.scale(1.35, 1.35, 1.35);
            GlStateManager.translate(0, 0.1, 0);
            return renderInfo;
        });
    }

    private void registerYukiOnna() {
        EntityGaiaYukiOnna entity = new EntityGaiaYukiOnna(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[] {"CONIFEROUS"}, new String[] {"SNOWY"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_YUKI_ONNA;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.MISC_FUR, 0, 2, getDropChance(2, 3), true));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_GOLD));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));
            ItemStack fanIce = new ItemStack(GaiaItems.WEAPON_FAN_ICE);
            if(Enchantments.KNOCKBACK != null)
                fanIce.addEnchantment(Enchantments.KNOCKBACK, 4);
            loot.add(getRareDrop(fanIce));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    /*
        Hostile Mob Registry
    */
    private void registerAnubis() {
        EntityGaiaAnubis entity = new EntityGaiaAnubis(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"SANDY"}, new String[]{"MESA"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_ANUBIS;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_GOLD));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));
            loot.add(getRareDrop(GaiaItems.MISC_BOOK));
            loot.add(getRareDrop(GaiaItems.SPAWN_WERESHEEP));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerArachne() {
        EntityGaiaArachne entity = new EntityGaiaArachne(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(validUnderground, invalidUnderground);
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_ARACHNE;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.MISC_FURNACE_FUEL, 0, 1, getDropChance(1, 4), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX, 0));
            loot.add(getRareDrop(GaiaItems.MISC_BOOK));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerBanshee() {
        EntityGaiaBanshee entity = new EntityGaiaBanshee(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"MOUNTAIN"}, new String[]{"COLD", "DENSE", "HOT"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_BANSHEE;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.MISC_SOUL_FIRE, 0, 2, getDropChance(2, 3), true));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_GOLD));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));
            loot.add(getRareDrop(GaiaItems.WEAPON_BOOK_NIGHTMARE));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        registerRenderHook(entity.getClass(), (renderInfo, e) -> {
            GlStateManager.translate(0, 0.1, 0);
            return renderInfo;
        });
    }

    private void registerBaphomet() {
        EntityGaiaBaphomet entity = new EntityGaiaBaphomet(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"NETHER"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_BAPHOMET;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(Items.BLAZE_POWDER, 0, 2, getDropChance(2, 3), true));
            loot.add(getCommonDrop(GaiaItems.MISC_SOUL_FIRE, 0, 2, getDropChance(2, 3), true));
            loot.add(getCommonDrop(GaiaItems.FOOD_NETHER_WART, 0 , 1, getDropChance(1, 4), true));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX, 1));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));
            loot.add(getRareDrop(GaiaItems.ACCESSORY_TRINKET_WITHER));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerBeholder() {
        EntityGaiaBeholder entity = new EntityGaiaBeholder(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"END"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_BEHOLDER;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.MISC_SOUL_FIRE, 0, 2, getDropChance(2, 3), true));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_GOLD));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));
            loot.add(getRareDrop(GaiaItems.WEAPON_BOOK_NIGHTMARE));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
    }

    private void registerBoneKnight() {
        EntityGaiaBoneKnight entity = new EntityGaiaBoneKnight(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(validUnderground, invalidUnderground);
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_BONE_KNIGHT;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(Items.REDSTONE, 0, 2, getDropChance(2, 3), false));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX, 0));
            loot.add(getUncommonDrop(Item.getItemFromBlock(Blocks.REDSTONE_BLOCK)));
            loot.add(getRareDrop(Items.SKULL, 0));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerCecaelia() {
        EntityGaiaCecaelia entity = new EntityGaiaCecaelia(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"BEACH", "OCEAN", "RIVER"}, new String[]{"MUSHROOM"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_CECAELIA;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.MISC_PEARL, 0, 1, getDropChance(1, 2), true));
            loot.add(getCommonDrop(Items.CLAY_BALL, 0, 1, getDropChance(1, 4), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX, 0));
            if(Enchantments.LUCK_OF_THE_SEA != null) {
                ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
                ItemEnchantedBook.addEnchantment(enchantedBook, new EnchantmentData(Enchantments.LUCK_OF_THE_SEA, 1));
                loot.add(getRareDrop(enchantedBook));
            }

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerCobblestoneGolem() {
        EntityGaiaCobblestoneGolem entity = new EntityGaiaCobblestoneGolem(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"JUNGLE"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_COBBLESTONE_GOLEM;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(Items.IRON_NUGGET, 0, 2, getDropChance(2, 3), true));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_GOLD));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));
            loot.add(getRareDrop(GaiaItems.CHEST, 1));
            loot.add(getRareDrop(GaiaItems.SHARD_MISC, 0));
            loot.add(getRareDrop(GaiaItems.WEAPON_BOOK_METAL));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
    }

    private void registerCreep() {
        EntityGaiaCreep entity = new EntityGaiaCreep(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(validUnderground, invalidUnderground);
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_CREEP;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(Items.GUNPOWDER, 0, 1, getDropChance(1, 2), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX, 0));
            loot.add(getRareDrop(GaiaItems.SPAWN_CREEPER_GIRL));
            loot.add(getRareDecoDrop(GaiaBlocks.DOLL_CREEPER_GIRL));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        registerRenderHook(entity.getClass(), (renderInfo, e) -> {
            GlStateManager.scale(0.8, 0.8, 0.8);
            GlStateManager.translate(0, 0.3, 0);
            return renderInfo;
        });
    }

    private void registerDeathword() {
        EntityGaiaDeathword entity = new EntityGaiaDeathword(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(validUnderground, invalidUnderground);
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_DEATHWORD;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(Items.PAPER, 1, 3, 1.0f, false));
            loot.add(getCommonDrop(Items.BOOK, 0, 1, getDropChance(1, 4), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX, 0));
            loot.add(getRareDrop(GaiaItems.BAG_BOOK));
            loot.add(getRareDrop(GaiaItems.WEAPON_BOOK));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
    }

    private void registerDhampir() {
        EntityGaiaDhampir entity = new EntityGaiaDhampir(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[] {"CONIFEROUS", "SNOWY"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_DHAMPIR;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.MISC_SOUL_FIRE, 0, 2, getDropChance(2, 3), true));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_GOLD));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));
            loot.add(getRareDecoDrop(GaiaBlocks.DOLL_MAID));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerDullahan() {
        EntityGaiaDullahan entity = new EntityGaiaDullahan(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"MOUNTAIN"}, new String[]{"COLD", "DENSE", "HOT"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_DULLAHAN;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.MISC_SOUL_FIRE, 0, 1, getDropChance(1, 2), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));
            loot.add(getRareDecoDrop(GaiaBlocks.DOLL_DULLAHAN));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerFleshLich() {
        EntityGaiaFleshLich entity = new EntityGaiaFleshLich(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(validUnderground, invalidUnderground);
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_FLESH_LICH;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(Items.DYE, 4, 1, 3, 1.0f, false));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX, 0));
            loot.add(getUncommonDrop(Item.getItemFromBlock(Blocks.LAPIS_BLOCK)));
            loot.add(getRareDrop(Items.SKULL, 2));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerGelatinousSlime() {
        EntityGaiaGelatinousSlime entity = new EntityGaiaGelatinousSlime(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"SWAMP"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_GELATINOUS_SLIME;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(Items.SLIME_BALL, 0, 2, getDropChance(2, 3), true));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_GOLD));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));
            loot.add(getRareDrop(GaiaItems.SPAWN_SLIME_GIRL));
            loot.add(getRareDecoDrop(GaiaBlocks.DOLL_SLIME_GIRL));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        registerRenderHook(entity.getClass(), (renderInfo, e) -> {
            GlStateManager.scale(0.7, 0.7, 0.7);
            GlStateManager.translate(0, 0.15, 0);
            return renderInfo;
        });
    }

    private void registerGoblinFeral() {
        EntityGaiaGoblinFeral entity = new EntityGaiaGoblinFeral(world);
        entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(GaiaItems.WEAPON_PROP_SWORD_WOOD));
        String[] spawnBiomes = BiomeHelper.getBiomeNames(jerConfig.FERAL_GOBLIN);
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_GOBLIN_FERAL_MELEE;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getUncommonDrop(Items.IRON_NUGGET));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerGoblinFeralBomber() {
        EntityGaiaGoblinFeral entity = new EntityGaiaGoblinFeral(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(jerConfig.FERAL_GOBLIN_BOMBER);
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_GOBLIN_FERAL_BOMBER;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getUncommonDrop(Items.IRON_NUGGET));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
    }

    private void registerGoblinFeralArcher() {
        EntityGaiaGoblinFeral entity = new EntityGaiaGoblinFeral(world);
        entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
        String[] spawnBiomes = BiomeHelper.getBiomeNames(jerConfig.FERAL_GOBLIN_ARCHER);
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_GOBLIN_FERAL_RANGED;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getUncommonDrop(Items.IRON_NUGGET));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
    }

    private void registerGravemite() {
        EntityGaiaMite entity = new EntityGaiaMite(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"SANDY"}, new String[]{"MESA"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_MITE;
        registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
    }

    private void registerHarpy() {
        EntityGaiaHarpy entity = new EntityGaiaHarpy(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"PLAINS", "MESA"}, new String[]{"SAVANNA"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_HARPY;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(Items.FEATHER, 0, 1, getDropChance(1, 2), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));
            loot.add(getRareDecoDrop(GaiaBlocks.DECO_NEST_HARPY));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerIllagerButler() {
        EntityGaiaIllagerButler entity = new EntityGaiaIllagerButler(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(jerConfig.ILLAGER_BUTLER);
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_BUTLER;
        registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerIllagerFire() {
        EntityGaiaIllagerFire entity = new EntityGaiaIllagerFire(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(jerConfig.ILLAGER_INCINERATOR);
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_ILLAGER_FIRE;
        registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
    }

    private void registerIllagerInquisitor() {
        EntityGaiaIllagerInquisitor entity = new EntityGaiaIllagerInquisitor(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(jerConfig.ILLAGER_INQUISITOR);
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_ILLAGER_INQUISITOR;
        registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerKobold() {
        EntityGaiaKobold entity = new EntityGaiaKobold(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"SNOWY"}, new String[]{"OCEAN", "RIVER", "BEACH", "FOREST"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_KOBOLD;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.MISC_FUR, 0, 1, getDropChance(1, 2), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));
            loot.add(getRareDrop(GaiaItems.BAG_ARROW));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerMimic() {
        EntityGaiaMimic entity = new EntityGaiaMimic(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(validUnderground, invalidUnderground);
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_MIMIC;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX, 0));
            loot.add(getRareDrop(GaiaItems.FOOD_MONSTER_FEED_PREMIUM));
            loot.add(getRareDrop(GaiaItems.SPAWN_TRADER));
            loot.add(getRareDrop(GaiaItems.BAG_RECORD));
            loot.add(getRareDrop(GaiaItems.WEAPON_BOOK_HUNGER));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
    }

    private void registerMinotaur() {
        EntityGaiaMinotaur entity = new EntityGaiaMinotaur(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"PLAINS", "MESA"}, new String[]{"SAVANNA"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_MINOTAUR;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.FOOD_SMALL_APPLE_GOLD, 0, 1, getDropChance(1, 4), true));
            loot.add(getCommonDrop(unifyNugget(2), 1, 3, 1.0f, false));
            loot.add(getCommonDrop(unifyNugget(3), 1, 3, 1.0f, false));
            loot.add(getUncommonDrop(GaiaItems.BOX_DIAMOND));
            loot.add(getRareDrop(GaiaItems.ACCESSORY_CURSED));
            loot.add(getRareDecoDrop(GaiaBlocks.DECO_BUST_MINOTAUR));
            loot.add(getRareDrop(GaiaItems.MISC_RING, 1));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
    }

    private void registerMinotaurus() {
        EntityGaiaMinotaurus entity = new EntityGaiaMinotaurus(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"PLAINS", "MESA"}, new String[]{"SAVANNA"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_MINOTAURUS;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(Items.LEATHER, 0, 3, getDropChance(3, 4), true));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_GOLD));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));
            loot.add(getRareDrop(GaiaItems.SPAWN_HOLSTAURUS));
            loot.add(getRareDrop(GaiaItems.WEAPON_BOOK_BATTLE));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerMinotaurusArcher() {
        EntityGaiaMinotaurus entity = new EntityGaiaMinotaurus(world);
        entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"PLAINS", "MESA"}, new String[]{"SAVANNA"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_MINOTAURUS;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(Items.ARROW, 0, 3, getDropChance(3, 4), true));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_GOLD));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));
            loot.add(getRareDrop(GaiaItems.SPAWN_HOLSTAURUS));
            loot.add(getRareDrop(GaiaItems.BAG_ARROW));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
    }

    private void registerMummy() {
        EntityGaiaMummy entity = new EntityGaiaMummy(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"SANDY"}, new String[]{"MESA"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_MUMMY;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(Items.BONE, 0, 1, getDropChance(1, 2), false));
            loot.add(getCommonDrop(Items.ROTTEN_FLESH, 0, 1, getDropChance(1, 2), false));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerNineTails() {
        EntityGaiaNineTails entity = new EntityGaiaNineTails(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[] {"CONIFEROUS"}, new String[] {"SNOWY"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_NINETAILS;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.MISC_SOUL_FIRE, 0, 2, getDropChance(2, 3), true));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_GOLD));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));
            ItemStack fanFire = new ItemStack(GaiaItems.WEAPON_FAN_FIRE);
            if(Enchantments.FIRE_ASPECT != null)
                fanFire.addEnchantment(Enchantments.FIRE_ASPECT, 2);
            if(Enchantments.KNOCKBACK != null)
                fanFire.addEnchantment(Enchantments.KNOCKBACK, 1);
            loot.add(getRareDrop(fanFire));
            loot.add(getRareDecoDrop(GaiaBlocks.DOLL_NINE_TAILS));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerOni() {
        EntityGaiaOni entity = new EntityGaiaOni(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[] {"CONIFEROUS"}, new String[] {"SNOWY"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_ONI;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.MISC_SOUL_FIRE, 0, 1, getDropChance(1, 4), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));
            if(Enchantments.SHARPNESS != null) {
                ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
                enchantedBook.addEnchantment(Enchantments.SHARPNESS, 1);
                loot.add(getRareDrop(enchantedBook));
            }

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerOrc() {
        EntityGaiaOrc entity = new EntityGaiaOrc(world);
        entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(Items.STONE_AXE));
        entity.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(GaiaItems.SHIELD_PROP, 1, 1));
        entity.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(Items.LEATHER_LEGGINGS));
        entity.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(Items.LEATHER_CHESTPLATE));
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"SAVANNA"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_ORC_MELEE;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.FOOD_MEAT, 0, 1, getDropChance(1, 2), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
    }

    private void registerOrcRanged() {
        EntityGaiaOrc entity = new EntityGaiaOrc(world);
        entity.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(GaiaItems.WEAPON_PROP, 1, 0));
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"SAVANNA"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_ORC_RANGED;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.FOOD_MEAT, 0, 1, getDropChance(1, 2), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));
            loot.add(getRareDrop(GaiaItems.BAG_BOOK));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
    }

    private void registerShaman() {
        EntityGaiaShaman entity = new EntityGaiaShaman(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"JUNGLE"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_SHAMAN;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.FOOD_ROTTEN_HEART, 0, 2, getDropChance(2, 3), true));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_GOLD));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));
            loot.add(getRareDrop(GaiaItems.MISC_BOOK));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerSharko() {
        EntityGaiaSharko entity = new EntityGaiaSharko(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"BEACH", "OCEAN", "RIVER"}, new String[]{"MUSHROOM"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_SHARKO;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.MISC_PEARL, 0, 2, getDropChance(2, 3), true));
            loot.add(getCommonDrop(Items.PRISMARINE_SHARD, 0, 1, getDropChance(1, 4), true));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX, 0));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));
            loot.add(getRareDrop(GaiaItems.BOOK_BUFF));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
    }

    private void registerSludgeGirl() {
        EntityGaiaSludgeGirl entity = new EntityGaiaSludgeGirl(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"SWAMP"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_SLUDGE_GIRL;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(Items.SLIME_BALL, 0, 1, getDropChance(1, 2), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerSphinx() {
        EntityGaiaSphinx entity = new EntityGaiaSphinx(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"SANDY"}, new String[]{"MESA"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_SPHINX;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.FOOD_SMALL_APPLE_GOLD, 0, 1, getDropChance(1, 4), true));
            loot.add(getCommonDrop(unifyNugget(2), 1, 3, 1.0f, false));
            loot.add(getCommonDrop(unifyNugget(3), 1, 3, 1.0f, false));
            loot.add(getUncommonDrop(GaiaItems.BOX_DIAMOND));
            loot.add(getRareDecoDrop(GaiaBlocks.BUST_SPHINX));
            loot.add(getRareDrop(GaiaItems.MISC_RING, 2));
            loot.add(getRareDrop(GaiaItems.CHEST, 2));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
    }

    private void registerSporeling() {
        EntityGaiaSporeling entity = new EntityGaiaSporeling(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[] {"SPOOKY"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_SPORELING;
        registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        registerRenderHook(entity.getClass(), (renderInfo, e) -> {
            GlStateManager.translate(0, 0.5, 0);
            return renderInfo;
        });
    }

    private void registerSpriggan() {
        EntityGaiaSpriggan entity = new EntityGaiaSpriggan(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"DENSE", "FOREST", "RARE"}, new String[]{"CONIFEROUS", "COLD", "HOT", "SPARSE", "SPOOKY"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_SPRIGGAN;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.FOOD_ROOT, 0, 2, getDropChance(2, 3), true));
            loot.add(getCommonDrop(Item.getItemFromBlock(Blocks.LOG), 0, 1, getDropChance(1, 2), false, JEMConditional.killedWithAxe));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_GOLD));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));
            loot.add(getRareDrop(GaiaItems.WEAPON_BOOK_NATURE));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerSuccubus() {
        EntityGaiaSuccubus entity = new EntityGaiaSuccubus(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"NETHER"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_SUCCUBUS;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.MISC_SOUL_FIERY, 0, 1, getDropChance(1, 2), true));
            loot.add(getCommonDrop(Items.QUARTZ, 0, 1, getDropChance(1, 8), true));
            loot.add(getCommonDrop(Items.GLOWSTONE_DUST, 0, 1, getDropChance(1, 8), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX, 1));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerVampire() {
        EntityGaiaVampire entity = new EntityGaiaVampire(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[] {"CONIFEROUS", "SNOWY"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_VAMPIRE;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.FOOD_SMALL_APPLE_GOLD, 0, 1, getDropChance(1, 4), true));
            loot.add(getCommonDrop(unifyNugget(2), 1, 3, 1.0f, false));
            loot.add(getCommonDrop(unifyNugget(3), 1, 3, 1.0f, false));
            loot.add(getUncommonDrop(GaiaItems.BOX_DIAMOND));
            loot.add(getRareDecoDrop(GaiaBlocks.BUST_VAMPIRE));
            loot.add(getRareDrop(GaiaItems.MISC_RING, 3));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        registerRenderHook(entity.getClass(), (renderInfo, e) -> {
            GlStateManager.scale(1.3, 1.3, 1.3);
            GlStateManager.translate(0, -0.25, 0);
            return renderInfo;
        });
    }

    private void registerWerecat() {
        EntityGaiaWerecat entity = new EntityGaiaWerecat(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"DENSE", "FOREST", "RARE"}, new String[]{"CONIFEROUS", "COLD", "HOT", "SPARSE", "SPOOKY"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_WERECAT;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.FOOD_MEAT, 0, 1, getDropChance(1, 2), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerWitch() {
        EntityGaiaWitch entity = new EntityGaiaWitch(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[] {"SPOOKY"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_WITCH;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.FOOD_NETHER_WART, 0, 2, getDropChance(2, 3), true));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_GOLD));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));
            loot.add(getRareDrop(GaiaItems.MISC_BOOK));
            loot.add(getRareDecoDrop(GaiaBlocks.DECO_MANDRAGORA_POT));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerWitherCow() {
        EntityGaiaWitherCow entity = new EntityGaiaWitherCow(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"NETHER"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_WITHER_COW;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.FOOD_WITHER, 0, 1, getDropChance(1, 2), true));
            loot.add(getCommonDrop(Items.QUARTZ, 0, 1, getDropChance(1, 8), true));
            loot.add(getCommonDrop(Items.GLOWSTONE_DUST, 0, 1, getDropChance(1, 8), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX, 1));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerYeti() {
        EntityGaiaYeti entity = new EntityGaiaYeti(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"SNOWY"}, new String[]{"OCEAN", "RIVER", "BEACH", "FOREST"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_YETI;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.MISC_FUR, 0, 2, getDropChance(2, 3), true));
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_GOLD));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));
            loot.add(getRareDrop(GaiaItems.WEAPON_BOOK_FREEZING));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.hostile, spawnBiomes, drops);
        } else {
            registerMob(entity, LightLevel.hostile, spawnBiomes, lootTable);
        }
    }

    /*
        Hostile Day Mob Registry
    */
    private void registerAnt() {
        EntityGaiaAnt entity = new EntityGaiaAnt(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"SANDY"}, new String[]{"MESA"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_ANT;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(Items.DYE, 2, 0, 1, getDropChance(1, 2),true));
            loot.add(getCommonDrop(GaiaItems.FOOD_HONEY, 0, 1, getDropChance(1, 2),true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        }
        else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerAntRanger() {
        EntityGaiaAntRanger entity = new EntityGaiaAntRanger(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"SANDY"}, new String[]{"MESA"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_ANT_RANGER;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.FOOD_MEAT, 0, 1, getDropChance(1, 2),true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));

            if(Enchantments.LOOTING != null) {
                ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
                ItemEnchantedBook.addEnchantment(enchantedBook, new EnchantmentData(Enchantments.LOOTING, 1));
                loot.add(getRareDrop(enchantedBook));
            }

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        }
        else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
        registerRenderHook(entity.getClass(), (renderInfo, e) -> {
            GlStateManager.translate(0, 1.0, 0);
            return renderInfo;
        });
    }

    private void registerMandragora() {
        EntityGaiaMandragora entity = new EntityGaiaMandragora(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"CONIFEROUS", "DENSE", "FOREST", "RARE"}, new String[]{"CONIFEROUS", "COLD", "HOT", "SNOWY", "SPARSE", "SPOOKY"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_MANDRAGORA;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.FOOD_ROOT, 0, 1, getDropChance(1, 2),true, JEMConditional.killedWithShovel));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));
            loot.add(getRareDecoDrop(GaiaBlocks.DECO_GARDEN_GNOME));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        }
        else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
        registerRenderHook(entity.getClass(), (renderInfo, e) -> {
            GlStateManager.scale(1.3, 1.3, 1.3);
            GlStateManager.translate(0, 0.25, 0);
            return renderInfo;
        });
    }

    private void registerMatango() {
        EntityGaiaMatango entity = new EntityGaiaMatango(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[] {"SPOOKY"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_MATANGO;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        }
        else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerNaga() {
        EntityGaiaNaga entity = new EntityGaiaNaga(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"SWAMP"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_NAGA;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            LootDrop fishDrop = getCommonDrop(Items.FISH, 0, 2, getDropChance(2, 3),true);
            fishDrop.smeltedItem = new ItemStack(Items.COOKED_FISH);
            loot.add(fishDrop);
            loot.addAll(getNuggetDrops(Items.GOLD_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_GOLD));
            loot.add(getUncommonDrop(GaiaItems.BAG_BOOK));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        }
        else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
    }

    private void registerSelkie() {
        EntityGaiaSelkie entity = new EntityGaiaSelkie(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"SNOWY"}, new String[]{"OCEAN", "RIVER", "BEACH", "FOREST"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_SELKIE;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(GaiaItems.MISC_FUR, 0, 1, getDropChance(1, 2), true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));
            loot.add(getUncommonDrop(GaiaItems.BAG_ARROW));
            if(Enchantments.LURE != null) {
                ItemStack enchantedBook = new ItemStack(Items.ENCHANTED_BOOK);
                ItemEnchantedBook.addEnchantment(enchantedBook, new EnchantmentData(Enchantments.LURE, 1));
                loot.add(getRareDrop(enchantedBook));
            }

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        }
        else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerSiren() {
        EntityGaiaSiren entity = new EntityGaiaSiren(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[]{"SWAMP"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_SIREN;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            LootDrop fishDrop = getCommonDrop(Items.FISH, 0, 1, getDropChance(1, 2),true);
            fishDrop.smeltedItem = new ItemStack(Items.COOKED_FISH);
            loot.add(fishDrop);
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));
            loot.add(getUncommonDrop(GaiaItems.BAG_ARROW));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        }
        else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
        adjustHumanoidRenderHook(entity.getClass());
    }

    private void registerToad() {
        EntityGaiaToad entity = new EntityGaiaToad(world);
        String[] spawnBiomes = BiomeHelper.getBiomeNames(new String[] {"SPOOKY"});
        ResourceLocation lootTable = GaiaLootTables.ENTITIES_GAIA_TOAD;

        if(!GaiaConfig.OPTIONS.disableDrops) {
            List<LootDrop> loot = LootTableHelper.toDrops(manager.getLootTableFromLocation(lootTable));

            loot.add(getCommonDrop(Items.SLIME_BALL, 0, 1, getDropChance(1, 2),true));
            loot.addAll(getNuggetDrops(Items.IRON_NUGGET));
            loot.add(getUncommonDrop(GaiaItems.BOX_IRON));
            loot.add(getRareDrop(GaiaItems.ACCESSORY_TRINKET_POISON));

            LootDrop[] drops = loot.toArray(new LootDrop[0]);
            registerMob(entity, LightLevel.any, spawnBiomes, drops);
        }
        else {
            registerMob(entity, LightLevel.any, spawnBiomes, lootTable);
        }
        registerRenderHook(entity.getClass(), (renderInfo, e) -> {
            GlStateManager.scale(1.2, 1.2, 1.2);
            GlStateManager.translate(0, 0.25, 0);
            return renderInfo;
        });
    }

        /*
            Helper Methods
        */

    private float getDropChance(int min, int max) {
        float chance = (float) min / (float) max;
        return Float.parseFloat(String.format("%.2f", chance));
    }

    private LootDrop getCommonDrop(Item drop, int minDrop, int maxDrop, float chance, boolean affectedByLooting) {
        if(affectedByLooting)
            return new LootDrop(drop, minDrop, maxDrop, chance, Conditional.affectedByLooting);
        else
            return new LootDrop(drop, minDrop, maxDrop, chance);
    }

    private LootDrop getCommonDrop(Item drop, int meta, int minDrop, int maxDrop, float chance, boolean affectedByLooting) {
        if(affectedByLooting)
            return new LootDrop(drop, meta, minDrop, maxDrop, chance, Conditional.affectedByLooting);
        else
            return new LootDrop(drop, meta, minDrop, maxDrop, chance);
    }

    private LootDrop getCommonDrop(Item drop, int minDrop, int maxDrop, float chance, boolean affectedByLooting, Conditional conditional) {
        if(affectedByLooting)
            return new LootDrop(drop, minDrop, maxDrop, chance, Conditional.affectedByLooting, conditional);
        else
            return new LootDrop(drop, minDrop, maxDrop, chance, conditional);
    }

    private LootDrop getCommonDrop(ItemStack stack, int minDrop, int maxDrop, float chance, boolean affectedByLooting) {
        if(affectedByLooting)
            return new LootDrop(stack, minDrop, maxDrop, chance, Conditional.affectedByLooting);
        else
            return new LootDrop(stack, minDrop, maxDrop, chance);
    }

    private List<LootDrop> getNuggetDrops(Item nugget) {
        List<LootDrop> loot = new ArrayList<>();
        loot.add(new LootDrop(nugget, 1, 3, 1.0f));

        if(GaiaConfig.OPTIONS.additionalOre) {
            if (nugget.equals(Items.GOLD_NUGGET)) {
                loot.add(new LootDrop(unifyNugget(5), 1, 3, 1.0f));
            } else {
                loot.add(new LootDrop(unifyNugget(4), 1, 3, 1.0f));
            }
        }
        return loot;
    }

    private ItemStack unifyNugget(int meta) {
        String oreDict = "";
        ItemStack stack;

        switch(meta) {
            case 0:
                oreDict = "nuggetIron";
                break;
            case 1:
                oreDict = "nuggetGold";
                break;
            case 2:
                oreDict = "nuggetDiamond";
                break;
            case 3:
                oreDict = "nuggetEmerald";
                break;
            case 4:
                oreDict = "nuggetCopper";
                break;
            case 5:
                oreDict = "nuggetSilver";
                break;
        }
        List<ItemStack> oreEquivalents = OreDictionary.getOres(oreDict);
        if(!(oreEquivalents.isEmpty() || oreEquivalents.get(0).isEmpty())) {
            ItemStack unity = oreEquivalents.get(0);
            Item item = unity.getItem();
            int newMeta = unity.getItemDamage();
            stack = new ItemStack(item, 1, newMeta);
        } else {
            stack = new ItemStack(GaiaItems.SHARD, 1, meta);
        }
        return stack;
    }

    private LootDrop getUncommonDrop(Item drop) {
        return new LootDrop(drop, 0, 1, 0.025f, Conditional.rareDrop);
    }

    private LootDrop getUncommonDrop(Item drop, int meta) {
        return new LootDrop(drop, meta, 0, 1, 0.025f, Conditional.rareDrop);
    }

    private LootDrop getRareDecoDrop(Block decoBlock) {
        return new LootDrop(Item.getItemFromBlock(decoBlock), 0, 1, 0.01f, Conditional.rareDrop);
    }

    private LootDrop getRareDrop(Item drop) {
        return new LootDrop(drop, 0, 1, 0.01f, Conditional.rareDrop);
    }

    private LootDrop getRareDrop(Item drop, int meta) {
        return new LootDrop(drop, meta, 0, 1, 0.01f, Conditional.rareDrop);
    }

    private LootDrop getRareDrop(ItemStack drop) {
        return new LootDrop(drop, 0, 1, 0.01f, Conditional.rareDrop);
    }

    private void adjustHumanoidRenderHook(Class<? extends EntityLiving> clazz) {
        registerRenderHook(clazz, (renderInfo, entity) -> {
            GlStateManager.translate(0, 0.15, 0);
            return renderInfo;
        });
    }
}
