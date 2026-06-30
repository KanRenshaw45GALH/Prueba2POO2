package Controlador;

import estrategia.PagoEfectivo;
import estrategia.PagoTarjeta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import modeloUsuario.Usuario;
import modeloUsuario.UsuarioPremium;

import java.io.IOException;

public class CambiarSuscripcionControlador {

    @FXML private Button btnTarjeta;
    @FXML private Button btnEfectivo;
    @FXML private Label lblMonto;
    @FXML private Label lblFechaSuscripcion;
    @FXML private Label lblFechaLimite;
    @FXML private Label lblMensaje;
    Usuario usuarioActivo;

    private UsuarioPremium usuarioPremium;

    public void setUsuario(Usuario usuario) {
        this.usuarioActivo  = usuario;

        if (usuario instanceof UsuarioPremium) {
            this.usuarioPremium = (UsuarioPremium) usuario;
            cargarDatos();
        } else {
            btnTarjeta.setDisable(true);
            btnEfectivo.setDisable(true);
            lblMensaje.setText("Esta opcion solo esta disponible para usuarios Premium");
            lblMensaje.setStyle("-fx-text-fill: #e53935;");
        }
    }

    private void cargarDatos() {
        lblMonto.setText("$" + usuarioPremium.getPagarSuscripcion());
        lblFechaSuscripcion.setText(String.valueOf(usuarioPremium.getFechaSuscripcion()));
        lblFechaLimite.setText(String.valueOf(usuarioPremium.getFechaLimiteSuscripcion()));
    }

    @FXML
    private void pagarConTarjeta() {
        if (usuarioPremium == null) {
            return;
        }
        usuarioPremium.setEstrategiaPago(new PagoTarjeta());
        confirmarPago();
    }

    @FXML
    private void pagarConEfectivo() {
        if (usuarioPremium == null) {
            return;
        }
        usuarioPremium.setEstrategiaPago(new PagoEfectivo());
        confirmarPago();
    }

    private void confirmarPago() {
        usuarioPremium.getEstrategiaPago().pagar(usuarioPremium.getPagarSuscripcion());
        lblMensaje.setText("Suscripcion pagada correctamente.");
        lblMensaje.setStyle("-fx-text-fill: #2e7d32;");
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuGeneral.fxml"));
        Parent root = loader.load();

        MenuGeneralControlador controlador = loader.getController();
        controlador.setUsuario(usuarioActivo);

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}