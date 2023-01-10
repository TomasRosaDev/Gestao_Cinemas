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
                                "WHERE titulo="+"'"+filme.getTitulo()+"'"+" and ano="+ filme.getAno();
        try {
            if (stmt.execute(queryFilmeDetails)) {
                resultQueryFilmeDetails=stmt.getResultSet();
                resultQueryFilmeDetails.next();
                filme.setTituloOriginal(resultQueryFilmeDetails.getString("titulo_original"));
                filme.setPais(resultQueryFilmeDetails.getString("pais"));
                filme.setDataEstreia(convertCalendarFromString(resultQueryFilmeDetails.getString("data_estreia")));
                filme.setDescricao(resultQueryFilmeDetails.getString("descricao"));
                filme.setDistribuidor(new Distribuidor(resultQueryFilmeDetails.getString("nome_distribuidor")));
                filme.setRealizador(new Realizador(resultQueryFilmeDetails.getString("nome_realizador")));
                filme.setDuracao(resultQueryFilmeDetails.getString("duracao"));
            }
            String queryFilmeGeneros="SELECT nome_genero FROM filme_genero WHERE titulo_filme='"+filme.getTitulo()+"' and ano_filme="+ filme.getAno();
            if (stmt.execute(queryFilmeGeneros)){
                resultQueryFilmeGeneros=stmt.getResultSet();
               // Genero generos[]=new Genero[1];//Nao da para fazer
                ArrayList<Genero> generos= new ArrayList<>();
                //int i=0;
                while (resultQueryFilmeGeneros.next()) {
                    String genero = resultQueryFilmeGeneros.getString("nome_genero");
                    //generos[0]= Genero.valueOf(genero);
                    generos.add(Genero.valueOf(genero));
                  //  i++;
                }
                filme.setGeneros(generos);
            }
            String queryFilmeAtores="SELECT nome_ator FROM filme_ator WHERE titulo_filme='"+filme.getTitulo()+"' and ano_filme="+ filme.getAno();

            if (stmt.execute(queryFilmeAtores)){

                resultQueryFilmeAtores=stmt.getResultSet();
               // Ator atores[]={};//Não da para fazer
                ArrayList<Ator> atores= new ArrayList<>();
               // int i=0;
                while (resultQueryFilmeAtores.next()) {
                    atores.add(new Ator(resultQueryFilmeAtores.getString("nome_ator")));
                    //i++;
                }
                filme.setAtores(atores);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFilmeHomePageDetails(Filme filme){
        ResultSet resultqueryHomePageDetails;
        String queryHomePageDetails="select idade from filme where titulo='"+filme.getTitulo()+"' and ano="+filme.getAnoString();
        try {
            if(stmt.execute(queryHomePageDetails)){
                resultqueryHomePageDetails=stmt.getResultSet();
                resultqueryHomePageDetails.next();
                filme.setIdadeMinima(resultqueryHomePageDetails.getString("idade"));
                //filme.setImage(resultqueryHomePageDetails.get...);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Filme> listaFilmes(Date dia) throws SQLException {
        ArrayList<Filme> listaFilmes = new ArrayList<>();
        ResultSet resultQueryFilmes;
        String queryFilme = "select distinct titulo_filme, ano_filme from sessao where dataHoraInicio>='"+strDay(dia)+"' and dataHoraInicio<'"+strNextDay(dia)+"'";
        try {
            if (stmt.execute(queryFilme)) {
                resultQueryFilmes = stmt.getResultSet();
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
        String querySessaoFilme = "select dataHoraInicio, numero_sala from sessao where titulo_filme='"+filme.getTitulo()+"' and ano_filme='"+filme.getAnoString()+"' and dataHoraInicio>='"+strDay(dia)+"' and dataHoraInicio<'"+strNextDay(dia)+"'";
        try {
            if (stmt.execute(querySessaoFilme)) {
                resultQuerySessoes = stmt.getResultSet();
                int i=0;
                while (resultQuerySessoes.next()) {
                    sessoes.add(new Sessao(filme,new Sala(Integer.parseInt(resultQuerySessoes.getString("numero_sala")),this),calendartoCalendarFromDateSqlString(resultQuerySessoes.getString("dataHoraInicio")),this));
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
        String querySessao = "SELECT numero_sala, dataHoraInicio, dataHoraFim  FROM sessao WHERE titulo_filme='"+filme.getTitulo()+"' and ano_filme="+filme.getAno()+" and dataHoraInicio='"+dataHoraInicio+"'";
        try {
            if (stmt.execute(querySessao)) {
                resultQuerySessao = stmt.getResultSet();
                while (resultQuerySessao.next()) {
                    Calendar dataInicio=convertCalendarFromString(resultQuerySessao.getString("dataHoraInicio"));//Erro ao chamar o metod convertCalenderFromString porque esse metodo nao formata horas
                    Sala sala=getSala(resultQuerySessao.getString("numero_sala"));
                    //Calendar dataFim=convertCalendarFromString(resultQuerySessao.getString("dataHoraFim"));
                    return new Sessao(filme,sala,dataInicio, this);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Sala getSala(String n_sala) {
        ResultSet resultQuerySala;
        String querySala = "SELECT numero FROM sala where numero="+n_sala;
        try {
            if (stmt.execute(querySala)) {
                resultQuerySala = stmt.getResultSet();
                resultQuerySala.next();
                String numSala = resultQuerySala.getString(1);
                return new Sala( Integer.parseInt(numSala),this);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Lugar[][] getLugares(String n_sala) {
        ResultSet resultQueryLugar=null;
        ResultSet resultQueryNLinhas=null;
        ResultSet resultQueryNColunas=null;
        Lugar[][] lugares= null;
        String queryLugar = "SELECT nome, tipo, posicao_linha, posicao_coluna FROM lugar WHERE numero_sala="+n_sala;
        String queryNLinhas = "select count( distinct posicao_linha) from lugar where numero_sala="+n_sala;
        String queryNColunas = "select count( distinct posicao_coluna) from lugar where numero_sala="+n_sala;
        try {
                String nLinhas="";
                String nColunas= "";
                if(stmt.execute(queryNLinhas)) {
                    resultQueryNLinhas = stmt.getResultSet();
                    while (resultQueryNLinhas.next()) {
                        nLinhas = resultQueryNLinhas.getString(1);
                    }
                }
                if(stmt.execute(queryNColunas)){
                    resultQueryNColunas=stmt.getResultSet();
                    while (resultQueryNColunas.next()) {
                        nColunas= resultQueryNColunas.getString(1);
                    }
                }
                lugares = new Lugar[Integer.parseInt(nLinhas)][Integer.parseInt(nColunas)];
            if (stmt.execute(queryLugar)) {
                resultQueryLugar = stmt.getResultSet();
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
                return convertCalendarFromString(resultQueryDaraHoraFimSessao.getString("dataHoraFim"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean getExisteBilhete(String posicao,Sessao sessao){
        ResultSet resultQueryExisteBilhete;
        String queryExisteBilhete = "select count(*) as ExisteBilhete from bilhete where numero_sala='"+sessao.getSala().getNumeroSala()+"' and nome_lugar='"+posicao+"' and titulo_filme='"+sessao.getFilme().getTitulo()+"' and ano_filme="+sessao.getFilme().getAno(); //se obter 1 registo quer dizer que existe um bilhete para esse lugar nessa sessao, logo e um lugar ja ocupado
        try {
            if (stmt.execute(queryExisteBilhete)) {
                resultQueryExisteBilhete = stmt.getResultSet();
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

    public String getPrecoBilhete(String posicao,Sessao sessao){
        ResultSet resultQueryPrecoBilhete;
        String queryPrecoBilhete = "";
        try {
            if (stmt.execute(queryPrecoBilhete)) {
                resultQueryPrecoBilhete = stmt.getResultSet();
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
}
