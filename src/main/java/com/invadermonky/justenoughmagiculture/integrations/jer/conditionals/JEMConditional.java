package com.invadermonky.justenoughmagiculture.integrations.jer.conditionals;

import jeresources.api.conditionals.Conditional;

public class JEMConditional {
    public static final Conditional killedWithAxe = new Conditional("jem.axeKill.text", Conditional.belowLooting);
    public static final Conditional killedWithShovel = new Conditional("jem.shovelKill.text", Conditional.belowLooting);
}
