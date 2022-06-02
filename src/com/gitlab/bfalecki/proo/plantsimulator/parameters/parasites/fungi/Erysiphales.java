package com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.fungi;

import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.DevelopmentState;

public class Erysiphales extends Fungus{
    public Erysiphales(DevelopmentState state){
        super(state);
    }
    public Erysiphales(){
        this(new DevelopmentState());
    }
}
