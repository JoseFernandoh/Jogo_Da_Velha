package br.com.jogodavelha.telas.telaInicial;

import br.com.jogodavelha.configJogo.Jogador;
import br.com.jogodavelha.configJogo.Jogando;
import br.com.jogodavelha.telas.telaJogador.TelaJogadorFXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class TelaInicoFXML {

    private Stage stage;

    public void Jogadores2(){
        telaJogador();
    }

    public void jogadores1(){
        Jogando.setJogarIA(true);
        telaJogador();
    }

    private void telaJogador(){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/br/com/jogodavelha/telas/telaJogador/TelaJogadorFXML.fxml")));
            stage.setScene(new Scene(loader.load()));
            stage.show();
            ((TelaJogadorFXML)loader.getController()).setStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
