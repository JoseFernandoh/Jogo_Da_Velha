package br.com.jogodavelha;

import br.com.jogodavelha.telas.telaInicial.TelaInicoFXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class JogoDaVelha extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("telas/telaInicial/TelaInicoFXML.fxml")));
            primaryStage.setScene(new Scene(loader.load()));
            primaryStage.show();
            ((TelaInicoFXML)loader.getController()).setStage(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
