package com.solarsim.Backend.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solarsim.Backend.Model.Product.*;
import com.solarsim.Backend.Model.Product.ProductDTO.ProductDTO;
import com.solarsim.Backend.Model.Product.ProductType.ProductType;
import com.solarsim.Backend.Service.ProductService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        ProductService service = productHandler(productDTO);
        Product product = convertTo(productDTO.productType(), productDTO.data());
        service.addProduct(product);
        return ResponseEntity.ok().build();
    }

    private ProductService productHandler(ProductDTO productDTO) {
        switch (productDTO.productType()) {
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
                throw new IllegalArgumentException("Unknown product type: " + productDTO.productType());
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
