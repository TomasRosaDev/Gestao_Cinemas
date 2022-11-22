package DadosPermanentes;

import java.util.Arrays;

public class Sala {
    private int numero;
    private Filme[] filmes;
    private Lugar[] lugares;

    public Sala(int numero, Filme[] filmes, Lugar[] lugares){
        this.numero = numero;
        this.filmes = filmes;
        this.lugares = lugares;
    }

    public Sala(int numero){
        this.numero = numero;
        this.filmes = null;
        this.lugares = null;
    }

    public int getNumero() {
        return numero;
    }

    public Filme[] getFilmes() {
        return filmes;
    }

    public Lugar[] getLugares() {
        return lugares;
    }

    public void setNumero(int numero) throws Exception {
        if(numero>0) {
            this.numero = numero;
        } else{
            throw new Exception("O numero da sala tem de ser maior que zero.");
        }
    }

    public void setFilmes(Filme[] filmes) {
        this.filmes = filmes;
    }

    public void setLugares(Lugar[] lugares) {
        this.lugares = lugares;
    }

    @Override
    public String toString(){
        String aux = "";

        aux += this.numero + "\n";
        for(int i=0;i<filmes.length; i++){
            //no ultimo filme nao colocar virgula a seguir
            if(i==filmes.length-1){
                aux+=filmes[i].getTitulo();
            } else{
            aux+=filmes[i].getTitulo()+", ";
        }

        aux+= "\n";
        for(int i=0;i<=lugares.length; i++){
            if(i==lugares.length-1){
                aux+=lugares[i].toString();
            } else{
                aux+=lugares[i].toString() + ", ";
            }
        }

        }

        return aux;
    }

}
