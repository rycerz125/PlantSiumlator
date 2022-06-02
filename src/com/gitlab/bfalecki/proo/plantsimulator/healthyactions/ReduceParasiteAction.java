package com.gitlab.bfalecki.proo.plantsimulator.healthyactions;

import com.gitlab.bfalecki.proo.plantsimulator.Simulator;

import java.io.Serializable;

public class ReduceParasiteAction extends HealthyAction implements Serializable {
    private Class parasiteClassToReduce;
    public ReduceParasiteAction(Class parasiteClass){
        parasiteClassToReduce = parasiteClass;
        totalDuration = 20;
        remainingTime = totalDuration;
    }
    @Override
    public void performActionPart() {
        if(remainingTime == 0){
            Simulator.plant.getParasitesAccess().decreaseParasiteDevelopment(parasiteClassToReduce);
        }
    }
}
