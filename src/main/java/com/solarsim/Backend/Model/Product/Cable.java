package com.solarsim.Backend.Model.Product;

import jakarta.persistence.Entity;

@Entity
public class Cable extends Product{
    private String brand;
    private double sizeInMeters;
    private String type ;

    public Cable(String name, String description, Integer price,
                 String brand, double sizeInMeters, String type) {
        super(name, description, price);
        this.brand = brand;
        this.sizeInMeters = sizeInMeters;
        this.type = type;
    }

    public Cable() {

    }

}
