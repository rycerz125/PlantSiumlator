package com.gitlab.bfalecki.proo.plantsimulator;

import com.gitlab.bfalecki.proo.plantsimulator.healthyactions.HealthyAction;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.TemperatureValue;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.PercentageValue;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.DevelopmentState;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.fungi.Erysiphales;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Fern;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Philodendron;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Plant;

import java.io.Serializable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class Simulator implements Serializable {
    public static Plant plant;
    public static HealthyAction currentHealthyAction;
    public Simulator(Class PlantClass){
        plant = Plant.Builder().withInsolation(45).withIrrigation(20).withTemperature(20).withSoilPH(4.9f).build();
        if (PlantClass == Fern.class){
            plant =  Fern.Builder().withInsolation(45).withIrrigation(20).withTemperature(20).withSoilPH(4.9f).build();
        } else if(PlantClass == Philodendron.class)
            plant = Philodendron.Builder().build();
        else   plant = Plant.Builder().withInsolation(45).withIrrigation(20).withTemperature(20).withSoilPH(4.9f).build();
    }
    public Simulator(){}
    public void startSimulation() throws InterruptedException {

        plant.calculateHealth();
        plant.getSoilPHAccess().add(1.4f);
        plant.getParasitesAccess().setParasite(new Erysiphales(new DevelopmentState(DevelopmentState.States.lightInfection)));
        plant.getParasitesAccess().increaseParasiteDevelopment(Erysiphales.class);
        plant.describe();


        final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        final CountDownLatch latch = new CountDownLatch(1);
        executorService.scheduleAtFixedRate(() -> {
            if (!plant.isDead()) {
                SimulationSaver.saveToFile(Main.fileName, this); // zapis do pliku
                simulateChangingParameters(); // symulacja drufujących parametrów
                plant.calculateHealth();    // oblicz zdrowie
                System.out.println(((PercentageValue) plant.getHealthAccess().getValue()).asFloat()); // wyswietl na ekran stan + parametry // Main.gui.refresh();
            } else {
                SimulationSaver.saveToFile(Main.fileName, this); // zapis do pliku
                System.out.println("Roslina " + plant.getSystematicName() + " nie zyje.");
               latch.countDown();
            }
        }, 0,1000, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(() ->{
            if (currentHealthyAction == null) return;
            if (currentHealthyAction.getRemainingTime() > 0) {
                currentHealthyAction.decrementRemainingTime();
                currentHealthyAction.performActionPart();

                System.out.println("Wykonuje akcje temperaturowa: " + ((TemperatureValue)plant.getTemperatureAccess().getValue()).asFloat());

            }else currentHealthyAction = null;
        }, 200, 1000, TimeUnit.MILLISECONDS);



        latch.await();
        executorService.shutdownNow();
    }
    private void simulateChangingParameters(){
        plant.getParasitesAccess().increaseParasiteDevelopment(Erysiphales.class);
    }
    public void performHealthyAction(HealthyAction healthyActionClicked){
        if (currentHealthyAction == null) currentHealthyAction = healthyActionClicked;
        else if (healthyActionClicked.getRemainingTime() != 0) return;
        currentHealthyAction = healthyActionClicked;
    }
}
