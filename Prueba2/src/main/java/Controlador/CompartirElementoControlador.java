package Controlador;

import hilos.CompartirHilo;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modeloElemento.Elemento;
import modeloUsuario.ListadoUsuarios;
import modeloUsuario.Usuario;

import java.io.IOException;
import java.util.List;

public class CompartirElementoControlador {

    @FXML private ComboBox<String> ComboBoxElementos;
    @FXML private TextField TextNombreDestino;
    @FXML private Label lblMensaje;
    @FXML private Button BtnCompartir;
    @FXML private Button BtnVolver;

    private Usuario usuarioActivo;
    private ListadoUsuarios listadoUsuarios;
    private List<Elemento> listaElementos;

    // Setter del usuario activo - carga sus elementos en el ComboBox
    public void setUsuario(Usuario usuario) {
        this.usuarioActivo = usuario;
        cargarElementos();
    }

    // Setter del listado de usuarios para buscar al destinatario
    public void setListadoUsuarios(ListadoUsuarios listadoUsuarios) {
        this.listadoUsuarios = listadoUsuarios;
    }

    private void cargarElementos() {
        listaElementos = usuarioActivo.getElemento();
        ObservableList<String> items = FXCollections.observableArrayList();

        if (listaElementos == null || listaElementos.isEmpty()) {
            lblMensaje.setText("No tienes elementos para compartir.");
            lblMensaje.setStyle("-fx-text-fill: #e53935;");
            BtnCompartir.setDisable(true);
            return;
        }

        for (int i = 0; i < listaElementos.size(); i++) {
            Elemento e = listaElementos.get(i);
            String tipo = e.getClass().getSimpleName().replace("Elemento", "");
            items.add((i + 1) + ". [" + tipo.toUpperCase() + "] " + e.getTitulo());
        }
        ComboBoxElementos.setItems(items);
        lblMensaje.setText("");
    }

    @FXML
    private void compartir(ActionEvent event) {
        String seleccionado = ComboBoxElementos.getValue();
        String nombreDestino = TextNombreDestino.getText().trim();

        // Validaciones
        if (seleccionado == null) {
            mostrarError("Selecciona un elemento para compartir.");
            return;
        }
        if (nombreDestino.isBlank()) {
            mostrarError("Ingresa el nombre del usuario destino.");
            return;
        }

        // Obtener el elemento seleccionado
        int indiceReal = Integer.parseInt(seleccionado.split("\\.")[0]) - 1;
        Elemento elementoSeleccionado = listaElementos.get(indiceReal);

        // Buscar usuario destino en el listado
        if (listadoUsuarios == null) {
            mostrarError("No hay listado de usuarios disponible.");
            return;
        }

        Usuario usuarioDestino = listadoUsuarios.buscarPorNombre(nombreDestino);

        if (usuarioDestino == null) {
            mostrarError("No se encontró un usuario con ese nombre.");
            return;
        }

        if (usuarioDestino.getNombreCompleto().equalsIgnoreCase(usuarioActivo.getNombreCompleto())) {
            mostrarError("No puedes compartir un elemento contigo mismo.");
            return;
        }

        // Verificar si ya fue compartido
        boolean yaCompartido = elementoSeleccionado.getColaboradores().stream()
                .anyMatch(u -> u.getNombreCompleto().equalsIgnoreCase(nombreDestino));
        if (yaCompartido) {
            mostrarError("Este elemento ya fue compartido con ese usuario.");
            return;
        }

        // Deshabilitar botón mientras se procesa
        BtnCompartir.setDisable(true);
        lblMensaje.setText("Compartiendo...");
        lblMensaje.setStyle("-fx-text-fill: #1565C0;");

        // Ejecutar en hilo separado usando CompartirHilo
        final Usuario destinoFinal = usuarioDestino;
        CompartirHilo hilo = new CompartirHilo(usuarioActivo, destinoFinal, elementoSeleccionado);
        Thread thread = new Thread(hilo);
        thread.setDaemon(true);

        // Actualizar UI una vez que el hilo termina
        thread.start();
        new Thread(() -> {
            try {
                thread.join(); // Esperar que el hilo termine
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                lblMensaje.setText("✔ Elemento compartido con " + destinoFinal.getNombreCompleto() + " exitosamente.");
                lblMensaje.setStyle("-fx-text-fill: #2e7d32;");
                BtnCompartir.setDisable(false);
                TextNombreDestino.clear();
                ComboBoxElementos.getSelectionModel().clearSelection();
            });
        }).start();
    }

    private void mostrarError(String mensaje) {
        lblMensaje.setText(mensaje);
        lblMensaje.setStyle("-fx-text-fill: #e53935;");
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuGeneral.fxml"));
        Parent root = loader.load();

        MenuGeneralControlador controlador = loader.getController();
        controlador.setUsuario(usuarioActivo);
        controlador.setListadoUsuarios(listadoUsuarios);

        Stage stage = (Stage) BtnVolver.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
