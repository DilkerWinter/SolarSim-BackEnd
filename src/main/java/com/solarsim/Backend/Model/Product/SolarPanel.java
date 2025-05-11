package com.solarsim.Backend.Model.Product;

import jakarta.persistence.Entity;

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
}
