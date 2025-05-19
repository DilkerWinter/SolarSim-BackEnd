package com.solarsim.Backend.Service.ProductService;

import com.solarsim.Backend.Model.Product.SolarPanelSupport;
import com.solarsim.Backend.Repository.ProductRepository.SolarPanelSupportRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolarPanelSupportService {

    private final SolarPanelSupportRepository supportRepository;

    public SolarPanelSupportService(SolarPanelSupportRepository supportRepository) {
        this.supportRepository = supportRepository;
    }

    public List<SolarPanelSupport> getAllSupports() {
        return supportRepository.findAll();
    }

    public Optional<SolarPanelSupport> getSupportById(String id) {
        return supportRepository.findById(id);
    }

    @Transactional
    public void addSupport(SolarPanelSupport support) {
        supportRepository.save(support);
    }

    @Transactional
    public boolean updateSupport(SolarPanelSupport support) {
        Optional<SolarPanelSupport> existing = supportRepository.findById(support.getId());
        if (existing.isPresent()) {
            supportRepository.save(support);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteSupport(String id) {
        Optional<SolarPanelSupport> existing = supportRepository.findById(id);
        if (existing.isPresent()) {
            supportRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
