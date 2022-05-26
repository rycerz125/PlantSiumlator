package com.gitlab.bfalecki.proo.plantsimulator.plants;

import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.PercentageValue;

public class Philodendron extends Plant{
    @Override
    public String getSystematicName(){
        return "Philodendron";
    }
    @Override
    public void calculateHealth() {
        float healthIncrease = 11;
        healthIncrease += 10;
        this.getHealthAccess().setValue(
                new PercentageValue(
                        ((PercentageValue) getHealthAccess().getValue()).asFloat() + healthIncrease
                )
        );
    }
}
