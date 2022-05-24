package com.gitlab.bfalecki.proo.plantsimulator.plants;

import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.SoilPHValue;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.TemperatureValue;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.PercentageValue;

public class Fern extends Plant{
    @Override
    public void calculateHealth() {
        float pollutionValue = ((PercentageValue)getPollutionAccess().getValue()).asFloat();
        float insolationValue = ((PercentageValue)getInsolationAccess().getValue()).asFloat();
        float irrigationValue = ((PercentageValue)getIrrigationAccess().getValue()).asFloat();
        float healthIncrease = (float) (
                - 0.1*pollutionValue -
                0.1*Math.abs(45 - insolationValue) -
                0.1*Math.abs(20 - irrigationValue)
        );
        healthIncrease += -20;
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
