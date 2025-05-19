package com.solarsim.Backend.Service.ProductService;

import com.solarsim.Backend.Model.Product.Cable;
import com.solarsim.Backend.Repository.ProductRepository.CableRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CableServiceTest {

    private CableRepository cableRepository;
    private CableService cableService;

    @BeforeEach
    void setUp() {
        cableRepository = Mockito.mock(CableRepository.class);
        cableService = new CableService(cableRepository);
    }

    @Test
    @DisplayName("Should return a list of all cables")
    void findAllCablesSuccess() {
        Cable cable1 = new Cable();
        cable1.setId("1");
        Cable cable2 = new Cable();
        cable2.setId("2");

        when(cableRepository.findAll()).thenReturn(Arrays.asList(cable1, cable2));

        List<Cable> cables = cableService.getAllCables();

        assertNotNull(cables);
        assertEquals(2, cables.size());
        verify(cableRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return cable when found by ID")
    void findCableByIdSuccess() {
        Cable cable = new Cable();
        cable.setId("123");

        when(cableRepository.findById("123")).thenReturn(Optional.of(cable));

        Optional<Cable> result = cableService.getCableById("123");

        assertTrue(result.isPresent());
        assertEquals("123", result.get().getId());
        verify(cableRepository, times(1)).findById("123");
    }

    @Test
    @DisplayName("Should return empty when cable is not found by ID")
    void findCableByIdFailure() {
        when(cableRepository.findById("not_exist")).thenReturn(Optional.empty());

        Optional<Cable> result = cableService.getCableById("not_exist");

        assertFalse(result.isPresent());
        verify(cableRepository, times(1)).findById("not_exist");
    }

    @Test
    @DisplayName("Should save a new cable")
    void addCableSuccess() {
        Cable cable = new Cable();
        cable.setId("abc");

        cableService.addCable(cable);

        verify(cableRepository, times(1)).save(cable);
    }

    @Test
    @DisplayName("Should update existing cable and return true")
    void updateCableSuccess() {
        Cable cable = new Cable();
        cable.setId("updateId");

        when(cableRepository.findById("updateId")).thenReturn(Optional.of(cable));

        boolean result = cableService.updateCable(cable);

        assertTrue(result);
        verify(cableRepository, times(1)).findById("updateId");
        verify(cableRepository, times(1)).save(cable);
    }

    @Test
    @DisplayName("Should return false when trying to update non-existent cable")
    void updateCableFailure() {
        Cable cable = new Cable();
        cable.setId("missingId");

        when(cableRepository.findById("missingId")).thenReturn(Optional.empty());

        boolean result = cableService.updateCable(cable);

        assertFalse(result);
        verify(cableRepository, times(1)).findById("missingId");
        verify(cableRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should delete existing cable and return true")
    void deleteCableSuccess() {
        Cable cable = new Cable();
        cable.setId("deleteId");

        when(cableRepository.findById("deleteId")).thenReturn(Optional.of(cable));

        boolean result = cableService.deleteCable("deleteId");

        assertTrue(result);
        verify(cableRepository, times(1)).findById("deleteId");
        verify(cableRepository, times(1)).deleteById("deleteId");
    }

    @Test
    @DisplayName("Should return false when trying to delete non-existent cable")
    void deleteCableFailure() {
        when(cableRepository.findById("nonExistId")).thenReturn(Optional.empty());

        boolean result = cableService.deleteCable("nonExistId");

        assertFalse(result);
        verify(cableRepository, times(1)).findById("nonExistId");
        verify(cableRepository, never()).deleteById(anyString());
    }
}
