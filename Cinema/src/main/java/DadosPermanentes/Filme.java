package DadosPermanentes;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;

/**
 *
 * @author Jorge Vieira
 */
public class Filme {
    private String titulo;
    private int ano;
    private int idadeMinima;
    private BufferedImage imagem;
    private SbdHandler sbdHandler;
    private String tituloOriginal;
    private ArrayList<Genero> generos;
    private Date dataEstreia;
    private Realizador realizador;
    private ArrayList<Ator> atores;
    private Distribuidor distribuidor;
    private String pais;
    private int duracao;
    private String descricao;

    public Filme(String titulo,String ano,SbdHandler sbdHandler) {
        setTitulo(titulo);
        setAno(ano);
        if(sbdHandler!=null) {
            this.sbdHandler = sbdHandler;
        }else{
            throw new RuntimeException("Handler invalido");
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAno() {
        return ano;
    }

    public String getAnoString() {
        return getAno()+"";
    }

    @Override
    public String toString() {
        String aux="Titulo: "+titulo+"("+ano+")//Genero: "+ generos.get(0).toString();
        for (int i = 1; i< generos.size(); i++){
            aux+=", "+ generos.get(i).toString();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        aux+="//Titulo Original: "+getTituloOriginal()+"//Data Estreia: "+sdf.format(dataEstreia)+"//Realizador: "+realizador.getNome()+"//Atores: "+atores.get(0).getNome();
        for (int i=1;i<atores.size();i++){
            aux+=", "+atores.get(i).getNome();
        }
        aux+="//Distribuidor: "+distribuidor.getNome()+"//Pais: "+pais+"//Duracao: "+duracao+"m"+"//Descricao: "+descricao;
        return aux;
    }

    public void setIdadeMinima(int idadeMinima) {
        int idade = idadeMinima;
        if (idade >= 0 && idade <= 18) {
            this.idadeMinima = idadeMinima;
        }else{
            throw new RuntimeException("Idade minima invalida");
        }
    }
    public void setImagem(BufferedImage imagem){this.imagem=imagem;}

    public void setTituloOriginal(String tituloOriginal) {
        if (tituloOriginal == null) {
            this.tituloOriginal = getTitulo();
        } else if (tituloOriginal.length() <= 100) {
            this.tituloOriginal = tituloOriginal;
        } else {
            throw new RuntimeException("Titulo original invalido");
        }
    }

    public void setGeneros(ArrayList<Genero> generos) {
        if (generos.size() == 0) {
            throw new RuntimeException("Genero invalido");
        }
        this.generos = new ArrayList<>();
        for (Genero genero : generos) {
            if (!this.generos.contains(genero) && genero != null) {
                this.generos.add(genero);
            }
        }
        if (this.generos.size() == 0) {
            this.generos = null;
            throw new RuntimeException("Genero invalido");
        }

    }

    public void setDataEstreia(Date dataEstreia) {

        if (dataEstreia.getYear() + 1900 == getAno()) {

            this.dataEstreia = dataEstreia;
        } else {
            throw new RuntimeException("Ano invalido");
        }

    }

    public void setRealizador(Realizador realizador) {
        this.realizador = realizador;
    }

    public void setAtores(ArrayList<Ator> atores) {
        this.atores = atores;
    }

    public SbdHandler getSbdHandler() {
        return sbdHandler;
    }

    public void setAno(String ano) {
        int anoPara = Integer.parseInt(ano);
        int anoatual = Year.now().getValue();
        if (anoPara <= anoatual + 1 && anoPara >= 1888) {
            this.ano = anoPara;
        } else {
            throw new RuntimeException("Ano invalido");
        }
    }

    public void setTitulo(String titulo) {
        if (titulo.length() <= 100) {
            this.titulo = titulo;
        } else {
            throw new RuntimeException("Titulo invalido");
        }
    }

    public void setDistribuidor(Distribuidor distribuidor) {
        this.distribuidor = distribuidor;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getIdadeMinima() {
        if (idadeMinima == 0) {
            sbdHandler.setFilmeHomePageDetails(this);
        }
        return idadeMinima;
    }

    public String getTituloOriginal() {
        if (tituloOriginal == null) {
            sbdHandler.setFilmeDetails(this);
        }
        return tituloOriginal;
    }

    public ArrayList<Genero> getGeneros() {
        if (generos == null) {
            sbdHandler.setFilmeHomePageDetails(this);
        }
        return generos;
    }

    public String getGenerosStr() {
        String genStr = "";
        for (Genero genero : getGeneros()) {
            genStr += genero.toString() + ", ";
        }
        genStr = genStr.substring(0, genStr.length() - 2);
        return genStr;
    }

    public Date getDataEstreia() {
        if (dataEstreia == null) {
            sbdHandler.setFilmeDetails(this);
        }
        return dataEstreia;
    }

    public String getRealizador() {
        if (realizador == null) {
            sbdHandler.setFilmeDetails(this);
        }
        return realizador.getNome();
    }

    public String getAtores() {
        String ator = "";
        if (this.atores == null) {
            sbdHandler.setFilmeDetails(this);
        }
        for (int i = 1; i < atores.size(); i++) {
            ator += ", " + atores.get(i).getNome();
        }
        return ator;
    }

    public String getDistribuidor() {
        if (distribuidor == null) {
            sbdHandler.setFilmeDetails(this);
        }
        return distribuidor.getNome();
    }

    public String getPais() {
        if (pais == null) {
            sbdHandler.setFilmeDetails(this);
        }
        return pais;
    }

    public int getDuracao() {
        if(duracao==0){
            sbdHandler.setFilmeDetails(this);
        }
        return duracao;
    }

    public String getDescricao() {
        if (descricao == null) {
            sbdHandler.setFilmeDetails(this);
        }
        return descricao;
    }

    public BufferedImage getImagem(){
        if(imagem==null){
            sbdHandler.setFilmeHomePageDetails(this);
        }
        return imagem;
    }
    public BufferedImage getImagemAux(){
        return imagem;
    }

    public String getTituloOriginalAux() {
        return tituloOriginal;
    }

    public Date getDataEstreiaAux() {
        return dataEstreia;
    }
}
