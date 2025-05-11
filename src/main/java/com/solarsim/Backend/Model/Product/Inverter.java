package com.solarsim.Backend.Model.Product;

import jakarta.persistence.Entity;

@Entity
public class Inverter extends Product {
    private double maxPotencyKw;
    private String exitPotencyV;
    private String entryPotencyV;
    private String type;
}
