package com.gitlab.bfalecki.proo.plantsimulator.plants;

import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.NumericValue;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.SoilPHValue;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.TemperatureValue;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.PercentageValue;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.pollutions.AirPollution;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.pollutions.Dust;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.pollutions.SoilPollution;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.DevelopmentState;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.fungi.Erysiphales;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.fungi.FusariumOxysporum;

public class Orchid extends Plant{
    @Override
    protected void initializeParasites(){
        parasitesResistances.put(new FusariumOxysporum(), 1);
        parasitesResistances.put(new Erysiphales(), 2);
    }
    @Override
    public void calculateHealth() {
        float airPollution =  ((NumericValue) getPollutionsAccess().getPollution(AirPollution.class).getValue()).asFloat();
        float soilPollution =  ((NumericValue) getPollutionsAccess().getPollution(SoilPollution.class).getValue()).asFloat();
        float dust =  ((NumericValue) getPollutionsAccess().getPollution(Dust.class).getValue()).asFloat();
        float insolation = ((PercentageValue)getInsolationAccess().getValue()).asFloat();
        float irrigation = ((PercentageValue)getIrrigationAccess().getValue()).asFloat();
        float temperature = ((TemperatureValue)getTemperatureAccess().getValue()).asFloat();
        float soilPH = ((SoilPHValue) getSoilPHAccess().getValue()).asFloat();
        int erysiphalesDev = ((DevelopmentState)getParasitesAccess().getParasite(Erysiphales.class).getValue()).asInt();
        int fusariumOxysporumDev = ((DevelopmentState)getParasitesAccess().getParasite(FusariumOxysporum.class).getValue()).asInt();
        float healthIncrease = (float) (
                - 0.001*airPollution - 0.01*soilPollution- 0.005*dust -
                        Math.pow(0.09*Math.abs(60 - insolation), 2) / 6-
                        Math.pow(0.07*Math.abs(45 - irrigation), 3)/3 -
                        Math.pow(0.9*Math.abs(5.4 - soilPH), 4)/4 -
                        0.1*erysiphalesDev/getParasitesAccess().getResistance(Erysiphales.class)
                        - 0.3*fusariumOxysporumDev/getParasitesAccess().getResistance(FusariumOxysporum.class)
                        - 0.007*Math.abs(20 - temperature)
        );
        if (temperature < 1){
            healthIncrease -=3;
        }
        if (erysiphalesDev > 2){
            healthIncrease -= 0.8;
            if (erysiphalesDev > 3)
                healthIncrease -=0.6;
        }
        if (fusariumOxysporumDev > 2){
            healthIncrease -= 3;
            if (fusariumOxysporumDev > 3)
                healthIncrease -=3;
        }

        healthIncrease += 1;
        this.getHealthAccess().setValue(
                new PercentageValue(
                        ((PercentageValue) getHealthAccess().getValue()).asFloat() + healthIncrease
                )
        );
    }
    @Override
    public String getSystematicName(){
        return "Orchidaceae";
    }
    public static class Builder extends Plant.Builder {
        private final Orchid plant =  new Orchid();
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
        public Orchid build(){
            return plant;
        }
    }
    public static Builder Builder(){
        return new Builder();
    }
}
