package com.gitlab.bfalecki.proo.plantsimulator;

import com.gitlab.bfalecki.proo.plantsimulator.healthyactions.HealthyAction;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.pollutions.AirPollution;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.pollutions.Dust;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.pollutions.SoilPollution;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.DevelopmentState;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.fungi.Erysiphales;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.fungi.FusariumOxysporum;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Fern;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Orchid;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Philodendron;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Plant;

import java.util.SplittableRandom;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class Simulator{
    ScheduledExecutorService executorService;
    private int tickTimeInMillis = 10; //200 ticks = 24h
    public static Plant plant;
    public static HealthyAction currentHealthyAction;
    public Simulator(Class PlantClass){
        currentHealthyAction = null;
        if (PlantClass == Fern.class)
            plant =  Fern.Builder().withInsolation(45).withIrrigation(40).withTemperature(20).withSoilPH(5.5f).build();
        else if(PlantClass == Philodendron.class)
            plant = Philodendron.Builder().withInsolation(40).withIrrigation(20).withTemperature(20).withSoilPH(5.1f).build();
        else if(PlantClass == Orchid.class)
            plant = Orchid.Builder().withInsolation(60).withIrrigation(45).withTemperature(20).withSoilPH(5.2f).build();
        else   plant = Plant.Builder().build();
    }
    public Simulator(){}
    private boolean isNight(){
        return (plant.getLifeTimeCounter() / 100) % 2 != 0;
    }
    private boolean getTrueWithProbability(double probability){
        return new SplittableRandom().nextLong(0,(long) (1/probability)) == 0L;
    }
    public void setSpeed(double percent){
        double multiplier = Math.pow(10, (percent+10)/19);
        tickTimeInMillis = (int)(24 /(double) 2 * 3600*1000 / multiplier / 100);
    }
    public void startSimulation() throws InterruptedException {

        plant.calculateHealth();
        //plant.describe();

        executorService = Executors.newScheduledThreadPool(1);


        final CountDownLatch latch = new CountDownLatch(1);
        executorService.scheduleAtFixedRate(() -> {
            try {
                Thread.sleep(tickTimeInMillis);
            } catch (InterruptedException ignored) {
            }
            if (!plant.isDead()) {

                SimulationSaver.saveToFile(Main.fileName); // zapis do pliku
                simulateChangingParameters(); // symulacja drufujących parametrów
                plant.calculateHealth();    // oblicz zdrowie
                Main.gui.guiDesigner.refreshGui();
            } else {
                SimulationSaver.saveToFile(Main.fileName); // zapis do pliku
                Main.gui.showDeathAnnouncement();
                latch.countDown();
            }
        }, 0,10, TimeUnit.MILLISECONDS);
        executorService.scheduleAtFixedRate(() ->{

            if (currentHealthyAction == null) return;
            try {
                Thread.sleep(tickTimeInMillis);
            } catch (InterruptedException ignored) {
            }
            if (currentHealthyAction.getRemainingTime() > 0) {
                currentHealthyAction.decrementRemainingTime();
                currentHealthyAction.performActionPart();

            }else currentHealthyAction = null;
        }, 10/2, 10, TimeUnit.MILLISECONDS);



        latch.await();
        executorService.shutdownNow();
    }
    private void simulateChangingParameters(){
        plant.incrementLifeTimeCounter();
        if (isNight()) {
            plant.getInsolationAccess().add(-0.2f);
            plant.getTemperatureAccess().add(-0.1f);
        }
        else {
            plant.getInsolationAccess().add(0.2f);
            plant.getTemperatureAccess().add(0.1f);
        }

        plant.getIrrigationAccess().add(-0.1f);
        if (getTrueWithProbability(0.02))
            plant.getPollutionsAccess().increasePollution(AirPollution.class, 2);
        if (getTrueWithProbability(0.03))
            plant.getPollutionsAccess().increasePollution(AirPollution.class, -0.5f);

        if (getTrueWithProbability(0.01))
            plant.getPollutionsAccess().increasePollution(SoilPollution.class, 3);
        if (getTrueWithProbability(0.01))
            plant.getPollutionsAccess().increasePollution(SoilPollution.class, -1f);

        plant.getPollutionsAccess().increasePollution(Dust.class, 0.01f);

        if (getTrueWithProbability(0.016 / ((double) plant.getParasitesAccess().getResistance(Erysiphales.class)+1d))){
            int prev = ((DevelopmentState)plant.getParasitesAccess().getParasite(Erysiphales.class).getValue()).asInt();
            plant.getParasitesAccess().increaseParasiteDevelopment(Erysiphales.class);
            int current = ((DevelopmentState)plant.getParasitesAccess().getParasite(Erysiphales.class).getValue()).asInt();
            if (current > 2 && current != prev)
                Main.gui.showParasiteAnnouncement();
        }
        if (getTrueWithProbability(0.016 / ((double) plant.getParasitesAccess().getResistance(FusariumOxysporum.class)+1d))){
            int prev = ((DevelopmentState)plant.getParasitesAccess().getParasite(FusariumOxysporum.class).getValue()).asInt();
            plant.getParasitesAccess().increaseParasiteDevelopment(FusariumOxysporum.class);
            int current = ((DevelopmentState)plant.getParasitesAccess().getParasite(FusariumOxysporum.class).getValue()).asInt();
            if (current > 2 && current != prev)
                Main.gui.showParasiteAnnouncement();
        }


    }
    public void performHealthyAction(HealthyAction healthyActionClicked){
        if (currentHealthyAction == null) currentHealthyAction = healthyActionClicked;
        else if (healthyActionClicked.getRemainingTime() != 0) return;
        currentHealthyAction = healthyActionClicked;
    }
}
