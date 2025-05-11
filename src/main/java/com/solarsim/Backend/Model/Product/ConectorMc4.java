package com.solarsim.Backend.Model.Product;

import jakarta.persistence.Entity;

@Entity
public class ConectorMc4 extends Product {

    public ConectorMc4(String name, String description, Integer price) {
        super(name, description, price);
    }

    public ConectorMc4() {
        super();
    }
}
