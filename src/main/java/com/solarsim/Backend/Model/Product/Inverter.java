package com.solarsim.Backend.Model.Product;

import jakarta.persistence.Entity;

@Entity
public class Inverter extends Product {
    private double maxPotencyKw;
    private String exitPotencyV;
    private String entryPotencyV;
    private String type;

    public Inverter(String name, String description, Integer price,
                    double maxPotencyKw, String exitPotencyV, String entryPotencyV, String type) {
        super(name, description, price);
        this.maxPotencyKw = maxPotencyKw;
        this.exitPotencyV = exitPotencyV;
        this.entryPotencyV = entryPotencyV;
        this.type = type;
    }

    public Inverter() {

    }

    public double getMaxPotencyKw() {
        return maxPotencyKw;
    }

    public void setMaxPotencyKw(double maxPotencyKw) {
        this.maxPotencyKw = maxPotencyKw;
    }

    public String getExitPotencyV() {
        return exitPotencyV;
    }

    public void setExitPotencyV(String exitPotencyV) {
        this.exitPotencyV = exitPotencyV;
    }

    public String getEntryPotencyV() {
        return entryPotencyV;
    }

    public void setEntryPotencyV(String entryPotencyV) {
        this.entryPotencyV = entryPotencyV;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
