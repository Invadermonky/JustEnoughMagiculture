package com.invadermonky.justenoughmagiculture.integrations.jer.conditionals;

import jeresources.api.conditionals.Conditional;
import jeresources.api.render.TextModifier;

public class JEMConditional {
    public static final Conditional affectedByAncient = new Conditional("jem.affectedByAncient.text", TextModifier.purple);
    public static final Conditional affectedByResearch = new Conditional("jem.affectedByResearch", TextModifier.purple);
    public static final Conditional dependsOnAge = new Conditional("jem.dependsOnAge.text", TextModifier.orange);
    public static final Conditional dependsOnSize = new Conditional("jem.dependsOnSize.text", TextModifier.lightGreen);
    public static final Conditional dependsOnVariant = new Conditional("jem.dependsOnVariant.text", TextModifier.lightCyan);
    public static final Conditional isAdult = new Conditional("jem.adult.text", TextModifier.orange);
    public static final Conditional isNotAdult = new Conditional("jem.notAdult.text", isAdult);
    public static final Conditional killedWithAxe = new Conditional("jem.axeKill.text", Conditional.belowLooting);
    public static final Conditional killedWithShovel = new Conditional("jem.shovelKill.text", Conditional.belowLooting);
    public static final Conditional requiresBottle = new Conditional("jem.requiresBottle.text", TextModifier.white);
    public static final Conditional isSquashed = new Conditional("jem.squashed.text", TextModifier.darkGreen);
    public static final Conditional isNotSquashed = new Conditional("jem.notSquashed.text", TextModifier.lightRed);
}
