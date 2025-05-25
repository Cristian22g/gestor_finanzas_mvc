package com.finanzas.gestor_finanzas.vista;


import com.finanzas.gestor_finanzas.modelo.Usuario;
import com.finanzas.gestor_finanzas.servicio.UsuarioServicio;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/finanzas/gestor_finanzas/vista/login/Login.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setMaximized(true);
        stage.setResizable(false);
        scene.getStylesheets().addAll(
                getClass().getResource("/styles/general.css").toExternalForm(),
                getClass().getResource("/styles/login.css").toExternalForm()
        );

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        /*
        UsuarioServicio us = new UsuarioServicio();
        try {
            List<Usuario> usuarios = us.obtenerTodos();
            usuarios.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        */

        launch(args);
    }
}