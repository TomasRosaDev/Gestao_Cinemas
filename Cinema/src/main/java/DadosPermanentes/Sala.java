package DadosPermanentes;

public class Sala {
    //Campos
    private int numeroSala;
    private Lugar[][] lugares;
    private SbdHandler sbdHandler;


    //Construtor
    public Sala(int numeroSala, SbdHandler sbdHandler) {
        setNumeroSala(numeroSala); // Verifica o se o numero é válido.
        this.sbdHandler=sbdHandler;
    }

    //Getters
    public int getNumeroSala() {
        return numeroSala;
    }

    //Setters
    private void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    public Lugar[][] getLugares() {
        if(lugares==null){
            lugares=sbdHandler.getLugares(""+numeroSala);
        }
        return lugares;
    }

    public String toString(){
        String aux="Numero: "+getNumeroSala()+"\nLugares:";
        for (int i=0;i<lugares.length;i++){
            for (int j=0;j<lugares[i].length;j++){
                aux+="\n"+lugares[i][j].toString();
            }
        }

        return aux;
    }

}
