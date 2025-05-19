package com.solarsim.Backend.Service.ProductService;

import com.solarsim.Backend.Model.Product.SolarPanel;
import com.solarsim.Backend.Repository.ProductRepository.SolarPanelRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolarPanelService {

    private final SolarPanelRepository solarPanelRepository;

    public SolarPanelService(SolarPanelRepository solarPanelRepository) {
        this.solarPanelRepository = solarPanelRepository;
    }

    public List<SolarPanel> getAllSolarPanels() {
        return solarPanelRepository.findAll();
    }

    public Optional<SolarPanel> getSolarPanelById(String id) {
        return solarPanelRepository.findById(id);
    }

    @Transactional
    public void addSolarPanel(SolarPanel solarPanel) {
        solarPanelRepository.save(solarPanel);
    }

    @Transactional
    public boolean updateSolarPanel(SolarPanel solarPanel) {
        Optional<SolarPanel> optional = solarPanelRepository.findById(solarPanel.getId());
        if (optional.isPresent()) {
            solarPanelRepository.save(solarPanel);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteSolarPanel(String id) {
        Optional<SolarPanel> optional = solarPanelRepository.findById(id);
        if (optional.isPresent()) {
            solarPanelRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
