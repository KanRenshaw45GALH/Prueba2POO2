package Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modeloUsuario.Usuario;
import java.io.IOException;

public class CambiarSuscripcionControlador {

    @FXML private Label lblMonto;
    @FXML private Label lblFechaSuscripcion;
    @FXML private Label lblFechaLimite;
    @FXML private Label lblMensaje;
    @FXML private Button BtnVolver;

    private float montoAPagar = 4.99f;
    private Usuario usuarioActivo;

    @FXML
    public void initialize() {
        lblMonto.setText("$" + montoAPagar);
        lblFechaSuscripcion.setText("-");
        lblFechaLimite.setText("-");
    }

    public void setUsuario(Usuario usuario) {
        this.usuarioActivo = usuario;
    }

    public Usuario getUsuarioActivo() {
        return this.usuarioActivo;
    }

    @FXML
    void pagarConTarjeta(ActionEvent event) {
        abrirVentanaPago("/PagoTarjeta.fxml", "Pago con Tarjeta", true);
    }

    @FXML
    void pagarConBitcoin(ActionEvent event) {
        abrirVentanaPago("/PagoBitcoin.fxml", "Pago con Bitcoin", false);
    }

    private void abrirVentanaPago(String fxmlPath, String titulo, boolean esTarjeta) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle(titulo);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));

            if (esTarjeta) {
                PagoTarjetaControlador controlador = loader.getController();
                controlador.setMonto(montoAPagar, this);
            } else {
                PagoBitcoinControlador controlador = loader.getController();
                controlador.setMonto(montoAPagar, this);
            }

            stage.showAndWait();
        } catch (IOException e) {
            lblMensaje.setText("Error al abrir la ventana de pago: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void registrarPagoExitoso() {
        lblMensaje.setText("Pago realizado con exito");
    }

    @FXML
    void cancelarSuscripcion(ActionEvent event) {
        lblMensaje.setText("Suscripción cancelada. Eres usuario general de nuevo");
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuGeneral.fxml"));
        Parent root = loader.load();

        MenuGeneralControlador controlador = loader.getController();
        controlador.setUsuario(usuarioActivo);

        Stage stage = (Stage) BtnVolver.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}