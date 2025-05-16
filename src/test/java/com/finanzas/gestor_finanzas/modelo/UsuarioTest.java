package com.finanzas.gestor_finanzas.modelo;

import com.finanzas.gestor_finanzas.excepciones.ContrasenaException;
import com.finanzas.gestor_finanzas.excepciones.DniException;
import com.finanzas.gestor_finanzas.excepciones.NombreApellidoException;
import com.finanzas.gestor_finanzas.excepciones.NombreUsuarioException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @ParameterizedTest
    @MethodSource("proveedorDnis")
    void comprobarDnis(String dni, boolean esValido){
        if(esValido) assertDoesNotThrow(() -> new Usuario("Juanito12","Contrasen@1", dni, "Juan", "Pérez", "García"));
        else assertThrows(DniException.class, () -> new Usuario("Juanito12","Contrasen@1", dni, "Juan", "Pérez", "García"));
    }

    @ParameterizedTest
    @MethodSource("proveedorNombresUsuario")
    void comprobarNombresUsuario(String nombreUsuario, boolean esValido){
        if(esValido) assertDoesNotThrow(() -> new Usuario(nombreUsuario,"Contrasen@1", "90123456A", "Juan", "Pérez", "García"));
        else assertThrows(NombreUsuarioException.class, () -> new Usuario(nombreUsuario,"Contrasen@1", "90123456A", "Juan", "Pérez", "García"));
    }

    @ParameterizedTest
    @MethodSource("proveedorContrasenas")
    void comprobarContrasenas(String contrasena, boolean esValido){
        if(esValido) assertDoesNotThrow(() -> new Usuario("Juanito12",contrasena, "90123456A", "Juan", "Pérez", "García"));
        else assertThrows(ContrasenaException.class, () -> new Usuario("Juanito12",contrasena, "90123456A", "Juan", "Pérez", "García"));
    }

    @ParameterizedTest
    @MethodSource("proveedorNombresApellidos")
    void comprobarNombreYapellidos(String nombreApellido, boolean esValido){
        if(esValido) assertDoesNotThrow(() -> new Usuario("Juanito12","@Contrasena4Correct@", "90123456A", nombreApellido, nombreApellido, nombreApellido));
        else assertThrows(NombreApellidoException.class, () -> new Usuario("Juanito12","@Contrasena4Correct@", "90123456A", nombreApellido, nombreApellido, nombreApellido));
    }


    // PRUEBAS
    private static Stream<Arguments> proveedorDnis(){
        return Stream.of(
                Arguments.of("23456789D", true),
                Arguments.of("123", false),
                Arguments.of("23456789F", false),
                Arguments.of("987654321", false),
                Arguments.of("34567890V", true)
        );
    }

    private static Stream<Arguments> proveedorNombresUsuario(){
        return Stream.of(
                Arguments.of("Juann23", true),
                Arguments.of("ju", false),
                Arguments.of("Juan-23", false),
                Arguments.of("ju@n", false),
                Arguments.of("juan", true)
        );
    }

    private static Stream<Arguments> proveedorContrasenas(){
        return Stream.of(
                Arguments.of("@Contrasena4Correct@", true),
                Arguments.of("Fa1s@", false),
                Arguments.of("Contrasena123INCORRECTA", false),
                Arguments.of("C0rrEct@-", true),
                Arguments.of("-Mi-Contrasena1", true)
        );
    }

    private static Stream<Arguments> proveedorNombresApellidos(){
        return Stream.of(
                Arguments.of("Manu2", false),
                Arguments.of("", false),
                Arguments.of("Manuel", true),
                Arguments.of("pe", true),
                Arguments.of("Manuel@", false)
        );
    }

}