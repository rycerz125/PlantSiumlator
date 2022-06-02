package com.gitlab.bfalecki.proo.plantsimulator.gui;

import com.gitlab.bfalecki.proo.plantsimulator.Main;
import com.gitlab.bfalecki.proo.plantsimulator.Simulator;
import com.gitlab.bfalecki.proo.plantsimulator.healthyactions.*;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.NumericValue;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.PercentageValue;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.pollutions.AirPollution;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.pollutions.Dust;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.numericparameters.percentageparameters.pollutions.SoilPollution;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.DevelopmentState;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.fungi.Erysiphales;
import com.gitlab.bfalecki.proo.plantsimulator.parameters.parasites.fungi.FusariumOxysporum;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Plant;

import javax.swing.*;

public class GuiDesigner{
    private JButton button1;
    private JPanel panel;
    private JButton button2;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button9;
    private JButton button10;
    private JButton reduceButton4;
    private JButton reduceButton3;
    private JButton reduceButton;
    private JButton reduceButton1;
    private JButton reduceButton2;
    private JProgressBar progressBar1;
    private JPanel generalPanel;
    private JProgressBar progressBar2;
    private JLabel airPollLab;
    private JLabel soiPollLab;
    private JLabel dustLab;
    private JLabel fusOxysLab;
    private JLabel ErysiphLab;
    private JLabel insolLab;
    private JLabel irrigLab;
    private JLabel tempLab;
    private JLabel phLab;
    private JPanel mainPanel;
    private JSlider slider1;
    private static JFrame frame;

    public static void main(String[] args){
        frame = new JFrame("Control Panel");
        frame.getContentPane().add(new GuiDesigner().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        JSlider slider = (JSlider) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(2);
        JLabel label = (JLabel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(3);
        double speedPercent = slider.getValue();
        label.setText("speed: x" + String.format("%.0f",Math.pow(10, (speedPercent+10)/19))) ;
        Main.simulator.setSpeed(speedPercent);
    }
    public GuiDesigner() {
        button9.addActionListener(actionEvent -> {
            if (Simulator.plant.isDead()) return;
            Main.simulator.performHealthyAction(new TemperatureAction(Direction.UP));
        });
        button5.addActionListener(actionEvent -> {
            if (Simulator.plant.isDead()) return;
            Main.simulator.performHealthyAction(new TemperatureAction(Direction.DOWN));
        });
        reduceButton3.addActionListener(actionEvent -> {
            if (Simulator.plant.isDead()) return;
            Main.simulator.performHealthyAction(new ReduceParasiteAction(FusariumOxysporum.class));
        });
        reduceButton4.addActionListener(actionEvent -> {
            if (Simulator.plant.isDead()) return;
            Main.simulator.performHealthyAction(new ReduceParasiteAction(Erysiphales.class));
        });
        reduceButton.addActionListener(actionEvent -> {
            if (Simulator.plant.isDead()) return;
            Main.simulator.performHealthyAction(new ReducePollutionAction(AirPollution.class));
        });
        reduceButton1.addActionListener(actionEvent -> {
            if (Simulator.plant.isDead()) return;
            Main.simulator.performHealthyAction(new ReducePollutionAction(SoilPollution.class));
        });
        reduceButton2.addActionListener(actionEvent -> {
            if (Simulator.plant.isDead()) return;
            Main.simulator.performHealthyAction(new ReducePollutionAction(Dust.class));
        });
        button1.addActionListener(actionEvent -> {
            if (Simulator.plant.isDead()) return;
            Main.simulator.performHealthyAction(new InsolationAction(Direction.DOWN));
        });
        button2.addActionListener(actionEvent -> {
            if (Simulator.plant.isDead()) return;
            Main.simulator.performHealthyAction(new InsolationAction(Direction.UP));
        });
        button7.addActionListener(actionEvent -> {
            if (Simulator.plant.isDead()) return;
            Main.simulator.performHealthyAction(new IrrigationAction());
        });
        button6.addActionListener(actionEvent -> {
            if (Simulator.plant.isDead()) return;
            Main.simulator.performHealthyAction(new SoilPHAction(Direction.DOWN));
        });
        button10.addActionListener(actionEvent -> {
            if (Simulator.plant.isDead()) return;
            Main.simulator.performHealthyAction(new SoilPHAction(Direction.UP));
        });
        slider1.addChangeListener(changeEvent -> {
            JSlider slider = (JSlider) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(2);
            JLabel label = (JLabel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(3);
            double speedPercent = slider.getValue();
            label.setText("speed: x" + String.format("%.0f",Math.pow(10, (speedPercent+10)/19))) ;
            Main.simulator.setSpeed(speedPercent);
        });
    }
    public void refreshGui(){

        Plant plant = Simulator.plant;
        String fusarDevState = ((DevelopmentState) plant.getParasitesAccess().getParasite(FusariumOxysporum.class).getValue()).asString();
        String erysiphDevState = ((DevelopmentState) plant.getParasitesAccess().getParasite(Erysiphales.class).getValue()).asString();

        float health = ( (PercentageValue)plant.getHealthAccess().getValue()).asFloat();
        float irrig = ((PercentageValue)plant.getIrrigationAccess().getValue()).asFloat();
        float insol = ((PercentageValue)plant.getInsolationAccess().getValue()).asFloat();
        float temp = ((NumericValue)plant.getTemperatureAccess().getValue()).asFloat();
        float pH = ((NumericValue)plant.getSoilPHAccess().getValue()).asFloat();
        float airPoll = ((PercentageValue)plant.getPollutionsAccess().getPollution(AirPollution.class).getValue()).asFloat();
        float dust = ((PercentageValue)plant.getPollutionsAccess().getPollution(Dust.class).getValue()).asFloat();
        float soilPoll = ((PercentageValue)plant.getPollutionsAccess().getPollution(SoilPollution.class).getValue()).asFloat();

        float opProgress;
        if (Simulator.currentHealthyAction != null) {
            float opRemainingTime = (float) Simulator.currentHealthyAction.getRemainingTime();
            float opTotalTime = (float) Simulator.currentHealthyAction.getTotalDuration();
            opProgress = (opTotalTime - opRemainingTime) / opTotalTime * 100;
        } else opProgress = 100;

        JPanel genPanel = (JPanel) ((JPanel) frame.getContentPane().getComponent(0)).getComponent(0);
        JProgressBar progressBar1 = (JProgressBar) genPanel.getComponent(1);
        JProgressBar progressBar2 = (JProgressBar) genPanel.getComponent(2);

        progressBar1.setValue((int)opProgress);
        progressBar1.setString("Current Operation " + String.format("%.1f", opProgress) + "%");
        progressBar2.setValue((int) health);
        progressBar2.setString("Health " + String.format("%.1f", health) + "%");

        JTabbedPane tabbedPane = (JTabbedPane) genPanel.getComponent(0);

        JPanel parasitesPanel =(JPanel) tabbedPane.getComponent(0);
        JLabel fusOxysLab = (JLabel) parasitesPanel.getComponent(3);
        JLabel erysiphLab = (JLabel) parasitesPanel.getComponent(4);
        fusOxysLab.setText(fusarDevState);
        erysiphLab.setText(erysiphDevState);

        JPanel pollPanel = (JPanel) tabbedPane.getComponent(1);
        JLabel airPollLab = (JLabel) pollPanel.getComponent(1);
        JLabel soilPollLab = (JLabel) pollPanel.getComponent(5);
        JLabel dustLab = (JLabel) pollPanel.getComponent(7);
        airPollLab.setText(airPoll + "%");
        soilPollLab.setText(soilPoll + "%");
        dustLab.setText(dust + "%");

        JPanel lastPanel = (JPanel) tabbedPane.getComponent(2);
        JLabel insolLab = (JLabel) lastPanel.getComponent(2);
        JLabel irrigLab = (JLabel) lastPanel.getComponent(3);
        JLabel tempLab = (JLabel) lastPanel.getComponent(4);
        JLabel phLab = (JLabel) lastPanel.getComponent(5);
        insolLab.setText(String.format("%.1f", insol) + "%");
        irrigLab.setText(String.format("%.1f",irrig) + "%");
        tempLab.setText(String.format("%.1f",temp)+ "°C");
        phLab.setText(pH +"");

        frame.pack();
    }
}
