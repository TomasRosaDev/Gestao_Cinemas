package Cliente;

import DadosPermanentes.*;

import javax.swing.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Cliente {
    private SbdHandler sbdHandler;
    private int NumeroCliente;
    private ArrayList<Bilhete> bilhetes;
    private Float despeza;
    private Sessao sessao;
    private Filme filme;
    private JLabel[] tipoBilhetes;
    private ArrayList<Filme> filmes=new ArrayList<>();
    private Date dia;

    public Cliente(SbdHandler sbdHandler){
        this.sbdHandler=sbdHandler;
        //NumeroCliente=sbdHandler.getClienteNumber();
        setDespeza(0.0f);
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public void setDespeza(Float despeza) {
        this.despeza = despeza;
    }

    public void setSessao(Sessao sessao){
        this.sessao=sessao;
    }

    public Sessao getSessao(){
        return this.sessao;
    }

    public Float getDespeza() {
        return despeza;
    }

    public void setBilhetes(ArrayList<Bilhete> bilhetes){
        this.bilhetes=bilhetes;
    }

    public ArrayList<Bilhete> getBilhetes(){
        return bilhetes;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Filme getFilme() {
        return filme;
    }

    public  void setFilmesArray(ArrayList<Filme> filmes){
        this.filmes=filmes;
    }

    public ArrayList<Filme> getFilmes(Date dia) {
        if(filmes.isEmpty()||!strDay(this.dia).equals(strDay(dia))){
            this.dia=dia;
            try {
                this.filmes=sbdHandler.listaFilmes(dia);
            } catch (SQLException e) {
                throw new RuntimeException("Tipo de exception nao esperado");
            }
        }
        return filmes;
    }

    public void setTiposDeBilhetes(JLabel[] tipoBilhetes){
        this.tipoBilhetes=tipoBilhetes;
        this.despeza=Integer.parseInt(tipoBilhetes[0].getText())*5.6f+Integer.parseInt(tipoBilhetes[1].getText())*2.3f+Integer.parseInt(tipoBilhetes[2].getText())*4f;
    }

    public JLabel[] getTipoDeBilhetes(){
        return tipoBilhetes;
    }

    public String strDay(Date dia){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(dia)+" 00:00:00";
    }
}
