package Controlador;

import DAOs.BitcoinDAO;
import estrategia.PagoBitcoin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PagoBitcoinControlador {

    @FXML private Label lblMonto;
    @FXML private TextField txtDui;
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
        String dui = txtDui.getText();

        if (dui == null || dui.trim().isEmpty()) {
            lblMensaje.setText("Ingrese un numero de DUI valido");
            return;
        }

        PagoBitcoin estrategia = new PagoBitcoin();
        estrategia.setDatosDui(dui);

        if (estrategia.pagar(monto)) {
            BitcoinDAO bitcoinDAO = new BitcoinDAO();
            int idUsuarioActual = controladorPrincipal.getUsuarioActivo().getIdUsuario();

            boolean guardadoExitoso = bitcoinDAO.insertar(idUsuarioActual, estrategia);

            if (guardadoExitoso) {
                controladorPrincipal.registrarPagoExitoso();
                cerrarVentana();
            } else {
                lblMensaje.setText("Error de registro en la base de datos");
            }
        } else {
            lblMensaje.setText("Error al procesar el pago");
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