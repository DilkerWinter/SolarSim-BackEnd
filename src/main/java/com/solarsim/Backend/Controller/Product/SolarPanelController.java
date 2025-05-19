package com.solarsim.Backend.Controller.Product;

import com.solarsim.Backend.Model.Product.SolarPanel;
import com.solarsim.Backend.Service.ProductService.SolarPanelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product/solarpanel")
public class SolarPanelController {

    private final SolarPanelService solarPanelService;

    public SolarPanelController(SolarPanelService solarPanelService) {
        this.solarPanelService = solarPanelService;
    }

    @GetMapping
    public ResponseEntity<List<SolarPanel>> getAllSolarPanels() {
        List<SolarPanel> list = solarPanelService.getAllSolarPanels();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolarPanel> getSolarPanelById(@PathVariable String id) {
        return solarPanelService.getSolarPanelById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createSolarPanel(@Valid @RequestBody SolarPanel solarPanel) {
        solarPanelService.addSolarPanel(solarPanel);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateSolarPanel(@Valid @RequestBody SolarPanel solarPanel) {
        boolean updated = solarPanelService.updateSolarPanel(solarPanel);
        if (!updated) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSolarPanelById(@RequestParam String id) {
        boolean deleted = solarPanelService.deleteSolarPanel(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
