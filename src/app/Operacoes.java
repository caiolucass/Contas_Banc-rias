package app;

import java.util.Date;

public class Operacoes implements IDado {

    public Conta conta;
    private int numero_Conta;
    private int cod_Operacao;
    private double valor;
    protected Date data;

    public Operacoes(Conta conta, Date data) {
        conta = null;
        numero_Conta = 0;
        cod_Operacao = 0;
        valor = 0.0;
        data = new Date();
    }

    public Operacoes(int numero_Conta, int cod_Operacao, double valor, Date data) {
        this.numero_Conta = numero_Conta;
        this.cod_Operacao = cod_Operacao;
        this.valor = valor;
        this.data = data;
    }

    public int getNumero_Conta() {
        return numero_Conta;
    }

    public void setNumero_Conta(int numero_Conta) {
        this.numero_Conta = numero_Conta;
    }

    public int getCod_Operacao() {
        return cod_Operacao;
    }

    public void setCod_Operacoes(int cod_Operacao) {
        this.cod_Operacao = cod_Operacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String getID() {
        return data.toString();
    }

    /* mostrar todas as informações de cada cliente que operacionou */
    @Override
    public String toString() {

        return "Numero da conta: " + numero_Conta + "\nCódigo da operação:" + cod_Operacao + "\nValor: " + valor
                + "\nData: " + data;
    }

    @Override
    public int compareTo(IDado o) {
        Operacoes aux = (Operacoes) o;
        return Integer.compare(aux.numero_Conta, this.numero_Conta);
    }

    /* Realizar Saque */
    public boolean sacar(double valor) {

        double saldo = conta.getSaldo_inicial();

        if (valor < saldo && saldo > 0) {

            saldo -= valor;
            return true;
        } else
            return false;
    }

    /* Realizar Deposito */
    public boolean depositar(double valor) {

        double saldo = conta.getSaldo_inicial();

        if (valor > 0 && saldo >= valor) {
            saldo += valor;
            return true;
        } else
            return false;
    }

    /* codigo 1 = Sacar codigo 2 = Depositar */
    public boolean realizar_Operacaoes(int codigo, double valor, Date data) {

        boolean operar = false;
        switch (codigo) {
            case 1:
                if (this.sacar(valor)) {
                    new Operacoes(numero_Conta, cod_Operacao, valor, data);
                    operar = true;
                }
                break;
            case 2:
                if (this.depositar(valor)) {
                    new Operacoes(numero_Conta, cod_Operacao, valor, data);
                    operar = true;
                }
                break;
            default:
                break;
        }
        return operar;
    }
}