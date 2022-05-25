package com.gitlab.bfalecki.proo.plantsimulator;

import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.PercentageValue;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.DevelopmentState;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.fungi.Erysiphales;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Fern;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Philodendron;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Plant;

import java.util.concurrent.*;

public final class Simulator {
    public static Plant plant;
    public Simulator(Class PlantClass){
        if (PlantClass == Fern.class){
            plant = Fern.Builder().withInsolation(45).withIrrigation(20).withTemperature(100).withSoilPH(4.9f).build();
        } else if(PlantClass == Philodendron.class)
            plant = new Philodendron();
        else   plant = Plant.Builder().withInsolation(45).withIrrigation(20).withTemperature(100).withSoilPH(4.9f).build();
    }
    public void startSimulation() throws InterruptedException {

        plant.calculateHealth();
        plant.getSoilPHAccess().add(1.4f);
        plant.getParasitesAccess().setParasite(new Erysiphales(new DevelopmentState(DevelopmentState.State.lightInfection)));
        plant.getParasitesAccess().increaseParasiteDevelopment(Erysiphales.class);
        plant.describe();


        final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        final CountDownLatch latch = new CountDownLatch(1);
        executorService.scheduleAtFixedRate(() -> {
            if (!plant.isDead()) {
                plant.getParasitesAccess().increaseParasiteDevelopment(Erysiphales.class); // symulacja drufujących parametrów
                System.out.println(((PercentageValue) plant.getHealthAccess().getValue()).asFloat());
                plant.calculateHealth();
            } else {
                System.out.println("Roslina Nie zyje.");
                latch.countDown();
            }
        }, 0,1, TimeUnit.SECONDS);
        latch.await();
        executorService.shutdownNow();
    }
}
