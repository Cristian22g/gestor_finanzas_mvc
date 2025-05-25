package com.finanzas.gestor_finanzas.modelo;

import com.finanzas.gestor_finanzas.excepciones.CantidadException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TransaccionTest {

    private Transaccion transaccionCompleta;
    private Transaccion transaccionSinId;

    @BeforeEach
    void setUp() throws CantidadException {
        transaccionCompleta = new Transaccion(1, 10, 100.5, "INGRESO", "Salario", "Pago mensual", LocalDate.of(2024, 5, 10));
        transaccionSinId = new Transaccion(10,2, 50.0, "GASTO", "Comida", "Cena en restaurante", LocalDate.of(2024, 5, 12));
    }

    @Test
    void testConstructorCompleto() {
        assertEquals(1, transaccionCompleta.getId());
        assertEquals(10, transaccionCompleta.getIdUsuario());
        assertEquals(100.5, transaccionCompleta.getMonto());
        assertEquals("INGRESO", transaccionCompleta.getTipo());
        assertEquals("Salario", transaccionCompleta.getCategoria());
        assertEquals("Pago mensual", transaccionCompleta.getDescripcion());
        assertEquals(LocalDate.of(2024, 5, 10), transaccionCompleta.getFecha());
    }

    @Test
    void testConstructorSinId() {
        assertEquals(0, transaccionSinId.getId()); // por defecto
        assertEquals(10, transaccionSinId.getIdUsuario());
        assertEquals(50.0, transaccionSinId.getMonto());
        assertEquals("GASTO", transaccionSinId.getTipo());
        assertEquals("Comida", transaccionSinId.getCategoria());
        assertEquals("Cena en restaurante", transaccionSinId.getDescripcion());
        assertEquals(LocalDate.of(2024, 5, 12), transaccionSinId.getFecha());
    }

    @Test
    void testSetters() throws CantidadException {
        transaccionSinId.setId(5);
        transaccionSinId.setIdUsuario(20);
        transaccionSinId.setMonto(75.0);
        transaccionSinId.setTipo("INGRESO");
        transaccionSinId.setCategoria("Bonificación");
        transaccionSinId.setDescripcion("Bono anual");
        transaccionSinId.setFecha(LocalDate.of(2025, 1, 1));

        assertEquals(5, transaccionSinId.getId());
        assertEquals(20, transaccionSinId.getIdUsuario());
        assertEquals(75.0, transaccionSinId.getMonto());
        assertEquals("INGRESO", transaccionSinId.getTipo());
        assertEquals("Bonificación", transaccionSinId.getCategoria());
        assertEquals("Bono anual", transaccionSinId.getDescripcion());
        assertEquals(LocalDate.of(2025, 1, 1), transaccionSinId.getFecha());
    }

    @Test
    void testMontoNegativoNoPermitido() {
       assertThrows(CantidadException.class, () -> new Transaccion(5,2, -99.0, "GASTO", "Prueba", "Descripción", LocalDate.now()));
    }

    @Test
    void testCamposNullPosibles() throws CantidadException {
        Transaccion t = new Transaccion(1,2, 100.0, null, null, null, LocalDate.now());
        assertNull(t.getTipo());
        assertNull(t.getCategoria());
        assertNull(t.getDescripcion());
    }
}