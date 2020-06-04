package app;

public class Pesquisa {

    //metodo estatico de realizar busca binaria no vetor de contas
    public static int pesquisaBinaria(Conta [] vetor, int chave)
    {
        return pesquisaBinaria(vetor, chave, 0, vetor.length - 1);
    }
    
    public static int pesquisaBinaria(Conta [] vetor, int chave, int esq, int dir)
    {
            int meio = (esq + dir) / 2;

            if (Integer.compare(chave, vetor[meio].getNumero()) == 0)
                return meio;

            if (esq >= dir)
                return -1;

            else
              if (Integer.compare(vetor[meio].getNumero(),(chave)) < 0)
                return pesquisaBinaria(vetor, chave, meio + 1, dir);
                
            else
                return pesquisaBinaria(vetor, chave, esq, meio - 1);
   }
}