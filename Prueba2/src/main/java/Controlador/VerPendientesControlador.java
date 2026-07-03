package Controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import modeloUsuario.Usuario;
import modeloElemento.Elemento;
import modeloElemento.ElementoTarea;
import modeloElemento.ElementoRecordatorio;
import catalogo.Estado;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class VerPendientesControlador {

    @FXML private VBox BtnPendientes;

    private Usuario usuarioActivo;

    public void setUsuario(Usuario usuario) {
        this.usuarioActivo = usuario;
        cargarPendientes();
    }

    private void cargarPendientes() {
        BtnPendientes.getChildren().clear();
        List<Elemento> lista = usuarioActivo.getElemento();

        if (lista == null || lista.isEmpty()) {
            Label vacio = new Label("Sin elementos pendientes.");
            vacio.setStyle("-fx-font-size: 14px; -fx-text-fill: #888;");
            BtnPendientes.getChildren().add(vacio);
            return;
        }

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        boolean hay = false;

        for (Elemento e : lista) {
            if (e instanceof ElementoTarea t
                    && t.getEstado() != Estado.COMPLETADO
                    && t.getEstado() != Estado.CANCELADA) {

                String color;

                switch (t.getPrioridad()) {
                    case ALTA:
                        color = "#E53935";
                        break;
                    case MEDIA:
                        color = "#FDD835";
                        break;
                    case BAJA:
                        color = "#43A047";
                        break;
                    default:
                        color = "#1565C0";
                }

                VBox card = crearCard(
                        "TAREA",
                        t.getTitulo(),
                        "Estado: " + t.getEstado() + " | Prioridad: " + t.getPrioridad(),
                        color
                );


                BtnPendientes.getChildren().add(card);
                hay = true;

            } else if (e instanceof ElementoRecordatorio r) {

                String color;

                switch (r.getPrioridad()) {
                    case ALTA:
                        color = "#E53935";
                        break;
                    case MEDIA:
                        color = "#FDD835";
                        break;
                    case BAJA:
                        color = "#43A047";
                        break;
                    default:
                        color = "#1565C0";
                }

                String fecha = r.getFechaLimite() != null
                        ? r.getFechaLimite().format(fmt)
                        : "Sin fecha";

                VBox card = crearCard(
                        "RECORDATORIO",
                        r.getTitulo(),
                        "Vence: " + fecha,
                        color
                );

                BtnPendientes.getChildren().add(card);
                hay = true;
            }
        }


        if (!hay) {
            Label vacio = new Label("No tienes pendientes.");
            vacio.setStyle("-fx-font-size: 14px; -fx-text-fill: #888;");
            BtnPendientes.getChildren().add(vacio);
        }
    }

    private VBox crearCard(String tipo, String titulo, String detalle, String color) {

        VBox card = new VBox(5);

        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 10;" +
                        "-fx-border-color: " + color + ";" +
                        "-fx-border-width: 3;" +
                        "-fx-padding: 15;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0.1, 0, 2);"
        );

        Label lblTipo = new Label(tipo);
        lblTipo.setStyle(
                "-fx-font-size: 11px;" +
                        "-fx-text-fill: white;" +
                        "-fx-background-color: " + color + ";" +
                        "-fx-background-radius: 5;" +
                        "-fx-padding: 2 8 2 8;"
        );

        Label lblTitulo = new Label(titulo);
        lblTitulo.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #0D47A1;");

        Label lblDetalle = new Label(detalle);
        lblDetalle.setStyle("-fx-font-size: 13px; -fx-text-fill: #555;");

        card.getChildren().addAll(lblTipo, lblTitulo, lblDetalle);

        return card;
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
