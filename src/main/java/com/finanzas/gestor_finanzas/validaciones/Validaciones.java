package validaciones;

public class Validaciones {
    public static boolean ValidarDni(String dni){
        if (dni == null || !dni.matches("\\d{8}[A-HJ-NP-TV-Z]")) {
            return false;
        }

        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numero = Integer.parseInt(dni.substring(0, 8));
        char letraEsperada = letras.charAt(numero % 23);
        char letraActual = dni.charAt(8);

        return letraEsperada == letraActual;
    }

    public static boolean ValidarNombreUsuario(String nombreUsuario){

                return nombreUsuario.matches("^[a-zA-Z0-9]{4,20}$");
    }

    public static boolean ValidarContrasena(String contrasena){
        //debe contener mínimo 6 caracteres y números, letras mayúscula y minúscula, y caracter especial.
        return contrasena.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-={}\\[\\]:;\"'<>,.?/]).{6,}$");
    }
    
    public static boolean ValidarNombreCuenta(String nombreCuenta){
    	return nombreCuenta.matches("[^[a-zA-Z0-9]{4,20}$]");
    }
    
    public static boolean ValidarNombre(String nombre){
    	return nombre.matches("[^[a-zA-Z]{3,20}$]");
    }
    
    public static boolean ValidarApellidos(String nombre){
    	return nombre.matches("[^[a-zA-Z]{3,20}$]");
    }

}
