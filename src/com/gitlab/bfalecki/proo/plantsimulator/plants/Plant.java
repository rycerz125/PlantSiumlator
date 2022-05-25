package com.gitlab.bfalecki.proo.plantsimulator.plants;

import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.*;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.Health;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.Insolation;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.Irrigation;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.PercentageValue;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.pollutions.AirPollution;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.pollutions.Dust;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.pollutions.Pollution;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.pollutions.SoilPollution;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.DevelopmentState;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.Parasite;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.fungi.Erysiphales;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.fungi.FusariumOxysporum;

import java.util.ArrayList;
import java.util.List;

public class Plant {
    private Insolation insolation;
    private Irrigation irrigation;
    private Health health;
    private Temperature temperature;
    private SoilPH soilPH;
    private Parasites parasitesObject;
    private Pollutions pollutionsObject;
    protected List<Parasite> parasites;
    private List<Pollution> pollutions;

    public Plant(){
        insolation = new Insolation();
        irrigation = new Irrigation();
        health = new Health();
        temperature = new Temperature();
        soilPH = new SoilPH();

        parasitesObject = new Parasites();
        parasites = new ArrayList<>();
        parasites.add(new FusariumOxysporum());
        parasites.add(new Erysiphales());

        pollutionsObject = new Pollutions();
        pollutions = new ArrayList<>();
        pollutions.add(new AirPollution());
        pollutions.add(new SoilPollution());
        pollutions.add(new Dust());
    }




    public void describe(){
        float airPollutionValue =  ((NumericValue) getPollutionsAccess().getPollution(AirPollution.class).getValue()).asFloat();
        float insolationValue = ((NumericValue) insolation.getValue()).asFloat();
        float irrigationValue = ((NumericValue) irrigation.getValue()).asFloat();
        float healthValue = ((NumericValue) health.getValue()).asFloat();
        float temperatureValue = ((NumericValue) temperature.getValue()).asFloat();
        float soilPHValue = ((NumericValue) soilPH.getValue()).asFloat();
        for (Parasite parasite : parasites){
                System.out.println("Parasite - " + parasite.getClass().getSimpleName() + " : "+  ((DevelopmentState)parasite.getValue()).asString());
        }
        System.out.println("pollution : " + airPollutionValue +
                "\ninsolation : " + insolationValue +
                "\nirrigation : " + irrigationValue +
                "\nhealth : " + healthValue +
                "\ntemperature : " + temperatureValue +
                "\nsoil pH : " + soilPHValue);
    }
    //accessers

    public Insolation getInsolationAccess(){return insolation;}
    public Irrigation getIrrigationAccess(){return irrigation;}
    public Health getHealthAccess(){return health;}
    public Temperature getTemperatureAccess(){return temperature;}
    public SoilPH getSoilPHAccess(){return soilPH;}
    public Parasites getParasitesAccess(){
        return parasitesObject;
    }
    public Pollutions getPollutionsAccess(){return pollutionsObject;}

    public void calculateHealth(){
        float healthIncrease = 5;
        health.setValue(
                new PercentageValue(
                ((PercentageValue) health.getValue()).asFloat() + healthIncrease
                )
        );
    }

    public boolean isDead(){
        return ((PercentageValue)health.getValue()).asFloat() == 0;
    }
    public class Pollutions{
        public void setPollution(Pollution pollution){
            for (Pollution pollutionInList : pollutions){
                if (pollution.getClass() == pollutionInList.getClass())
                    pollutionInList.setValue(pollution.getValue());
            }
        }
        public Pollution getPollution(Class pollutionClass){
            for (Pollution pollution : pollutions){
                if (pollutionClass == pollution.getClass())
                    return pollution;
            }
            return null;
        }
        public void increasePollution(Class pollutionClass, float value){
            for (Pollution pollution : pollutions){
                if (pollutionClass == pollution.getClass())
                    pollution.setValue(new PercentageValue(
                            ((PercentageValue)pollution.getValue()).asFloat() + value
                    ));
            }
        }
    }
    public class Parasites{
        public void setParasite(Parasite parasite){
            for (Parasite parasiteInList : parasites){
                if (parasite.getClass() == parasiteInList.getClass())
                    parasiteInList.setValue(parasite.getValue());
            }
        }
        public Parasite getParasite(Class parasiteClass){
            for (Parasite parasite : parasites){
                if (parasiteClass == parasite.getClass())
                    return parasite;
            }
            return null;
        }
        public void increaseParasiteDevelopment(Class parasiteClass){
            for (Parasite parasiteInList : parasites){
                if (parasiteClass == parasiteInList.getClass())
                    parasiteInList.increaseDevelopment();
            }
        }
        public void decreaseParasiteDevelopment(Class parasiteClass){
            for (Parasite parasiteInList : parasites){
                if (parasiteClass == parasiteInList.getClass())
                    parasiteInList.decreaseDevelopment();
            }
        }
    }
    public static class Builder {
        protected Plant plant =  new Plant();
        public Builder withInsolation(float value){
            plant.getInsolationAccess().setValue(new PercentageValue(value));
            return this;
        }
        public Builder withIrrigation(float value){
            plant.getIrrigationAccess().setValue(new PercentageValue(value));
            return this;
        }
        public Builder withTemperature(float value){
            plant.getTemperatureAccess().setValue(new TemperatureValue(value));
            return this;
        }
        public Builder withSoilPH(float value){
            plant.getSoilPHAccess().setValue(new SoilPHValue(value));
            return this;
        }
        public Plant build(){
            return plant;
        }
    }
    public static Builder Builder(){
        return new Builder();
    }
}
