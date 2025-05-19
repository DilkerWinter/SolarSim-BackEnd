package com.solarsim.Backend.Service.ProductService;

import com.solarsim.Backend.Model.Product.Product;
import com.solarsim.Backend.Repository.ProductRepository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private CableRepository cableRepository;
    private ConectorMc4Repository conectorMc4Repository;
    private InverterRepository inverterRepository;
    private SolarPanelRepository solarPanelRepository;
    private SolarPanelSupportRepository solarPanelSupportRepository;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        cableRepository = Mockito.mock(CableRepository.class);
        conectorMc4Repository = Mockito.mock(ConectorMc4Repository.class);
        inverterRepository = Mockito.mock(InverterRepository.class);
        solarPanelRepository = Mockito.mock(SolarPanelRepository.class);
        solarPanelSupportRepository = Mockito.mock(SolarPanelSupportRepository.class);

        productService = new ProductService(
                cableRepository,
                conectorMc4Repository,
                inverterRepository,
                solarPanelRepository,
                solarPanelSupportRepository
        );
    }

    @Test
    @DisplayName("Should return all products grouped by type")
    void getAllProductsSuccess() {
        when(cableRepository.findAll()).thenReturn(Collections.emptyList());
        when(conectorMc4Repository.findAll()).thenReturn(Collections.emptyList());
        when(inverterRepository.findAll()).thenReturn(Collections.emptyList());
        when(solarPanelRepository.findAll()).thenReturn(Collections.emptyList());
        when(solarPanelSupportRepository.findAll()).thenReturn(Collections.emptyList());

        Map<String, List<? extends Product>> products = productService.getAllProducts();

        assertNotNull(products);
        assertEquals(5, products.size());

        assertTrue(products.containsKey("CABLE"));
        assertTrue(products.containsKey("CONECTORMC4"));
        assertTrue(products.containsKey("INVERTER"));
        assertTrue(products.containsKey("SOLARPANEL"));
        assertTrue(products.containsKey("SOLARPANELSUPPORT"));

        verify(cableRepository, times(1)).findAll();
        verify(conectorMc4Repository, times(1)).findAll();
        verify(inverterRepository, times(1)).findAll();
        verify(solarPanelRepository, times(1)).findAll();
        verify(solarPanelSupportRepository, times(1)).findAll();
    }
}
