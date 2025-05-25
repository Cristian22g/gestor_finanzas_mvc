package com.finanzas.gestor_finanzas.vista.nuevaCuenta;

import com.finanzas.gestor_finanzas.modelo.Usuario;
import com.finanzas.gestor_finanzas.servicio.CuentaServicio;
import com.finanzas.gestor_finanzas.vista.dashboard.DashboardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class NuevaCuentaController {

    @FXML
    private TextField nombreCuentaField;
    @FXML private TextField saldoCuentaField;
    @FXML private Label mensajeLabel;

    FXMLLoader loader;
    Parent root;
    Scene scene;
    Stage stage;
    DashboardController controller;

    Usuario usuario;

    public void nuevaCuenta(Usuario usuario){
        this.usuario = usuario;
    };

    public void registrar(){

        try{
            String nombre = (nombreCuentaField.getText().isEmpty()) ? "" : nombreCuentaField.getText();
            Double saldo = (saldoCuentaField.getText().isEmpty()) ? 0 : Double.parseDouble(saldoCuentaField.getText());

            if(saldo == 0){
                mensajeLabel.setText("Saldo inválido.");
                return;
            }

            CuentaServicio cs = new CuentaServicio();
            cs.registrarCuenta(usuario.getId(), nombre, saldo);

            cs = null;
            cargarDashboard();

        }catch (NumberFormatException e) {
            mensajeLabel.setText("Debes ingresar un saldo válido.");
        }catch (Exception e){
            mensajeLabel.setText(e.getMessage());
            System.out.println(e.getMessage());
        }

    }

    public void cargarDashboard(){
        try{
            cambiarEscena();
            controller = loader.getController();
            controller.cargarDashboard(usuario);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
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

        stage = (Stage) nombreCuentaField.getScene().getWindow();
        stage.setScene(scene);

        stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        stage.setX(0);
        stage.setY(0);
        stage.setResizable(false);

        stage.show();
    }



}
