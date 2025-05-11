package com.solarsim.Backend.Service.ProductService;

import com.solarsim.Backend.Model.Product.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(String id);
    Product getProduct(String id);
    List<Product> getAllProducts();
}
