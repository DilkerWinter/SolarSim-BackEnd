package com.solarsim.Backend.Service.ProductService;

import com.solarsim.Backend.Model.Product.SolarPanel;
import com.solarsim.Backend.Repository.ProductRepository.SolarPanelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SolarPanelServiceTest {

    private SolarPanelRepository solarPanelRepository;
    private SolarPanelService solarPanelService;

    @BeforeEach
    void setUp() {
        solarPanelRepository = Mockito.mock(SolarPanelRepository.class);
        solarPanelService = new SolarPanelService(solarPanelRepository);
    }

    @Test
    @DisplayName("Should return a list of all solar panels")
    void findAllSolarPanelsSuccess() {
        SolarPanel panel1 = new SolarPanel();
        panel1.setId("1");
        SolarPanel panel2 = new SolarPanel();
        panel2.setId("2");

        when(solarPanelRepository.findAll()).thenReturn(Arrays.asList(panel1, panel2));

        List<SolarPanel> panels = solarPanelService.getAllSolarPanels();

        assertNotNull(panels);
        assertEquals(2, panels.size());
        verify(solarPanelRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return solar panel when found by ID")
    void findSolarPanelByIdSuccess() {
        SolarPanel panel = new SolarPanel();
        panel.setId("123");

        when(solarPanelRepository.findById("123")).thenReturn(Optional.of(panel));

        Optional<SolarPanel> result = solarPanelService.getSolarPanelById("123");

        assertTrue(result.isPresent());
        assertEquals("123", result.get().getId());
        verify(solarPanelRepository, times(1)).findById("123");
    }

    @Test
    @DisplayName("Should return empty when solar panel is not found by ID")
    void findSolarPanelByIdFailure() {
        when(solarPanelRepository.findById("not_exist")).thenReturn(Optional.empty());

        Optional<SolarPanel> result = solarPanelService.getSolarPanelById("not_exist");

        assertFalse(result.isPresent());
        verify(solarPanelRepository, times(1)).findById("not_exist");
    }

    @Test
    @DisplayName("Should save a new solar panel")
    void addSolarPanelSuccess() {
        SolarPanel panel = new SolarPanel();
        panel.setId("abc");

        solarPanelService.addSolarPanel(panel);

        verify(solarPanelRepository, times(1)).save(panel);
    }

    @Test
    @DisplayName("Should update existing solar panel and return true")
    void updateSolarPanelSuccess() {
        SolarPanel panel = new SolarPanel();
        panel.setId("updateId");

        when(solarPanelRepository.findById("updateId")).thenReturn(Optional.of(panel));

        boolean result = solarPanelService.updateSolarPanel(panel);

        assertTrue(result);
        verify(solarPanelRepository, times(1)).findById("updateId");
        verify(solarPanelRepository, times(1)).save(panel);
    }

    @Test
    @DisplayName("Should return false when trying to update non-existent solar panel")
    void updateSolarPanelFailure() {
        SolarPanel panel = new SolarPanel();
        panel.setId("missingId");

        when(solarPanelRepository.findById("missingId")).thenReturn(Optional.empty());

        boolean result = solarPanelService.updateSolarPanel(panel);

        assertFalse(result);
        verify(solarPanelRepository, times(1)).findById("missingId");
        verify(solarPanelRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should delete existing solar panel and return true")
    void deleteSolarPanelSuccess() {
        SolarPanel panel = new SolarPanel();
        panel.setId("deleteId");

        when(solarPanelRepository.findById("deleteId")).thenReturn(Optional.of(panel));

        boolean result = solarPanelService.deleteSolarPanel("deleteId");

        assertTrue(result);
        verify(solarPanelRepository, times(1)).findById("deleteId");
        verify(solarPanelRepository, times(1)).deleteById("deleteId");
    }

    @Test
    @DisplayName("Should return false when trying to delete non-existent solar panel")
    void deleteSolarPanelFailure() {
        when(solarPanelRepository.findById("nonExistId")).thenReturn(Optional.empty());

        boolean result = solarPanelService.deleteSolarPanel("nonExistId");

        assertFalse(result);
        verify(solarPanelRepository, times(1)).findById("nonExistId");
        verify(solarPanelRepository, never()).deleteById(anyString());
    }
}
