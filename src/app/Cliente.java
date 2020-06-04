package app;

import java.util.Date;

class Cliente implements IDado {

	private String nome;
	public String cpf;
	Lista listaOperacoes = new Lista(); //lista de operacoes do cliente

	public Cliente(String cpf, String nome) throws IllegalArgumentException {

		this.nome = nome;
		this.cpf = cpf;
	}

	@Override
	public String getID() {
		return cpf;
	}

	public void setID(String cpf) {
		if (cpf.length() < 11 || !cpf.contains("-")) {
			throw new IllegalArgumentException("CPF inválido.");
		} else {
			this.cpf = cpf;
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome.length() < 5 || !nome.contains(" ")) {
			throw new IllegalArgumentException("Nome inválido.");
		} else {
			this.nome = nome;
		}
	}

	@Override
	public int compareTo(IDado o) {
		Cliente aux = (Cliente) o;
		return (aux.nome.compareTo(this.nome));
	}

	@Override
	public boolean equals(Object obj) {
		Cliente aux = (Cliente) obj;
		return (aux.cpf == this.cpf);
	}

	@Override
	public String toString() {
		return ("Nome do cliente: " + this.nome + "- CPF: " + this.cpf);
	}

	/*
     *criar uma nova Operacao, com o Cliente atual, 
     *a Conta onde ele foi operacionado, e a data;
	*/
	public void addNasOperacoes(Conta conta, Date data)
	{
		Operacoes operacao = new Operacoes(this, conta, data);
		Elemento novo = new Elemento(operacao);
		listaOperacoes.Inserir(novo);
		conta.addNasOperacoes(operacao);
	}
   
	/* 
	*Função que gera o relatorio toda vez que,
	*um cliente operaciona, mostrando o valor do
	*saldo de cada conta e o saldo_final
    */
	public String Relatorio_saldo(){

		Elemento aux = listaOperacoes.prim.prox;
		Operacoes operacao;
		double saldo = 0.0;
		double saldo_final = 0.0;
		String relatorio = "";

		while (aux != null)
		{
			operacao = (Operacoes)aux.meuDado;
			saldo += operacao.getValor();
			relatorio += operacao.toString()+ "\n\n";
			aux = aux.prox;
		}
		saldo_final = saldo;
		relatorio += "Saldo final: "+ saldo_final + "\n";
		return relatorio;
	}

}