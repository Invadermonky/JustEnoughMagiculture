package com.invadermonky.justenoughmagiculture.integrations.jer.conditionals;

import jeresources.api.conditionals.Conditional;
import jeresources.api.render.TextModifier;

public class JEMConditional {
    public static final Conditional killedWithAxe = new Conditional("jem.axeKill.text", Conditional.belowLooting);
    public static final Conditional killedWithShovel = new Conditional("jem.shovelKill.text", Conditional.belowLooting);
    public static final Conditional squashed = new Conditional("jem.squashed.text", TextModifier.darkGreen);
    public static final Conditional notSquashed = new Conditional("jem.notSquashed.text", TextModifier.lightRed);
}
