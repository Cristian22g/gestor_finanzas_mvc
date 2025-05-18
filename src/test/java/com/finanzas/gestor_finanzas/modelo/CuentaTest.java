package com.finanzas.gestor_finanzas.modelo;

import com.finanzas.gestor_finanzas.excepciones.CantidadException;
import com.finanzas.gestor_finanzas.excepciones.NombreCuentaException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;



public class CuentaTest {
        @Test
        void testConstructorCompletoValido() {
            assertDoesNotThrow(() -> {
                Cuenta cuenta = new Cuenta(1, 10, "Ahorros2025", 1000.50);
                assertEquals(1, cuenta.getId());
                assertEquals(10, cuenta.getIdUsuario());
                assertEquals("Ahorros2025", cuenta.getNombreCuenta());
                assertEquals(1000.50, cuenta.getSaldoActual());
            });
        }

        @Test
        void testConstructorSinIdValido() {
            assertDoesNotThrow(() -> {
                Cuenta cuenta = new Cuenta(99, "Corriente123", 500.00);
                assertEquals(0, cuenta.getId()); // ID por defecto
                assertEquals(99, cuenta.getIdUsuario());
                assertEquals("Corriente123", cuenta.getNombreCuenta());
            });
        }

        @Test
        void testSettersFuncionanCorrectamente() throws NombreCuentaException, CantidadException {
            Cuenta cuenta = new Cuenta();
            cuenta.setId(42);
            cuenta.setIdUsuario(7);
            cuenta.setNombreCuenta("CuentaNueva");
            cuenta.setSaldoActual(1500.75);

            assertEquals(42, cuenta.getId());
            assertEquals(7, cuenta.getIdUsuario());
            assertEquals("CuentaNueva", cuenta.getNombreCuenta());
            assertEquals(1500.75, cuenta.getSaldoActual());
        }

        @Test
        void testSaldoNegativoNoPermitido(){
            assertThrows(CantidadException.class, () -> new Cuenta(3, "Cuenta Corriente", -1));
        }


        static private Stream<Arguments> proveedorNombreCuentas(){
            return Stream.of(
                    Arguments.of("CuentaCorriente", true),
                    Arguments.of("Cuent@Corriente", false),
                    Arguments.of("ch", false),
                    Arguments.of("Cuenta2", true),
                    Arguments.of("Cuenta Espacio", true)
            );
        }

        @ParameterizedTest
        @MethodSource("proveedorNombreCuentas")
        void comprobarNombreCuentas(String nombre, boolean esValido){
            if(esValido) assertDoesNotThrow(() -> new Cuenta(3, nombre, 500));
            else assertThrows(NombreCuentaException.class, () -> new Cuenta(3, nombre, 500));
        }

}
