package com.gitlab.bfalecki.proo.plantsimulator.gui;

import com.gitlab.bfalecki.proo.plantsimulator.Main;
import com.gitlab.bfalecki.proo.plantsimulator.Simulator;
import com.gitlab.bfalecki.proo.plantsimulator.healthyactions.TemperatureAction;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Fern;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Orchid;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Philodendron;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

public class ControlPanelObserver {
    public static final CountDownLatch plantChoiceLatch = new CountDownLatch(1);
    public static final CountDownLatch simulationSourceChoiceLatch = new CountDownLatch(1);
    public static Class PlantClass;
    public static boolean newSimulation = true;
//    public static class Button1Listener implements ActionListener{
//        @Override
//        public void actionPerformed(ActionEvent actionEvent) {
//            if (Simulator.plant.isDead()) return;
//            Main.simulator.performHealthyAction(new TemperatureAction(TemperatureAction.Direction.UP));
//        }
//    }
    public static class orchidButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent){
            PlantClass = Orchid.class;
            plantChoiceLatch.countDown();
        }
    }
    public static class fernButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent){
            PlantClass = Fern.class;
            plantChoiceLatch.countDown();
        }
    }
    public static class philodendronButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent){
            PlantClass = Philodendron.class;
            plantChoiceLatch.countDown();
        }
    }
    public static class newSimulationButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent){
            newSimulation = true;
            simulationSourceChoiceLatch.countDown();
        }
    }
    public static class lastSimulationButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent){
            newSimulation = false;
            simulationSourceChoiceLatch.countDown();
        }
    }
}
