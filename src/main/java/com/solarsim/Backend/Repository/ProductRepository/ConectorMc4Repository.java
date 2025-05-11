package com.solarsim.Backend.Repository.ProductRepository;

import com.solarsim.Backend.Model.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConectorMc4Repository extends JpaRepository<Product, String> {
}
