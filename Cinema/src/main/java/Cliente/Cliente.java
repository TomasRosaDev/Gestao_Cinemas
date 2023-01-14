package Cliente;

import DadosPermanentes.*;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Cliente {
    private SbdHandler sbdHandler;
    private int NumeroCliente;
    private Bilhete [] bilhetes={};
    private Float despeza;
    private Sessao sessao;
    private Filme filme;
    ArrayList<Filme> filmes=new ArrayList<>();
    Date dia;

    public Cliente(SbdHandler sbdHandler){
        this.sbdHandler=sbdHandler;
        //NumeroCliente=sbdHandler.getClienteNumber();
        setDespeza(0.0f);
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

    public void setBilhetes(Bilhete[] bilhetes){
        this.bilhetes=bilhetes;
    }

    public Bilhete[] getBilhetes(){
        return bilhetes;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
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
                throw new RuntimeException(e);
            }
        }
        return filmes;
    }

    public String strDay(Date dia){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(dia)+" 00:00:00";
    }
}
