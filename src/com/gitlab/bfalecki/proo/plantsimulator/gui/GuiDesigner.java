package com.gitlab.bfalecki.proo.plantsimulator.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiDesigner{
    private JButton button1;
    private JPanel panel;
    private JButton button2;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button9;
    private JButton button10;
    private JButton button3;
    private JButton button4;
    private JButton button8;
    private JButton button11;
    private JButton button12;
    private JProgressBar progressBar1;
    private JPanel generalPanel;
    private JProgressBar progressBar2;

    public static void main(String[] args){
        JFrame frame = new JFrame("Control Panel");


        frame.getContentPane().add(new GuiDesigner().generalPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
    public GuiDesigner() {

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
System.out.println("sasdsad");
            }
        });
    }

}
