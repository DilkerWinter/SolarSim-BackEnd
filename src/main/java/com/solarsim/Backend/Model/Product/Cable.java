package com.solarsim.Backend.Model.Product;

import jakarta.persistence.Entity;

@Entity
public class Cable extends Product{
    private String brand;
    private double sizeInMeters;
    private String type ;

    public void calculateSizeBySolarPanel(int solarPanelQuantity){
        this.sizeInMeters = solarPanelQuantity * 3;
    }
}
