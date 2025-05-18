package com.solarsim.Backend.Model.Product;

import jakarta.persistence.Entity;

@Entity
public class SolarPanelSupport extends Product {
    private String type;
    private Integer maxCapacity;

    public SolarPanelSupport(String name, String description, Integer price, String brand,
                             String type, Integer maxCapacity){
        super(name, description, price, brand);
        this.type = type;
        this.maxCapacity = maxCapacity;
    }

    public SolarPanelSupport(){

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
