package br.com.jogodavelha;

import br.com.jogodavelha.telas.telaInicial.TelaInicoFXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
            primaryStage.setTitle("Jogo Da Velha");
            primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("telas/icon/jogoIcon/IconJogoDaVelha.png"))));
            primaryStage.show();
            ((TelaInicoFXML)loader.getController()).setStage(primaryStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
