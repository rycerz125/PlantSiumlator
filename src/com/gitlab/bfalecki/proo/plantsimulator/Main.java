package com.gitlab.bfalecki.proo.plantsimulator;

import com.gitlab.bfalecki.proo.plantsimulator.gui.ControlPanelObserver;
import com.gitlab.bfalecki.proo.plantsimulator.gui.Gui;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static Simulator simulator;
    public static Gui gui;
    public static final String fileName = "simulationState.ser";
    private static boolean lastSimulationUsable = false;
    public static void main(String[] args){
        gui = new Gui();
        if (new File(fileName).exists()) {
            try {
                SimulationSaver.loadFromFile(fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (Simulator.plant != null)
                lastSimulationUsable = !Simulator.plant.isDead();
        }

        if(lastSimulationUsable) {
            gui.showSimulationSourceChoice();
            try {
                System.out.println("wybierz source");
                ControlPanelObserver.simulationSourceChoiceLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!ControlPanelObserver.newSimulation){
            simulator = new Simulator();
        } else{
            System.out.println("wybierz rosline");
            gui.showPlantChoice();
            try {
                ControlPanelObserver.plantChoiceLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            simulator = new Simulator(ControlPanelObserver.PlantClass);
        }



        System.out.println("wybrano, startujemy symulacje!");
        gui.showParametersControlButtons();


        try {
            simulator.startSimulation();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
