package app;

public class Hash_Cliente {
    

    private int N = 20071;
    private Lista [] tabela;

        public Hash_Cliente( )
        {
            tabela = new Lista[N];
        }

        //dispersao da tabela hash
        private int hash(String chave)
        {
            int hash = 0;

            for (int i = 0; i < chave.length(); i++){
                hash = (31 * hash + chave.charAt(i)) % N;
            }
            return hash % N;
        }

        //verificar se esta vazia
        public int vazio()
        {
            int contador = 0;

			for(Lista tabelas : tabela){

                if (tabela == null || tabelas.vazia()){
                    contador++; 
                }
            } 
            return contador;
        }

        //inserir na tabela
        public void inserir(IDado novo)
        {
            String cpf = novo.getID();
            Elemento elemento;
            String chave = (cpf.toString());

            if (chave != null) {
                int i = hash(chave);
                
                if (tabela[i] == null) {
                    tabela[i] = new Lista();
                }
                
                elemento = new Elemento(novo);
                tabela[i].Inserir(elemento);
            }
        }

        //pesquisar na tabela
        public IDado pesquisar(String cpf)
        {
            String chave_ = (cpf.toString());

            if (chave_ != null)
            {
                int i = hash(chave_);
                return (tabela[i].localizar(cpf));
            }
            return null;
        }

    }