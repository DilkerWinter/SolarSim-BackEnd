package com.solarsim.Backend.Model.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Inverter extends Product {
    @Column(name = "max_potency_kw ")
    private Integer maxPotencyKw;
    @Column(name = "exit_potency_v")
    private Integer exitPotencyV;
    @Column(name = "entry_potency_v")
    private Integer entryPotencyV;
    private String type;

    public Inverter(String name, String description, Integer price, String brand,
                    Integer maxPotencyKw, Integer exitPotencyV, Integer entryPotencyV, String type) {
        super(name, description, price, brand);
        this.maxPotencyKw = maxPotencyKw;
        this.exitPotencyV = exitPotencyV;
        this.entryPotencyV = entryPotencyV;
        this.type = type;
    }

    public Inverter() {

    }

    public Integer getMaxPotencyKw() {
        return maxPotencyKw;
    }

    public void setMaxPotencyKw(Integer maxPotencyKw) {
        this.maxPotencyKw = maxPotencyKw;
    }

    public Integer getExitPotencyV() {
        return exitPotencyV;
    }

    public void setExitPotencyV(Integer exitPotencyV) {
        this.exitPotencyV = exitPotencyV;
    }

    public Integer getEntryPotencyV() {
        return entryPotencyV;
    }

    public void setEntryPotencyV(Integer entryPotencyV) {
        this.entryPotencyV = entryPotencyV;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
