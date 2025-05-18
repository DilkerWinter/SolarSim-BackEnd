package com.solarsim.Backend.Service.ProductService;

import com.solarsim.Backend.Model.Product.ConectorMc4;
import com.solarsim.Backend.Model.Product.Product;
import com.solarsim.Backend.Repository.ProductRepository.ConectorMc4Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConectorMc4Service implements ProductService {

    @Autowired
    private ConectorMc4Repository conectorMc4Repository;

    public ConectorMc4Service(ConectorMc4Repository conectorMc4Repository) {
        this.conectorMc4Repository = conectorMc4Repository;
    }

    @Override
    public void addProduct(Product product) {
        if (product instanceof ConectorMc4) {
            ConectorMc4 conector = (ConectorMc4) product;
            conectorMc4Repository.save(conector);
        } else {
            throw new IllegalArgumentException("Product is not of type ConectorMc4");
        }
    }

    @Override
    public void updateProduct(Product product) {
        if (product instanceof ConectorMc4) {
            ConectorMc4 conector = (ConectorMc4) product;
            Optional<Product> existing = conectorMc4Repository.findById(conector.getId());
            if (existing.isPresent()) {
                conectorMc4Repository.save(conector);
            } else {
                throw new RuntimeException("ConectorMc4 not found");
            }
        } else {
            throw new IllegalArgumentException("Product is not of type ConectorMc4");
        }
    }

    @Override
    public void deleteProduct(String id) {
        Optional<Product> conector = conectorMc4Repository.findById(id);
        if (conector.isPresent()) {
            conectorMc4Repository.deleteById(id);
        } else {
            throw new RuntimeException("ConectorMc4 not found");
        }
    }

    @Override
    public Product getProductById(String id) {
        Optional<Product> conector = conectorMc4Repository.findById(id);
        return conector.orElseThrow(() -> new RuntimeException("ConectorMc4 not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) (List<?>) conectorMc4Repository.findAll();
    }
}
