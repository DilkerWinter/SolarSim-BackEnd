package com.solarsim.Backend.Model.Product;

import jakarta.persistence.Entity;

import java.util.Map;

@Entity
public class Cable extends Product{
    private String type ;

    public Cable(String name, String description, Integer price,String brand,
                 String type) {
        super(name, description, price, brand);
        this.type = type;
    }

    public Cable() {

    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
