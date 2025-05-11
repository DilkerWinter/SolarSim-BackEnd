package com.solarsim.Backend.Repository.ProductRepository;

import com.solarsim.Backend.Model.Product.SolarPanel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolarPanelRepository extends JpaRepository<SolarPanel, String> {
}
