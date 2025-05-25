module com.finanzas.gestor_finanzas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires java.desktop;

    opens com.finanzas.gestor_finanzas.vista.login to javafx.fxml;
    opens com.finanzas.gestor_finanzas.vista.dashboard to javafx.fxml;
    opens com.finanzas.gestor_finanzas.vista.nuevaCuenta to javafx.fxml;

    exports com.finanzas.gestor_finanzas.vista;
    exports com.finanzas.gestor_finanzas.modelo;
    exports com.finanzas.gestor_finanzas.servicio;
    exports com.finanzas.gestor_finanzas.repositorio;
    exports com.finanzas.gestor_finanzas.dao;

    exports com.finanzas.gestor_finanzas.vista.login;
    exports com.finanzas.gestor_finanzas.vista.dashboard;

}