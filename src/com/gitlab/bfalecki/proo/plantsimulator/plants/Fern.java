package com.gitlab.bfalecki.proo.plantsimulator.plants;

import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.SoilPHValue;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.TemperatureValue;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.PercentageValue;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.DevelopmentState;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.fungi.Erysiphales;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.fungi.FusariumOxysporum;

public class Fern extends Plant{
    @Override
    public void calculateHealth() {
        float pollutionValue = ((PercentageValue)getPollutionAccess().getValue()).asFloat();
        float insolationValue = ((PercentageValue)getInsolationAccess().getValue()).asFloat();
        float irrigationValue = ((PercentageValue)getIrrigationAccess().getValue()).asFloat();
        int erysiphalesDevelopment = ((DevelopmentState)getParasite(Erysiphales.class).getValue()).asInt();
        int fusariumOxysporumDevelopment = ((DevelopmentState)getParasite(FusariumOxysporum.class).getValue()).asInt();
        float healthIncrease = (float) (
                - 0.1*pollutionValue -
                0.1*Math.abs(45 - insolationValue) -
                0.1*Math.abs(20 - irrigationValue) -
                1*erysiphalesDevelopment - 2*fusariumOxysporumDevelopment
        );
        healthIncrease += 1;
        this.getHealthAccess().setValue(
                new PercentageValue(
                        ((PercentageValue) getHealthAccess().getValue()).asFloat() + healthIncrease
                )
        );
    }
    public static class Builder extends Plant.Builder {
        private Fern plant =  new Fern();
        public Builder withPollution(float value){
            plant.getPollutionAccess().setValue(new PercentageValue(value));
            return this;
        }
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
