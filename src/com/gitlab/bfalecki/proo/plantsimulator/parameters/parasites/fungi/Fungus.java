package com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.fungi;

import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.Parasite;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.DevelopmentState;

public abstract class Fungus extends Parasite {
    public Fungus(DevelopmentState state){
        super(state);
    }
}
