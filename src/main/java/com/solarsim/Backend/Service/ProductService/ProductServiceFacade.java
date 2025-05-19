package com.solarsim.Backend.Service.ProductService;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solarsim.Backend.Model.Product.*;
import com.solarsim.Backend.Model.Product.ProductDTO.ProductDTO;
import com.solarsim.Backend.Model.Product.ProductType.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ProductServiceFacade {

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

    public void addProduct(ProductDTO productDTO) {
        ProductService service = productHandler(productDTO.productType());
        Product product = convertTo(productDTO.productType(), productDTO.data());
        service.addProduct(product);
    }

    public Object getProducts(ProductType productType, String id) {
        if (productType != null && id != null) {
            ProductService service = productHandler(productType);
            return service.getProductById(id);
        }

        if (productType != null) {
            ProductService service = productHandler(productType);
            return service.getAllProducts();
        }

        Map<ProductType, List<Product>> allProducts = new HashMap<>();

        allProducts.put(ProductType.CABLE, new ArrayList<>(cableService.getAllProducts()));
        allProducts.put(ProductType.INVERTER, new ArrayList<>(inverterService.getAllProducts()));
        allProducts.put(ProductType.SOLARPANEL, new ArrayList<>(solarPanelService.getAllProducts()));
        allProducts.put(ProductType.SOLARPANELSUPPORT, new ArrayList<>(solarPanelSupportService.getAllProducts()));
        allProducts.put(ProductType.CONECTORMC4, new ArrayList<>(conectorMc4Service.getAllProducts()));

        return allProducts;
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
        Product product;

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
