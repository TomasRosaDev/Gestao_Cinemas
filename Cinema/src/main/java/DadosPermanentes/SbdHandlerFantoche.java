package DadosPermanentes;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class SbdHandlerFantoche extends SbdHandler{
    public SbdHandlerFantoche(){

    }

    @Override
    public void setFilmeDetails(Filme filme) {

    }

    @Override
    public void setFilmeHomePageDetails(Filme filme) {

    }

    @Override
    public ArrayList<Filme> listaFilmes(Date dia) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Sessao> getHorasSessoesDoFilme(Filme filme, Date dia) {
        return null;
    }

    @Override
    public Sessao getSessaoFilme(Filme filme, String dataHoraInicio, int nSala) {
        return null;
    }

    @Override
    public Sala getSala(int n_sala) {
        return null;
    }

    @Override
    public Bilhete[][] getBilhetes(Sessao sessao) {
        return new Bilhete[0][];
    }

    @Override
    public Lugar[][] getLugares(int n_sala) {
        Lugar[][] lugares = new Lugar[3][3];
        String[] letras= {"A","B","C"};
        for(int i=0;i< lugares.length;i++){
            for(int j=0;j<lugares[0].length;j++){
                lugares[i][j]= new Lugar(letras[i]+(j+1), TipoLugar.Normal);
            }
        }
        return lugares;
    }

    @Override
    public boolean getExisteBilhete(String posicao, Sessao sessao) {
        return false;
    }

    @Override
    public Date getDataHoraFim(Sessao sessao) {
        return null;
    }


    @Override
    public void concluirCompra(ArrayList<Bilhete> bilhetes) {

    }
    @Override
    public String getPrecoBilhete(String posicao, Sessao sessao) {
        return null;
    }

    @Override
    public ArrayList<Sessao> getSessoes() {
        return null;
    }

    @Override
    public ArrayList<Filme> getFilmes() {
        return null;
    }

    @Override
    public ArrayList<Distribuidor> getDistribuidores() {
        return null;
    }

    @Override
    public ArrayList<Realizador> getRealizadores() {
        return null;
    }

    @Override
    public ArrayList<Genero> getGeneros() {
        return null;
    }

    @Override
    public ArrayList<Ator> getAtores() {
        return null;
    }

    @Override
    public void uploadImagem(String fileStr) {

    }

    @Override
    public void closeConection() {

    }

}
