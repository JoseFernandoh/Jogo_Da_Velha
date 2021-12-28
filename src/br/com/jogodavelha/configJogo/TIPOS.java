package br.com.jogodavelha.configJogo;

public enum TIPOS {
    X("/br/com/jogodavelha/telas/icon/iconesJogo/XJogo.png"),
    O("/br/com/jogodavelha/telas/icon/iconesJogo/OJogo.png"),
    TRIANGULO("/br/com/jogodavelha/telas/icon/iconesJogo/Triangulo.png"),
    OCULOS("/br/com/jogodavelha/telas/icon/iconesJogo/Oculos.png"),
    CORACAO("/br/com/jogodavelha/telas/icon/iconesJogo/Coracao.png"),
    ESTRELA("/br/com/jogodavelha/telas/icon/iconesJogo/Estrela.png"),
    FOLHA("/br/com/jogodavelha/telas/icon/iconesJogo/Folha.png"),
    CONFIRMAR("/br/com/jogodavelha/telas/icon/iconesJogo/Confirmacao.png");

    private final String surce;

    TIPOS(String surce) {
        this.surce = surce;
    }

    public String getSurce() {
        return surce;
    }
}