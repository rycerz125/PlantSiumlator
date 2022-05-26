package com.gitlab.bfalecki.proo.plantsimulator.healthyactions;

import com.gitlab.bfalecki.proo.plantsimulator.Main;

public class TemperatureAction extends HealthyAction{
    private final Direction direction;

    public enum Direction{
        UP, DOWN
    }
    public TemperatureAction(Direction direction){
        this.direction = direction;
        totalDuration = 10;
        remainingTime = totalDuration;
    }

    @Override
    public int getRemainingTime() {
        return remainingTime;
    }

    @Override
    public void decrementRemainingTime() {
        remainingTime--;
    }

    @Override
    public void performActionPart() {
        if (direction.equals(Direction.UP))
            Main.simulator.plant.getTemperatureAccess().add(1);
        else if (direction.equals(Direction.DOWN))
            Main.simulator.plant.getTemperatureAccess().add(-1);
    }
}
