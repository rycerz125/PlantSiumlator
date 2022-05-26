package com.gitlab.bfalecki.proo.plantsimulator;

import com.gitlab.bfalecki.proo.plantsimulator.healthyactions.HealthyAction;
import com.gitlab.bfalecki.proo.plantsimulator.plants.Plant;

import java.io.*;

public class SimulationSaver {
    public static void saveToFile(String fileName, Simulator simulator){
        Data data = new Data(Simulator.plant, Simulator.currentHealthyAction);
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            objectOutputStream.writeObject(data);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void loadFromFile(String fileName) throws FileNotFoundException {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            Data data =(Data) objectInputStream.readObject();
            objectInputStream.close();
            Simulator.plant = data.plant;
            Simulator.currentHealthyAction = data.healthyAction;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private static class Data implements Serializable {
        Plant plant;
        HealthyAction healthyAction;
        public Data(Plant plant, HealthyAction healthyAction){
            this.plant = plant;
            this.healthyAction = healthyAction;
        }
    }
}
