package com.invadermonky.justenoughmagiculture.integrations.jer.mods;

import com.invadermonky.justenoughmagiculture.configs.JEMConfig;
import com.invadermonky.justenoughmagiculture.integrations.jer.IJERIntegration;
import com.invadermonky.justenoughmagiculture.integrations.jer.JERBase;
import crazypants.enderio.base.init.ModObject;
import jeresources.api.conditionals.Conditional;
import jeresources.api.drop.LootDrop;
import jeresources.entry.MobEntry;
import jeresources.registry.MobRegistry;
import net.minecraft.entity.monster.EntityEnderman;

import java.lang.reflect.Field;
import java.util.Set;

public class JEREnderIO extends JERBase implements IJERIntegration {
    private static JEREnderIO instance;

    private JEREnderIO() {}

    public static JEREnderIO getInstance() {
        return instance == null ? instance = new JEREnderIO() : instance;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void injectLoot() {
        if(JEMConfig.ENDER_IO.enableJERInjectedLoot) {
            for (MobEntry mobEntry : MobRegistry.getInstance().getMobs()) {
                if (mobEntry.getEntity() instanceof EntityEnderman) {
                    try {
                        Field entryField = MobRegistry.getInstance().getClass().getDeclaredField("registry");
                        entryField.setAccessible(true);
                        Set<MobEntry> mobEntries = (Set<MobEntry>) entryField.get(MobRegistry.getInstance());

                        LootDrop headDrop = new LootDrop(ModObject.blockEndermanSkull.getItem(), 0, 1, 0.05f, Conditional.affectedByLooting);

                        mobEntries.remove(mobEntry);
                        mobEntry.addDrop(headDrop);
                        mobEntries.add(mobEntry);
                    } catch (Exception ignored) {}
                }
            }
        }
    }
}
