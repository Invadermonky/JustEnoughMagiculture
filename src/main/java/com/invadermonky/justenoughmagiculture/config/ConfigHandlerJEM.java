package com.invadermonky.justenoughmagiculture.config;

import com.invadermonky.justenoughmagiculture.JustEnoughMagiculture;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(
        modid = JustEnoughMagiculture.MOD_ID
)
public class ConfigHandlerJEM {
    public static final JERPlantConfig jer_crops = new JERPlantConfig();
    public static final JERDungeonsConfig jer_dungeons = new JERDungeonsConfig();
    public static final JEREntityConfig jer_entities = new JEREntityConfig();

    public static class JERDungeonsConfig {
        public boolean ars_magica_2 = true;
        public boolean astral_sorcery = true;
    }

    public static class JEREntityConfig {
        public boolean ad_inferos = true;
        public boolean ars_magica_2 = true;
        public boolean deadly_monsters = true;
        public boolean embers = true;
        public boolean ender_zoo = true;
        public boolean thaumcraft = true;
    }

    public static class JERPlantConfig {
        public boolean ad_inferos = true;
        //TODO: public boolean harvestcraft = true;
        public boolean mystical_agriculture = true;
        public boolean unique_crops = true;
    }

    @Mod.EventBusSubscriber
    public static class ConfigChangeListener {
        @SubscribeEvent
        public static void onConfigChange(ConfigChangedEvent.OnConfigChangedEvent event) {
            if(event.getModID().equals(JustEnoughMagiculture.MOD_ID)) {
                ConfigManager.load(JustEnoughMagiculture.MOD_ID, Type.INSTANCE);
            }
        }
    }
}
