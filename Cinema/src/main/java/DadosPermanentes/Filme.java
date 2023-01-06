package DadosPermanentes;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author Jorge Vieira
 */
public class Filme {
    private String titulo;
    private int ano;

    private SbdHandler sbdHandler;
    private String tituloOriginal;
    private ArrayList<Genero> generos;
    private Calendar dataEstreia;
    private Realizador realizador;
    private ArrayList<Ator> atores;
    private Distribuidor distribuidor;
    private String pais;
    private String duracao;
    private String descricao;

    public Filme(String titulo,String ano,SbdHandler sbdHandler) {
        this.titulo = titulo;
        this.ano = Integer.parseInt(ano);
        this.sbdHandler=sbdHandler;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAno() {
        return ano;
    }

    public String getAnoString() {
        return Integer.toString(ano);
    }

    @Override
    public String toString() {
        String aux="Genero: "+ generos.get(0).toString();
        for (int i = 1; i< generos.size(); i++){
            aux+=", "+ generos.get(i).toString();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");
        aux+="\nTitulo: "+titulo+"//\nTitulo Original: "+tituloOriginal+"//\nData Estreia: "+sdf.format(dataEstreia.getTime())+"//\nRealizador: "+realizador.getNome()+"//\nAtores "+atores.get(0).getNome();
        for (int i=1;i<atores.size();i++){
            aux+=", "+atores.get(i).getNome();
        }
        aux+="//\nDistribuidor: "+distribuidor.getNome()+"//\nPais: "+pais+"//\nAno: "+ano+"//\nDuracao: "+duracao+"//\nDescricao: "+descricao;
        return aux;
    }

    public void setTituloOriginal(String tituloOriginal) {
        this.tituloOriginal = tituloOriginal;
    }

    public void setGeneros(ArrayList<Genero> generos) {
        this.generos = generos;
    }

    public void setDataEstreia(Calendar dataEstreia) {
        this.dataEstreia = dataEstreia;
    }

    public void setRealizador(Realizador realizador) {
        this.realizador = realizador;
    }

    public void setAtores(ArrayList<Ator> atores) {
        this.atores = atores;
    }

    public void setDistribuidor(Distribuidor distribuidor) {
        this.distribuidor = distribuidor;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTituloOriginal() {
        if(tituloOriginal==null){
            sbdHandler.setFilmeDetails(this);
        }
        return tituloOriginal;
    }

    public ArrayList<Genero> getGeneros() {
        if(generos==null){
            sbdHandler.setFilmeDetails(this);
        }
        return generos;
    }

    public Calendar getDataEstreia() {
        if(dataEstreia==null){
            sbdHandler.setFilmeDetails(this);
        }
        return dataEstreia;
    }

    public String getRealizador() {
        if(realizador==null){
            sbdHandler.setFilmeDetails(this);
        }
        return realizador.getNome();
    }

    public String getAtores() {
        String ator = "";
        if(this.atores==null){
            sbdHandler.setFilmeDetails(this);
        }
        for (int i=1;i<atores.size();i++){
            ator+=", "+atores.get(i).getNome();
        }
        return ator;
    }

    public String getDistribuidor() {
        if(distribuidor==null){
            sbdHandler.setFilmeDetails(this);
        }
        return distribuidor.getNome();
    }

    public String getPais() {
        if(pais==null){
            sbdHandler.setFilmeDetails(this);
        }
        return pais;
    }

    public String getDuracao() {
        if(duracao==null){
            sbdHandler.setFilmeDetails(this);
        }
        return duracao;
    }

    public String getDescricao() {
        if(descricao==null){
            sbdHandler.setFilmeDetails(this);
        }
        return descricao;
    }
}
