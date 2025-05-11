package com.solarsim.Backend.Model.Product.ProductDTO;

import com.solarsim.Backend.Model.Product.ProductType.ProductType;

import java.util.Map;

public record ProductDTO(ProductType productType, Map<String, Object> data) {
}
