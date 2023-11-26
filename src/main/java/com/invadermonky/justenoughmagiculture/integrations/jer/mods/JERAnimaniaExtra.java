package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.animania.addons.extra.common.entity.amphibians.EntityAmphibian;
import com.animania.addons.extra.common.entity.amphibians.EntityDartFrogs;
import com.animania.addons.extra.common.entity.amphibians.EntityFrogs;
import com.animania.addons.extra.common.entity.amphibians.EntityToad;
import com.animania.addons.extra.common.entity.peafowl.EntityAnimaniaPeacock;
import com.animania.addons.extra.common.entity.peafowl.EntityPeachickBase;
import com.animania.addons.extra.common.entity.peafowl.EntityPeafowlBase;
import com.animania.addons.extra.common.entity.peafowl.PeafowlBlue.EntityPeacockBlue;
import com.animania.addons.extra.common.entity.peafowl.PeafowlBlue.EntityPeafowlBlue;
import com.animania.addons.extra.common.entity.peafowl.PeafowlCharcoal.EntityPeacockCharcoal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlCharcoal.EntityPeafowlCharcoal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlOpal.EntityPeacockOpal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlOpal.EntityPeafowlOpal;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPeach.EntityPeacockPeach;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPeach.EntityPeafowlPeach;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPurple.EntityPeacockPurple;
import com.animania.addons.extra.common.entity.peafowl.PeafowlPurple.EntityPeafowlPurple;
import com.animania.addons.extra.common.entity.peafowl.PeafowlTaupe.EntityPeacockTaupe;
import com.animania.addons.extra.common.entity.peafowl.PeafowlTaupe.EntityPeafowlTaupe;
import com.animania.addons.extra.common.entity.peafowl.PeafowlWhite.EntityPeacockWhite;
import com.animania.addons.extra.common.entity.peafowl.PeafowlWhite.EntityPeafowlWhite;
import com.animania.addons.extra.common.entity.rodents.*;
import com.animania.addons.extra.common.entity.rodents.rabbits.EntityAnimaniaRabbit;
import com.animania.addons.extra.common.entity.rodents.rabbits.EntityRabbitKitBase;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.EntityRabbitBuckChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitChinchilla.EntityRabbitDoeChinchilla;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.EntityRabbitBuckCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitCottonail.EntityRabbitDoeCottontail;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitDutch.EntityRabbitBuckDutch;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitDutch.EntityRabbitDoeDutch;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.EntityRabbitBuckHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitHavana.EntityRabbitDoeHavana;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.EntityRabbitBuckJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitJack.EntityRabbitDoeJack;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.EntityRabbitBuckLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitLop.EntityRabbitDoeLop;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitNewZealand.EntityRabbitBuckNewZealand;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitNewZealand.EntityRabbitDoeNewZealand;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitRex.EntityRabbitBuckRex;
import com.animania.addons.extra.common.entity.rodents.rabbits.RabbitRex.EntityRabbitDoeRex;
import com.animania.addons.extra.config.ExtraConfig;
import com.invadermonky.justenoughmagiculture.client.render.entity.mods.animania.peafowl.RenderPeafowlBaseJER;
import com.invadermonky.justenoughmagiculture.client.render.entity.mods.animania.rabbits.*;
import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.configs.mods.JEMConfigAnimaniaExtra;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import com.invadermonky.justenoughmagiculture.integrations.jer.conditionals.JEMLightLevel;
import com.invadermonky.justenoughmagiculture.util.BiomeHelper;
import com.invadermonky.justenoughmagiculture.util.ModIds;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class JERAnimaniaExtra extends JERBase implements IJERIntegration {
    private static JERAnimaniaExtra instance;
    private static final JEMConfigAnimaniaExtra.JER.Animals jerConfig = JEMConfig.ANIMANIA_EXTRA.JUST_ENOUGH_RESOURCES.ANIMALS;
    private static final boolean registerMales = JEMConfig.ANIMANIA_EXTRA.JUST_ENOUGH_RESOURCES.registerMaleAnimals;
    private static final boolean registerFemales = JEMConfig.ANIMANIA_EXTRA.JUST_ENOUGH_RESOURCES.registerFemaleAnimals;

    private JERAnimaniaExtra() {}

    public JERAnimaniaExtra(boolean registerJERMobs) {
        if(registerJERMobs) registerModEntities();
    }

    public static JERAnimaniaExtra getInstance() {
        return instance == null ? instance = new JERAnimaniaExtra() : instance;
    }

    @Override
    public void registerModEntities() {
        registerAmphibians();
        registerHamsters();
        registerHedgehogs();
        registerFerrets();
        registerPeafowl();
        registerRabbits();
    }

    private void registerAmphibians() {
        if (jerConfig.enableDartFrog) {
            EntityDartFrogs dartFrog = new EntityDartFrogs(world);
            registerMob(dartFrog, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.dartFrogBiomeTypes), getAmphibianLootTable(dartFrog));
            registerRenderHook(dartFrog.getClass(), ((renderInfo, e) -> {
                GlStateManager.scale(2.0,2.0,2.0);
                GlStateManager.translate(0,-0.05,0);
                return renderInfo;
            }));
        }

        if (jerConfig.enableFrog) {
            EntityFrogs frog = new EntityFrogs(world);
            registerMob(frog, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.frogBiomeTypes), getAmphibianLootTable(frog));
            registerRenderHook(frog.getClass(), ((renderInfo, e) -> {
                GlStateManager.scale(2.0,2.0,2.0);
                GlStateManager.translate(0,-0.05,0);
                return renderInfo;
            }));
        }

        if (jerConfig.enableToad) {
            EntityToad toad = new EntityToad(world);
            registerMob(toad, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.toadBiomeTypes), getAmphibianLootTable(toad));
            registerRenderHook(toad.getClass(), ((renderInfo, e) -> {
                GlStateManager.scale(2.0,2.0,2.0);
                GlStateManager.translate(0,-0.05,0);
                return renderInfo;
            }));
        }
    }

    private void registerHamsters() {
        if (jerConfig.enableHamster) {
            registerMob(new EntityHamster(world), JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.hamsterBiomeTypes), getHamsterLootTable());
        }
    }

    private void registerHedgehogs() {
        if(jerConfig.enableHedgehogAlbino) {
            registerMob(new EntityHedgehogAlbino(world), JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.hedgehogAlbinoBiomeTypes), getHedgehogLootTable());
        }

        if(jerConfig.enableHedgehog) {
            registerMob(new EntityHedgehog(world), JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.hedgehogBiomeTypes), getHedgehogLootTable());        }
    }

    private void registerFerrets() {
        if(jerConfig.enableFerretGray) {
            registerMob(new EntityFerretGrey(world), JEMLightLevel.animal, BiomeHelper.getBiomeNamesForBiomes(ExtraConfig.settings.spawning_and_breeding.ferretGrayBiomeTypes), getFerretLootTable());
        }

        if(jerConfig.enableFerretWhite) {
            registerMob(new EntityFerretWhite(world), JEMLightLevel.animal, BiomeHelper.getBiomeNamesForBiomes(ExtraConfig.settings.spawning_and_breeding.ferretWhiteBiomeTypes), getFerretLootTable());
        }
    }

    private void registerPeafowl() {
        if(jerConfig.enablePeafowlBlue) {
            if(registerMales) {
                EntityPeacockBlue peacockBlue = new EntityPeacockBlue(world);
                registerMob(peacockBlue, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.peafowlBlueBiomeTypes), getPeafowlLootTable(peacockBlue));
            }
            if(registerFemales) {
                EntityPeafowlBlue peafowlBlue = new EntityPeafowlBlue(world);
                registerMob(peafowlBlue, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.peafowlBlueBiomeTypes), getPeafowlLootTable(peafowlBlue));
            }
        }

        if(jerConfig.enablePeafowlCharcoal) {
            if(registerMales) {
                EntityPeacockCharcoal peacockCharcoal = new EntityPeacockCharcoal(world);
                registerMob(peacockCharcoal, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.peafowlCharcoalBiomeTypes), getPeafowlLootTable(peacockCharcoal));
            }
            if(registerFemales) {
                EntityPeafowlCharcoal peafowlCharcoal = new EntityPeafowlCharcoal(world);
                registerMob(peafowlCharcoal, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.peafowlCharcoalBiomeTypes), getPeafowlLootTable(peafowlCharcoal));
            }
        }

        if(jerConfig.enablePeafowlOpal) {
            if(registerMales) {
                EntityPeacockOpal peacockOpal = new EntityPeacockOpal(world);
                registerMob(peacockOpal, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.peafowlOpalBiomeTypes), getPeafowlLootTable(peacockOpal));
            }
            if(registerFemales) {
                EntityPeafowlOpal peafowlOpal = new EntityPeafowlOpal(world);
                registerMob(peafowlOpal, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.peafowlOpalBiomeTypes), getPeafowlLootTable(peafowlOpal));
            }
        }

        if(jerConfig.enablePeafowlPeach) {
            if(registerMales) {
                EntityPeacockPeach peacockPeach = new EntityPeacockPeach(world);
                registerMob(peacockPeach, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.peafowlPeachBiomeTypes), getPeafowlLootTable(peacockPeach));
            }
            if(registerFemales) {
                EntityPeafowlPeach peafowlPeach = new EntityPeafowlPeach(world);
                registerMob(peafowlPeach, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.peafowlPeachBiomeTypes), getPeafowlLootTable(peafowlPeach));
            }
        }

        if(jerConfig.enablePeafowlPurple) {
            if(registerMales) {
                EntityPeacockPurple peacockPurple = new EntityPeacockPurple(world);
                registerMob(peacockPurple, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.peafowlPurpleBiomeTypes), getPeafowlLootTable(peacockPurple));
            }
            if(registerFemales) {
                EntityPeafowlPurple peafowlPurple = new EntityPeafowlPurple(world);
                registerMob(peafowlPurple, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.peafowlPurpleBiomeTypes), getPeafowlLootTable(peafowlPurple));
            }
        }

        if(jerConfig.enablePeafowlTaupe) {
            if(registerMales) {
                EntityPeacockTaupe peacockTaupe = new EntityPeacockTaupe(world);
                registerMob(peacockTaupe, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.peafowlTaupeBiomeTypes), getPeafowlLootTable(peacockTaupe));
            }
            if(registerFemales) {
                EntityPeafowlTaupe peafowlTaupe = new EntityPeafowlTaupe(world);
                registerMob(peafowlTaupe, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.peafowlTaupeBiomeTypes), getPeafowlLootTable(peafowlTaupe));
            }
        }

        if(jerConfig.enablePeafowlWhite) {
            if(registerMales) {
                EntityPeacockWhite peacockWhite = new EntityPeacockWhite(world);
                registerMob(peacockWhite, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.peafowlWhiteBiomeTypes), getPeafowlLootTable(peacockWhite));
            }
            if(registerFemales) {
                EntityPeafowlWhite peafowlWhite = new EntityPeafowlWhite(world);
                registerMob(peafowlWhite, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.peafowlWhiteBiomeTypes), getPeafowlLootTable(peafowlWhite));
            }
        }

        if(registerFemales) {
            registerRenderHook(EntityPeafowlBase.class, ((renderInfo, e) -> {
                GlStateManager.translate(0.24, 0.4, 0);
                return renderInfo;
            }));
        }
    }

    private void registerRabbits() {
        if(jerConfig.enableRabbitChinchilla) {
            if(registerMales) {
                EntityRabbitBuckChinchilla buckChinchilla = new EntityRabbitBuckChinchilla(world);
                registerMob(buckChinchilla, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.rabbitChinchillaBiomeTypes), getRabbitLootTable(buckChinchilla));
            }
            if(registerFemales) {
                EntityRabbitDoeChinchilla doeChinchilla = new EntityRabbitDoeChinchilla(world);
                registerMob(doeChinchilla, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.rabbitChinchillaBiomeTypes), getRabbitLootTable(doeChinchilla));
            }
        }

        if(jerConfig.enableRabbitCottontail) {
            if(registerMales) {
                EntityRabbitBuckCottontail buckCottontail = new EntityRabbitBuckCottontail(world);
                registerMob(buckCottontail, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.rabbitCottontailBiomeTypes), getRabbitLootTable(buckCottontail));
            }
            if(registerFemales) {
                EntityRabbitDoeCottontail doeCottontail = new EntityRabbitDoeCottontail(world);
                registerMob(doeCottontail, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.rabbitCottontailBiomeTypes), getRabbitLootTable(doeCottontail));
            }
        }

        if(jerConfig.enableRabbitDutch) {
            if(registerMales) {
                EntityRabbitBuckDutch buckDutch = new EntityRabbitBuckDutch(world);
                registerMob(buckDutch, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.rabbitDutchBiomeTypes), getRabbitLootTable(buckDutch));
            }
            if(registerFemales) {
                EntityRabbitDoeDutch doeDutch = new EntityRabbitDoeDutch(world);
                registerMob(doeDutch, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.rabbitDutchBiomeTypes), getRabbitLootTable(doeDutch));
            }
        }

        if(jerConfig.enableRabbitHavana) {
            if(registerMales) {
                EntityRabbitBuckHavana buckHavana = new EntityRabbitBuckHavana(world);
                registerMob(buckHavana, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.rabbitHavanaBiomeTypes), getRabbitLootTable(buckHavana));
            }
            if(registerFemales) {
                EntityRabbitDoeHavana doeHavana = new EntityRabbitDoeHavana(world);
                registerMob(doeHavana, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.rabbitHavanaBiomeTypes), getRabbitLootTable(doeHavana));
            }
        }

        if(jerConfig.enableRabbitJack) {
            if(registerMales) {
                EntityRabbitBuckJack buckJack = new EntityRabbitBuckJack(world);
                registerMob(buckJack, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.rabbitJackBiomeTypes), getRabbitLootTable(buckJack));
            }
            if(registerFemales) {
                EntityRabbitDoeJack doeJack = new EntityRabbitDoeJack(world);
                registerMob(doeJack, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.rabbitJackBiomeTypes), getRabbitLootTable(doeJack));
            }
        }

        if(jerConfig.enableRabbitLop) {
            if(registerMales) {
                EntityRabbitBuckLop buckLop = new EntityRabbitBuckLop(world);
                registerMob(buckLop, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.rabbitLopBiomeTypes), getRabbitLootTable(buckLop));
            }
            if(registerFemales) {
                EntityRabbitDoeLop doeLop = new EntityRabbitDoeLop(world);
                registerMob(doeLop, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.rabbitLopBiomeTypes), getRabbitLootTable(doeLop));
            }
        }

        if(jerConfig.enableRabbitNewZealand) {
            if(registerMales) {
                EntityRabbitBuckNewZealand buckNewZealand = new EntityRabbitBuckNewZealand(world);
                registerMob(buckNewZealand, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.rabbitNewZealandBiomeTypes), getRabbitLootTable(buckNewZealand));
            }
            if(registerFemales) {
                EntityRabbitDoeNewZealand doeNewZealand = new EntityRabbitDoeNewZealand(world);
                registerMob(doeNewZealand, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.rabbitNewZealandBiomeTypes), getRabbitLootTable(doeNewZealand));
            }
        }

        if(jerConfig.enableRabbitRex) {
            if (registerMales) {
                EntityRabbitBuckRex buckRex = new EntityRabbitBuckRex(world);
                registerMob(buckRex, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.rabbitRexBiomeTypes), getRabbitLootTable(buckRex));
            }
            if (registerFemales) {
                EntityRabbitDoeRex doeRex = new EntityRabbitDoeRex(world);
                registerMob(doeRex, JEMLightLevel.animal, BiomeHelper.getBiomeNamesForTypes(ExtraConfig.settings.spawning_and_breeding.rabbitRexBiomeTypes), getRabbitLootTable(doeRex));
            }
        }
    }

    public void registerRenderOverrides() {
        RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlBlue.class, RenderPeafowlBaseJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlCharcoal.class, RenderPeafowlBaseJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlOpal.class, RenderPeafowlBaseJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlPeach.class, RenderPeafowlBaseJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlPurple.class, RenderPeafowlBaseJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlTaupe.class, RenderPeafowlBaseJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityPeafowlWhite.class, RenderPeafowlBaseJER.FACTORY);

        RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckChinchilla.class, RenderBuckChinchillaJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeChinchilla.class, RenderDoeChinchillaJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckCottontail.class, RenderBuckCottontailJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeCottontail.class, RenderDoeCottontailJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckDutch.class, RenderBuckDutchJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeDutch.class, RenderDoeDutchJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckHavana.class, RenderBuckHavanaJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeHavana.class, RenderDoeHavanaJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckJack.class, RenderBuckJackJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeJack.class, RenderDoeJackJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckNewZealand.class, RenderBuckNewZealandJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeNewZealand.class, RenderDoeNewZealandJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckRex.class, RenderBuckRexJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeRex.class, RenderDoeRexJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRabbitBuckLop.class, RenderBuckLopJER.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityRabbitDoeLop.class, RenderDoeLopJER.FACTORY);
    }

    private ResourceLocation getAmphibianLootTable(EntityAmphibian entity) {
        if(entity instanceof EntityDartFrogs)
            return new ResourceLocation("extra/" + ModIds.ANIMANIA.MOD_ID, "dart_frog");
        else if(entity instanceof EntityFrogs)
            return new ResourceLocation("extra/" + ModIds.ANIMANIA.MOD_ID, "frog");
        else if(entity instanceof EntityToad)
            return new ResourceLocation("extra/" + ModIds.ANIMANIA.MOD_ID, "toad");

        return null;
    }

    private ResourceLocation getHamsterLootTable() {
        return new ResourceLocation("extra/" + ModIds.ANIMANIA.MOD_ID, "hamster");
    }

    private ResourceLocation getHedgehogLootTable() {
        return new ResourceLocation("extra/" + ModIds.ANIMANIA.MOD_ID, "hedgehog");
    }

    private ResourceLocation getFerretLootTable() {
        return new ResourceLocation("extra/" + ModIds.ANIMANIA.MOD_ID, "ferret");
    }

    private ResourceLocation getPeafowlLootTable(EntityAnimaniaPeacock entity) {
        return entity instanceof EntityPeachickBase ? null : new ResourceLocation("extra/" + ModIds.ANIMANIA.MOD_ID, "peacocks/peacock_" + entity.type.toString().toLowerCase());
    }

    private ResourceLocation getRabbitLootTable(EntityAnimaniaRabbit entity) {
        return entity instanceof EntityRabbitKitBase ? null : entity.rabbitType.isPrime ? new ResourceLocation("extra/" + ModIds.ANIMANIA.MOD_ID, "rabbit_prime") : new ResourceLocation("extra/" + ModIds.ANIMANIA.MOD_ID, "rabbit_regular");
    }
}
