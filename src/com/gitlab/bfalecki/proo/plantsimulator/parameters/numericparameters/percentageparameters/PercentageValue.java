package com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.NumericValue;

public class PercentageValue extends NumericValue {
    public PercentageValue(float value){
        if (value < 0) this.value = 0;
        else if (value > 100) this.value = 100;
        else this.value = value;
    }
    public PercentageValue(){
        this(0);
    }
}
