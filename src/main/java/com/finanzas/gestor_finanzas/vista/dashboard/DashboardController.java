package com.finanzas.gestor_finanzas.vista.dashboard;

import com.finanzas.gestor_finanzas.excepciones.NombreCuentaException;
import com.finanzas.gestor_finanzas.modelo.Cuenta;
import com.finanzas.gestor_finanzas.modelo.Transaccion;
import com.finanzas.gestor_finanzas.modelo.Usuario;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.finanzas.gestor_finanzas.servicio.CuentaServicio;
import com.finanzas.gestor_finanzas.servicio.TransaccionServicio;
import com.finanzas.gestor_finanzas.vista.nuevaCuenta.NuevaCuentaController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class DashboardController {
    private Usuario usuario;
    private List<Cuenta> cuentas;
    private Cuenta cuentaActual;
    private List<Button> cuentasBotones;
    private List<Transaccion> transacciones;

    @FXML
    private Label mensajeLabelCuentas;
    @FXML
    private VBox cuentasBox;
    @FXML
    private Label bienvenida;
    @FXML
    private Label saldoTotal;

    // -------------------- //
    @FXML
    private TableView<Transaccion> transaccionesTabla;
    @FXML
    private TableColumn<Transaccion, Double> colMonto;
    @FXML
    private TableColumn<Transaccion, String> colTipo;
    @FXML
    private TableColumn<Transaccion, String> colCategoria;
    @FXML
    private TableColumn<Transaccion, String> colDescripcion;
    @FXML
    private TableColumn<Transaccion, LocalDate> colFecha;

    @FXML
    private ComboBox<String> tipoComboBox;
    @FXML
    private TextField montoField;
    @FXML
    private TextField categoriaField;
    @FXML
    private TextField descripcionField;
    @FXML
    private Label mensajeLabelTransaccion;
    @FXML
    private Button realizarTransaccionBtn;

    @FXML
    private Button nuevaCuentaBtn;
    @FXML
    private Button eliminarCuentaBtn;

    public void cargarDashboard(Usuario usuario){
        tipoComboBox.setValue("GASTO");
        setUsuario(usuario);
        bienvenida.setText("Hola, " + usuario.getNombre() + ".");
        setCuentas();
        anadirCuentas();

        if(cuentas.size() == 3) nuevaCuentaBtn.setDisable(true);

        if(cuentasBotones != null || !cuentasBotones.isEmpty()){
            eventoCuentas();
        }
        eventoTransacciones();

    }


    private void setCuentas() {
        CuentaServicio cs = new CuentaServicio();
        try {
            System.out.println(usuario.getId());
            this.cuentas = cs.obtenerPorUsuarioId(usuario.getId());
            if (cuentas == null || cuentas.size() == 0) mensajeLabelCuentas.setText("No tienes cuentas disponibles.");
            else mensajeLabelCuentas.setText("");

        } catch (Exception e) {
            mensajeLabelCuentas.setText("Error al cargar las cuentas.");
        }
    }


    private void anadirCuentas(){
        try{
            this.cuentasBotones = new ArrayList<>();

            for(Cuenta cuenta : cuentas){
                Button cuentaBtn = new Button(cuenta.getNombreCuenta());
                cuentaBtn.getStyleClass().add("boton-cuenta");
                cuentasBox.getChildren().add(cuentaBtn);
                cuentasBotones.add(cuentaBtn);
            }
        }catch (Exception e){
            mensajeLabelCuentas.setText("Error al cargar las cuentas.");
        }
    }

    private void eventoCuentas(){
        cuentasBotones.forEach( btn ->{
            btn.setOnMouseClicked(e -> {
                cuentaActual = cuentas.get(cuentasBotones.indexOf(btn));
                try {
                    CuentaServicio cs = new CuentaServicio();
                    cuentaActual = cs.obtenerPorId(cuentaActual.getId());
                    cs = null;
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());;
                }
                mensajeLabelTransaccion.setText("");
                transacciones = buscarTransacciones();
                if(transacciones == null) return;
                cargarTransacciones();

                saldoTotal.setText("Saldo: " + cuentaActual.getSaldoActual());
                if(cuentaActual.getSaldoActual() > 0) saldoTotal.setTextFill(Paint.valueOf("#134be1"));
                else saldoTotal.setTextFill(Paint.valueOf("RED"));

            });
        });
    }
    private List<Transaccion> buscarTransacciones(){
        TransaccionServicio ts = new TransaccionServicio();
        return ts.filtrarPorCuentaUsuario(cuentaActual.getIdUsuario(), cuentaActual.getId());

    }
    private void cargarTransacciones(){
        transaccionesTabla.getItems().clear();
        transaccionesTabla.getItems().addAll(transacciones);
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        System.out.println("Usuario logueado: " + usuario.getNombreUsuario());
    }


    private void eventoTransacciones(){
        realizarTransaccionBtn.setOnMouseClicked(e -> {
            if(cuentaActual == null){
                mensajeLabelTransaccion.setText("No hay ninguna cuenta seleccionada.");
                return;
            }

            try{
                String tipoSeleccionado = tipoComboBox.getValue();
                Double monto = (montoField.getText().isEmpty()) ? 0 :Double.valueOf(montoField.getText());
                String categoria = categoriaField.getText();
                String descripcion = descripcionField.getText();

                if(categoria.isEmpty()){
                    mensajeLabelTransaccion.setText("La categoria no puede estar vacía.");
                    return;
                }
                if(descripcion.isEmpty()){
                    mensajeLabelTransaccion.setText("La descripción no puede estar vacía.");
                    return;
                }

                monto = (tipoSeleccionado.equals("INGRESO")) ? Math.abs(monto) : -Math.abs(monto);

                TransaccionServicio ts = new TransaccionServicio();
                ts.registrarTransaccion(usuario.getId(),cuentaActual.getId(),monto,
                        tipoSeleccionado, categoria, descripcion, LocalDate.now());

                mensajeLabelTransaccion.setText("");
                transacciones = buscarTransacciones();
                cargarTransacciones();
                vaciarCamposTransaccion();

                double nuevosaldo = cuentaActual.getSaldoActual() + monto;
                CuentaServicio cs = new CuentaServicio();
                cs.cambiarSaldo(cuentaActual.getId(), nuevosaldo);
                cuentaActual = cs.obtenerPorId(cuentaActual.getId());
                saldoTotal.setText("Saldo: " + cuentaActual.getSaldoActual());

                cs = null;
                ts = null;

            }catch (NumberFormatException ex) {
                mensajeLabelTransaccion.setText("Debes ingresar un saldo válido.");
            }catch(Exception ex){
                mensajeLabelTransaccion.setText(ex.getMessage());
                System.out.println(ex.getMessage());
            }
        });
    }

    public void vaciarCamposTransaccion(){
        montoField.setText("");
        categoriaField.setText("");
        descripcionField.setText("");
    }


    // configuracion columnas transacciones.
    @FXML
    public void initialize() {
        transaccionesTabla.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
        colMonto.setCellValueFactory(new PropertyValueFactory<>("monto"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    }


    public void cambiarEscenaNuevaCuenta() throws IOException {
        FXMLLoader loader;
        Parent root;
        Scene scene;
        Stage stage;
        NuevaCuentaController controller;

        loader = new FXMLLoader(getClass().getResource("/com/finanzas/gestor_finanzas/vista/nuevaCuenta/nuevaCuenta.fxml"));
        root = loader.load();

        scene = new Scene(root);
        scene.getStylesheets().addAll(
                getClass().getResource("/styles/general.css").toExternalForm(),
                getClass().getResource("/styles/login.css").toExternalForm()
        );

        stage = (Stage) categoriaField.getScene().getWindow();
        stage.setScene(scene);

        stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        stage.setX(0);
        stage.setY(0);
        stage.setResizable(false);

        controller = loader.getController();
        controller.nuevaCuenta(usuario);
        stage.show();
    }

}
