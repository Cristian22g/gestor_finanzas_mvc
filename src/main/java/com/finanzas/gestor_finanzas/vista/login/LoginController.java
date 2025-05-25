package com.finanzas.gestor_finanzas.vista.login;

import com.finanzas.gestor_finanzas.modelo.Usuario;
import com.finanzas.gestor_finanzas.servicio.UsuarioServicio;
import com.finanzas.gestor_finanzas.vista.dashboard.DashboardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


public class LoginController {

    @FXML private TextField nombreUsuarioField;
    @FXML private PasswordField contrasenaField;
    @FXML private Label mensajeLabel;

    FXMLLoader loader;
    Parent root;
    Scene scene;
    Stage stage;
    DashboardController controller;
    Usuario usuario;


    @FXML
    private void login() {
        String nombreUsuario = nombreUsuarioField.getText();
        String contrasena = contrasenaField.getText();

        usuario = buscarUsuario(nombreUsuario);

        if (usuario == null) mensajeLabel.setText("Nombre de usuario incorrecto.");
        else if(!usuario.getContrasena().equals(contrasena)) mensajeLabel.setText("Contraseña incorrecta.");

        else{
            try {
                cambiarEscena();
                controller = loader.getController();
                controller.cargarDashboard(usuario);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                mensajeLabel.setText("Error al iniciar sesión");
                System.out.println(e.getMessage());
            }
        }
    }

    private Usuario buscarUsuario(String nombreUsuario){
        UsuarioServicio us = new UsuarioServicio();
        try {
            List<Usuario> usuarios = us.obtenerTodos();
            Usuario usuario = usuarios.stream()
                    .filter(user -> user.getNombreUsuario().equals(nombreUsuario))
                    .findFirst()
                    .orElse(null);

                    return usuario;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

    private void cambiarEscena() throws IOException {
        loader = new FXMLLoader(getClass().getResource("/com/finanzas/gestor_finanzas/vista/dashboard/dashboard.fxml"));
        root = loader.load();

        scene = new Scene(root);
        scene.getStylesheets().addAll(
                getClass().getResource("/styles/general.css").toExternalForm(),
                getClass().getResource("/styles/dashboard.css").toExternalForm()
        );

        stage = (Stage) nombreUsuarioField.getScene().getWindow();
        stage.setScene(scene);

        stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        stage.setX(0);
        stage.setY(0);
        stage.setResizable(false);

        stage.show();
    }
}
