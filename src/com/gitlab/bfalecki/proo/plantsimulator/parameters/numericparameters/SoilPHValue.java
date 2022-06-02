package com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters;

public class SoilPHValue extends NumericValue{
    public SoilPHValue(float value){
        if (value < 3.5) this.value = 3.5f;
        else if (value > 10.5) this.value = 10.5f;
        else this.value = value;
    }
    public SoilPHValue(){this(6.5f);}
}
