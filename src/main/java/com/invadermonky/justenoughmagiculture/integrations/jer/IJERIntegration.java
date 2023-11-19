package com.invadermonky.justenoughmagiculture.integrations.jer;

public interface IJERIntegration {
    default void registerModDungeons() {}

    default void registerModEntities() {}

    default void registerModPlants() {}

    default void registerModVillagers() {}

    default void injectLoot() {}
}
