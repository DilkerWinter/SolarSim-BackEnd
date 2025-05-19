package com.solarsim.Backend.Controller.Product;

import com.solarsim.Backend.Model.Product.Product;
import com.solarsim.Backend.Service.ProductService.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Map<String, List<? extends Product>>> getAll() {
        Map<String, List<? extends Product>> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
}
