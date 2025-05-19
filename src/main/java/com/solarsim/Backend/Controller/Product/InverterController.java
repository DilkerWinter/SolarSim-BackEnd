package com.solarsim.Backend.Controller.Product;

import com.solarsim.Backend.Model.Product.Inverter;
import com.solarsim.Backend.Service.ProductService.InverterService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product/inverter")
public class InverterController {

    private final InverterService inverterService;

    public InverterController(InverterService inverterService) {
        this.inverterService = inverterService;
    }

    @GetMapping
    public ResponseEntity<List<Inverter>> getAllInverters() {
        return ResponseEntity.ok().body(inverterService.getAllInverters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inverter> getInverterById(@PathVariable String id) {
        return inverterService.getInverterById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createInverter(@Valid @RequestBody Inverter inverter) {
        inverterService.addInverter(inverter);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateInverter(@Valid @RequestBody Inverter inverter) {
        boolean updated = inverterService.updateInverter(inverter);
        if (!updated) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteInverter(@RequestParam String id) {
        boolean deleted = inverterService.deleteInverter(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
