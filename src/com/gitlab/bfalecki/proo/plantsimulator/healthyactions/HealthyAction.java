package com.gitlab.bfalecki.proo.plantsimulator.healthyactions;

import java.io.Serializable;

public abstract class HealthyAction implements HealthyActionInterface, Serializable {
    protected int totalDuration;
    protected int remainingTime;
}
