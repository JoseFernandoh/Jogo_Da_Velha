package br.com.jogodavelha.configJogo;

import br.com.jogodavelha.telas.telaFinal.TelaFinalFXML;
import br.com.jogodavelha.telas.telaJogo.TelaJogoFXML;
import br.com.jogodavelha.telas.telaJogo.TelaJogoIAFXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Jogando {
    private static boolean jogarIA = false;
    private final Stage stage;
    private int inpat = 0;
    private final TelaJogoFXML telaJogoFXML;
    private final TelaJogoIAFXML telaJogoIAFXML;

    public Jogando(Jogador jogador1, Jogador jogador2, Stage stage) {
        telaJogoFXML = new TelaJogoFXML(jogador1,jogador2,this);
        telaJogoIAFXML = new TelaJogoIAFXML(jogador1,jogador2,this);
        this.stage = stage;
        jogar();
    }

    public void jogar(){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/br/com/jogodavelha/telas/telaJogo/TelaJogoFXML.fxml")));
            if(jogarIA){
                loader.setController(telaJogoIAFXML);
            }else{
                loader.setController(telaJogoFXML);
            }
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void telaVitoria(TelaFinalFXML telaFinalFXML){
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("/br/com/jogodavelha/telas/telaFinal/TelaFinalFXML.fxml")));
            loader.setController(telaFinalFXML);
            stage.setScene(new Scene(loader.load()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getInpat() {
        return inpat;
    }

    public void setInpat() {
        this.inpat++;
    }

    public static void setJogarIA(boolean jogarIA) {
        Jogando.jogarIA = jogarIA;
    }

    public static boolean isJogarIA(){
        return Jogando.jogarIA;
    }

}
