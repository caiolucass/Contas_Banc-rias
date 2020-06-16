package app;

class Cliente implements IDado {

	private String nome;
	public String cpf;
	Lista listaOperacoes = new Lista(); // lista de operacoes do cliente

	public Cliente(String cpf, String nome) throws IllegalArgumentException {

		this.cpf = cpf;
		this.nome = nome;
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
		return ("CPF do Cliente: " + this.cpf + " Nome: " + this.nome);
	}

	/*
	 * criar uma nova Operacao, com o Cliente atual, a Conta onde ele foi
	 * operacionado, e a data;
	 */
	public void addNasOperacoes(Conta conta) {
		Elemento novo = new Elemento(conta);
		listaOperacoes.Inserir(novo);
	}

	/*
	 * Função que gera o relatorio toda vez que, um cliente operaciona, mostrando o
	 * valor do saldo de cada conta e o saldo_final
	 */
	public String Relatorio_saldo() {

		Elemento aux = listaOperacoes.prim.prox;

		Conta conta;
		double saldo = 0.0;
		double saldo_final = 0.0;
		String relatorio = "";

		while (aux != null) {
			conta = (Conta) aux.meuDado;
			saldo += conta.getSaldo_inicial();
			relatorio += conta.toString() + "\n\n";
			aux = aux.prox;
		}
		saldo_final = saldo;
		relatorio += "Saldo final: " + saldo_final + "\n";
		return relatorio;
	}

}