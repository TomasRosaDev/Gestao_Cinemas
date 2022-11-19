/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DadosPermanentes;

/**
 *
 * @author Jorge Vieira
 */
public class mainAuxFilme {
    public static void main(String[] args) {
        Realizador rea=new Realizador("Steven Spilberge");
        Ator raul=new Ator("Raul Silva");
        Ator fabio=new Ator("Fabio Simoes");
        Ator tomas=new Ator("Tomas Rosa");
        Ator[] atores={raul, fabio, tomas};
        Distribuidor distribuidor=new Distribuidor("Cinemas NOZ");
        Genero[] generos={Genero.ACAO,Genero.DANCA,Genero.FAROESTE};
        Filme filme=new Filme("Labregos in da Street", "Labregation in the Street", generos, "12/3/1815", rea, atores, distribuidor, "Portugal", 360, "Labregos a fazerem um trabalho");
        System.out.println(filme.toString());
    }
}
