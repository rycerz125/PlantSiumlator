package com.gitlab.bfalecki.proo.plantsimulator.healthyactions;

import com.gitlab.bfalecki.proo.plantsimulator.Simulator;

import java.io.Serializable;

public class ReducePollutionAction extends HealthyAction implements Serializable {
    private Class pollutionClassToReduce;
    public ReducePollutionAction(Class pollutionClass){
        pollutionClassToReduce = pollutionClass;
        totalDuration = 6;
        remainingTime = totalDuration;
    }
    @Override
    public void performActionPart() {
            Simulator.plant.getPollutionsAccess().increasePollution(pollutionClassToReduce, -2);

    }
}
