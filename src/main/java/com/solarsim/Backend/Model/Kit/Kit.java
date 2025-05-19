package com.solarsim.Backend.Model.Kit;

import com.solarsim.Backend.Model.Product.Product;
import jakarta.persistence.*;

import java.util.List;

public class Kit {

    private String id;
    private String name;
    private String description;
    private List<Product> productList;
    private Integer totalPrice;
    private Integer totalSizeInMeters;
    private Integer totalPowerGenerated;

    public Integer getTotalPriceByProductValue(){
        this.totalPrice = productList.stream().mapToInt(Product::getPrice).sum();
        return totalPrice;
    }
}
