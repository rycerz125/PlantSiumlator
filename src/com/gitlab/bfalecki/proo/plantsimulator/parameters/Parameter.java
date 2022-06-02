package com.gitlab.bfalecki.proo.plantsimulator.parameters;


import java.io.Serializable;

public abstract class Parameter implements Serializable {
    private ParameterValue value;
    public void setValue(ParameterValue value){
        this.value = value;
    }
    public ParameterValue getValue(){
        return value;
    }
    public Parameter(ParameterValue value){
        this.value = value;
    }

}
