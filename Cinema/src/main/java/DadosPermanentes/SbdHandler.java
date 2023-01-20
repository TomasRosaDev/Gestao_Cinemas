package DadosPermanentes;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class SbdHandler {
    public SbdHandler(){}
    public abstract void setFilmeDetails(Filme filme);
    public abstract void setFilmeHomePageDetails(Filme filme);
    public abstract ArrayList<Filme> listaFilmes(Date dia) throws SQLException;
    public abstract ArrayList<Sessao> getHorasSessoesDoFilme(Filme filme,Date dia);
    public abstract Sessao getSessaoFilme(Filme filme, String dataHoraInicio, int nSala);
    public abstract Sala getSala(int n_sala);
    public abstract Bilhete[][] getBilhetes(Sessao sessao);
    public abstract Lugar[][] getLugares(int n_sala);
    public abstract boolean getExisteBilhete(String posicao,Sessao sessao);
    public abstract Date getDataHoraFim(Sessao sessao);
    public abstract void concluirCompra(ArrayList<Bilhete> bilhetes);
    public abstract String getPrecoBilhete(String posicao,Sessao sessao);
    public abstract ArrayList<Sessao> getSessoes();
    public abstract ArrayList<Filme> getFilmes();
    public abstract ArrayList<Distribuidor> getDistribuidores();
    public abstract ArrayList<Realizador> getRealizadores();
    public abstract ArrayList<Genero> getGeneros();
    public abstract ArrayList<Ator> getAtores();
    public abstract void uploadImagem(String fileStr);
    public abstract void closeConection();
}
