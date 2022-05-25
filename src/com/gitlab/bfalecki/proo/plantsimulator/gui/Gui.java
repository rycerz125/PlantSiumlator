package com.gitlab.bfalecki.proo.plantsimulator.gui;

import javax.swing.*;
import java.awt.*;


public class Gui{
    private ControlPanelObserver controlPanelObserver;
    private int liczba = 0;
    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    private JButton button;
    public Gui() {
        controlPanelObserver = new ControlPanelObserver();
        frame = new JFrame();
        button = new JButton("nacisnij");
        button.addActionListener(controlPanelObserver);
        label = new JLabel("liczba nacisniec = 0");
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
        panel.setLayout(new GridLayout(0,1));
        panel.add(button);
        panel.add(label);
        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("moja ramka GUI");
        frame.pack();
        frame.setVisible(true);
    }
}
