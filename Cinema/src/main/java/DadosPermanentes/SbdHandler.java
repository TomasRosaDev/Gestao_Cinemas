package DadosPermanentes;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class SbdHandler {
    Connection con;
    Statement stmt= null;

    public SbdHandler() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://estga-dev.ua.pt/PTDA_BD_04?", "PTDA_04", "Kiut684h");
            stmt = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void setFilmeDetails(Filme filme){
        ResultSet resultQueryFilmeDetails;
        ResultSet resultQueryFilmeGeneros;
        ResultSet resultQueryFilmeAtores;
        String queryFilmeDetails="SELECT titulo_original, pais, data_estreia, descricao, nome_distribuidor, nome_realizador, duracao FROM filme " +
                                "WHERE titulo_original="+"'"+filme.getTituloOriginal()+"'"+" and ano="+ filme.getAno();
        String queryFilmeGeneros="SELECT nome_genero FROM filme_genero WHERE titulo_original="+"'"+filme.getTituloOriginal()+"'"+" and ano="+ filme.getAno();
        String queryFilmeAtores="SELECT nome_ator FROM filme_ator WHERE titulo_original="+"'"+filme.getTituloOriginal()+"'"+" and ano="+ filme.getAno();
        try {
            if (stmt.execute(queryFilmeDetails)) {
                resultQueryFilmeDetails = stmt.getResultSet();
                filme.setTituloOriginal(resultQueryFilmeDetails.getString("titulo_original"));
                filme.setPais(resultQueryFilmeDetails.getString("pais"));
                filme.setDataEstreia(convertCalendarFromString(resultQueryFilmeDetails.getString("data_estreia")));
                filme.setDescricao(resultQueryFilmeDetails.getString("descricao"));
                filme.setDistribuidor(new Distribuidor(resultQueryFilmeDetails.getString("nome_distribuidor")));
                filme.setRealizador(new Realizador(resultQueryFilmeDetails.getString("nome_realizador")));
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

    public ArrayList<Filme> listaFilmes(Date dia) throws SQLException {
        ArrayList<Filme> listaFilmes = new ArrayList<>();
        ResultSet resultQueryFilmes;//Lista Filmes ativos e corresposndetes ao dia actual ou a um dia passado por parametr
        String queryFilme = "select titulo, ano from filme where data_estreia="+"'"+dia+"'";
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

    public String[] getHorasSessoesDoFilme(Filme filme,Date dia){
        String[] sessoes={};
        ResultSet resultQuerySessoes;
        String querySessaoFilme = "select dataHoraInicio from sessao where titulo_original_filme="+"'"+filme.getTituloOriginal()+"'"+" and ano_filme="+Date.valueOf(String.valueOf(dia.getYear()));//Lista Filmes ativos e corresposndetes ao dia actual ou a um dia passado por parametro
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
        String querySessao = "";
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
                while (resultQueryLugar.next()) {
                    String nome = resultQueryLugar.getString("nome");
                    TipoLugar tipo=TipoLugar.valueOf(resultQueryLugar.getString("tipo"));
                    int linha =resultQueryLugar.getInt("posicao_linha");
                    int coluna =resultQueryLugar.getInt("posicao_coluna");
                    lugares[linha][coluna]=new Lugar(nome,tipo);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lugares;
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

    public Estado getEstadoBilhete(String posicao,Sessao sessao){
        ResultSet resultQueryEstadoBilhete;
        String queryEstadoBilhete = "";
        try {
            if (stmt.execute(queryEstadoBilhete)) {
                resultQueryEstadoBilhete = stmt.getResultSet();
                return Estado.valueOf(resultQueryEstadoBilhete.getString("Estado"));
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
