package Cliente;

import DadosPermanentes.*;

public class Cliente {
    private SbdHandler sbdHandler;
    private int NumeroCliente;
    private Bilhete [] bilhetes={};
    private Float despeza;
    private Sessao sessao;
    private Filme filme;

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
}
