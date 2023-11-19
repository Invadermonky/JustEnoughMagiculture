package com.invadermonky.justenoughmagiculture.util;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;

import java.util.Iterator;

public class BlockStateHelper {
    public static IProperty getProperty(IBlockState state, String propertyName) {
        Iterator var2 = state.getPropertyKeys().iterator();

        IProperty property;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            property = (IProperty)var2.next();
        } while(!property.getName().equalsIgnoreCase(propertyName));

        return property;
    }

    public static Comparable getPropertyValue(IProperty property, String propertyValue) {
        Iterator var2 = property.getAllowedValues().iterator();

        Object value;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            value = var2.next();
        } while(!value.toString().equalsIgnoreCase(propertyValue));

        return value instanceof Comparable ? (Comparable)value : null;
    }
}
