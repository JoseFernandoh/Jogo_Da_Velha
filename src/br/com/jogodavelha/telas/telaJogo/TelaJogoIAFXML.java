package br.com.jogodavelha.telas.telaJogo;

import br.com.jogodavelha.TestarJogo.TestarResultado;
import br.com.jogodavelha.configJogo.Jogador;
import br.com.jogodavelha.configJogo.JogadorIA;
import br.com.jogodavelha.configJogo.Jogando;
import br.com.jogodavelha.telas.imgFundo.AdicionarFundo;
import br.com.jogodavelha.telas.telaFinal.TelaFinalFXML;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
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
import java.util.concurrent.TimeUnit;

public class TelaJogoIAFXML implements Initializable {

    private boolean vezIa;
    private int alternarJogador;
    private final Jogador jogador1;
    private final Jogador jogador2;
    private final Jogando jogando;
    private int lugarSobrando;
    public int[] selecionados = new int[8];
    private final int[] selectAleatorio = new int[]{-1, 1};

    public TelaJogoIAFXML(Jogador jogador1, Jogador jogador2, Jogando jogando) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.jogando = jogando;
    }

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

    @FXML
    private SplitPane telaFundo;

    @FXML
    private ImageView img2Jogador;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        img2Jogador.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/br/com/jogodavelha/telas/icon/jogadores/Conputador.png"))));
        telaFundo.setBackground(AdicionarFundo.addFundo());
        alternarJogador = selectAleatorio[ThreadLocalRandom.current().nextInt(2)];
        mudarJogador();
        selecionados = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        statusJogador1.setText("V: " + jogador1.getVitoria() + " D: " + jogador1.getDerrota());
        statusJogador2.setText("V: " + jogador2.getVitoria() + " D: " + jogador2.getDerrota());
        statusInpate.setText("E: " + jogando.getInpat());
        lugarSobrando = 9;
        for (byte i = 0; i < 9; i++) {
            mudarImg(i);
        }
    }


    public void mudarImg(byte n) {
        AnchorPane anchorPane = (AnchorPane) jogodavelha.getChildren().get(n);
        ImageView imageView = (ImageView) anchorPane.getChildren().get(0);
        imageView.setOnMouseClicked(e -> {
            if (imageView.getImage() == null && !vezIa) {
                selecionados[n] = alternarJogador;
                mudarImg(imageView, jogador1.getTipo().getSurce());
            }
        });
        imageView.imageProperty().addListener((observableValue, image, t1) -> {
            testeJogo();
        });

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
                telaFinalFXML = new TelaFinalFXML(jogador2, jogando, new Image(Objects.requireNonNull(getClass().getResourceAsStream("/br/com/jogodavelha/telas/icon/jogadores/Conputador.png"))), jogodavelha);
            }
        }
        jogando.telaVitoria(telaFinalFXML);
    }

    private void mudarJogador() {
        if (alternarJogador == -1) {
            alternarJogador = 1;
            vezJogador.setText(jogador1.getNome());
            jogadorO.setFill(Color.valueOf("#424641"));
            jogadorX.setFill(Color.CHARTREUSE);
            vezIa = false;
        } else {
            alternarJogador = -1;
            vezJogador.setText(jogador2.getNome());
            jogadorX.setFill(Color.valueOf("#424641"));
            jogadorO.setFill(Color.CHARTREUSE);
            vezIa = true;
            new Thread(this::vezIa).start();
        }
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
        lugarSobrando--;
        mudarJogador();
    }

    public void vezIa() {
        try {
            TimeUnit.MILLISECONDS.sleep(700);
            int n = JogadorIA.IA(selecionados);
            AnchorPane anchorPane = (AnchorPane) jogodavelha.getChildren().get(n);
            ImageView imageView = (ImageView) anchorPane.getChildren().get(0);
            selecionados[n] = alternarJogador;
            new Thread(() -> Platform.runLater(() -> mudarImg(imageView, jogador2.getTipo().getSurce()))).start();
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void mudarImg(ImageView imageView, String surce) {
        imageView.setImage(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream(surce))));
    }

}



