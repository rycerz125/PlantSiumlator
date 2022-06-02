package com.gitlab.bfalecki.proo.plantsimulator.healthyactions;

import com.gitlab.bfalecki.proo.plantsimulator.Simulator;

import java.io.Serializable;

public class IrrigationAction extends HealthyAction implements Serializable {

    public IrrigationAction(){
        totalDuration = 4;
        remainingTime = totalDuration;
    }

    @Override
    public void performActionPart() {
        Simulator.plant.getIrrigationAccess().add(4);
    }
}
