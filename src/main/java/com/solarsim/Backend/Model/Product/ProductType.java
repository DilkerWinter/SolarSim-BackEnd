package com.solarsim.Backend.Model.Product;

public enum ProductType {
    CABLE("cable"),
    CONECTORMC4("conectormc4"),
    INVERTER("inverter"),
    SOLARPANEL("solarpanel"),
    SOLARPANELSUPPORT("solarpanelsupport");


    private String type;

    ProductType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}

