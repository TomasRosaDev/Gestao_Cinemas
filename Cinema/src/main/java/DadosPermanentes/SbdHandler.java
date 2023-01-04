package DadosPermanentes;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SbdHandler {
    Connection con;
    Statement stmt = null;

    public SbdHandler() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://estga-dev.ua.pt/PTDA_BD_04?", "PTDA_04", "Kiut684h");
            Statement stmt = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void setFilmeDetails(Filme filme){
        ResultSet resultQueryFilmeDetails;
        ResultSet resultQueryFilmeGeneros;
        ResultSet resultQueryFilmeAtores;
        String queryFilmeDetails="";
        String queryFilmeGeneros="";
        String queryFilmeAtores="";
        try {
            if (stmt.execute(queryFilmeDetails)) {
                resultQueryFilmeDetails = stmt.getResultSet();
                filme.setTituloOriginal(resultQueryFilmeDetails.getString("tituloOriginal"));
                filme.setPais(resultQueryFilmeDetails.getString("pais"));
                filme.setDataEstreia(convertCalendarFromString(resultQueryFilmeDetails.getString("dataEstreia")));
                filme.setDescricao(resultQueryFilmeDetails.getString("descricao"));
                filme.setDistribuidor(new Distribuidor(resultQueryFilmeDetails.getString("distribuidor")));
                filme.setRealizador(new Realizador(resultQueryFilmeDetails.getString("realizador")));
                filme.setDuracao(resultQueryFilmeDetails.getString("duracao"));
            }
            if (stmt.execute(queryFilmeGeneros)){
                resultQueryFilmeGeneros=stmt.getResultSet();
                Genero generos[]={};
                int i=0;
                while (resultQueryFilmeGeneros.next()) {
                    String genero = resultQueryFilmeGeneros.getString("genero");
                    generos[i]=Genero.valueOf(genero);
                    i++;
                }
                filme.setGeneros(generos);
            }
            if (stmt.execute(queryFilmeAtores)){
                resultQueryFilmeAtores=stmt.getResultSet();
                Ator atores[]={};
                int i=0;
                while (resultQueryFilmeAtores.next()) {
                    atores[i]= new Ator(resultQueryFilmeAtores.getString("nome"));
                    i++;
                }
                filme.setAtores(atores);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Filme> listaFilmes(Calendar dia) {
        ArrayList<Filme> listaFilmes = new ArrayList<>();
        ResultSet resultQueryFilmes;//Lista Filmes ativos e corresposndetes ao dia actual ou a um dia passado por parametr
        String queryFilme = "";
        try {
            if (stmt.execute(queryFilme)) {
                resultQueryFilmes = stmt.getResultSet();
                while (resultQueryFilmes.next()) {
                    String titulo = resultQueryFilmes.getString("titulo");
                    String ano =  resultQueryFilmes.getString("ano");
                    listaFilmes.add(new Filme(titulo, ano,this));
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return listaFilmes;
    }

    public String[] getHorasSessoesDoFilme(Filme filme,Calendar dia){
        String[] sessoes={};
        ResultSet resultQuerySessoes;
        String querySessaoFilme = "";//Lista Filmes ativos e corresposndetes ao dia actual ou a um dia passado por parametro
        try {
            if (stmt.execute(querySessaoFilme)) {
                resultQuerySessoes = stmt.getResultSet();
                int i=0;
                while (resultQuerySessoes.next()) {
                    sessoes[i]= resultQuerySessoes.getString("dataHoraInicio");
                    i++;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sessoes;
    }

    public Sessao getSessaoFilme(Filme filme, String dataHoraInicio) {
        ResultSet resultQuerySessao;
        String querySessao = "";//Lista Filmes ativos e corresposndetes ao dia actual ou a um dia passado por parametro
        try {
            if (stmt.execute(querySessao)) {
                resultQuerySessao = stmt.getResultSet();
                while (resultQuerySessao.next()) {
                    Sala sala=getSala(resultQuerySessao.getString("n_sala"));
                    Calendar dataInicio=convertCalendarFromString(resultQuerySessao.getString("data_inicio"));
                    Calendar dataFim=convertCalendarFromString(resultQuerySessao.getString("data_fim"));
                    return new Sessao(filme,sala,dataInicio, this);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Sala getSala(String n_sala) {
        ResultSet resultQuerySala = null;
        String queryAtor = "";
        try {
            if (stmt.execute(queryAtor)) {
                String nSala = resultQuerySala.getString("numero");
                Lugar[][] lugares = getLugares(n_sala);
                return new Sala( Integer.parseInt(nSala),this);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public Lugar[][] getLugares(String n_sala) {
        Lugar[][] lugares = {};
        ResultSet resultQueryLugar=null;
        String queryLugar = "";
        try {
            if (stmt.execute(queryLugar)) {
                int i=0;
                while (resultQueryLugar.next()) {
                    String posicao = resultQueryLugar.getString("numero");

                    i++;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Calendar getDataHoraFim(String titulo,String ano,String nSala,Calendar dataHoraInicio){
        ResultSet resultQueryDaraHoraFimSessao;
        String queryDaraHoraFimSessao = "";
        try {
            if (stmt.execute(queryDaraHoraFimSessao)) {
                resultQueryDaraHoraFimSessao = stmt.getResultSet();
                return convertCalendarFromString(resultQueryDaraHoraFimSessao.getString("DaraHoraFim"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Bilhete.Estado getEstadoBilhete(String posicao,Sessao sessao){
        ResultSet resultQueryEstadoBilhete;
        String queryEstadoBilhete = "";
        try {
            if (stmt.execute(queryEstadoBilhete)) {
                resultQueryEstadoBilhete = stmt.getResultSet();
                return Bilhete.Estado.valueOf(resultQueryEstadoBilhete.getString("Estado"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String getPrecoBilhete(String posicao,Sessao sessao){
        ResultSet resultQueryPrecoBilhete;
        String queryPrecoBilhete = "";
        try {
            if (stmt.execute(queryPrecoBilhete)) {
                resultQueryPrecoBilhete = stmt.getResultSet();
                return resultQueryPrecoBilhete.getString("Preco");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    
    public Calendar convertCalendarFromString(String date){
        String[] date1=date.split("/");
        return new GregorianCalendar(Integer.parseInt(date1[0]),Integer.parseInt(date1[1]),Integer.parseInt(date1[2]));
    }
}
