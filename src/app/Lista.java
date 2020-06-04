package app;

public class Lista {

    public Elemento prim;
    public Elemento ult;
    public int cont;

    public Lista(){
        this.prim = new Elemento(null);
        this.ult = this.prim;
    }

    //inserir elemento na lista
    public void Inserir(Elemento e){
        ult.prox = e;
        ult = ult.prox;
        cont++;
    }

    //retirar elemento da lista
    public IDado Retirar(String identificador){

        Elemento aux, anterior;

        anterior = prim;
        aux = prim.prox;

        while(aux != null){

            if(aux.meuDado.getID().equals(identificador)){

                anterior.prox = aux.prox;

                if(aux == ult){
                    ult = anterior;
                }
                return aux.meuDado;
            }
            else{
                anterior = aux;
                aux = aux.prox;
            }
        }
        return aux.meuDado;
    }   

    //juntar as listas
    public void concatenar(Lista outra)
    {
        if (outra.vazia()) return;

        this.ult.prox = outra.prim.prox;
        outra.ult = this.ult;
        outra = new Lista();
    }
    
    //localizar elemento na lista
    public IDado localizar(String identificador)
    {
        Elemento aux = prim.prox;

        while (aux != null)
        {
            if (aux.meuDado.getID().compareTo( identificador ) == 0 )
            {
                return aux.meuDado;
            }
            aux = aux.prox;
        }
        return aux.meuDado;
    }

    //copar a lista
    public Lista copiar()
    {
        Elemento aux = prim.prox;
        Lista nova = new Lista();
        if (!this.vazia())
            while (aux != null)
            {
                nova.Inserir(aux);
                aux = aux.prox;
            }
        return nova;
    }

    //verificar se esta vazia
    public boolean vazia()
    {
        return (this.ult == this.prim);
    }

}