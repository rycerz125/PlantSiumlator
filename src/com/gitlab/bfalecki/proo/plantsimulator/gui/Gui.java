package com.gitlab.bfalecki.proo.plantsimulator.gui;

import com.gitlab.bfalecki.proo.plantsimulator.Simulator;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Fern;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Orchid;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Philodendron;

import javax.swing.*;
import java.awt.*;


public class Gui{
    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    public GuiDesigner guiDesigner;
    private JLabel plantPictureLabel;
    private JButton temperatureButton, orchidButton, fernButton, philodendronButton,
        newSimulationButton, lastSimulationButton;
    public Gui() {

        frame = new JFrame();
        initializeButtons();

        label = new JLabel();
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(0,1));

        panel.add(label);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Plant Simulator v0.1");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private void initializeButtons(){
        temperatureButton = new JButton("inc temperature");
        orchidButton = new JButton(Orchid.getSystematicName());
        fernButton = new JButton(Fern.getSystematicName());
        philodendronButton = new JButton(Philodendron.getSystematicName());
        newSimulationButton = new JButton("new simulation");
        lastSimulationButton = new JButton("last simulation");


        temperatureButton.addActionListener(new ControlPanelObserver.Button1Listener());
        orchidButton.addActionListener(new ControlPanelObserver.orchidButtonListener());
        fernButton.addActionListener(new ControlPanelObserver.fernButtonListener());
        philodendronButton.addActionListener(new ControlPanelObserver.philodendronButtonListener());
        newSimulationButton.addActionListener(new ControlPanelObserver.newSimulationButtonListener());
        lastSimulationButton.addActionListener(new ControlPanelObserver.lastSimulationButtonListener());

    }
    public void showSimulationSourceChoice(){
        label.setText("Choose simulation source");
        panel.add(newSimulationButton);
        panel.add(lastSimulationButton);
        frame.pack();
    }
    public void showPlantChoice(){
        label.setText("choose plant type");
        panel.remove(newSimulationButton);
        panel.remove(lastSimulationButton);

        panel.add(orchidButton);
        panel.add(fernButton);
        panel.add(philodendronButton);
        frame.pack();
    }
    public void showParametersControlButtons(){
        plantPictureLabel = new JLabel(new ImageIcon(Simulator.plant.getImage()));
        panel.remove(orchidButton);
        panel.remove(fernButton);
        panel.remove(philodendronButton);
        panel.remove(newSimulationButton);
        panel.remove(lastSimulationButton);
        panel.remove(label);

        panel.add(plantPictureLabel);
        panel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        guiDesigner = new GuiDesigner();
        guiDesigner.main(new String[0]);

        frame.pack();
    }
}
