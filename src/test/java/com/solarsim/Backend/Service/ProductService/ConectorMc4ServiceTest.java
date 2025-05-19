package com.solarsim.Backend.Service.ProductService;

import com.solarsim.Backend.Model.Product.ConectorMc4;
import com.solarsim.Backend.Repository.ProductRepository.ConectorMc4Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConectorMc4ServiceTest {

    private ConectorMc4Repository conectorMc4Repository;
    private ConectorMc4Service conectorMc4Service;

    @BeforeEach
    void setUp() {
        conectorMc4Repository = Mockito.mock(ConectorMc4Repository.class);
        conectorMc4Service = new ConectorMc4Service(conectorMc4Repository);
    }

    @Test
    @DisplayName("Should return a list of all ConectorMc4")
    void findAllConectorMc4Success() {
        ConectorMc4 c1 = new ConectorMc4();
        c1.setId("1");
        ConectorMc4 c2 = new ConectorMc4();
        c2.setId("2");

        when(conectorMc4Repository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<ConectorMc4> connectors = conectorMc4Service.getAllConectorMc4();

        assertNotNull(connectors);
        assertEquals(2, connectors.size());
        verify(conectorMc4Repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return ConectorMc4 when found by ID")
    void findConectorMc4ByIdSuccess() {
        ConectorMc4 c = new ConectorMc4();
        c.setId("123");

        when(conectorMc4Repository.findById("123")).thenReturn(Optional.of(c));

        Optional<ConectorMc4> result = conectorMc4Service.getConectorMc4ById("123");

        assertTrue(result.isPresent());
        assertEquals("123", result.get().getId());
        verify(conectorMc4Repository, times(1)).findById("123");
    }

    @Test
    @DisplayName("Should return empty when ConectorMc4 is not found by ID")
    void findConectorMc4ByIdFailure() {
        when(conectorMc4Repository.findById("not_exist")).thenReturn(Optional.empty());

        Optional<ConectorMc4> result = conectorMc4Service.getConectorMc4ById("not_exist");

        assertFalse(result.isPresent());
        verify(conectorMc4Repository, times(1)).findById("not_exist");
    }

    @Test
    @DisplayName("Should save a new ConectorMc4")
    void addConectorMc4Success() {
        ConectorMc4 c = new ConectorMc4();
        c.setId("abc");

        conectorMc4Service.addConectorMc4(c);

        verify(conectorMc4Repository, times(1)).save(c);
    }

    @Test
    @DisplayName("Should update existing ConectorMc4 and return true")
    void updateConectorMc4Success() {
        ConectorMc4 c = new ConectorMc4();
        c.setId("updateId");

        when(conectorMc4Repository.findById("updateId")).thenReturn(Optional.of(c));

        boolean result = conectorMc4Service.updateConectorMc4(c);

        assertTrue(result);
        verify(conectorMc4Repository, times(1)).findById("updateId");
        verify(conectorMc4Repository, times(1)).save(c);
    }

    @Test
    @DisplayName("Should return false when trying to update non-existent ConectorMc4")
    void updateConectorMc4Failure() {
        ConectorMc4 c = new ConectorMc4();
        c.setId("missingId");

        when(conectorMc4Repository.findById("missingId")).thenReturn(Optional.empty());

        boolean result = conectorMc4Service.updateConectorMc4(c);

        assertFalse(result);
        verify(conectorMc4Repository, times(1)).findById("missingId");
        verify(conectorMc4Repository, never()).save(any());
    }

    @Test
    @DisplayName("Should delete existing ConectorMc4 and return true")
    void deleteConectorMc4Success() {
        ConectorMc4 c = new ConectorMc4();
        c.setId("deleteId");

        when(conectorMc4Repository.findById("deleteId")).thenReturn(Optional.of(c));

        boolean result = conectorMc4Service.deleteConectorMc4("deleteId");

        assertTrue(result);
        verify(conectorMc4Repository, times(1)).findById("deleteId");
        verify(conectorMc4Repository, times(1)).deleteById("deleteId");
    }

    @Test
    @DisplayName("Should return false when trying to delete non-existent ConectorMc4")
    void deleteConectorMc4Failure() {
        when(conectorMc4Repository.findById("nonExistId")).thenReturn(Optional.empty());

        boolean result = conectorMc4Service.deleteConectorMc4("nonExistId");

        assertFalse(result);
        verify(conectorMc4Repository, times(1)).findById("nonExistId");
        verify(conectorMc4Repository, never()).deleteById(anyString());
    }
}
