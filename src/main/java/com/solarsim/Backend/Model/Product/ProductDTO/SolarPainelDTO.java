package com.solarsim.Backend.Model.Product.ProductDTO;

public record SolarPainelDTO (
        String name,
        String description,
        Integer price,
        double potencyKiloWatts,
        double potencyVoltage,
        double height,
        double width ){
}
