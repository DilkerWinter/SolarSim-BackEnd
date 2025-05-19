package com.solarsim.Backend.Service.ProductService;

import com.solarsim.Backend.Model.Product.Product;
import com.solarsim.Backend.Repository.ProductRepository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final CableRepository cableRepository;
    private final ConectorMc4Repository conectorMc4Repository;
    private final InverterRepository inverterRepository;
    private final SolarPanelRepository solarPanelRepository;
    private final SolarPanelSupportRepository solarPanelSupportRepository;

    public ProductService(CableRepository cableRepository, ConectorMc4Repository conectorMc4Repository, InverterRepository inverterRepository, SolarPanelRepository solarPanelRepository, SolarPanelSupportRepository solarPanelSupportRepository) {
        this.cableRepository = cableRepository;
        this.conectorMc4Repository = conectorMc4Repository;
        this.inverterRepository = inverterRepository;
        this.solarPanelRepository = solarPanelRepository;
        this.solarPanelSupportRepository = solarPanelSupportRepository;
    }

    public Map<String, List<? extends Product>> getAllProducts() {
        Map<String, List<? extends Product>> result = new HashMap<>();
        result.put("CABLE", cableRepository.findAll());
        result.put("SOLARPANELSUPPORT", solarPanelSupportRepository.findAll());
        result.put("INVERTER", inverterRepository.findAll());
        result.put("SOLARPANEL", solarPanelRepository.findAll());
        result.put("CONECTORMC4", conectorMc4Repository.findAll());
        return result;
    }
}
