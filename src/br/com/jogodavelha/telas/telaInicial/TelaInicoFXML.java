package br.com.jogodavelha.telas.telaInicial;

import br.com.jogodavelha.configJogo.Jogando;
import br.com.jogodavelha.telas.imgFundo.AdicionarFundo;
import br.com.jogodavelha.telas.telaJogador.TelaJogadorFXML;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TelaInicoFXML implements Initializable {

    @FXML
    private AnchorPane paneFundo;

    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        paneFundo.setBackground(AdicionarFundo.addFundo());
    }

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
            TelaJogadorFXML telaJogadorFXML = loader.getController();
            if(Jogando.isJogarIA()){
                telaJogadorFXML.setIma2(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/br/com/jogodavelha/telas/icon/jogadores/Conputador.png"))));
                telaJogadorFXML.setNameJogador2("Violet");
            }
            telaJogadorFXML.setStage(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
