package com.solarsim.Backend.Service.ProductService;

import com.solarsim.Backend.Model.Product.SolarPanelSupport;
import com.solarsim.Backend.Model.Product.Product;
import com.solarsim.Backend.Repository.ProductRepository.SolarPanelSupportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolarPanelSupportService implements ProductService {

    @Autowired
    private SolarPanelSupportRepository solarPanelSupportRepository;

    public SolarPanelSupportService(SolarPanelSupportRepository solarPanelSupportRepository) {
        this.solarPanelSupportRepository = solarPanelSupportRepository;
    }

    @Override
    public void addProduct(Product product) {
        if (product instanceof SolarPanelSupport) {
            SolarPanelSupport support = (SolarPanelSupport) product;
            solarPanelSupportRepository.save(support);
        } else {
            throw new IllegalArgumentException("Product is not of type SolarPanelSupport");
        }
    }

    @Override
    public void updateProduct(Product product) {
        if (product instanceof SolarPanelSupport) {
            SolarPanelSupport support = (SolarPanelSupport) product;
            Optional<SolarPanelSupport> existing = solarPanelSupportRepository.findById(support.getId());
            if (existing.isPresent()) {
                solarPanelSupportRepository.save(support);
            } else {
                throw new RuntimeException("SolarPanelSupport not found");
            }
        } else {
            throw new IllegalArgumentException("Product is not of type SolarPanelSupport");
        }
    }

    @Override
    public void deleteProduct(String id) {
        Optional<SolarPanelSupport> support = solarPanelSupportRepository.findById(id);
        if (support.isPresent()) {
            solarPanelSupportRepository.deleteById(id);
        } else {
            throw new RuntimeException("SolarPanelSupport not found");
        }
    }

    @Override
    public Product getProductById(String id) {
        Optional<SolarPanelSupport> support = solarPanelSupportRepository.findById(id);
        return support.orElseThrow(() -> new RuntimeException("SolarPanelSupport not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) (List<?>) solarPanelSupportRepository.findAll();
    }
}
