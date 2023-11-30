package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigChocolateQuest;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.util.LogHelper;
import com.invadermonky.justenoughmagiculture.util.StringHelper;
import jeresources.api.conditionals.LightLevel;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import team.cqr.cqrepoured.entity.EntityEquipmentExtraSlot;
import team.cqr.cqrepoured.entity.boss.*;
import team.cqr.cqrepoured.entity.boss.endercalamity.EntityCQREnderCalamity;
import team.cqr.cqrepoured.entity.boss.endercalamity.EntityCQREnderKing;
import team.cqr.cqrepoured.entity.boss.exterminator.EntityCQRExterminator;
import team.cqr.cqrepoured.entity.boss.gianttortoise.EntityCQRGiantTortoise;
import team.cqr.cqrepoured.entity.boss.netherdragon.EntityCQRNetherDragon;
import team.cqr.cqrepoured.entity.boss.spectrelord.EntityCQRSpectreLord;
import team.cqr.cqrepoured.entity.mobs.*;
import team.cqr.cqrepoured.entity.mount.EntityGiantEndermite;
import team.cqr.cqrepoured.entity.mount.EntityGiantSilverfishGreen;
import team.cqr.cqrepoured.entity.mount.EntityGiantSilverfishNormal;
import team.cqr.cqrepoured.entity.mount.EntityGiantSilverfishRed;
import team.cqr.cqrepoured.init.CQRItems;
import team.cqr.cqrepoured.init.CQRLoottables;

public class JERChocolateQuest extends JERBase implements IJERIntegration {
    JEMConfigChocolateQuest.JER jerConfig = JEMConfig.CHOCOLATE_QUEST.JUST_ENOUGH_RESOURCES;

    public JERChocolateQuest(boolean enableJERDungeons, boolean enableJERMobs) {
        if(enableJERDungeons) registerModDungeons();
        if(enableJERMobs) registerModEntities();
    }

    @Override
    public void registerModDungeons() {
        for(ResourceLocation chestLoot : CQRLoottables.getChestLootTables()) {
            try {
                String category = chestLoot.toString().replace("chests/", "");
                String unlocName = StringHelper.getDungeonTranslationKey(category.split(":")[0], category.split(":")[1]);
                registerDungeonLoot(category, unlocName, chestLoot);
            } catch(Exception e) {
                LogHelper.warn("Error generating JER dungeon strings for " + chestLoot.toString());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void registerModEntities() {
        registerBosses();
        registerMobs();
        registerMounts();
    }

    private void registerBosses() {
        if(jerConfig.CQR_BOSSES.enableBoarMage) {
            registerCQRMob(new EntityCQRBoarmage(world), LightLevel.any, CQRLoottables.ENTITIES_BOARMAGE);
        }

        if(jerConfig.CQR_BOSSES.enableDragonNether) {
            registerMob(new EntityCQRNetherDragon(world), LightLevel.any, CQRLoottables.ENTITIES_DRAGON_NETHER);
        }

        if(jerConfig.CQR_BOSSES.enableEnderCalamity) {
            registerMob(new EntityCQREnderCalamity(world), LightLevel.any, CQRLoottables.ENTITIES_ENDER_CALAMITY);
            registerRenderHook(EntityCQREnderCalamity.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.2,1.2,1.2);
                return renderInfo;
            }));
        }

        if(jerConfig.CQR_BOSSES.enableEndermanKing) {
            EntityCQREnderKing enderKing = new EntityCQREnderKing(world);
            enderKing.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(CQRItems.GREAT_SWORD_DIAMOND));
            enderKing.setItemStackToExtraSlot(EntityEquipmentExtraSlot.POTION, new ItemStack(CQRItems.POTION_HEALING, 3));
            enderKing.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(CQRItems.KING_CROWN, 1));
            registerCQRMob(enderKing, LightLevel.any, CQRLoottables.ENTITIES_ENDERMAN);
        }

        if(jerConfig.CQR_BOSSES.enableExterminator) {
            registerCQRMob(new EntityCQRExterminator(world), LightLevel.any, CQRLoottables.ENTITIES_EXTERMINATOR);
        }

        if(jerConfig.CQR_BOSSES.enableGiantTurtle) {
            registerMob(new EntityCQRGiantTortoise(world), LightLevel.any, CQRLoottables.ENTITIES_TURTLE);
            registerRenderHook(EntityCQRGiantTortoise.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.4,1.4,1.4);
                GlStateManager.translate(-0.05,-0.2,0);
                return renderInfo;
            }));
        }

        if(jerConfig.CQR_BOSSES.enableGoblinShaman) {
            EntityCQRGoblin goblin = new EntityCQRGoblin(world);
            goblin.setCustomNameTag(I18n.format("entity.cqrepoured:goblin_shaman.name"));
            registerMob(goblin, LightLevel.any, CQRLoottables.ENTITIES_GOBLIN_SHAMAN);
        }

        if(jerConfig.CQR_BOSSES.enableLich) {
            registerCQRMob(new EntityCQRLich(world), LightLevel.any, CQRLoottables.ENTITIES_LICH);
        }

        if(jerConfig.CQR_BOSSES.enableNecromancer) {
            registerCQRMob(new EntityCQRNecromancer(world), LightLevel.any, CQRLoottables.ENTITIES_NECROMANCER);
        }

        if(jerConfig.CQR_BOSSES.enablePirateCaptain) {
            EntityCQRPirateCaptain pirateCaptain = new EntityCQRPirateCaptain(world);
            pirateCaptain.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(CQRItems.CAPTAIN_REVOLVER, 1));
            registerCQRMob(pirateCaptain, LightLevel.any, CQRLoottables.ENTITIES_PIRATE_CAPTAIN);
        }

        if(jerConfig.CQR_BOSSES.enableShelob) {
            registerMob(new EntityCQRGiantSpider(world), LightLevel.any, CQRLoottables.ENTITIES_SPIDER);
            registerRenderHook(EntityCQRGiantSpider.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.2,1.2,1.2);
                GlStateManager.translate(-0.05,-0.2,0);
                return renderInfo;
            }));
        }

        if(jerConfig.CQR_BOSSES.enableSpectreLord) {
            registerCQRMob(new EntityCQRSpectreLord(world), LightLevel.any, CQRLoottables.ENTITIES_SPECTRE_LORD);
        }

        if(jerConfig.CQR_BOSSES.enableAbyssWalkerKing) {
            EntityCQRWalkerKing walkerKing = new EntityCQRWalkerKing(world);
            walkerKing.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, new ItemStack(CQRItems.GREAT_SWORD_DIAMOND));
            walkerKing.setItemStackToSlot(EntityEquipmentSlot.OFFHAND, new ItemStack(CQRItems.SHIELD_WALKER_KING, 1));
            walkerKing.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(CQRItems.KING_CROWN, 1));
            registerCQRMob(walkerKing, LightLevel.any, CQRLoottables.ENTITIES_WALKER_KING);
        }
    }

    private void registerMobs() {
        if(jerConfig.CQR_MOBS.enableBoarman) {
            registerCQRMob(new EntityCQRBoarman(world), LightLevel.any, CQRLoottables.ENTITIES_BOARMAN);
        }

        if(jerConfig.CQR_MOBS.enableDwarf) {
            registerCQRMob(new EntityCQRDwarf(world), LightLevel.any, CQRLoottables.ENTITIES_DWARF);
        }

        if(jerConfig.CQR_MOBS.enableEnderman) {
            registerCQRMob(new EntityCQREnderman(world), LightLevel.any, CQRLoottables.ENTITIES_ENDERMAN);
        }

        if(jerConfig.CQR_MOBS.enableGoblin) {
            registerCQRMob(new EntityCQRGoblin(world), LightLevel.any, CQRLoottables.ENTITIES_GOBLIN);
        }

        if(jerConfig.CQR_MOBS.enableGolem) {
            registerCQRMob(new EntityCQRGolem(world), LightLevel.any, CQRLoottables.ENTITIES_GOLEM);
        }

        if(jerConfig.CQR_MOBS.enableGremlin) {
            registerCQRMob(new EntityCQRGremlin(world), LightLevel.any, CQRLoottables.ENTITIES_GREMLIN);
        }

        if(jerConfig.CQR_MOBS.enableHuman) {
            registerCQRMob(new EntityCQRHuman(world), LightLevel.any, CQRLoottables.ENTITIES_HUMAN);
        }

        if(jerConfig.CQR_MOBS.enableIllager) {
            registerCQRMob(new EntityCQRIllager(world), LightLevel.any, CQRLoottables.ENTITIES_ILLAGER);
        }

        if(jerConfig.CQR_MOBS.enableMandril) {
            registerCQRMob(new EntityCQRMandril(world), LightLevel.any, CQRLoottables.ENTITIES_MANDRIL);
        }

        if(jerConfig.CQR_MOBS.enableMinotaur) {
            registerCQRMob(new EntityCQRMinotaur(world), LightLevel.any, CQRLoottables.ENTITIES_MINOTAUR);
        }

        if(jerConfig.CQR_MOBS.enableMummy) {
            registerCQRMob(new EntityCQRMummy(world), LightLevel.any, CQRLoottables.ENTITIES_MUMMY);
        }

        if(jerConfig.CQR_MOBS.enableNPC) {
            registerCQRMob(new EntityCQRNPC(world), LightLevel.any, CQRLoottables.ENTITIES_NPC);
        }

        if(jerConfig.CQR_MOBS.enableOgre) {
            registerCQRMob(new EntityCQROgre(world), LightLevel.any, CQRLoottables.ENTITIES_OGRE);
        }

        if(jerConfig.CQR_MOBS.enableOrc) {
            registerCQRMob(new EntityCQROrc(world), LightLevel.any, CQRLoottables.ENTITIES_ORC);
        }

        if(jerConfig.CQR_MOBS.enablePirate) {
            registerCQRMob(new EntityCQRPirate(world), LightLevel.any, CQRLoottables.ENTITIES_PIRATE);
        }

        if(jerConfig.CQR_MOBS.enableSkeleton) {
            registerCQRMob(new EntityCQRSkeleton(world), LightLevel.any, CQRLoottables.ENTITIES_SKELETON);
        }

        if(jerConfig.CQR_MOBS.enableSpectre) {
            registerCQRMob(new EntityCQRSpectre(world), LightLevel.any, CQRLoottables.ENTITIES_SPECTRE);
        }

        if(jerConfig.CQR_MOBS.enableTriton) {
            registerCQRMob(new EntityCQRTriton(world), LightLevel.any, CQRLoottables.ENTITIES_TRITON);
        }

        if(jerConfig.CQR_MOBS.enableAbyssWalker) {
            registerCQRMob(new EntityCQRWalker(world), LightLevel.any, CQRLoottables.ENTITIES_WALKER);
        }

        if(jerConfig.CQR_MOBS.enableZombie) {
            registerCQRMob(new EntityCQRZombie(world), LightLevel.any, CQRLoottables.ENTITIES_ZOMBIE);
        }
    }

    private void registerMounts() {
        if(jerConfig.CQR_MOUNTS.enableGiantEndermite) {
            registerMob(new EntityGiantEndermite(world), LightLevel.any, CQRLoottables.ENTITIES_GIANT_ENDERMITE);
            registerRenderHook(EntityGiantEndermite.class, ((renderInfo, e) -> {
                GlStateManager.scale(1.35,1.35,1.35);
                GlStateManager.translate(-0.05,0.15,0);
                return renderInfo;
            }));
        }

        if(jerConfig.CQR_MOUNTS.enableGiantSilverfish) {
            registerMob(new EntityGiantSilverfishNormal(world), LightLevel.any, CQRLoottables.ENTITIES_GIANT_SILVERFISH);
            adjustMountRenderHook(EntityGiantSilverfishNormal.class);
        }

        if(jerConfig.CQR_MOUNTS.enableGiantSilverfishGreen) {
            registerMob(new EntityGiantSilverfishGreen(world), LightLevel.any, CQRLoottables.ENTITIES_GIANT_SILVERFISH_GREEN);
            adjustMountRenderHook(EntityGiantSilverfishGreen.class);
        }

        if(jerConfig.CQR_MOUNTS.enableGiantSilverfishRed) {
            registerMob(new EntityGiantSilverfishRed(world), LightLevel.any, CQRLoottables.ENTITIES_GIANT_SILVERFISH_RED);
            adjustMountRenderHook(EntityGiantSilverfishRed.class);
        }
    }
    
    private void registerCQRMob(EntityLivingBase entity, LightLevel lightLevel, ResourceLocation lootTable) {
        registerMob(entity, lightLevel, lootTable);
        registerRenderHook(entity.getClass(), (renderInfo, entityLivingBase) -> {
            GlStateManager.translate(-0.05,-0.45,0);
            return renderInfo;
        });
    }

    private void adjustMountRenderHook(Class<? extends EntityLivingBase> clazz) {
        registerRenderHook(clazz, ((renderInfo, e) -> {
            GlStateManager.scale(1.5,1.5,1.5);
            GlStateManager.translate(-0.05,0.4,0);
            return renderInfo;
        }));
    }
}
