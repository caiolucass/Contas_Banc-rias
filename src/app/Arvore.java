package app;

public class Arvore {

    private Nodo raiz;

    public Arvore() {
        raiz = null;
    }

    // verificar se a arvore esta vazia
    public Boolean vazia() {
        return (this.raiz == null);
    }

    // Inserir Dado no nodo
    public void inserir(IDado novo) {
        this.raiz = adicionar(this.raiz, novo);
    }

    // Adcionar Nodo na árvore
    private Nodo adicionar(Nodo no, IDado novo) {
        if (no == null) {
            no = new Nodo(novo);
        } else if (no.meuDado.getID().toString().compareTo(novo.getID().toString()) < 0) {
            no.esq = adicionar(no.esq, novo);
        } else if (no.meuDado.getID().toString().compareTo(novo.getID().toString()) > 0) {
            no.dir = adicionar(no.dir, novo);
        } else {
            System.out.println("Dado com informação de " + novo + " já foi inserido anteriormente.");
        }
        return no;
    }

    // Remover Dado
    public void remover(String cpf) {
        this.raiz = removerArvore(this.raiz, cpf);
    }

    // Remover Nodo da árvore
    private Nodo removerArvore(Nodo no, String cpf) {
        if (no == null) {
            return null;
        } else {
            if (no.meuDado.getID().equals(cpf)) {
                if (no.dir == null) {

                    return no.esq;
                } else if (no.esq == null) {

                    return no.dir;
                } else {
                    no.esq = antecessor(no, no.esq);
                    return no;
                }
            } else if (no.meuDado.getID().toString().compareTo(cpf.toString()) > 0) {

                no.esq = removerArvore(no.esq, cpf);
            } else {

                no.dir = removerArvore(no.dir, cpf);
            }
            return no;
        }
    }

    // Verificar Nodo antecessor
    private Nodo antecessor(Nodo retirado, Nodo no) {
        if (no.dir != null) {
            no.dir = antecessor(retirado, no.dir);
            return no;
        } else {
            retirado = no;
            return no.esq;
        }
    }

    // Pesquisar Nodo na árvore
    private IDado pesquisar(Nodo no, String cpf) {
        if (no == null)
            return null;
        else {
            if (no.meuDado.getID().equals(cpf)) {
                return no.meuDado;
            } else if (no.meuDado.getID().toString().compareTo(cpf.toString()) > 0) {

                return pesquisar(no.dir, cpf);
            } else {
                return pesquisar(no.esq, cpf);
            }
        }
    }

    // Pesquisar Dado no Nodo
    public IDado pesquisar(String cpf) {
        return pesquisar(this.raiz, cpf);
    }

    // imprimir decrescente
    private void imprimirArvoreCrescente(Nodo no) {
        if (no != null) {
            imprimirArvoreCrescente(no.esq);
            System.out.println(no.meuDado.getID());
            imprimirArvoreCrescente(no.dir);
        }
    }

    public void imprimirCrescente() {
        imprimirArvoreCrescente(this.raiz);
    }

    // imprimir crescente
    private void imprimirArvoreDecrescente(Nodo no) {
        if (no != null) {
            imprimirArvoreDecrescente(no.dir);
            System.out.println(no.meuDado.getID());
            imprimirArvoreDecrescente(no.esq);
        }
    }

    public void imprimirDecrescente() {
        imprimirArvoreDecrescente(this.raiz);
    }

    // contador
    private int contar(Nodo no) {
        if (no != null)
            return 1 + contar(no.esq) + contar(no.dir);

        return 0;
    }

    // quantidade nodos
    public int contaNodos() {
        return contar(this.raiz);
    }

    public int max(int a, int b) {
        return ((a > b) ? a : b);
    }

    // contar altura
    private int alturaArvore(Nodo no) {
        if (no == null)
            return 0;
        else
            return 1 + max(alturaArvore(no.esq), alturaArvore(no.dir));

    }

    // altura da arvore
    public int altura() {
        return alturaArvore(this.raiz);
    }
}