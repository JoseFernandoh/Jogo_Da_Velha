package br.com.jogodavelha.telas.telaFinal;

import br.com.jogodavelha.configJogo.Jogador;
import br.com.jogodavelha.configJogo.Jogando;
import br.com.jogodavelha.telas.imgFundo.AdicionarFundo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaFinalFXML implements Initializable {
    private Jogador jogadorVencedor = null;
    private final Jogando controller;
    private final Image image;
    private final GridPane tabelaJogo;


    @FXML
    private ImageView imgVencedor;

    @FXML
    private Label nomeVencedor;

    @FXML
    private Label titulo;

    @FXML
    private AnchorPane pane;


    public TelaFinalFXML(Jogador jogadorVencedor, Jogando controller, Image image, GridPane tabelaJogo) {
        this.jogadorVencedor = jogadorVencedor;
        this.controller = controller;
        this.image = image;
        this.tabelaJogo = tabelaJogo;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane.setBackground(AdicionarFundo.addFundo());
        diminuirimg();
        tabelaJogo.setGridLinesVisible(true);
        tabelaJogo.setPrefWidth(190);
        tabelaJogo.setPrefHeight(160);
        tabelaJogo.setLayoutX(375);
        tabelaJogo.setLayoutY(45);
        pane.getChildren().add(tabelaJogo);

        if(jogadorVencedor == null){
            titulo.setText("Empate");
            nomeVencedor.setText("Sem Ganhador");
        }else{
            nomeVencedor.setText(jogadorVencedor.getNome());
        }

        imgVencedor.setImage(image);
    }

    public void diminuirimg(){
        for (int i = 0; i < 9; i++) {
            AnchorPane anchorPane = (AnchorPane) tabelaJogo.getChildren().get(i);
            ImageView imageView = (ImageView) anchorPane.getChildren().get(0);
            imageView.setFitHeight(30);
            imageView.setFitWidth(50);

        }
    }

    public void novoJogo(){
        controller.jogar();
    }

    public void sairDoJogo(){
        Platform.exit();
    }
}
