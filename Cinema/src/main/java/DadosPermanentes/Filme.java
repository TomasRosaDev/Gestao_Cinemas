package DadosPermanentes;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author Jorge Vieira
 */
public class Filme {
    private String titulo;
    private String tituloOriginal;
    private Genero[] generos;
    private Calendar dataEstreia;
    private Realizador realizador;
    private Ator[] atores;
    private Distribuidor distribuidor;
    private String pais;
    private int ano;
    private int duracao;
    private String descricao;

    public Filme(String titulo, String tituloOriginal, Genero[] generos, String dataEstreia, Realizador realizador, Ator[] atores, Distribuidor distribuidor, String pais, int duracao, String descricao) {
        this.titulo = titulo;
        this.tituloOriginal = tituloOriginal;
        this.generos=generos;
        String[] datas=dataEstreia.split("/");
        int dia=Integer.parseInt(datas[0]);
        int mes=Integer.parseInt(datas[1]);
        int ano=Integer.parseInt(datas[2]);
        this.dataEstreia =new GregorianCalendar(ano, mes, dia);
        this.realizador = realizador;
        this.atores = atores;
        this.distribuidor = distribuidor;
        this.pais = pais;
        this.ano = ano;
        this.duracao = duracao;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        String aux=generos[0].toString();
        for (int i=1;i<generos.length;i++){
            aux+=", "+generos[i].toString();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");
        aux+="\n"+titulo+"\n"+tituloOriginal+"\n"+sdf.format(dataEstreia.getTime())+"\n"+realizador.getNome()+"\n"+atores[0].getNome();
        for (int i=1;i<atores.length;i++){
            aux+=", "+atores[i].getNome();
        }
        aux+="\n"+distribuidor.getNome()+"\n"+pais+"\n"+ano+"\n"+duracao+"\n"+descricao;
        return aux;
    }
    
    
    
}
