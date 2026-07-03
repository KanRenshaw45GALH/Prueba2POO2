package Controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modeloElemento.Elemento;
import modeloElemento.ElementoRecordatorio;
import modeloElemento.ElementoTarea;
import modeloUsuario.ListadoUsuarios;
import modeloUsuario.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VerElementosControlador {

    @FXML private ListView<String> listaElementos;
    @FXML private VBox panelDetalle;

    // Campos comunes de ambos elementos
    @FXML private Label lblTitulo;
    @FXML private Label lblDescripcion;
    @FXML private Label lblPrioridad;
    @FXML private Label lblFechaCreacion;
    @FXML private Label lblFechaLimite;
    @FXML private Label lblColaboradores;

    // Campos especificos de Tarea
    @FXML private VBox cardEstado;
    @FXML private Label lblEstado;

    // Campos especificos de Recordatorio
    @FXML private VBox cardFechaRecordatorio;
    @FXML private Label lblFechaRecordatorio;
    @FXML private VBox cardAlerta;
    @FXML private Label lblAlerta;

    private Usuario usuarioActivo;
    private ListadoUsuarios listadoUsuarios;
    // Lista ordenada: primero tareas, luego recordatorios
    private List<Elemento> listaOrdenada = new ArrayList<>();

    public void setListadoUsuarios(ListadoUsuarios listadoUsuarios) {
        this.listadoUsuarios = listadoUsuarios;
    }

    public void setUsuario(Usuario usuario) {
        this.usuarioActivo = usuario;
        cargarLista();
    }

    private void cargarLista() {
        listaElementos.setFixedCellSize(24);
        listaOrdenada.clear();
        ObservableList<String> items = FXCollections.observableArrayList();

        List<Elemento> elementos = usuarioActivo.getElemento();

        if (elementos == null || elementos.isEmpty()) {
            items.add("No tienes elementos creados.");
            listaElementos.setItems(items);
            return;
        }

        // Primero se muestran las tareas
        for (Elemento e : elementos) {
            if (e instanceof ElementoTarea) {
                listaOrdenada.add(e);
                items.add("📝 " + e.getTitulo());
            }
        }

// Luego recordatorios
        for (Elemento e : elementos) {
            if (e instanceof ElementoRecordatorio) {
                listaOrdenada.add(e);
                items.add("⏰ " + e.getTitulo());
            }
        }

        listaElementos.setItems(items);

        // Al seleccionar un item de la lista, mostrar sus caracteristicas
        listaElementos.getSelectionModel().selectedIndexProperty().addListener(
                (obs, oldVal, newVal) -> {
                    int idx = newVal.intValue();
                    if (idx >= 0 && idx < listaOrdenada.size()) {
                        mostrarDetalle(listaOrdenada.get(idx));
                    }
                }
        );
    }

    private void mostrarDetalle(Elemento e) {
        // Campos comunes
        lblTitulo.setText(e.getTitulo());
        lblDescripcion.setText(e.getDescripcion());
        lblPrioridad.setText(String.valueOf(e.getPrioridad()));
        lblFechaCreacion.setText(e.getFechaCreacion() != null ? e.getFechaCreacion().toString() : "-");
        lblFechaLimite.setText(e.getFechaLimite() != null ? e.getFechaLimite().toString() : "-");

        // Mostrar los colaboradores del elemento
        if (e.getColaboradores() == null || e.getColaboradores().isEmpty()) {
            lblColaboradores.setText("Sin colaboradores");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Usuario u : e.getColaboradores()) {
                sb.append(u.getNombreCompleto()).append("\n");
            }
            lblColaboradores.setText(sb.toString().trim());
        }

        // Ocultar todos los campos especificos primero
        cardEstado.setVisible(false);
        cardEstado.setManaged(false);
        cardFechaRecordatorio.setVisible(false);
        cardFechaRecordatorio.setManaged(false);
        cardAlerta.setVisible(false);
        cardAlerta.setManaged(false);

        // Mostrar campos segun el tipo: tarea o recordatorio
        if (e instanceof ElementoTarea tarea) {
            lblEstado.setText(String.valueOf(tarea.getEstado()));
            cardEstado.setVisible(true);
            cardEstado.setManaged(true);

        } else if (e instanceof ElementoRecordatorio recordatorio) {
            lblFechaRecordatorio.setText(
                    recordatorio.getFechaRecordatorio() != null
                            ? recordatorio.getFechaRecordatorio().toString()
                            : "-"
            );
            lblAlerta.setText(recordatorio.isAlerta() ? "Si" : "No");
            cardFechaRecordatorio.setVisible(true);
            cardFechaRecordatorio.setManaged(true);
            cardAlerta.setVisible(true);
            cardAlerta.setManaged(true);
        }

        // Mostrar el panel de detalle
        panelDetalle.setVisible(true);
        panelDetalle.setManaged(true);

        panelDetalle.getParent().layout();
        listaElementos.getParent().layout();
    }

    @FXML
    private void volver(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MenuGeneral.fxml"));
        Parent root = loader.load();

        MenuGeneralControlador controlador = loader.getController();
        controlador.setListadoUsuarios(listadoUsuarios);
        controlador.setUsuario(usuarioActivo);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}