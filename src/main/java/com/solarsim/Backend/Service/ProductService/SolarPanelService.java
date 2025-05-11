package com.solarsim.Backend.Service.ProductService;

import com.solarsim.Backend.Model.Product.SolarPanel;
import com.solarsim.Backend.Model.Product.Product;
import com.solarsim.Backend.Repository.ProductRepository.SolarPanelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolarPanelService implements ProductService {

    @Autowired
    private SolarPanelRepository solarPanelRepository;

    public SolarPanelService(SolarPanelRepository solarPanelRepository) {
        this.solarPanelRepository = solarPanelRepository;
    }

    @Override
    public void addProduct(Product product) {
        if (product instanceof SolarPanel) {
            SolarPanel panel = (SolarPanel) product;
            solarPanelRepository.save(panel);
        } else {
            throw new IllegalArgumentException("Product is not of type SolarPanel");
        }
    }

    @Override
    public void updateProduct(Product product) {
        if (product instanceof SolarPanel) {
            SolarPanel panel = (SolarPanel) product;
            Optional<SolarPanel> existing = solarPanelRepository.findById(panel.getId());
            if (existing.isPresent()) {
                solarPanelRepository.save(panel);
            } else {
                throw new RuntimeException("SolarPanel not found");
            }
        } else {
            throw new IllegalArgumentException("Product is not of type SolarPanel");
        }
    }

    @Override
    public void deleteProduct(String id) {
        Optional<SolarPanel> panel = solarPanelRepository.findById(id);
        if (panel.isPresent()) {
            solarPanelRepository.deleteById(id);
        } else {
            throw new RuntimeException("SolarPanel not found");
        }
    }

    @Override
    public Product getProduct(String id) {
        Optional<SolarPanel> panel = solarPanelRepository.findById(id);
        return panel.orElseThrow(() -> new RuntimeException("SolarPanel not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) (List<?>) solarPanelRepository.findAll();
    }
}
