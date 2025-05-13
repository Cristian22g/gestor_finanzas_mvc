module com.finanzas.gestor_finanzas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.finanzas.gestor_finanzas to javafx.fxml;
    exports com.finanzas.gestor_finanzas.vista;
    exports com.finanzas.gestor_finanzas.modelo;
    exports com.finanzas.gestor_finanzas.servicio;
    exports com.finanzas.gestor_finanzas.repositorio;
}