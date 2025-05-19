package com.solarsim.Backend.Service.ProductService;

import com.solarsim.Backend.Model.Product.Inverter;
import com.solarsim.Backend.Repository.ProductRepository.InverterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InverterServiceTest {

    private InverterRepository inverterRepository;
    private InverterService inverterService;

    @BeforeEach
    void setUp() {
        inverterRepository = Mockito.mock(InverterRepository.class);
        inverterService = new InverterService(inverterRepository);
    }

    @Test
    @DisplayName("Should return a list of all inverters")
    void findAllInvertersSuccess() {
        Inverter inverter1 = new Inverter();
        inverter1.setId("1");
        Inverter inverter2 = new Inverter();
        inverter2.setId("2");

        when(inverterRepository.findAll()).thenReturn(Arrays.asList(inverter1, inverter2));

        List<Inverter> inverters = inverterService.getAllInverters();

        assertNotNull(inverters);
        assertEquals(2, inverters.size());
        verify(inverterRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return inverter when found by ID")
    void findInverterByIdSuccess() {
        Inverter inverter = new Inverter();
        inverter.setId("123");

        when(inverterRepository.findById("123")).thenReturn(Optional.of(inverter));

        Optional<Inverter> result = inverterService.getInverterById("123");

        assertTrue(result.isPresent());
        assertEquals("123", result.get().getId());
        verify(inverterRepository, times(1)).findById("123");
    }

    @Test
    @DisplayName("Should return empty when inverter is not found by ID")
    void findInverterByIdFailure() {
        when(inverterRepository.findById("not_exist")).thenReturn(Optional.empty());

        Optional<Inverter> result = inverterService.getInverterById("not_exist");

        assertFalse(result.isPresent());
        verify(inverterRepository, times(1)).findById("not_exist");
    }

    @Test
    @DisplayName("Should save a new inverter")
    void addInverterSuccess() {
        Inverter inverter = new Inverter();
        inverter.setId("abc");

        inverterService.addInverter(inverter);

        verify(inverterRepository, times(1)).save(inverter);
    }

    @Test
    @DisplayName("Should update existing inverter and return true")
    void updateInverterSuccess() {
        Inverter inverter = new Inverter();
        inverter.setId("updateId");

        when(inverterRepository.findById("updateId")).thenReturn(Optional.of(inverter));

        boolean result = inverterService.updateInverter(inverter);

        assertTrue(result);
        verify(inverterRepository, times(1)).findById("updateId");
        verify(inverterRepository, times(1)).save(inverter);
    }

    @Test
    @DisplayName("Should return false when trying to update non-existent inverter")
    void updateInverterFailure() {
        Inverter inverter = new Inverter();
        inverter.setId("missingId");

        when(inverterRepository.findById("missingId")).thenReturn(Optional.empty());

        boolean result = inverterService.updateInverter(inverter);

        assertFalse(result);
        verify(inverterRepository, times(1)).findById("missingId");
        verify(inverterRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should delete existing inverter and return true")
    void deleteInverterSuccess() {
        Inverter inverter = new Inverter();
        inverter.setId("deleteId");

        when(inverterRepository.findById("deleteId")).thenReturn(Optional.of(inverter));

        boolean result = inverterService.deleteInverter("deleteId");

        assertTrue(result);
        verify(inverterRepository, times(1)).findById("deleteId");
        verify(inverterRepository, times(1)).deleteById("deleteId");
    }

    @Test
    @DisplayName("Should return false when trying to delete non-existent inverter")
    void deleteInverterFailure() {
        when(inverterRepository.findById("nonExistId")).thenReturn(Optional.empty());

        boolean result = inverterService.deleteInverter("nonExistId");

        assertFalse(result);
        verify(inverterRepository, times(1)).findById("nonExistId");
        verify(inverterRepository, never()).deleteById(anyString());
    }
}
