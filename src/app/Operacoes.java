package app;
import java.util.Date;

public class Operacoes implements IDado {
     
    private Conta conta;
    private int numero_Conta;
    private int cod_Operacao;
    private double valor;
    public Date data;

    public Operacoes(Cliente cliente, Conta conta, Date tempo) {
        numero_Conta = 0;
        cod_Operacao = 0;
        valor = 0.0;
        data = new Date();
    }

    public Operacoes(int numero_Conta, int cod_Operacao, double valor, Date data){
        this.numero_Conta = numero_Conta;
        this.cod_Operacao = cod_Operacao;
        this.valor = valor;
        this.data = data;
    }

	public int getNumero_Conta(){
        return numero_Conta;
    }

    public void setNumero_Conta(int numero_Conta){
        this.numero_Conta = numero_Conta;
    }

    public int getCod_Operacao(){
        return cod_Operacao;
    }

    public void setCod_Operacoes(int cod_Operacao){ 
        this.cod_Operacao = cod_Operacao;
    }

    public double getValor(){
        return valor;
    }

    public void setValor(int valor){
        this.valor = valor;
    }

    public Date getData(){
        return data;
    }

    public void setData(Date data){
        this.data = data;
    }

    @Override
	public String getID() {
        return data.toString();
    }

     //mostrar todas as informações de cada cliente que operacionou
     @Override
     public String toString(){
  
         return "Numero da conta: " + numero_Conta +
         "\nCódigo da operação:" + cod_Operacao +
         "\nValor: "+ valor +
         "\nData: " + data;
     }
  
     @Override
     public int compareTo(IDado o) {
         Operacoes aux = (Operacoes) o;
         return Integer.compare(aux.numero_Conta,this.numero_Conta);
     }
    

    public void Saque(double valor){

        double saldo = conta.getSaldo_inicial();

        if(valor < conta.getSaldo_inicial() && valor > 0){
             
            if(saldo > valor){

                saldo -= valor;

                System.out.println("Valor sacado:" + valor);
                System.out.println("Saldo atualizado: R$"
                + (saldo) +
                 "-Número da conta "+ numero_Conta +
                 "-Código da operação "+ cod_Operacao
               + "-Data "+ data);
            }
            else{
                System.out.println("Saldo Insuficiente.");
            }
        }
        else{
            System.out.println("O valor do saque deve ser positivo.");
        }
    }

    public void Deposito(double valor){

        double saldo = conta.getSaldo_inicial();

        if(valor > 0){

             saldo += valor;

            System.out.println("Valor depositado:" + valor);
            System.out.println("Saldo atualizado: R$"+
            + (saldo) + 
            "-Número da conta "+ numero_Conta +
            "-Código da operação "+ cod_Operacao
             +"-Data "+ data);
        }
        else{
            System.out.println("Error.Impossível depositar valor negativo!");
        }
    }
}