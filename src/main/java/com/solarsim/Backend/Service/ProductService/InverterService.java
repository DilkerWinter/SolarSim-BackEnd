package com.solarsim.Backend.Service.ProductService;

import com.solarsim.Backend.Model.Product.Inverter;
import com.solarsim.Backend.Model.Product.Product;
import com.solarsim.Backend.Repository.ProductRepository.InverterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InverterService implements ProductService {

    @Autowired
    private InverterRepository inverterRepository;

    public InverterService(InverterRepository inverterRepository) {
        this.inverterRepository = inverterRepository;
    }

    @Override
    public void addProduct(Product product) {
        if (product instanceof Inverter) {
            Inverter inverter = (Inverter) product;
            inverterRepository.save(inverter);
        } else {
            throw new IllegalArgumentException("Product is not of type Inverter");
        }
    }

    @Override
    public void updateProduct(Product product) {
        if (product instanceof Inverter) {
            Inverter inverter = (Inverter) product;
            Optional<Inverter> existing = inverterRepository.findById(inverter.getId());
            if (existing.isPresent()) {
                inverterRepository.save(inverter);
            } else {
                throw new RuntimeException("Inverter not found");
            }
        } else {
            throw new IllegalArgumentException("Product is not of type Inverter");
        }
    }

    @Override
    public void deleteProduct(String id) {
        Optional<Inverter> inverter = inverterRepository.findById(id);
        if (inverter.isPresent()) {
            inverterRepository.deleteById(id);
        } else {
            throw new RuntimeException("Inverter not found");
        }
    }

    @Override
    public Product getProduct(String id) {
        return (Product) inverterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inverter not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) (List<?>) inverterRepository.findAll();
    }
}
