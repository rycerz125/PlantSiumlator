package com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.pollutions;

import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.PercentageParameter;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.PercentageValue;

public class Pollution extends PercentageParameter {
    public Pollution(PercentageValue value){
        super(value);
    }
    public Pollution(){this(new PercentageValue());}
}
