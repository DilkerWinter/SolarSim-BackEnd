package com.solarsim.Backend.Model.Product;

import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class SolarPanel extends Product {
    private double potencyKiloWatts;
    private double potencyVoltage;
    private double height;
    private double width;

    public SolarPanel(String name, String description, Integer price,
                      double potencyKiloWatts, double potencyVoltage,
                      double height, double width) {
        super(name, description, price);
        this.potencyKiloWatts = potencyKiloWatts;
        this.potencyVoltage = potencyVoltage;
        this.height = height;
        this.width = width;
    }

    public SolarPanel() {

    }

    public double getPotencyKiloWatts() {
        return potencyKiloWatts;
    }

    public void setPotencyKiloWatts(double potencyKiloWatts) {
        this.potencyKiloWatts = potencyKiloWatts;
    }

    public double getPotencyVoltage() {
        return potencyVoltage;
    }

    public void setPotencyVoltage(double potencyVoltage) {
        this.potencyVoltage = potencyVoltage;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }
}
