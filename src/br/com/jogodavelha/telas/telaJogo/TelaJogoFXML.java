package br.com.jogodavelha.telas.telaJogo;

import br.com.jogodavelha.TestarJogo.TestarResultado;
import br.com.jogodavelha.configJogo.Jogador;
import br.com.jogodavelha.configJogo.Jogando;
import br.com.jogodavelha.telas.telaFinal.TelaFinalFXML;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class TelaJogoFXML implements Initializable {
    private int alternarJogador;
    private final Jogador jogador1;
    private final Jogador jogador2;
    private final Jogando jogando;
    private int lugarSobrando;
    public int[] selecionados = new int[8];
    private final int[] selectAleatorio = new int[]{-1, 1};

    @FXML
    private Polyline jogadorO;

    @FXML
    private Polyline jogadorX;

    @FXML
    private GridPane jogodavelha;

    @FXML
    private Label statusInpate;

    @FXML
    private Label statusJogador1;

    @FXML
    private Label statusJogador2;

    @FXML
    private Label vezJogador;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alternarJogador = selectAleatorio[ThreadLocalRandom.current().nextInt(2)];
        selecionados = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        mudarJogador();
        statusJogador1.setText("V: " + jogador1.getVitoria() + " D: " + jogador1.getDerrota());
        statusJogador2.setText("V: " + jogador2.getVitoria() + " D: " + jogador2.getDerrota());
        statusInpate.setText("E: " + jogando.getInpat());

        for (byte i = 0; i < 9; i++) {
            mudarImg(i);
        }
    }

    public TelaJogoFXML(Jogador jogador1, Jogador jogador2, Jogando jogando) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.jogando = jogando;
    }


    public void mudarImg(byte n) {
        AnchorPane anchorPane = (AnchorPane) jogodavelha.getChildren().get(n);
        ImageView imageView = (ImageView) anchorPane.getChildren().get(0);
        imageView.setOnMouseClicked(e -> {
            if (imageView.getImage() == null) {
                Supplier<Jogador> j = () -> alternarJogador == 1 ? jogador1 : jogador2;
                Jogador jogador = j.get();
                selecionados[n] = alternarJogador;
                mudarImg(imageView, jogador.getTipo().getSurce());
                testeJogo();
            }
        });
    }

    private void mudarJogador() {
        if (alternarJogador == -1) {
            alternarJogador = 1;
            vezJogador.setText(jogador1.getNome());
            jogadorO.setFill(Color.valueOf("#424641"));
            jogadorX.setFill(Color.CHARTREUSE);
        } else {
            alternarJogador = -1;
            vezJogador.setText(jogador2.getNome());
            jogadorX.setFill(Color.valueOf("#424641"));
            jogadorO.setFill(Color.CHARTREUSE);
        }
    }

    public void terminarJogo(int n) {
        TelaFinalFXML telaFinalFXML = null;
        if (n == 0) {
            telaFinalFXML = new TelaFinalFXML(null, jogando, new Image(Objects.requireNonNull(getClass().getResourceAsStream("/br/com/jogodavelha/telas/icon/jogadores/Empate.png"))), jogodavelha);
            jogando.setInpat();
        } else {
            if (n == 1) {
                telaFinalFXML = new TelaFinalFXML(jogador1, jogando, new Image(Objects.requireNonNull(getClass().getResourceAsStream("/br/com/jogodavelha/telas/icon/jogadores/Jogador1.png"))), jogodavelha);
                jogador1.setVitoria();
                jogador2.setDerrota();
            } else if (n == -1) {
                jogador1.setDerrota();
                jogador2.setVitoria();
                telaFinalFXML = new TelaFinalFXML(jogador2, jogando, new Image(Objects.requireNonNull(getClass().getResourceAsStream("/br/com/jogodavelha/telas/icon/jogadores/Jogador2.png"))), jogodavelha);
            }
        }
        jogando.telaVitoria(telaFinalFXML);
    }

    private void testeJogo() {
        if (lugarSobrando <= 5) {
            int teste = TestarResultado.testarJogo(selecionados);
            if (teste != 0) {
                terminarJogo(teste);
                return;
            }
            if (lugarSobrando == 1) {
                terminarJogo(0);
                return;
            }
        }
        mudarJogador();
        lugarSobrando--;
    }

    public void mudarImg(ImageView imageView, String surce) {
        imageView.setImage(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(surce))));
    }

}

