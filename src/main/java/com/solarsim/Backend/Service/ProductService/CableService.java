package com.solarsim.Backend.Service.ProductService;

import com.solarsim.Backend.Model.Product.Cable;
import com.solarsim.Backend.Model.Product.Product;
import com.solarsim.Backend.Repository.ProductRepository.CableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CableService implements ProductService {

    @Autowired
    private CableRepository cableRepository;

    public CableService(CableRepository cableRepository) {
        this.cableRepository = cableRepository;
    }

    @Override
    public void addProduct(Product product) {
        if (product instanceof Cable) {
            Cable cable = (Cable) product;
            cableRepository.save(cable);
        } else {
            throw new IllegalArgumentException("Product is not of type Cable");
        }
    }

    @Override
    public void updateProduct(Product product) {
        if (product instanceof Cable) {
            Cable cable = (Cable) product;
            Optional<Cable> existingCable = cableRepository.findById(cable.getId());
            if (existingCable.isPresent()) {
                cableRepository.save(cable);
            } else {
                throw new RuntimeException("Cable not found");
            }
        } else {
            throw new IllegalArgumentException("Product is not of type Cable");
        }
    }

    @Override
    public void deleteProduct(String id) {
        Optional<Cable> cable = cableRepository.findById(id);
        if (cable.isPresent()) {
            cableRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cable not found");
        }
    }

    @Override
    public Product getProduct(String id) {
        Optional<Cable> cable = cableRepository.findById(id);
        return cable.orElseThrow(() -> new RuntimeException("Cable not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) (List<?>) cableRepository.findAll();
    }
}
