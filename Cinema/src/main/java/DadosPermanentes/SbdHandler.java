package DadosPermanentes;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class SbdHandler {
    Connection con;
    CallableStatement cstmt;

    public SbdHandler() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://estga-dev.ua.pt/PTDA_BD_04?", "PTDA_04", "Kiut684h");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFilmeDetails(Filme filme){
        ResultSet resultQueryFilmeDetails;
        ResultSet resultQueryFilmeAtores;

        try {
            cstmt = con.prepareCall("{call filmeDetalhes(?, ?)}");
            cstmt.setString(1, filme.getTitulo());
            cstmt.setInt(2, filme.getAno());

            if (cstmt.execute()) {
                resultQueryFilmeDetails = cstmt.getResultSet();
                resultQueryFilmeDetails.next();
                filme.setTituloOriginal(resultQueryFilmeDetails.getString("titulo_original"));
                filme.setPais(resultQueryFilmeDetails.getString("pais"));
                filme.setDataEstreia(convertStringtoDatesql(resultQueryFilmeDetails.getString("data_estreia")));
                filme.setDescricao(resultQueryFilmeDetails.getString("descricao"));
                filme.setDistribuidor(new Distribuidor(resultQueryFilmeDetails.getString("nome_distribuidor")));
                filme.setRealizador(new Realizador(resultQueryFilmeDetails.getString("nome_realizador")));
                filme.setDuracao(resultQueryFilmeDetails.getString("duracao"));
            }

            cstmt = con.prepareCall("{call filmeAtores(?, ?)}");
            cstmt.setString(1, filme.getTitulo());
            cstmt.setInt(2, filme.getAno());

            if (cstmt.execute()){
                resultQueryFilmeAtores=cstmt.getResultSet();
                ArrayList<Ator> atores= new ArrayList<>();
                while (resultQueryFilmeAtores.next()) {
                    atores.add(new Ator(resultQueryFilmeAtores.getString("nome_ator")));
                }
                filme.setAtores(atores);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFilmeHomePageDetails(Filme filme){
        ResultSet resultqueryHomePageDetails;
        ResultSet resultQueryFilmeGeneros;

        try {
            cstmt = con.prepareCall("{call paginaInicialDetalhes(?, ?)}");
            cstmt.setString(1, filme.getTitulo());
            cstmt.setInt(2, filme.getAno());

            if(cstmt.execute()){
                resultqueryHomePageDetails=cstmt.getResultSet();
                resultqueryHomePageDetails.next();
                filme.setIdadeMinima(resultqueryHomePageDetails.getString("idade"));
                //filme.setImage(resultqueryHomePageDetails.get...);
            }

            cstmt = con.prepareCall("{call filmeGeneros(?, ?)}");
            cstmt.setString(1, filme.getTitulo());
            cstmt.setInt(2, filme.getAno());

            if (cstmt.execute()){
                resultQueryFilmeGeneros=cstmt.getResultSet();
                ArrayList<Genero> generos= new ArrayList<>();
                while (resultQueryFilmeGeneros.next()) {
                    String genero = resultQueryFilmeGeneros.getString("nome_genero");
                    generos.add(Genero.valueOf(genero));
                }
                filme.setGeneros(generos);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Filme> listaFilmes(Date dia) throws SQLException {
        ArrayList<Filme> listaFilmes = new ArrayList<>();
        ResultSet resultQueryFilmes;

        try {
            cstmt = con.prepareCall("{call filmes(?)}");
            cstmt.setString(1, strDay(dia));

            if (cstmt.execute()) {
                resultQueryFilmes = cstmt.executeQuery();
                while (resultQueryFilmes.next()) {
                    String titulo = resultQueryFilmes.getString("titulo_filme");
                    String ano =  resultQueryFilmes.getString("ano_filme");
                    listaFilmes.add(new Filme(titulo, ano,this));
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaFilmes;
    }

    public ArrayList<Sessao> getHorasSessoesDoFilme(Filme filme,Date dia){
        ArrayList <Sessao> sessoes=new ArrayList<Sessao>();
        ResultSet resultQuerySessoes;

        try {
            cstmt = con.prepareCall("{call filmeSessoes(?, ?, ?)}");
            cstmt.setString(1, filme.getTitulo());
            cstmt.setInt(2, filme.getAno());
            cstmt.setString(3, strDay(dia));

            if (cstmt.execute()) {
                resultQuerySessoes = cstmt.getResultSet();
                while (resultQuerySessoes.next()) {
                    sessoes.add(new Sessao(filme,new Sala(resultQuerySessoes.getInt("numero_sala"),this),calendartoCalendarFromDateSqlString(resultQuerySessoes.getString("dataHoraInicio")),this));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sessoes;
    }

    public Sessao getSessaoFilme(Filme filme, String dataHoraInicio, int nSala) {
        ResultSet resultQuerySessao;

        try {
            cstmt = con.prepareCall("{call sessao(?, ?, ?, ?)}");
            cstmt.setString(1, filme.getTitulo());
            cstmt.setInt(2, filme.getAno());
            cstmt.setInt(3, nSala);
            cstmt.setString(4, dataHoraInicio);

            if (cstmt.execute()) {
                resultQuerySessao = cstmt.getResultSet();
                while (resultQuerySessao.next()) {
                    Calendar dataInicio=convertCalendarFromString(resultQuerySessao.getString("dataHoraInicio"));//Erro ao chamar o metod convertCalenderFromString porque esse metodo nao formata horas
                    Sala sala=getSala(resultQuerySessao.getInt("numero_sala"));
                    //Calendar dataFim=convertCalendarFromString(resultQuerySessao.getString("dataHoraFim"));
                    return new Sessao(filme,sala,dataInicio, this);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Sala getSala(int n_sala) {
        ResultSet resultQuerySala;

        try {
            cstmt = con.prepareCall("{call numeroSala(?)}");
            cstmt.setInt(1, n_sala);

            if (cstmt.execute()) {
                resultQuerySala = cstmt.getResultSet();
                resultQuerySala.next();
                int numSala = resultQuerySala.getInt(1);
                return new Sala(numSala,this);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Lugar[][] getLugares(int n_sala) {
        ResultSet resultQueryLugar=null;
        ResultSet resultQueryNLinhas=null;
        ResultSet resultQueryNColunas=null;
        Lugar[][] lugares= null;

        try {
                cstmt = con.prepareCall("{call nLinhas(?)}");
                cstmt.setInt(1, n_sala);

                int nLinhas = 0;
                int nColunas = 0;
                if(cstmt.execute()) {
                    resultQueryNLinhas = cstmt.getResultSet();
                    while (resultQueryNLinhas.next()) {
                        nLinhas = resultQueryNLinhas.getInt(1);
                    }
                }

                cstmt = con.prepareCall("{call nColunas(?)}");
                cstmt.setInt(1, n_sala);
                if(cstmt.execute()){
                    resultQueryNColunas=cstmt.getResultSet();
                    while (resultQueryNColunas.next()) {
                        nColunas= resultQueryNColunas.getInt(1);
                    }
                }
                lugares = new Lugar[nLinhas][nColunas];

            cstmt = con.prepareCall("{call lugares(?)}");
            cstmt.setInt(1, n_sala);

            if (cstmt.execute()) {
                resultQueryLugar = cstmt.getResultSet();
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

    public boolean getExisteBilhete(String posicao,Sessao sessao){
        ResultSet resultQueryExisteBilhete;

        try {
            cstmt = con.prepareCall("{call existeBilhete(?, ?, ?, ?)}");
            cstmt.setInt(1, sessao.getSala().getNumeroSala());
            cstmt.setString(2, posicao);
            cstmt.setString(3, sessao.getFilme().getTitulo());
            cstmt.setInt(4, sessao.getFilme().getAno());

            if (cstmt.execute()) {
                resultQueryExisteBilhete = cstmt.getResultSet();
                resultQueryExisteBilhete.next();

                if(resultQueryExisteBilhete.getInt("ExisteBilhete")==1){ //existe um registo logo este lugar esta ocupado
                    return true;
                } else{
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    public Calendar getDataHoraFim(String titulo,int ano,int nSala,Calendar dataHoraInicio){
        ResultSet resultQueryDaraHoraFimSessao;

        try {
            cstmt = con.prepareCall("{call sessaoDataHoraFim(?, ?, ?)}");
            cstmt.setString(1, titulo);
            cstmt.setInt(2, ano);
            cstmt.setInt(3, nSala);
            cstmt.setString(4, String.valueOf(dataHoraInicio));

            if (cstmt.execute()) {
                resultQueryDaraHoraFimSessao = cstmt.getResultSet();
                return convertCalendarFromString(resultQueryDaraHoraFimSessao.getString("dataHoraFim"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String getPrecoBilhete(String posicao,Sessao sessao){
        ResultSet resultQueryPrecoBilhete;

        try {
            cstmt = con.prepareCall("{call precoBilhete(?, ?, ?, ?)}");
            cstmt.setInt(1, sessao.getSala().getNumeroSala());
            cstmt.setString(2, posicao);
            cstmt.setString(3, sessao.getFilme().getTitulo());
            cstmt.setInt(4, sessao.getFilme().getAno());

            if (cstmt.execute()) {
                resultQueryPrecoBilhete = cstmt.getResultSet();
                return resultQueryPrecoBilhete.getString("preco");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    
    public Calendar convertCalendarFromString(String date){
        String[] date1=date.split("-");
        return new GregorianCalendar(Integer.parseInt(date1[0]),Integer.parseInt(date1[1]),Integer.parseInt(date1[2]));
    }

    public Calendar calendartoCalendarFromDateSqlString(String date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar retDay=Calendar.getInstance();
        try {
            retDay.setTime(sdf.parse(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return retDay;
    }

    public void closeConection(){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String strDay(Date dia){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return dateFormat.format(dia);
    }

    public  String strNextDay(Date dia){
        Date nextDay=new Date(dia.getTime()+(1000*60*60*24*1));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String str=dateFormat.format(nextDay)+" 00:00:00";
        return str;
    }

    public Date convertStringtoDatesql(String datestr){
        return Date.valueOf(datestr);
    }
}
