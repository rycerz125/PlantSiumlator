package com.gitlab.bfalecki.proo.plantsimulator;

import com.gitlab.bfalecki.proo.plantsimulator.gui.Gui;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Fern;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Plant;

public class Main {
    public static Simulator simulator;
    static Gui gui;
    public static void main(String[] args){
        simulator = new Simulator(Fern.class);
        gui = new Gui();


        try {
            simulator.startSimulation();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
