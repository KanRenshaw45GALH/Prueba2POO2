package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainFX extends Application {


    //Codigo de InicioFXML:
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
                MainFX.class.getResource("/MenuBienvenida.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Gestor de Tareas y Recordatorios");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}