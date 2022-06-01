package com.gitlab.bfalecki.proo.plantsimulator.healthyactions;

import com.gitlab.bfalecki.proo.plantsimulator.Simulator;

import java.io.Serializable;

public class SoilPHAction extends HealthyAction implements Serializable {
    private final Direction directionOfChange;

    public SoilPHAction(Direction directionOfChange){
        this.directionOfChange = directionOfChange;
        totalDuration = 7;
        remainingTime = totalDuration;
    }

    @Override
    public void performActionPart() {
        if (directionOfChange.equals(Direction.UP))
            Simulator.plant.getSoilPHAccess().add(0.1f);
        else if (directionOfChange.equals(Direction.DOWN))
            Simulator.plant.getSoilPHAccess().add(-0.1f);
    }
}
