package com.gitlab.bfalecki.proo.plantsimulator.plants;

import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.NumericValue;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.SoilPHValue;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.TemperatureValue;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.PercentageValue;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.pollutions.AirPollution;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.DevelopmentState;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.fungi.Erysiphales;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.fungi.FusariumOxysporum;

public class Fern extends Plant{
    @Override
    protected void initializeParasites(){
        parasitesResistances.put(new FusariumOxysporum(), 0);
        parasitesResistances.put(new Erysiphales(), 1);
    }
    public static String getSystematicName(){
        return "Polypodiopsida Cronquist";
    }
    @Override
    public void calculateHealth() {
        float airPollutionValue =  ((NumericValue) getPollutionsAccess().getPollution(AirPollution.class).getValue()).asFloat();
        float insolationValue = ((PercentageValue)getInsolationAccess().getValue()).asFloat();
        float irrigationValue = ((PercentageValue)getIrrigationAccess().getValue()).asFloat();
        float temperatureValue = ((TemperatureValue)getTemperatureAccess().getValue()).asFloat();
        int erysiphalesDevelopment = ((DevelopmentState)getParasitesAccess().getParasite(Erysiphales.class).getValue()).asInt();
        int fusariumOxysporumDevelopment = ((DevelopmentState)getParasitesAccess().getParasite(FusariumOxysporum.class).getValue()).asInt();
        float healthIncrease = (float) (
                - 0.1*airPollutionValue -
                0.1*Math.abs(45 - insolationValue) -
                0.1*Math.abs(20 - irrigationValue) -
                        erysiphalesDevelopment - 2*fusariumOxysporumDevelopment
                - 0.1*Math.abs(20 - temperatureValue)
        );
        healthIncrease += 1;
        this.getHealthAccess().setValue(
                new PercentageValue(
                        ((PercentageValue) getHealthAccess().getValue()).asFloat() + healthIncrease
                )
        );
    }
    public static class Builder extends Plant.Builder {
        private final Fern plant =  new Fern();
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
        public Fern build(){
            return plant;
        }
    }
    public static Builder Builder(){
        return new Builder();
    }
}
