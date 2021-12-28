package br.com.jogodavelha.telas.telaJogador;

import br.com.jogodavelha.configJogo.Jogador;
import br.com.jogodavelha.configJogo.Jogando;
import br.com.jogodavelha.configJogo.TIPOS;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class TelaJogadorFXML implements Initializable {

    private final Map<ImageView, TIPOS> pegarTipos = new HashMap<>();
    private ImageView imgSelect = null;
    private Jogador jogador1;
    private Jogador jogador2;
    private byte contJogador = 1;
    private Stage stage;
    private boolean concluido = false;

    @FXML
    private Button confirmar;

    @FXML
    private Button jogar;

    @FXML
    private TextField nomeJogador1;

    @FXML
    private TextField nomeJogador2;

    @FXML
    private GridPane icones;

    @FXML
    private AnchorPane fundoJogador1;

    @FXML
    private AnchorPane fundoJogador2;

    @FXML
    private Label selectJogador;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int cont = 0;
        for (TIPOS tipo : TIPOS.values()) {
            configIconImg(cont,tipo);
            cont++;
        }
    }

    public void configIconImg(int n, TIPOS tipo){
        ImageView imageView = (ImageView) icones.getChildren().get(n);
        pegarTipos.put(imageView,tipo);
        imageView.setOnMouseClicked(e ->{
            if(imageView.getOpacity() == 1 && !concluido){
                if(imgSelect != null){
                    imgSelect.setOpacity(1);
                }
                imgSelect = imageView;
                imageView.setOpacity(0.23);
                confirmar.setDisable(false);
            }

        });
    }

    public void jogar(){
        new Jogando(jogador1,jogador2,stage);
    }

    public void concluido(){
       if(contJogador == 1){
            jogador1 = new Jogador(nomeJogador1.getText(),pegarTipos.get(imgSelect));
            confirmar.setDisable(true);
            nomeJogador1.setDisable(true);
            nomeJogador2.setDisable(false);
            setStyle(fundoJogador2);
            resetStyle(fundoJogador1);
            imgSelect = null;
            selectJogador.setText("Jogador 2");

        }else if (contJogador == 2){
            jogador2 = new Jogador(nomeJogador2.getText(),pegarTipos.get(imgSelect));
            nomeJogador2.setDisable(true);
            resetStyle(fundoJogador2);
            imgSelect = null;
            confirmar.setDisable(true);
            jogar.setDisable(false);
            selectJogador.setText("");
            concluido = true;
        }
        contJogador ++;
    }

    public void resetStyle(AnchorPane anchorPane){
        anchorPane.setStyle("-fx-background-color: null");
    }

    public void setStyle(AnchorPane anchorPane){
        anchorPane.setStyle("-fx-background-color: #99cc00;" +
                " -fx-background-radius: 10px;" +
                " -fx-border-color: green;" +
                "-fx-border-width: 4px;" +
                "-fx-border-radius: 10px;");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
