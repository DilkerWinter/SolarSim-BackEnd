package com.solarsim.Backend.Controller.Product;

import com.solarsim.Backend.Model.Product.Cable;
import com.solarsim.Backend.Service.ProductService.CableService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product/cable")
public class CableController {

    private final CableService cableService;

    public CableController(CableService cableService) {
        this.cableService = cableService;
    }

    @GetMapping
    public ResponseEntity<List<Cable>> getAllCables(){
        cableService.getAllCables();
        return ResponseEntity.ok().body(cableService.getAllCables());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cable> getCableById(@PathVariable String id) {
        return cableService.getCableById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cable> createCable(@Valid @RequestBody Cable cable) {
        cableService.addCable(cable);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Cable> updateCable(@Valid @RequestBody Cable cable) {
        boolean deleted = cableService.updateCable(cable);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteCableById(@RequestParam String id) {
        boolean deleted = cableService.deleteCable(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
