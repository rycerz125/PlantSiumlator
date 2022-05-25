package com.gitlab.bfalecki.proo.plantsimulator;

import com.gitlab.bfalecki.proo.plantsimulator.gui.Gui;

public class Main {
    static Simulator simulator;
    static Gui gui;
    public static void main(String[] args){
        simulator = new Simulator();
        gui = new Gui();


        try {
            simulator.startSimulation();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
