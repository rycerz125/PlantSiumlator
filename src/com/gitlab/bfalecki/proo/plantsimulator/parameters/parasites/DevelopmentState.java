package com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites;

import com.gitlab.bfalecki.proo.plantsimulator.parameters.ParameterValue;

public class DevelopmentState extends ParameterValue {
    private State state;
    public enum State {
        noPresence, germination, lightInfection, mediumInfection, criticalInfection;
        static public final State[] values = values();
        public State next() {
            int index = ordinal() + 1;
            if (index > values.length - 1) index = values.length - 1;
            return values[index];
        }
        public State previous() {
            int index = ordinal() - 1;
            if (index < 0) index = 0;
            return values[index];
        }
    }
    public DevelopmentState(State state){
        this.state = state;
    }
    public DevelopmentState(){
        this.state = State.noPresence;
    }
    public String asString(){
        return state.name();
    }
    public State asEnum(){
        return state;
    }
    public int asInt(){
        return state.ordinal();
    }

}
