package Controlador;

import DAOs.TarjetaDAO;
import estrategia.PagoTarjeta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.time.LocalDate;

public class PagoTarjetaControlador {

    @FXML private Label lblMonto;
    @FXML private TextField txtTitular;
    @FXML private TextField txtNumeroTarjeta;
    @FXML private PasswordField txtCvv;
    @FXML private TextField txtFechaVencimiento;
    @FXML private Label lblMensaje;

    private float monto;
    private CambiarSuscripcionControlador controladorPrincipal;

    public void setMonto(float monto, CambiarSuscripcionControlador controladorPrincipal) {
        this.monto = monto;
        this.controladorPrincipal = controladorPrincipal;
        lblMonto.setText("$" + monto);
    }

    @FXML
    void pagar(ActionEvent event) {
        String titular = txtTitular.getText();
        String numero = txtNumeroTarjeta.getText();
        String cvv = txtCvv.getText();
        String fechaTexto = txtFechaVencimiento.getText();

        // 1. Validar el formato escrito estricto MM/AA (Ej: 12/28)
        if (fechaTexto == null || !fechaTexto.matches("(0[1-9]|1[0-2])/[0-9]{2}")) {
            lblMensaje.setText("Error: La fecha de vencimiento debe cumplir el formato MM/AA (Ej: 12/29)");
            return;
        }

        // 2. Convertir el formato MM/AA a un objeto LocalDate lógico para el DAO
        String[] partes = fechaTexto.split("/");
        int mes = Integer.parseInt(partes[0]);
        int anio = Integer.parseInt("20" + partes[1]);

        // Creamos la fecha apuntando al último día de ese mes asignado
        LocalDate fechaVencimiento = LocalDate.of(anio, mes, 1).plusMonths(1).minusDays(1);

        if (titular == null || titular.isBlank() ||
                numero == null || numero.length() != 16 ||
                cvv == null || cvv.length() != 3 ||
                fechaVencimiento.isBefore(LocalDate.now())) {

            lblMensaje.setText("Error: verifique sus datos");
            return;
        }

        PagoTarjeta estrategia = new PagoTarjeta();
        estrategia.setDatosTarjeta(titular, numero, cvv, fechaVencimiento);

        if (estrategia.pagar(monto)) {
            TarjetaDAO tarjetaDAO = new TarjetaDAO();
            int idUsuarioActual = controladorPrincipal.getUsuarioActivo().getIdUsuario();

            boolean guardadoExitoso = tarjetaDAO.insertar(idUsuarioActual, estrategia);

            if (guardadoExitoso) {
                controladorPrincipal.registrarPagoExitoso();
                cerrarVentana();
            } else {
                lblMensaje.setText("Fallo en el registro de la base de datos");
            }
        } else {
            lblMensaje.setText("Error: datos de tarjeta rechazados");
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) lblMonto.getScene().getWindow();
        stage.close();
    }
}