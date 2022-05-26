package com.gitlab.bfalecki.proo.plantsimulator;

import com.gitlab.bfalecki.proo.plantsimulator.gui.Gui;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Fern;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static Simulator simulator;
    public static Gui gui;
    public static final String fileName = "simulationState.ser";
    public static void main(String[] args){
        simulator = new Simulator(Fern.class);
        try {
            if (new File(fileName).exists())
                SimulationSaver.loadFromFile(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        gui = new Gui();



        try {
            simulator.startSimulation();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
