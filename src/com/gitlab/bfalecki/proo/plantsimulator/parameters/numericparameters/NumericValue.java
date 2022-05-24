package com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters;

import com.gitlab.bfalecki.proo.plantsimulator.parameters.ParameterValue;

public abstract class NumericValue extends ParameterValue {
    protected float value;
    public float asFloat(){
        return value;
    }
}
