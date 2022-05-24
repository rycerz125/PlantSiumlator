package com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters;

public class Irrigation extends PercentageParameter {
    public Irrigation(){
        this(new PercentageValue());
    }
    public Irrigation(PercentageValue value){
        super(value);
    }
}
