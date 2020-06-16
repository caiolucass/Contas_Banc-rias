package app;

class Banco {
    static Conta [] contas; // vetor de contas
    private int contadorDecontas = 0; // contador de conta dos clientes

    public Banco() {
        contas = new Conta[390000]; // numero de conta do banco
        app.arvoreCliente = new Arvore();
    }

    /* ordernar vetor de contas numero */
    public void ordenarContas() {
        Ordenacao.quickSort(contas);
    }

    /*ordenar vetor de contas pelo saldo decrescente*/
    public void ordenarContasSaldo() {
        Ordenacao.quickSort2(contas);
    }

    /* recebe posição como parametro e retorna a conta naquela posicao */
    public Conta getConta(int posicao) {
        if (contas[posicao] != null) {

            return contas[posicao];
        } else {
            return null;
        }
    }

    /* iniciar uma conta no vetor de contas */
    public void setConta(Conta conta) {
        if (conta != null && contadorDecontas < contas.length) {

            contas[contadorDecontas++] = conta;
        }
    }

    /*
     * Inserir os Clientes na arvore de clientes no banco onde a sua reespectiva
     * conta se encontra Adicionar na lista de operacoes de cada Cliente e Conta
     *
     * CPF do cliente a ser operacionado Nome do Cliente Posicao da Conta em que o
     * Cliente vai operacionar
     */
    public void Operaracionar(Operacoes operacao, int posicaoDaConta) {

        Conta conta = contas[posicaoDaConta];

        conta.addNasOperacoes(operacao, conta);// adicionar a operação no cliente
    }

    /* pesquisar cliente na arvore de cliente */
    public Cliente pesquisarClienteNaArvore(String cpf) {
        Cliente dado = (Cliente) app.arvoreCliente.pesquisar(cpf);
        System.out.println(dado);
        return dado;
    }

    /* pesquisar uma conta no vetor de contas */
    public Conta pesquisarConta(int numeroConta) {

        int posicaoConta = pesquisarPosicaoDaConta(numeroConta);

        if (posicaoConta != -1) {

            return contas[posicaoConta];
        }
        return null;
    }

    /* pesquisar a posicao de uma determinada conta no vetor de contas */
    public int pesquisarPosicaoDaConta(int numeroConta) {

        return Pesquisa.pesquisaBinaria(contas, numeroConta);
    }
}