package com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites;

import com.gitlab.bfalecki.proo.plantsimulator.parameters.ParameterValue;

import java.io.Serializable;

public class DevelopmentState extends ParameterValue implements Serializable {
    private States state;
    public enum States {
        noPresence, germination, lightInfection, mediumInfection, criticalInfection;
        static public final States[] values = values();
        public States next() {
            int index = ordinal() + 1;
            if (index > values.length - 1) index = values.length - 1;
            return values[index];
        }
        public States previous() {
            int index = ordinal() - 1;
            if (index < 0) index = 0;
            return values[index];
        }
    }
    public DevelopmentState(States state){
        this.state = state;
    }
    public DevelopmentState(){
        this.state = States.noPresence;
    }
    public String asString(){
        return state.name();
    }
    public States asEnum(){
        return state;
    }
    public int asInt(){
        return state.ordinal();
    }

}
