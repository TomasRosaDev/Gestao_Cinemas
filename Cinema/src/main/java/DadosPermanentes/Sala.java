package DadosPermanentes;

import java.util.Arrays;

public class Sala {
    //Campos
    private int numeroSala;
    private Lugar[][] lugares;


    //Construtor
    public Sala(int numeroSala, Lugar[][] lugares) {
        setNumeroSala(numeroSala); // Verifica o se o numero é válido.
        this.lugares=lugares;
    }

    //Getters
    public int getNumeroSala() {
        return numeroSala;
    }

    //Setters
    private void setNumeroSala(int numeroSala) {
        if(numeroSala>0){
            this.numeroSala = numeroSala;
        }else{
            throw new IllegalArgumentException("O numero de sala tem de ser positivo.");
        }
    }

}
