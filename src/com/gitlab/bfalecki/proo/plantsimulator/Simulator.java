package com.gitlab.bfalecki.proo.plantsimulator;

import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.DevelopmentState;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.fungi.Erysiphales;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Fern;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Plant;

public class Simulator {
    public static void main(String[] args){
        Plant plant = Plant.Builder().withPollution(0).withInsolation(45).withIrrigation(20).withTemperature(19).withSoilPH(5.9f).build();
        plant.calculateHealth();
        plant.describe();

        Fern fern = Fern.Builder().withPollution(0).withInsolation(45).withIrrigation(20).withTemperature(100).withSoilPH(4.9f).build();
        fern.calculateHealth();
        fern.getSoilPHAccess().add(1.4f);
        fern.setParasite(new Erysiphales(new DevelopmentState(DevelopmentState.State.lightInfection)));
        fern.increaseParasiteDevelopment(Erysiphales.class);
        fern.describe();
    }
}
