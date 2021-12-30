package br.com.jogodavelha.configJogo;

import br.com.jogodavelha.TestarJogo.TestarResultado;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class JogadorIA {

    private static Map<Integer, Integer> listPosicao = new HashMap<>();
    public static int IA(int[] selecionado){
        int valor;
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
        int result = TestarResultado.testarJogo(selecionado);
        int melhorValor = 0;
        int valor;
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
        int valor = listPosicao.containsValue(1) ? 1 : 0;
        for(Integer key: listPosicao.keySet()){
            if(listPosicao.get(key) == valor){
                posicao.add(key);
            }
        }
        if(posicao.size() == 0){
            return (Integer) listPosicao.keySet().toArray()[ThreadLocalRandom.current().nextInt(listPosicao.size())];
        }

        return posicao.get(ThreadLocalRandom.current().nextInt(posicao.size()));
    }
}
