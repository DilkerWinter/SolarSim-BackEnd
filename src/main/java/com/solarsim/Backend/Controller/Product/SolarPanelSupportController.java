package com.solarsim.Backend.Controller.Product;

import com.solarsim.Backend.Model.Product.SolarPanelSupport;
import com.solarsim.Backend.Service.ProductService.SolarPanelSupportService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product/solarpanelsupport")
public class SolarPanelSupportController {

    private final SolarPanelSupportService supportService;

    public SolarPanelSupportController(SolarPanelSupportService supportService) {
        this.supportService = supportService;
    }

    @GetMapping
    public ResponseEntity<List<SolarPanelSupport>> getAllSupports() {
        return ResponseEntity.ok().body(supportService.getAllSupports());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolarPanelSupport> getSupportById(@PathVariable String id) {
        return supportService.getSupportById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createSupport(@Valid @RequestBody SolarPanelSupport support) {
        supportService.addSupport(support);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateSupport(@Valid @RequestBody SolarPanelSupport support) {
        boolean updated = supportService.updateSupport(support);
        if (!updated) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteSupport(@RequestParam String id) {
        boolean deleted = supportService.deleteSupport(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
