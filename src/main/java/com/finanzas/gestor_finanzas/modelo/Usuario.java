package com.finanzas.gestor_finanzas.modelo;


import com.finanzas.gestor_finanzas.utilidades.Utilidades;
import com.finanzas.gestor_finanzas.excepciones.*;
import com.finanzas.gestor_finanzas.validaciones.Validaciones;

import java.util.List;
import java.util.Objects;

/**
 * Clase que representa a un usuario del sistema.
 */
public class Usuario {

    /**
     * Identificador único del usuario.
     */
    private int id;

    /**
     * Nombre de usuario (único).
     */
    private String nombreUsuario;

    /**
     * Contraseña del usuario.
     */
    private String contrasena;

    /**
     * Documento Nacional de Identidad del usuario.
     */
    private String dni;

    /**
     * Nombre de pila del usuario.
     */
    private String nombre;

    /**
     * Primer apellido del usuario.
     */
    private String primerApellido;

    /**
     * Segundo apellido del usuario.
     */
    private String segundoApellido;

    /**
     * Lista de cuentas financieras asociadas al usuario.
     */
    private List<Cuenta> cuentas;

    /**
     * Lista de transacciones financieras del usuario.
     */
    private List<Transaccion> transacciones;


    /**
     * Constructor de usuario con ID especificado.
     *
     * @param id Identificador único del usuario.
     * @param nombreUsuario Nombre de usuario (debe cumplir con los criterios definidos).
     * @param contrasena Contraseña del usuario (debe ser segura y válida).
     * @param dni Documento Nacional de Identidad.
     * @param nombre Nombre del usuario.
     * @param primerApellido Primer apellido del usuario.
     * @param segundoApellido Segundo apellido del usuario.
     * @throws NombreUsuarioException Si el nombre de usuario no es válido.
     * @throws ContrasenaException Si la contraseña no cumple los requisitos.
     * @throws DniException Si el DNI no es válido.
     * @throws NombreApellidoException Si el nombre o apellidos no son válidos.
     */
    public Usuario(int id, String nombreUsuario, String contrasena, String dni, String nombre, String primerApellido, String segundoApellido) throws NombreUsuarioException, ContrasenaException, DniException, NombreApellidoException{
        setId(id);
        setNombreUsuario(nombreUsuario);
        setContrasena(contrasena);
        setDni(dni);
        setNombre(nombre);
        setPrimerApellido(primerApellido);
        setSegundoApellido(segundoApellido);
    }

    /**
     * Constructor de usuario sin especificar el ID (por defecto se establece en 0).
     *
     * @param nombreUsuario Nombre de usuario.
     * @param contrasena Contraseña del usuario.
     * @param dni Documento Nacional de Identidad.
     * @param nombre Nombre del usuario.
     * @param primerApellido Primer apellido del usuario.
     * @param segundoApellido Segundo apellido del usuario.
     * @throws NombreUsuarioException Si el nombre de usuario no es válido.
     * @throws ContrasenaException Si la contraseña no cumple los requisitos.
     * @throws DniException Si el DNI no es válido.
     * @throws NombreApellidoException Si el nombre o apellidos no son válidos.
     */
    public Usuario(String nombreUsuario, String contrasena, String dni, String nombre, String primerApellido, String segundoApellido) throws ContrasenaException, DniException, NombreUsuarioException, NombreApellidoException{
        this(0, nombreUsuario, contrasena, dni, nombre, primerApellido, segundoApellido);
    }

    // GETTERS Y SETTERS
    public int getId() {
        return id;
    }

    public String getNombre() {
		return nombre;
	}

    public void setNombre(String nombre) throws NombreApellidoException {
        boolean esCorrecto = Validaciones.validarNombreOapellido(nombre);
        if(!esCorrecto) throw  new NombreApellidoException("Nombre inválido.");
		this.nombre = Utilidades.ordenarUpperLower(nombre);
	}

	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) throws NombreApellidoException {
        boolean esCorrecto = Validaciones.validarNombreOapellido(primerApellido);
        if(!esCorrecto) throw new NombreApellidoException("Apellido inválido.");
        this.primerApellido = Utilidades.ordenarUpperLower(primerApellido);
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) throws NombreApellidoException {
        boolean esCorrecto = Validaciones.validarNombreOapellido(segundoApellido);
        if(!esCorrecto) throw new NombreApellidoException("Apellido inválido.");
		this.segundoApellido = Utilidades.ordenarUpperLower(segundoApellido);
	}

	public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) throws DniException {
        if(!Validaciones.ValidarDni(dni)) throw new DniException("Dni inválido.");
        this.dni = dni;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombreUsuario(String nombreUsuario) throws NombreUsuarioException{
        if(!Validaciones.validarNombreUsuario(nombreUsuario)) throw  new NombreUsuarioException("El nombre de la cuenta debe ser de entre 4 y 20 carácteres de letras/números.");
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasena(String contrasena) throws ContrasenaException {
        if(!Validaciones.validarContrasena(contrasena)) throw new ContrasenaException("La contraseña debe tener mínimo 6 caracteres e incluir al menos un número, una letra mayúscula y minúscula y un caracter especial.");
        this.contrasena = contrasena;
    }
    

    //HASH Y EQUALS COMPARANDO DNI
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(dni, usuario.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombreUsuario=" + nombreUsuario + ", contrasena=" + contrasena + ", dni=" + dni
				+ ", nombre=" + nombre + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido
				+ "]";
	}
}
