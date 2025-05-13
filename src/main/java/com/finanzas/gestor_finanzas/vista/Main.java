package com.finanzas.gestor_finanzas.vista;


import com.finanzas.gestor_finanzas.modelo.Usuario;
import com.finanzas.gestor_finanzas.servicio.UsuarioServicio;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        UsuarioServicio usuarioServicio = new UsuarioServicio();

        try {
            List<Usuario> usuarios = usuarioServicio.obtenerTodos();

            if (usuarios.isEmpty()) {
                System.out.println("No hay usuarios registrados.");
            } else {
                System.out.println("Lista de usuarios:");
                for (Usuario usuario : usuarios) {
                    System.out.println(usuario);
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}