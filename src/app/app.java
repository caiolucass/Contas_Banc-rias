package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class app {

    static Banco[] banco = new Banco[1]; // arvore banco no vetor
    static Arvore arvoreCliente;

    public static void main(String[] args) throws Exception {
        iniciar();
        menu();
    }

    public static void menu() throws Exception {

        Scanner ler = new Scanner(System.in);
        String leitura;
        Date data;
        int numero;
        int op;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");

        do {
            System.out.println();
            System.out.println("OPCOES: ");
            System.out.println("1. Mostrar informação consolidada de um CLIENTE usando Árvore (saldo e saldo final).");
            System.out.println("2. Exibir o extrato de operações de uma conta em um determinado período pedido pelo cliente.");
            System.out.println("3. Exibir um relatório de contas ordenado do maior saldo para o menor.");

            System.out.println("0. Sair");
            System.out.print("\nDigite sua opção:");
            op = Integer.parseInt(ler.nextLine());

            switch (op) {
                case 1:
                    System.out.print("\nEntre com o CPF do cliente:");
                    leitura = ler.nextLine();
                    pesquisarPorCliente(leitura);
                    ler.nextLine();
                    break;

                case 2:
                    System.out.print("Entre com o número da CONTA para pesquisa:");
                    numero = Integer.parseInt(ler.nextLine());
                    System.out.print("Entre com a data da OPERAÇÃO desejada:");
                    data = formato.parse(ler.nextLine());
                    pesquisarPorConta(numero, data);
                    ler.nextLine();
                    break;

                case 3:
                    System.out.println("\nExibindo relatórios de CONTAS ordenado pelo MAIOR SALDO...\n");
                    ordenarTodosSaldos();
                    ler.nextLine();
                    break;
            }
        } while (op != 0);
        ler.close();
    }

    // iniciar os 3 arquivos, iniciar as contas e ordenalas
    public static void iniciar() throws Exception {

        Scanner scanner = new Scanner(System.in);

        banco[0] = new Banco();
        arvoreCliente = new Arvore();

        System.out.println("Inicializando CLIENTES...");
        inicializarClientes();
        System.out.println("Terminou CLIENTES. Aperte ENTER para continuar\n");
        scanner.nextLine();

        System.out.println("Inicializando CONTAS...");
        inicializarContas();
        System.out.println("Terminou CONTAS. Aperte ENTER para continuar.");
        System.out.println("Ordenando CONTAS...\n");
        ordenarTodasContas();
        System.out.println("\nFinalizou ordenação de CONTAS pelo número da conta! Aperte ENTER para continuar.");
        scanner.nextLine();

        System.out.println("\nInicializando OPERAÇÕES...");
        inicializarOperacoes();
        System.out.println("\nTerminou OPERAÇÕES. Aperte ENTER para continuar.");
        System.out.println("\nÀrvore de CLIENTE está vazia? = " + arvoreCliente.vazia());
        System.out.println("imprimir  a arvore de cliente: ");

    }

    // inicializar arquivo de clientes
    public static void inicializarClientes() throws FileNotFoundException {

        File arquivo = new File("dadosClientes.txt");
        Scanner leitor = new Scanner(arquivo);

        while (leitor.hasNextLine()) {

            String linha = leitor.nextLine();
            String[] dados = linha.split(";");
            String cpf = dados[0];
            String nome = dados[1];
            arvoreCliente.inserir(new Cliente(cpf, nome));

        }
        leitor.close();
    }

    /* inicializar o arquivo de contas */
    public static void inicializarContas() throws FileNotFoundException {

        File arquivo = new File("dadosContas.txt");
        Scanner leitor = new Scanner(arquivo);

        while (leitor.hasNextLine()) {

            String linha = leitor.nextLine();
            String[] dados = linha.split(";");
            int numConta = Integer.parseInt(dados[0]);
            String cpf = dados[1];
            Double saldo = Double.parseDouble(dados[2]);
            banco[0].setConta(new Conta(numConta, cpf, saldo));
        }
        leitor.close();
    }

    /*
     * inicializar arquivo de operações acionar os clientes, adicionar na
     * arvoreDeClientes no banco onde a conta se encontra e adicionar a lista de
     * operações de cada cliente e conta
     */
    public static void inicializarOperacoes() throws FileNotFoundException, Exception {

        File arquivo = new File("dadosOperacoes.txt");
        Scanner leitor = new Scanner(arquivo);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");

        int posicaoDaConta;
        int numeroConta;
        int codOperacao;

        Operacoes operacao;
        Date data = new Date();
        double valor;

        while (leitor.hasNextLine()) {
            String linha = leitor.nextLine();
            posicaoDaConta = 0;
            String[] dados = linha.split(";");
            numeroConta = Integer.parseInt(dados[0]);
            codOperacao = Integer.parseInt(dados[1]);
            valor = Double.parseDouble(dados[2]);
            data = formato.parse(dados[3]);
            operacao = new Operacoes(numeroConta, codOperacao, valor, data);

            posicaoDaConta = banco[0].pesquisarPosicaoDaConta(numeroConta);

            if (posicaoDaConta != -1) {

                banco[0].Operaracionar(operacao, posicaoDaConta);
            }
        }
        leitor.close();
    }

    /* Pesquisar um CLIENTE na ArvoreDeCliente e no BANCO para mostrar seu saldo */
    public static void pesquisarPorCliente(String cpf) {

        Cliente pesquisa = banco[0].pesquisarClienteNaArvore(cpf);

        if (pesquisa != null) {
            System.out.printf("Relatório de movimentação da conta: " + pesquisa.Relatorio_saldo());
        } else {
            System.out.println("\nCliente de CPF " + cpf + " não foi encontrado.");
        }
    }

    /*
     * buscar uma determinada conta, e mostrar, o(s) cliente(s) que a acessaram e
     * suas operaçoes em determinada data
     */
    public static void pesquisarPorConta(int numeroConta, Date data) {

        Conta pesquisa = banco[0].pesquisarConta(numeroConta);

        if (pesquisa != null) {
            System.out.println(pesquisa.Extrato_Conta_Data(data));
        } else {
            System.out.println("Conta ou Data não encontrada.");
        }
    }

    /*
     * ordenar cada conta no vetor de conta em ordem numerica do numero da conta,
     * pelo Quicksort
     */
    public static void ordenarTodasContas() {

        for (Banco bancos : banco) {

            bancos.ordenarContas();
        }
    }

    /*
     * ordenar cada conta no vetor de conta em ordem decrescente do saldo, pelo
     * QuickSort2
     */
    public static void ordenarTodosSaldos() {

        for (Banco bancos : banco) {

            bancos.ordenarContasSaldo();
        }
    }
}