package com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites;

import com.gitlab.bfalecki.proo.plantsimulator.parameters.Parameter;

public abstract class Parasite extends Parameter {
    public Parasite(DevelopmentState state){
        super(state);
    }
    public void increaseDevelopment(){
        setValue(   new DevelopmentState(  ( (DevelopmentState)getValue() ).asEnum().next()  )   );
    }
    public void decreaseDevelopment(){
        setValue(   new DevelopmentState(  ( (DevelopmentState)getValue() ).asEnum().previous()  )   );
    }
}
