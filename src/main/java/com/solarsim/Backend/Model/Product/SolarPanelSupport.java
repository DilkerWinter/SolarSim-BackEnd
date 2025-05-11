package com.solarsim.Backend.Model.Product;

import jakarta.persistence.Entity;

@Entity
public class SolarPanelSupport extends Product {
    private String type;
    private Integer maxCapacity;

    public SolarPanelSupport(String name, String description, Integer price, String type, Integer maxCapacity){
        super(name, description, price);
        this.type = type;
        this.maxCapacity = maxCapacity;
    }

    public SolarPanelSupport(){

    }
}
