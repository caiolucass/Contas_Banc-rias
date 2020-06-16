package app;

public class Nodo {
    
    public IDado meuDado; //dado do nodo
    public Nodo dir, esq; //posições do nodo
        
    //construtor do nodo
        public Nodo( IDado d )
        {
            meuDado = d;
            dir = null;
            esq = null;
        }
}