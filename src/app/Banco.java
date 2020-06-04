package app;

import java.util.Date;

class Banco
    {
        private Conta [] contas;  //vetor de contas
        private Arvore arvoreCliente; //arvore de clientes
        private int contadorDecontas = 0; //contador de conta dos clientes
        
        public Banco()
        {
            contas = new Conta [390000]; //numero de conta do banco
            arvoreCliente = new Arvore(); //criar arvore de cliente
        }

        //ordernar vetor de contas
        public void ordenarContas()
        {
            Ordenacao.quickSort(contas);
        }

        public void ordenarContasSaldo()
        {
            Ordenacao.quickSort2(contas);
        }

        //recebe posição como parametro e retorna a conta naquela posicao
        public Conta getConta(int posicao)
        {
            if (contas[posicao] != null){

                return contas[posicao];
            } 
            else{
                return null;
            }
        }

        //iniciar uma conta no vetor de contas
        public void setConta(Conta conta)
        {
            if(conta != null ){

                contas [contadorDecontas++] = conta;
            } 
        }

        /* 
         *Inserir os Clientes na arvore de clientes
         *no banco onde a sua reespectiva conta se encontra
         *Adicionar na lista de operacoes de cada Cliente e conta
         *
         *CPF do cliente a ser operacionado
         *Nome do Cliente
         *Posicao da Conta em que o Cliente vai operacionar 
        */
        public void Operaracionar(String cpf, String nome, int posicaoDaConta, Date data)
        {
            Cliente cliente = pesquisarClienteNaArvore(cpf);
            if(cliente == null){

                cliente = new Cliente(nome,cpf);
                arvoreCliente.inserir(cliente);  
            }
            cliente.addNasOperacoes(contas[posicaoDaConta], data);//adicionar em cliente e conta
        }

        //pesquisar cliente na arvore de cliente
        public Cliente pesquisarClienteNaArvore(String cpf)
        {
            return ((Cliente)(arvoreCliente.pesquisar(cpf)));
        }

        //pesquisar uma conta no vetor de contas
        public Conta pesquisarConta(int numeroConta)  
        {
            int posicaoConta = pesquisarPosicaoDaConta(numeroConta);

            if (posicaoConta != -1){

                return contas[posicaoConta];
            } 
            return null;
        }

        // pesquisar a posicao de uma determinada conta no vetor de contas
        public int pesquisarPosicaoDaConta(int numeroConta)    
        {
            return Pesquisa.pesquisaBinaria(contas, numeroConta); 
        }
    }