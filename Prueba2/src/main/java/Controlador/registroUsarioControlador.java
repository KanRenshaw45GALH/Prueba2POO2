package Controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class registroUsarioControlador {
    @FXML
    private Label welcomeLabel;

    @FXML
    private void handleButton() {
        welcomeLabel.setText("¡Botón presionado!");
    }

}
