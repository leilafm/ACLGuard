package model;

import java.util.List;

// Holds individual athletes history and demographic data
public class Athlete {
    private String name;
    private int age;
    private boolean gender;
    private String sport;
    private double height;
    private double weight;
    private List<Boolean> history;
    private double bmi;
    private BiomechanicalData bioMechData;
    
    public Athlete(String name, int age, boolean gender, String sport, double height, double weight, List<Boolean> history) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.sport = sport;
        this.height = height;
        this.weight = weight;
        this.history = history;
        this.bmi = calculateBMI(height, weight);
        bioMechData = new BiomechanicalData();
    }

    // REQUIRES: height in cm, weight in kg
    // MODIFIES: this
    // EFFECTS: calculates BMI and returns that value
    public double calculateBMI(double height, double weight) {
        double heightM2 = (height * height) / 10000;
        return weight / heightM2;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    // NOTE: T -> Female, F -> Male
    public Boolean getGender() {
        return gender;
    }

    public String getSport() {
        return sport;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }


    public boolean hasFamilyHistory() {
        return history.get(0);
    }

    public boolean hasPrevInjuryR() {
        return history.get(1);
    }

    public boolean hasPrevInjuryL() {
        return history.get(2);
    }

    public List<Boolean> getHistory() {
        return history;
    }

    public double getBMI() {
        return bmi;
    }

    public BiomechanicalData getBioMechData() {
        return bioMechData;
    }
}
