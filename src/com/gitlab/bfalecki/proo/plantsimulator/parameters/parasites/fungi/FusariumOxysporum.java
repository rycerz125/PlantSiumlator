package com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.fungi;

import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.DevelopmentState;

public class FusariumOxysporum extends Fungus{
    public FusariumOxysporum(DevelopmentState state){
        super(state);
    }
    public FusariumOxysporum(){
        this(new DevelopmentState());
    }
}
