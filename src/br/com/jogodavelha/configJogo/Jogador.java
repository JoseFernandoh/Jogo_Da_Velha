package br.com.jogodavelha.configJogo;

public class Jogador {
    private final String nome;
    private int vitoria = 0;
    private int derrota = 0;
    private final TIPOS tipo;

    public Jogador(String nome, TIPOS tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public TIPOS getTipo() {
        return tipo;
    }

    public int getVitoria() {
        return vitoria;
    }

    public void setVitoria() {
        this.vitoria++;
    }

    public int getDerrota() {
        return derrota;
    }

    public void setDerrota() {
        this.derrota++;
    }

    public String getNome() {
        return nome;
    }
}
