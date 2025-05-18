package com.solarsim.Backend.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solarsim.Backend.Model.Product.*;
import com.solarsim.Backend.Model.Product.ProductDTO.ProductDTO;
import com.solarsim.Backend.Model.Product.ProductType.ProductType;
import com.solarsim.Backend.Service.ProductService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private CableService cableService;
    @Autowired
    private ConectorMc4Service conectorMc4Service;
    @Autowired
    private InverterService inverterService;
    @Autowired
    private SolarPanelService solarPanelService;
    @Autowired
    private SolarPanelSupportService solarPanelSupportService;

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody ProductDTO productDTO) {
        ProductService service = productHandler(productDTO.productType());
        Product product = convertTo(productDTO.productType(), productDTO.data());
        service.addProduct(product);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getProducts(@RequestParam(required = false) ProductType productType,
                                         @RequestParam(required = false) String id ) {
        if (productType != null && id != null) {
            ProductService service = productHandler(productType);
            Product product = service.getProductById(id);
            return ResponseEntity.ok(product);
        }

        if (productType != null) {
            ProductService service = productHandler(productType);
            List<Product> products = service.getAllProducts();
            return ResponseEntity.ok(products);
        }

        List<Map<ProductType, List<Object>>> allProducts = new ArrayList<>();

        List<Product> cables = cableService.getAllProducts();
        Map<ProductType, List<Object>> cableMap = new HashMap<>();
        cableMap.put(ProductType.CABLE, new ArrayList<>(cables));
        allProducts.add(cableMap);

        List<Product> inverters = inverterService.getAllProducts();
        Map<ProductType, List<Object>> inverterMap = new HashMap<>();
        inverterMap.put(ProductType.INVERTER, new ArrayList<>(inverters));
        allProducts.add(inverterMap);

        List<Product> solarPanels = solarPanelService.getAllProducts();
        Map<ProductType, List<Object>> solarPanelMap = new HashMap<>();
        solarPanelMap.put(ProductType.SOLARPANEL, new ArrayList<>(solarPanels));
        allProducts.add(solarPanelMap);

        List<Product> solarPanelSupports = solarPanelSupportService.getAllProducts();
        Map<ProductType, List<Object>> solarPanelSupportMap = new HashMap<>();
        solarPanelSupportMap.put(ProductType.SOLARPANEL, new ArrayList<>(solarPanelSupports));
        allProducts.add(solarPanelSupportMap);


        List<Product> conectors = conectorMc4Service.getAllProducts();
        Map<ProductType, List<Object>> conectorMap = new HashMap<>();
        conectorMap.put(ProductType.CONECTORMC4, new ArrayList<>(conectors));
        allProducts.add(conectorMap);

        return ResponseEntity.ok(allProducts);
    }

    private ProductService productHandler(ProductType productType) {
        switch (productType) {
            case CABLE:
                return cableService;
            case CONECTORMC4:
                return conectorMc4Service;
            case INVERTER:
                return inverterService;
            case SOLARPANEL:
                return solarPanelService;
            case SOLARPANELSUPPORT:
                return solarPanelSupportService;
            default:
                throw new IllegalArgumentException("Unknown product type: " + productType);
        }
    }

    private Product convertTo(ProductType productType, Map<String, Object> data) {
        ObjectMapper mapper = new ObjectMapper();
        Product product = null;

        switch (productType) {
            case CABLE:
                product = mapper.convertValue(data, Cable.class);
                break;
            case CONECTORMC4:
                product = mapper.convertValue(data, ConectorMc4.class);
                break;
            case INVERTER:
                product = mapper.convertValue(data, Inverter.class);
                break;
            case SOLARPANEL:
                product = mapper.convertValue(data, SolarPanel.class);
                break;
            case SOLARPANELSUPPORT:
                product = mapper.convertValue(data, SolarPanelSupport.class);
                break;
            default:
                throw new IllegalArgumentException("Unknown product type: " + productType);
        }
        return product;
    }
}
