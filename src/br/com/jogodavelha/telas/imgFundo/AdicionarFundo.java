package br.com.jogodavelha.telas.imgFundo;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.util.Objects;

public class AdicionarFundo {

    public static Background addFundo(){
        Image image = new Image(Objects.requireNonNull(AdicionarFundo.class.getResourceAsStream("/br/com/jogodavelha/telas/imgFundo/Background.jpg")));
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        return new Background(backgroundImage);
    }
}
