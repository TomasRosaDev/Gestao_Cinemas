package DadosPermanentes;

public class Sala {
    private int numero;

    public Sala(int numero){
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) throws Exception {
        if(numero>0) {
            this.numero = numero;
        } else{
            throw new Exception("O numero da sala tem de ser maior que zero.");
        }
    }

    @Override
    public String toString(){
        String aux = "";
        aux += this.numero;

        return aux;
    }

}
