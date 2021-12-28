package br.com.jogodavelha.configJogo;

import br.com.jogodavelha.TestarJogo.TestarResultado;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class JogadorIA {

    private static Map<Integer, Integer> listPosicao = new HashMap<>();

    public static int getNamber(int[] selecionado) {
        listPosicao = pegarPosicao(selecionado);
        int [] novoSelecionado = Arrays.copyOf(selecionado,selecionado.length);
        for (int i = 0; i < 1; i++) {
            testPosicao1(novoSelecionado);
        }
        return pegarMelhorPontuaçao();
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

    public static void testPosicao1(int[] selecionado) {
        Integer n = pegarPosicaoList(listPosicao);
        int[] novaListaTeste = vezDaIA(selecionado, n);
        int result = testRestoPosicao(novaListaTeste, 0, 1);
        listPosicao.put(n, listPosicao.get(n) + result);
    }

    public static int testRestoPosicao(int[] selecionado, Integer pontuacao, int vez) {
        int result = TestarResultado.testarJogo(selecionado);
        if (result == -1) {
            return pontuacao + 5;

        } else if (result == 1) {
            return pontuacao - 3;

        } else {
            Map<Integer, Integer> listTestResto;
            listTestResto = pegarPosicao(selecionado);
            if (listTestResto.size() == 0) {
                return -1;
            }
            Integer n = pegarPosicaoList(listTestResto);
            if (vez == 1) {
                testRestoPosicao(vezDoJogador1(selecionado, n), pontuacao, -1);
            } else {
                testRestoPosicao(vezDaIA(selecionado, n), pontuacao, 1);
            }
            return pontuacao - 1;
        }
    }

    public static int[] vezDoJogador1(int[] selecionado, int posicao) {
        selecionado[posicao] = 1;
        return selecionado;
    }

    public static int[] vezDaIA(int[] selecionado, int posicao) {
        selecionado[posicao] = -1;
        return selecionado;
    }

    public static Integer pegarPosicaoList(Map<Integer, Integer> list) {
        return (Integer) list.keySet().toArray()[ThreadLocalRandom.current().nextInt(list.size())];
    }

    public static int pegarMelhorPontuaçao() {
        int posicao = 0;
        int pontuacao = Integer.MIN_VALUE;
        for (Integer key : listPosicao.keySet()) {
            if (pontuacao < listPosicao.get(key)) {
                pontuacao = listPosicao.get(key);
                posicao = key;
            }
        }
        return posicao;
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 150; i++) {
//            int result = getNamber( new int[]{0,0,0,0,0,0,0,0,0});
//            System.out.println(listPosicao);
//            int result1 = getNamber( new int[]{0,1,0,0,-1,0,1,0,0});
//            int result2 = getNamber( new int[]{0,0,1,0,0,0,0,0,0});
//            int result3 = getNamber( new int[]{0,0,1,0,-1,0,0,0,0});
//            System.out.println(listPosicao);
//        }

        int result = getNamber(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0});
        System.out.println(listPosicao);
        System.out.println(result);

    }

}
