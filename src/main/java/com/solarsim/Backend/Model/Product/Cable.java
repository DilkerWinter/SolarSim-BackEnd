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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getSizeInMeters() {
        return sizeInMeters;
    }

    public void setSizeInMeters(double sizeInMeters) {
        this.sizeInMeters = sizeInMeters;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
