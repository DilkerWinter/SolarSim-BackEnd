package com.solarsim.Backend.Controller.Product;

import com.solarsim.Backend.Model.Product.ConectorMc4;
import com.solarsim.Backend.Service.ProductService.ConectorMc4Service;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product/conectormc4")
public class ConectorMc4Controller {

    private final ConectorMc4Service conectorMc4Service;

    public ConectorMc4Controller(ConectorMc4Service conectorMc4Service) {
        this.conectorMc4Service = conectorMc4Service;
    }

    @GetMapping
    public ResponseEntity<List<ConectorMc4>> getAllConectorMc4() {
        List<ConectorMc4> list = conectorMc4Service.getAllConectorMc4();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConectorMc4> getConectorMc4ById(@PathVariable String id) {
        return conectorMc4Service.getConectorMc4ById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createConectorMc4(@Valid @RequestBody ConectorMc4 conectorMc4) {
        conectorMc4Service.addConectorMc4(conectorMc4);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateConectorMc4(@Valid @RequestBody ConectorMc4 conectorMc4) {
        boolean updated = conectorMc4Service.updateConectorMc4(conectorMc4);
        if (!updated) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteConectorMc4ById(@RequestParam String id) {
        boolean deleted = conectorMc4Service.deleteConectorMc4(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
