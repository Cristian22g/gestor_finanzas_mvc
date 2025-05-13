package com.finanzas.gestor_finanzas.utilidades;

public class Utilidades {
	static public String ordenarUpperLower(String cadena) {
		return cadena.substring(0,1).toUpperCase() + cadena.substring(1, cadena.length()).toLowerCase();
	}
}
