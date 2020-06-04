package app;

public class Arvore {
    
    private Nodo raiz;

        public Arvore()
        {
            raiz = null;
        }

        //verificar se a arvore esta vazia
        public Boolean vazia()
        {
            return (this.raiz == null);
        }

        //adicionar nodo
        private Nodo adicionar(Nodo no, IDado novo)
        {
            if (no == null){
                no = new Nodo(novo);
            }
            else if(no.meuDado.getID().compareTo(novo.getID()) > 0){
                no.esq = adicionar(no.esq, novo);
            }
            else if (no.meuDado.getID().compareTo(novo.getID()) < 0){
                no.dir = adicionar(no.dir, novo);
            }
            else{
                System.out.printf("O dado chamado {1} já foi inserido anteriormente.", novo);
            }        
            return no;
        }

        //inserir
        public void inserir(IDado d)
        {
            this.raiz = adicionar(this.raiz, d);
        }

        //vereficar antecessor
        private Nodo antecessor(Nodo retirado, Nodo no)
        {
            if (no.dir != null)
            {
                no.dir = antecessor(retirado, no.dir);
                return no;
            }
            else
            {
                retirado = no;
                return no.esq;
            }
        }

        //remover um nodo da arvore
        private Nodo remover(Nodo no, String cpf)
        {
            if (no == null)
            {
                return no;
            }
            else
            {
                if (no.meuDado.getID().equals(cpf))
                {
                    if (no.dir == null){

                        return no.esq;
                    }  
                    else if(no.esq == null){

                        return no.dir;
                    }
                    else
                    {
                        no.esq = antecessor(no, no.esq);
                        return no;
                    }
                }//end if found
                else if (no.meuDado.getID().compareTo(cpf) > 0){

                    no.esq = remover(no.esq, cpf);
                }
                else{

                    no.dir = remover(no.dir, cpf);
                }
                return no;
            }
        }

        //remover
        public void remover(String cpf)
        {
            this.raiz = remover(this.raiz, cpf);
        }

        //pesquisar nodo
        private IDado pesquisar(Nodo no, Object cpf)
        {
            if (no == null)
                return null;
            else
            {
                if (no.meuDado.getID().equals(cpf)){
                    return no.meuDado;
                }
                else if(no.meuDado.getID().toString().compareTo(cpf.toString()) > 0){

                    return pesquisar(no.esq, cpf);
                }
                else{
                    return pesquisar(no.dir, cpf);
                } 
            }
        }

        //pesquisar dado do nodo
        public IDado pesquisar(Object cpf)
        {
            return pesquisar(this.raiz,cpf);
        }

        //imprimir decrescente
        private void imprimirCrescente(Nodo no)
        {
            if (no != null)
            {
                imprimirCrescente(no.esq);
                System.out.println(no.meuDado.getID());
                imprimirCrescente(no.dir);
            }
        }

        public void imprimirCrescente()
        {
            imprimirCrescente(this.raiz);
        }

        //imprimir crescente
        private void imprimirDecrescente(Nodo no)
        {
            if (no != null)
            {
                imprimirDecrescente(no.dir);
                System.out.println(no.meuDado.getID());
                imprimirDecrescente(no.esq);
            }
        }

        public void imprimirDecrescente()
        {
            imprimirDecrescente(this.raiz);
        }

        //contador
        private int contar(Nodo no)
        {
            if (no != null) return 1 + contar(no.esq) + contar(no.dir);

            return 0;
        }

        //quantidade nodos
        public int contaNodos()
        {
            return contar(this.raiz);
        }

        public int max(int a, int b)
        {
            return ((a > b) ? a : b);
        }

        //contar altura
        private int altura(Nodo no)
        {
            if (no == null) return 0;
            else return 1 + max(altura(no.esq), altura(no.dir));

        }

        //altura da arvore
        public int altura()
        {
            return altura(this.raiz);
        }
}