package com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters;

import com.gitlab.bfalecki.proo.plantsimulator.parameters.Parameter;

public class Temperature extends Parameter {
    public Temperature(){
        this(new TemperatureValue());
    }
    public Temperature(TemperatureValue value){
        super(value);
    }
    public void add(float value){
        setValue(   new TemperatureValue(  ( (TemperatureValue)getValue() ).asFloat() + value  )   );
    }
}
