package com.solarsim.Backend.Controller;

import com.solarsim.Backend.Model.Product.ProductDTO.ProductDTO;
import com.solarsim.Backend.Model.Product.ProductType.ProductType;
import com.solarsim.Backend.Service.ProductService.ProductServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductServiceFacade productServiceFacade;

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody ProductDTO productDTO) {
        productServiceFacade.addProduct(productDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getProducts(@RequestParam(required = false) ProductType productType,
                                         @RequestParam(required = false) String id) {
        Object result = productServiceFacade.getProducts(productType, id);
        return ResponseEntity.ok(result);
    }
}
