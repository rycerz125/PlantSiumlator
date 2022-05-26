package com.gitlab.bfalecki.proo.plantsimulator.gui;

import com.gitlab.bfalecki.proo.plantsimulator.Main;
import com.gitlab.bfalecki.proo.plantsimulator.healthyactions.TemperatureAction;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Plant;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanelObserver {

    public static class Button1Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Plant plant = Main.simulator.plant;
            if (plant.isDead()) return;

            Main.simulator.performHealthyAction(new TemperatureAction(TemperatureAction.Direction.UP));
            plant.describe();
        }
    }
    public class Button2Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent){

        }
    }
}
