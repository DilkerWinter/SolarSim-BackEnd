package com.solarsim.Backend.Repository.ProductRepository;

import com.solarsim.Backend.Model.Product.SolarPanelSupport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SolarPanelSupportRepository extends JpaRepository<SolarPanelSupport, String> {
}
