package br.com.jogodavelha.TestarJogo;

public class TestarResultado {

    public static int testarJogo(int[] tabelaJogo){
        int[][] tabela = new int[3][3];
        tabela[0] = new int[]{tabelaJogo[0],tabelaJogo[1],tabelaJogo[2]};
        tabela[1] = new int[]{tabelaJogo[3],tabelaJogo[4],tabelaJogo[5]};
        tabela[2] = new int[]{tabelaJogo[6],tabelaJogo[7],tabelaJogo[8]};

        return resultado(tabela);
    }

    private static int resultado(int[][] tabela){
        int[][] result = TestarResultado.testeResultado(tabela);
        for (int[] ref : result){
            for(int arr : ref){
                if(arr/3==1){
                    return 1;
                }
                else if(arr/3==-1){
                    return -1;
                }
            }
        }
        return 0;
    }

    private static int[][] testeResultado(int[][] tabelaJogo){
        int[][] result = new int[3][3];
        int cont=-1;
        for(int[] arr : tabelaJogo){
            cont++;
            for(int aux : arr){
                result[0][cont] += aux;
            }
        }
        for(int[] arr : tabelaJogo){
            cont=-1;
            for(int aux : arr){
                cont++;
                result[1][cont] += aux;
            }
        }
        result[2][0] = tabelaJogo[0][0]+tabelaJogo[1][1]+tabelaJogo[2][2];
        result[2][1] = tabelaJogo[2][0]+tabelaJogo[1][1]+tabelaJogo[0][2];
        return result;
    }
}
