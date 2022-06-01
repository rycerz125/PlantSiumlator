package com.gitlab.bfalecki.proo.plantsimulator.healthyactions;

import com.gitlab.bfalecki.proo.plantsimulator.Simulator;

import java.io.Serializable;

public class InsolationAction extends HealthyAction implements Serializable {
    private final Direction directionOfChange;

    public InsolationAction(Direction directionOfChange){
        this.directionOfChange = directionOfChange;
        totalDuration = 5;
        remainingTime = totalDuration;
    }

    @Override
    public void performActionPart() {
        if (directionOfChange.equals(Direction.UP))
            Simulator.plant.getInsolationAccess().add(2f);
        else if (directionOfChange.equals(Direction.DOWN))
            Simulator.plant.getInsolationAccess().add(-4f);
    }
}
