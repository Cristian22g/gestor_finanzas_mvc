package com.finanzas.gestor_finanzas.modelo;


import com.finanzas.gestor_finanzas.utilidades.Utilidades;
import validaciones.Validaciones;
import com.finanzas.gestor_finanzas.excepciones.*;

import java.util.Objects;

public class Usuario {
    private int id;
    private String nombreUsuario;
    private String contrasena;
    private String dni;
    private String nombre;
    private String primerApellido, segundoApellido;

    //CONSTRUCTOR COMPLETO
    public Usuario(int id, String nombreUsuario, String contrasena, String dni, String nombre, String primerApellido, String segundoApellido) throws NombreUsuarioException, ContrasenaException, DniException, ApellidoException, NombreException {
        setId(id);
        setNombreUsuario(nombreUsuario);
        setContrasena(contrasena);
        setDni(dni);
        setNombre(nombre);
        setPrimerApellido(primerApellido);
        setSegundoApellido(segundoApellido);
    }
    // CONSTRUCTOR SIN ID (a 0)
    public Usuario(String nombreUsuario, String contrasena, String dni, String nombre, String primerApellido, String segundoApellido) throws ContrasenaException, DniException, NombreUsuarioException, ApellidoException, NombreException {
        this(0, nombreUsuario, contrasena, dni, nombre, primerApellido, segundoApellido);
    }

    // GETTERS Y SETTERS
    public int getId() {
        return id;
    }

    public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) throws NombreException {
		if(Validaciones.ValidarNombre(nombre)) throw new NombreException("Nombre inválido.");
		this.nombre = Utilidades.ordenarUpperLower(nombre);;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) throws ApellidoException {
		if(Validaciones.ValidarApellidos(primerApellido)) throw new ApellidoException("Apellido inválido.");
		this.primerApellido = Utilidades.ordenarUpperLower(primerApellido);
		this.primerApellido = primerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) throws ApellidoException {
		if(Validaciones.ValidarApellidos(segundoApellido)) throw new ApellidoException("Apellido inválido.");
		
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
        if(!Validaciones.ValidarNombreUsuario(nombreUsuario)) throw  new NombreUsuarioException("El nombre de la cuenta debe ser de entre 4 y 20 carácteres de letras/números.");
        this.nombreUsuario = nombreUsuario;
    }

    public void setContrasena(String contrasena) throws ContrasenaException {
        if(!Validaciones.ValidarContrasena(contrasena)) throw new ContrasenaException("La contraseña debe tener mínimo 6 caracteres e incluir al menos un número, una letra mayúscula y minúscula y un caracter especial.");
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
	
	/*
    //TO STRING
    @Override
    public String toString() {
        return nombreUsuario;
    }
    */
}
