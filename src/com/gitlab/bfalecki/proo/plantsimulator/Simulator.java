package com.gitlab.bfalecki.proo.plantsimulator;

import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.PercentageValue;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.DevelopmentState;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.fungi.Erysiphales;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Fern;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Plant;

import java.util.concurrent.*;

public class Simulator {
    public void startSimulation() throws InterruptedException {
        Plant plant = Plant.Builder().withInsolation(45).withIrrigation(20).withTemperature(19).withSoilPH(5.9f).build();
        plant.calculateHealth();
        plant.describe();

        Fern fern = Fern.Builder().withInsolation(45).withIrrigation(20).withTemperature(100).withSoilPH(4.9f).build();
        fern.calculateHealth();
        fern.getSoilPHAccess().add(1.4f);
        fern.getParasitesAccess().setParasite(new Erysiphales(new DevelopmentState(DevelopmentState.State.lightInfection)));
        fern.getParasitesAccess().increaseParasiteDevelopment(Erysiphales.class);
        fern.describe();


        final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        final CountDownLatch latch = new CountDownLatch(1);
        executorService.scheduleAtFixedRate(() -> {
            if (!fern.isDead()) {
                fern.getParasitesAccess().increaseParasiteDevelopment(Erysiphales.class); // symulacja drufujących parametrów
                System.out.println(((PercentageValue) fern.getHealthAccess().getValue()).asFloat());
                fern.calculateHealth();
            } else {
                System.out.println("Roslina Fern Nie zyje.");
                latch.countDown();
            }
        }, 0,1, TimeUnit.SECONDS);
        latch.await();
        executorService.shutdownNow();
    }
}
