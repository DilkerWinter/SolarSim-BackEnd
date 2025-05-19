package com.solarsim.Backend.Service.ProductService;

import com.solarsim.Backend.Model.Product.SolarPanelSupport;
import com.solarsim.Backend.Repository.ProductRepository.SolarPanelSupportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SolarPanelSupportServiceTest {

    private SolarPanelSupportRepository supportRepository;
    private SolarPanelSupportService supportService;

    @BeforeEach
    void setUp() {
        supportRepository = Mockito.mock(SolarPanelSupportRepository.class);
        supportService = new SolarPanelSupportService(supportRepository);
    }

    @Test
    @DisplayName("Should return a list of all solar panel supports")
    void findAllSupportsSuccess() {
        SolarPanelSupport support1 = new SolarPanelSupport();
        support1.setId("1");
        SolarPanelSupport support2 = new SolarPanelSupport();
        support2.setId("2");

        when(supportRepository.findAll()).thenReturn(Arrays.asList(support1, support2));

        List<SolarPanelSupport> supports = supportService.getAllSupports();

        assertNotNull(supports);
        assertEquals(2, supports.size());
        verify(supportRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return solar panel support when found by ID")
    void findSupportByIdSuccess() {
        SolarPanelSupport support = new SolarPanelSupport();
        support.setId("123");

        when(supportRepository.findById("123")).thenReturn(Optional.of(support));

        Optional<SolarPanelSupport> result = supportService.getSupportById("123");

        assertTrue(result.isPresent());
        assertEquals("123", result.get().getId());
        verify(supportRepository, times(1)).findById("123");
    }

    @Test
    @DisplayName("Should return empty when solar panel support is not found by ID")
    void findSupportByIdFailure() {
        when(supportRepository.findById("not_exist")).thenReturn(Optional.empty());

        Optional<SolarPanelSupport> result = supportService.getSupportById("not_exist");

        assertFalse(result.isPresent());
        verify(supportRepository, times(1)).findById("not_exist");
    }

    @Test
    @DisplayName("Should save a new solar panel support")
    void addSupportSuccess() {
        SolarPanelSupport support = new SolarPanelSupport();
        support.setId("abc");

        supportService.addSupport(support);

        verify(supportRepository, times(1)).save(support);
    }

    @Test
    @DisplayName("Should update existing solar panel support and return true")
    void updateSupportSuccess() {
        SolarPanelSupport support = new SolarPanelSupport();
        support.setId("updateId");

        when(supportRepository.findById("updateId")).thenReturn(Optional.of(support));

        boolean result = supportService.updateSupport(support);

        assertTrue(result);
        verify(supportRepository, times(1)).findById("updateId");
        verify(supportRepository, times(1)).save(support);
    }

    @Test
    @DisplayName("Should return false when trying to update non-existent solar panel support")
    void updateSupportFailure() {
        SolarPanelSupport support = new SolarPanelSupport();
        support.setId("missingId");

        when(supportRepository.findById("missingId")).thenReturn(Optional.empty());

        boolean result = supportService.updateSupport(support);

        assertFalse(result);
        verify(supportRepository, times(1)).findById("missingId");
        verify(supportRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should delete existing solar panel support and return true")
    void deleteSupportSuccess() {
        SolarPanelSupport support = new SolarPanelSupport();
        support.setId("deleteId");

        when(supportRepository.findById("deleteId")).thenReturn(Optional.of(support));

        boolean result = supportService.deleteSupport("deleteId");

        assertTrue(result);
        verify(supportRepository, times(1)).findById("deleteId");
        verify(supportRepository, times(1)).deleteById("deleteId");
    }

    @Test
    @DisplayName("Should return false when trying to delete non-existent solar panel support")
    void deleteSupportFailure() {
        when(supportRepository.findById("nonExistId")).thenReturn(Optional.empty());

        boolean result = supportService.deleteSupport("nonExistId");

        assertFalse(result);
        verify(supportRepository, times(1)).findById("nonExistId");
        verify(supportRepository, never()).deleteById(anyString());
    }
}
