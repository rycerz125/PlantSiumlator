package com.gitlab.bfalecki.proo.plantsimulator.healthyactions;

public interface HealthyActionInterface {
    int getRemainingTime();
    void decrementRemainingTime();
    void performActionPart();
}
