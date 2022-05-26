package com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters;


public class TemperatureValue extends NumericValue {
    public TemperatureValue(float value){
        if (value < -1) this.value = -1;
        else if (value > 50) this.value = 50;
        else this.value = value;
    }
    public TemperatureValue(){this(20);}
}
