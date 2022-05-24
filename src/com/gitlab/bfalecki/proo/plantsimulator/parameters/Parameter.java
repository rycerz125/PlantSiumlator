package com.gitlab.bfalecki.proo.plantsimulator.parameters;

public abstract class Parameter {
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
