package com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.Parameter;

public class SoilPH extends Parameter {
    public SoilPH(){
        this(new SoilPHValue());
    }
    public SoilPH(SoilPHValue value){
        super(value);
    }
    public void add(float value){
        setValue(   new SoilPHValue(  ( (SoilPHValue)getValue() ).asFloat() + value  )   );
    }
}
