package com.invadermonky.justenoughmagiculture.util;

import net.minecraftforge.common.config.Config.Comment;

public class ConfigHelper {
    /**
     * General use JER Mob configuration that includes biome spawn settings.
     */
    public static class MobJER {
        public boolean enableMobJER;
        @Comment("JER Valid spawn biome types.")
        public String[] validBiomeTypes;
        @Comment("JER Invalid spawn biome types.")
        public String[] invalidBiomeTypes;

        public MobJER() {
            this.enableMobJER = true;
            this.validBiomeTypes = new String[0];
            this.invalidBiomeTypes = new String[0];
        }

        public MobJER(String[] validBiometypes) {
            this.enableMobJER = true;
            this.validBiomeTypes = validBiometypes;
            this.invalidBiomeTypes = new String[0];
        }

        public MobJER(String[] validBiomeTypes, String[] invalidBiomeTypes) {
            this.enableMobJER = true;
            this.validBiomeTypes = validBiomeTypes;
            this.invalidBiomeTypes = invalidBiomeTypes;
        }
    }
}
