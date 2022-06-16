package br.com.jogodavelha.configJogo;

import br.com.jogodavelha.TestarJogo.TestarResultado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class NovaIa {

    private static Map<Integer, Integer> listPosicao = new HashMap<>();
    public static int IA(int[] selecionado){
        int valor = 0;
        int melhorValor = Integer.MIN_VALUE;
        listPosicao = pegarPosicao(selecionado);
        for(Integer key : listPosicao.keySet()){
            selecionado[key] = -1;
            valor = calculandoMovimento(selecionado , 1);
            listPosicao.put(key, valor);
            selecionado[key] = 0;

            if(valor > melhorValor){
                melhorValor = valor;
            }
        }

        return melhorPosicao();
    }

    private static int calculandoMovimento(int[] selecionado , int vez) {
        int melhorValor = 0;
        int result = TestarResultado.testarJogo(selecionado);
        int valor = 0;
        if (result == -1) {
            return 1;

        } else if (result == 1) {
            return -1;

        } else {
            Map<Integer, Integer> listTestResto;
            listTestResto = pegarPosicao(selecionado);
            for(Integer key : listTestResto.keySet()) {
                selecionado[key] = vez;
                if(vez == 1){
                    valor = calculandoMovimento(selecionado,-1);
                }else{
                    valor = calculandoMovimento(selecionado,1);
                }
                selecionado[key] = 0;

                if(vez == 1){
                    if (valor < melhorValor) {
                        melhorValor = valor;
                    }
                }else{
                    if (valor > melhorValor) {
                        melhorValor = valor;
                    }
                }
            }

        }
        return melhorValor;
    }

    public static Map<Integer, Integer> pegarPosicao(int[] selecionado) {
        Map<Integer, Integer> listTest = new HashMap<>();
        for (int i = 0; i < selecionado.length; i++) {
            if (selecionado[i] == 0) {
                listTest.put(i, 0);
            }
        }
        return listTest;
    }

    public static int melhorPosicao(){
        List<Integer> posicao = new ArrayList<>();
        int valor = 0;
        if(verVitoria()){
            valor = 1;
        }
        for(Integer key: listPosicao.keySet()){
            if(listPosicao.get(key) == valor){
                posicao.add(key);
            }
        }

        return posicao.get(ThreadLocalRandom.current().nextInt(posicao.size()));
    }

    public static boolean verVitoria(){
        for(Integer key: listPosicao.keySet()){
            if(listPosicao.get(key) == 1){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(IA(new int[]{
                0,0,1,
                -1,0,0,
                0,1,0
        }));
        System.out.println(listPosicao);
    }

}
