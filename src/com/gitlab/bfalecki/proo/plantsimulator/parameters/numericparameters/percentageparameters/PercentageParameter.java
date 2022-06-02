package com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters;

import com.gitlab.bfalecki.proo.plantsimulator.parameters.Parameter;

public abstract class PercentageParameter extends Parameter {
    public PercentageParameter(PercentageValue value){
        super(value);
    }
    public void add(float value){
        setValue(   new PercentageValue(  ( (PercentageValue)getValue() ).asFloat() + value  )   );
    }
}
