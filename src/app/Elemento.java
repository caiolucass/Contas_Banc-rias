package app;

class Elemento{

    public IDado meuDado; //dado
    public Elemento prox; // elemento(apontador)

        //construtor
        public Elemento(IDado d)
        {
            this.meuDado = d;
            this.prox = null;
        }
}