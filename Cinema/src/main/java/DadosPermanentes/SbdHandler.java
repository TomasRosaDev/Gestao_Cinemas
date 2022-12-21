package DadosPermanentes;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class SbdHandler {
    Connection con = null;
    Statement stmt = null;

    public SbdHandler() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://estga-dev.ua.pt/PTDA_BD_04?", "PTDA_4", "Kiut684h");
            Statement stmt = con.createStatement();
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
                    String tituloOriginal = resultQueryFilmes.getString("titulo_original");
                    Calendar dataEstreia = convertCalendarFromString(resultQueryFilmes.getString("data_estreia"));
                    String descricao = resultQueryFilmes.getString("descricao");
                    int duracao = Integer.parseInt(resultQueryFilmes.getString("duracao"));
                    String pais = resultQueryFilmes.getString("nomeAtributo");
                    String ano=dataEstreia.get(Calendar.YEAR)+"";
                    Genero[] generos = getGeneros(titulo, ano);
                    Realizador realizador = getRealizador(titulo, ano);
                    Ator[] atores = getAtores(titulo, ano);
                    Distribuidor distribuidor = getDistribuidor(titulo, ano);

                    listaFilmes.add(new Filme(titulo, tituloOriginal, generos, dataEstreia, realizador, atores,
                            distribuidor, pais, duracao, descricao));
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return listaFilmes;
    }

    public Genero[] getGeneros(String titulo, String ano) {
        Genero[] generos={};
        ResultSet resultQueryGeneros;
        String queryGenero = "";//Lista Filmes ativos e corresposndetes ao dia actual ou a um dia passado por parametro
        try {
            if (stmt.execute(queryGenero)) {
                resultQueryGeneros = stmt.getResultSet();
                int i=0;
                while (resultQueryGeneros.next()) {
                    String genero = resultQueryGeneros.getString("genero");
                    generos[i]=Genero.valueOf(genero);
                    i++;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return generos;
    }

    public Realizador getRealizador(String titulo, String ano){
        ResultSet resultQueryRealizadorFilmes;//Lista Filmes ativos e corresposndetes ao dia actual ou a um dia passado por parametr
        String queryRealizadorFilme = "";
        try {
            if (stmt.execute(queryRealizadorFilme)) {
                resultQueryRealizadorFilmes = stmt.getResultSet();
                return new Realizador(resultQueryRealizadorFilmes.getString("nome_realizador"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public Ator[] getAtores(String titulo, String ano) {
        Ator[] atores={};
        ResultSet resultQueryAtores;
        String queryAtor = "";//Lista Filmes ativos e corresposndetes ao dia actual ou a um dia passado por parametro
        try {
            if (stmt.execute(queryAtor)) {
                resultQueryAtores = stmt.getResultSet();
                int i=0;
                while (resultQueryAtores.next()) {
                    atores[i]= new Ator(resultQueryAtores.getString("nome"));
                    i++;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return atores;
    }

    public Distribuidor getDistribuidor(String titulo, String ano){
        ResultSet resultQueryDistribuidorFilmes;//Lista Filmes ativos e corresposndetes ao dia actual ou a um dia passado por parametr
        String queryDistribuidorFilme = "";
        try {
            if (stmt.execute(queryDistribuidorFilme)) {
                resultQueryDistribuidorFilmes = stmt.getResultSet();
                return new Distribuidor(resultQueryDistribuidorFilmes.getString("nome_distribuidor"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
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
                    return new Sessao(filme,sala,dataInicio, dataFim);
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
                return new Sala( Integer.parseInt(nSala) , lugares);
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
                    String posicao = resultQuerySala.getString("numero");

                    i++;
                }
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
