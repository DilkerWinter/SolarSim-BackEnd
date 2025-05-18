package com.solarsim.Backend.Model.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class SolarPanel extends Product {
    @Column(name = "potency_kilo_watts")
    private Integer potencyKiloWatts;
    @Column(name = "potency_voltage")
    private Integer potencyVoltage;
    private Integer height;
    private Integer width;

    public SolarPanel(String name, String description, Integer price, String brand,
                      Integer potencyKiloWatts, Integer potencyVoltage,
                      Integer height, Integer width) {
        super(name, description, price, brand);
        this.potencyKiloWatts = potencyKiloWatts;
        this.potencyVoltage = potencyVoltage;
        this.height = height;
        this.width = width;
    }

    public SolarPanel() {

    }

    public Integer getPotencyKiloWatts() {
        return potencyKiloWatts;
    }

    public void setPotencyKiloWatts(Integer potencyKiloWatts) {
        this.potencyKiloWatts = potencyKiloWatts;
    }

    public Integer getPotencyVoltage() {
        return potencyVoltage;
    }

    public void setPotencyVoltage(Integer potencyVoltage) {
        this.potencyVoltage = potencyVoltage;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
}
