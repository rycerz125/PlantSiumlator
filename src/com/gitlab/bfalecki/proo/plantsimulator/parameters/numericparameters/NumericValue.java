package com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters;

import com.gitlab.bfalecki.proo.plantsimulator.parameters.ParameterValue;

import java.io.Serializable;

public abstract class NumericValue extends ParameterValue implements Serializable {
    protected float value;
    public float asFloat(){
        return value;
    }
}
