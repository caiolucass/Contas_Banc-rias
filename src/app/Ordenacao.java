package app;

public class Ordenacao {

    //ordenar o vetor de contas pelo numero da conta
    public static void quickSort(Conta [] vetor)
    {
        quickSort(vetor, 0, vetor.length - 1);

        for (int i = 0; i < vetor.length; i++)
            System.out.println(vetor[i]);
    }

    public static void quickSort(Conta [] vetor, int esquerda, int direita){

        if (esquerda < direita) {
            int IndiceParticao = Particao(vetor, esquerda, direita);

            quickSort(vetor, esquerda, IndiceParticao  - 1);
            quickSort(vetor, IndiceParticao + 1, direita);
        }
    }

        private static int Particao(Conta vetor[], int esquerda, int direita) {

            int pivo = vetor[direita].getNumero();
            int i = (esquerda - 1);
    
            for (int j = esquerda; j < direita; j++) {

                if (vetor[j].getNumero() <= pivo) {
                    i++;
    
                    int troca = vetor[i].getNumero();
                    vetor[i].setNumero(vetor[j].getNumero());
                    vetor[j].setNumero(troca);
                }
            }
    
            int troca= vetor[i + 1].getNumero();
            vetor[i + 1].setNumero(vetor[direita].getNumero());
            vetor[direita].setNumero(troca);
    
            return i + 1;
        }

/*------------------------------------------------------------------------------------------------*/

    //Ordenar o vetor de contas com saldo decrescente
    public static void quickSort2(Conta[] vetor)
    {
        quickSort2(vetor, 0, vetor.length - 1);

        for (int i = 0; i < vetor.length; i++)
            System.out.println(vetor[i]);
    }

    public static void quickSort2(Conta [] vetor, int esquerda, int direita){   

        if (esquerda < direita) {
            int IndiceParticao = Particao2(vetor, esquerda, direita);

            quickSort(vetor, esquerda, IndiceParticao  - 1);
            quickSort(vetor, IndiceParticao + 1, direita);
        }
    }

    private static int Particao2(Conta vetor[], int esquerda, int direita) {

        double pivo = vetor[direita].getSaldo_inicial();
        int i = (esquerda - 1);

        for (int j = esquerda; j < direita; j++) {

            if (vetor[j].getNumero() >= pivo) {
                i++;

                double troca = vetor[i].getSaldo_inicial();
                vetor[i].setSaldo_inicial(vetor[j].getSaldo_inicial());
                vetor[j].setSaldo_inicial(troca);
            }
        }

        double troca = vetor[i + 1].getSaldo_inicial();
        vetor[i + 1].setSaldo_inicial(vetor[direita].getSaldo_inicial());
        vetor[direita].setSaldo_inicial(troca);

        return i + 1;
    }
        

}


