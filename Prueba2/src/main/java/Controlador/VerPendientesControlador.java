package Controlador;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import modeloUsuario.Usuario;
import modeloElemento.Elemento;
import modeloElemento.ElementoTarea;
import modeloElemento.ElementoRecordatorio;
import catalogo.Estado;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VerPendientesControlador {

    @FXML private VBox contenedorPendientes;

    private Usuario usuarioActivo;

    public void setUsuario(Usuario usuario) {
        this.usuarioActivo = usuario;
        cargarPendientes();
    }

    private void cargarPendientes() {
        contenedorPendientes.getChildren().clear();
        List<Elemento> lista = usuarioActivo.getElemento();

        if (lista == null || lista.isEmpty()) {
            Label vacio = new Label("Sin elementos pendientes.");
            vacio.setStyle("-fx-font-size: 14px; -fx-text-fill: #888;");
            contenedorPendientes.getChildren().add(vacio);
            return;
        }

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        boolean hay = false;

        for (Elemento e : lista) {
            if (e instanceof ElementoTarea t
                    && t.getEstado() != Estado.COMPLETADO
                    && t.getEstado() != Estado.CANCELADA) {

                VBox card = crearCard(
                        "TAREA",
                        t.getTitulo(),
                        "Estado: " + t.getEstado() + " | Prioridad: " + t.getPrioridad()
                );
                contenedorPendientes.getChildren().add(card);
                hay = true;

            } else if (e instanceof ElementoRecordatorio r) {
                String fecha = r.getFechaLimite() != null
                        ? r.getFechaLimite().format(fmt) : "sin fecha";
                VBox card = crearCard(
                        "RECORDATORIO",
                        r.getTitulo(),
                        "Vence: " + fecha
                );
                contenedorPendientes.getChildren().add(card);
                hay = true;
            }
        }

        if (!hay) {
            Label vacio = new Label("No tienes pendientes.");
            vacio.setStyle("-fx-font-size: 14px; -fx-text-fill: #888;");
            contenedorPendientes.getChildren().add(vacio);
        }
    }

    private VBox crearCard(String tipo, String titulo, String detalle) {
        VBox card = new VBox(5);
        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;" +
                        "-fx-padding: 15;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0.1, 0, 2);"
        );

        Label lblTipo = new Label(tipo);
        lblTipo.setStyle("-fx-font-size: 11px; -fx-text-fill: white; -fx-background-color: #1565C0; -fx-background-radius: 5; -fx-padding: 2 8 2 8;");

        Label lblTitulo = new Label(titulo);
        lblTitulo.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #0D47A1;");

        Label lblDetalle = new Label(detalle);
        lblDetalle.setStyle("-fx-font-size: 13px; -fx-text-fill: #555;");

        card.getChildren().addAll(lblTipo, lblTitulo, lblDetalle);
        return card;
    }

    @FXML
    private void volver(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
