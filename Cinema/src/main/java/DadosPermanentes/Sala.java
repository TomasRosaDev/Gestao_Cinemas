package DadosPermanentes;

public class Sala {
    //Campos
    private int numeroSala;
    private Lugar[][] lugares;
    private SbdHandler sbdHandler;


    //Construtor
    public Sala(int numeroSala, SbdHandler sbdHandler){
        setNumeroSala(numeroSala); // Verifica o se o numero é válido.
        if(sbdHandler!=null) {
            this.sbdHandler = sbdHandler;
        }else{
            throw new RuntimeException("Handler invalido");
        }
    }

    //Getters
    public int getNumeroSala() {
        return numeroSala;
    }

    //Setters
    private void setNumeroSala(int numeroSala){
        if(numeroSala>0 && numeroSala<31) {
            this.numeroSala = numeroSala;
        }else{
            throw new RuntimeException("Numero sala invalido");
        }
    }
    public Lugar[][] getLugaresAux() {
        return lugares;
    }

    public Lugar[][] getLugares() {
        if(lugares==null){
            lugares=sbdHandler.getLugares(numeroSala);
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
    public SbdHandler getSbdHandler(){
        return this.sbdHandler;
    }

}
