package app;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class app {

    static Banco [] banco = new Banco[1]; // arvore banco no vetor
    static Hash hashDeClientes; // hash de clientes, através do cpf

    public static void main(String [] args) throws Exception {
        iniciar();
        menu();
    }

    public static void menu() throws Exception {
        
        Scanner ler = new Scanner(System.in);
        String leitura;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        Date data;
        int numero;
        int op;

        do {
            System.out.println();
            System.out.println("OPCOES: ");
            System.out.println("1. Mostrar informação consolidada de um CLIENTE (saldo e saldo final).");
            System.out.println("2. Exibir o extrato de operações de uma conta em um determinado período pedido pelo cliente.");
            System.out.println("3. Exibir um relatório de contas ordenado do maior saldo para o menor");
            System.out.println("\n0. SAIR");
            op = Integer.parseInt(ler.nextLine());

            switch (op) {
                case 1:
                    System.out.println("Entre com o CPF do cliente:");
                    leitura = ler.nextLine();
                    pesquisarPorCliente(leitura);
                    ler.nextLine();
                    break;

                case 2:
                    System.out.println("Entre com o número da CONTA para pesquisa:");
                    numero = Integer.parseInt(ler.nextLine());

                    System.out.println("Entre com a data da OPERAÇÃO desejada:");
                    data = formato.parse(ler.nextLine());
                    pesquisarPorConta(numero, data);
                    ler.nextLine();
                    break;

                case 3:
                    System.out.println("Exibindo relatórios de CONTAS ordenado pelo SALDO...");
                    ordenarTodosSaldos();
                    ler.nextLine();
                    break;
            }
        } while (op != 0);
        ler.close();
    }

    // iniciar os 3 arquivos, iniciar as contas e ordenalas
    public static void iniciar() throws Exception {

        for (int i = 0; i < banco.length; i++) {
            banco[i] = new Banco();
        } 
        hashDeClientes = new Hash();

        System.out.println("Inicializando CLIENTES...");
        inicializarClientes();
        System.out.println("Terminou CLIENTES. Aperte ENTER para continuar\n");

        System.out.println("Inicializando CONTAS...");
        inicializarContas();
        System.out.println("Terminou CONTAS. Aperte ENTER para continuar.");
        System.out.println("Ordenando CONTAS...\n");
        ordenarTodasContas();
        System.out.println("\nFinalizou ordenação de CONTAS! Aperte ENTER para continuar.\n");

        System.out.println("Inicializando OPERAÇÕES...");
        inicializarOperacoes();
        System.out.println("Quantidade =" + hashDeClientes.vazio());
        System.out.println("Terminou OPERAÇÕES. Aperte ENTER para continuar.");
    }

     // inicializar arquivo de clientes
     public static void inicializarClientes() throws FileNotFoundException, Exception{

        File arquivo = new File("dadosClientes.txt");
        Scanner leitor = new Scanner(arquivo);
        String linha = leitor.nextLine();
        String [] dados;
 
            while (leitor.hasNextLine())
            {
                dados = linha.split(";");
                hashDeClientes.inserir(new Cliente(dados[0],dados[1]));
            }
        leitor.close();
     }

    // inicializar o arquivo de contas
    public static void inicializarContas() throws FileNotFoundException, Exception {

        File arquivo = new File("dadosContas.txt");
        Scanner leitor = new Scanner(arquivo);
        String linha = leitor.nextLine();
        String [] dados; 

            while (leitor.hasNextLine()) {

                dados = linha.split(";");
                int numConta = Integer.parseInt(dados[0]);
                String cpf = dados[1];
                Double saldo = Double.parseDouble(dados[2]);
                banco[0].setConta(new Conta(numConta, cpf, saldo));
            }
        leitor.close();
    }

    //inicializar arquivo de operações acionar os clientes, adicionar na arvoreDeClientes
    //no banco onde a conta se encontra e adicionar a lista de operações de cada cliente e conta
    public static void inicializarOperacoes() throws FileNotFoundException, Exception {
        
        File arquivo = new File("dadosOperacoes.txt");
        Scanner leitor = new Scanner(arquivo); 

        String linha = leitor.nextLine();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy"); 
        String [] dados;
        String descobrir;
        String cpf ="";
        String nome ="";

        int posicaoDaConta;
        int numeroConta;
        int codOperacao;

        Operacoes operacao;
        Date data;
        double valor;

                while (leitor.hasNextLine())
                {
                    posicaoDaConta = -1;
                    dados = linha.split(";");
                    numeroConta = Integer.parseInt(dados[0]);
                    codOperacao = Integer.parseInt(dados[1]);
                    valor = Double.parseDouble(dados[2]);
                    data = formato.parse(dados[3]);
                    
                    //procurar onde alocar cada cliente
                    for (int i = 0; i < banco.length && posicaoDaConta == -1; i++)
                    {
                        posicaoDaConta = banco[i].pesquisarPosicaoDaConta(numeroConta);
                        descobrir = descobrirCliente(cpf);

                        if (posicaoDaConta != -1){

                            banco[i].Operaracionar(cpf, nome, posicaoDaConta, data);
                        }
                    }
                }
            leitor.close();
    }

   //pesquisar um cliente na ArvoreDeCliente e no Banco e mostrar seu saldo
   public static void pesquisarPorCliente(String cpf)
   {
       Cliente pesquisa = null;

       for (int i = 0; i < banco.length && pesquisa == null; i++){

           pesquisa = banco[i].pesquisarClienteNaArvore(cpf);
       }
       if (pesquisa != null){
           System.out.println(pesquisa.Relatorio_saldo());
       } 
       else {
           System.out.printf("Cliente de cpf {0} não foi encontrado.", cpf);
       }
   }

   //pesquisar uma determinada conta, e mostrar, o(s) cliente(s) que a acessaram e suas operaçoes
   public static void pesquisarPorConta(int numeroConta)
   {
       Conta pesquisa = null;

       for(int i = 0; i < banco.length && pesquisa == null; i++)
       {
           pesquisa = banco[i].pesquisarConta(numeroConta);
       } 

       System.out.println(pesquisa.Extrato());
   }

   //buscar uma determinada conta, e mostrar, o(s) cliente(s) que a acessaram
   //e suas operaçoes em determinada data
   public static void pesquisarPorConta(int numeroConta, Date data)
   {
       Conta pesquisa = null;
       // percorrer o banco ate encontrar a conta desejada
       // as conta estao ordenadas e a busca é feita por pesquisa binaria
       for(int i = 0; i < banco.length && pesquisa == null; i++){

        pesquisa = banco[i].pesquisarConta(numeroConta);
       } 
       
       System.out.println(pesquisa.ExtratoData(data));
   }

    //pesquisar na HASH de Cliente, para um cpf informado
    public static String descobrirCliente(String cpf)
    {
         Cliente pesquisa = (Cliente)hashDeClientes.pesquisar(cpf);

         if(pesquisa.cpf != null){
              return pesquisa.cpf;
         }
         else{
             return null;
         }         
    } 
    
    //ordenar cada conta no vetor de conta em ordem numerica do numero da conta, pelo Quicksort
    public static void ordenarTodasContas(){
          
        for(Banco bancos : banco){
  
            bancos.ordenarContas();
        }      
    }

    //ordenar cada conta no vetor de conta em ordem decrescente do saldo, pelo QuickSort2
    public static void ordenarTodosSaldos(){
        
        for(Banco bancos : banco){
  
            bancos.ordenarContasSaldo();
        } 
    }
}