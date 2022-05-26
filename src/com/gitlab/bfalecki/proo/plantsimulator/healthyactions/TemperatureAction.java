package com.gitlab.bfalecki.proo.plantsimulator.healthyactions;

import com.gitlab.bfalecki.proo.plantsimulator.Simulator;

import java.io.Serializable;

public class TemperatureAction extends HealthyAction implements Serializable {
    private final Direction directionOfChange;

    public enum Direction{
        UP, DOWN
    }
    public TemperatureAction(Direction directionOfChange){
        this.directionOfChange = directionOfChange;
        totalDuration = 10;
        remainingTime = totalDuration;
    }



    @Override
    public void performActionPart() {
        if (directionOfChange.equals(Direction.UP))
            Simulator.plant.getTemperatureAccess().add(1);
        else if (directionOfChange.equals(Direction.DOWN))
            Simulator.plant.getTemperatureAccess().add(-1);
    }
}
