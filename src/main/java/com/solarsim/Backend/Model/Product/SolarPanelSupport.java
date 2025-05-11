package com.solarsim.Backend.Model.Product;

import jakarta.persistence.Entity;

@Entity
public class SolarPanelSupport extends Product {
    private String type;
    private Integer capacity;
}
