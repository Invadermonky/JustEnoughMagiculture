package com.invadermonky.justenoughmagiculture.jer;

import com.invadermonky.justenoughmagiculture.init.JERIntegration;
import net.minecraft.world.World;

public abstract class AbstractJerIntegration {
    protected World world = JERIntegration.jerWorld;
    public void initJerDungeons() {};
    public void initJerEntities() {};
    public void initJerPlants() {};
}
