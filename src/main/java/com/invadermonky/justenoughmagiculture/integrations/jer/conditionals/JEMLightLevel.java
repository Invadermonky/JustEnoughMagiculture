package com.invadermonky.justenoughmagiculture.integrations.jer.conditionals;

import jeresources.api.conditionals.LightLevel;

import java.lang.reflect.Constructor;

public class JEMLightLevel {
    public static LightLevel animal = custom(8, true);

    public static LightLevel custom(int level, boolean above) {
        try {
            Constructor<LightLevel> constructor = LightLevel.class.getDeclaredConstructor(int.class , LightLevel.Relative.class);
            constructor.setAccessible(true);
            LightLevel lightLevel;
            if (above)
                lightLevel = constructor.newInstance(level, LightLevel.Relative.above);
            else
                lightLevel = constructor.newInstance(level, LightLevel.Relative.below);
            return lightLevel;
        } catch (Exception ignored) {
            return LightLevel.any;
        }
    }
}
