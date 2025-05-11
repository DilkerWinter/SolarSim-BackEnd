package com.solarsim.Backend.Model.Product;

import jakarta.persistence.Entity;

@Entity
public class SolarPanel extends Product {
    private double potencyKiloWatts;
    private double potencyVoltage;
    private double height;
    private double width;
}
