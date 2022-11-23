package DadosPermanentes;

import java.util.Arrays;

public class Sala {
    //Campos
    private int numeroSala;
    private Lugar[][] lugares;
    private int numeroFilas, numeroColunas;

    //Construtor
    public Sala(int numeroSala, int nFilas, int nColunas) {
        setNumeroSala(numeroSala); // Verifica o se o numero é válido.
        setNumeroFilasColunas(nFilas, nColunas); // Verifica se ambos são válidos
        this.lugares = new Lugar[numeroFilas][numeroColunas];

        //gerar todos os lugares automaticamente consoante o numero de filas e colunas

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

    public void setNumeroFilasColunas(int numeroFilas, int numeroColunas) {
        if(numeroFilas >0 && numeroColunas > 0){
            this.numeroFilas = numeroFilas;
            this.numeroColunas = numeroColunas;
        }else{
            throw new IllegalArgumentException("O numero de filas e colunas tem de ser positivo.");
        }

    }

    public static void main(String[] args) {
        Sala sala1 = new Sala(1,10,14);

        //Mostra a sala toda.
        for (int i = sala1.numeroFilas - 1; i >= 0; i--) {
            for(int j = 0; j < sala1.numeroColunas; j++){
                System.out.print("|F:"+ i + " L:" + j + "| ");
                // this.lugares[i][j] = new Lugar(); //Sala, Posicao A1/A2, tipo
            }
            System.out.println("");
        }
        System.out.println("\t\t\t\t\t\tECRÃ");


        //Código para tentar criar corredores... Alterar o código para haver um minimo de filas e colunas
        for (int i = sala1.numeroFilas - 1; i >= 0; i--) {
            for(int j = 0; j < sala1.numeroColunas; j++){

                if( (j==(sala1.numeroColunas/2)/2) || (j==( (sala1.numeroColunas)/2)+2 )){
                    System.out.print("CC");
                    //sala1.lugares[i][j] = new Lugar(); //Sala, Posicao A1/A2 tipo=null

                }else{
                    System.out.print("|"+i+""+j+"|");
                }
            }
            System.out.println("");
        }
        System.out.println("\t\t\t\t\t\tECRÃ");

        //Mostra a sala

        System.out.println("------");
        System.out.println(sala1.numeroSala);

    }

}
