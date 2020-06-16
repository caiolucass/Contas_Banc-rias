package app;

public class Pesquisa {

    // metodo estatico de realizar busca binaria no vetor de contas
    public static int pesquisaBinaria(Conta[] vetor, int chave) {
        return pesquisaBinaria(vetor, chave, 0, vetor.length - 1);
    }

    public static int pesquisaBinaria(Conta[] vetor, int chave, int esq, int dir) {

        if (esq > dir)
            return -1;

        int meio = (esq + dir) / 2;
        if (vetor[meio].getNumero() == chave)
            return meio;

        else if (vetor[meio].getNumero() < chave)
            return pesquisaBinaria(vetor, chave, meio + 1, dir);

        else
            return pesquisaBinaria(vetor, chave, esq, meio - 1);
    }
}