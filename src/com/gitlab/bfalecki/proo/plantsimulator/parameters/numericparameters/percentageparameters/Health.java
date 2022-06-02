package com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters;

public class Health extends PercentageParameter {
    public Health(){this(new PercentageValue(100));}
    public Health(PercentageValue value){
        super(value);
    }
}
