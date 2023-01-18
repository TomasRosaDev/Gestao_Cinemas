package DadosPermanentes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SbdHandler {
    Connection con;
    CallableStatement cstmt;

    public SbdHandler() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://estga-dev.ua.pt/PTDA_BD_04?", "PTDA_04", "Kiut684h");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //uploadImagem("Sorri");
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
        ResultSet resultQueryFilmeImagem;
        String imageName="";

        try {
            cstmt = con.prepareCall("{call paginaInicialDetalhes(?, ?)}");
            cstmt.setString(1, filme.getTitulo());
            cstmt.setInt(2, filme.getAno());

            if(cstmt.execute()){
                resultqueryHomePageDetails=cstmt.getResultSet();
                resultqueryHomePageDetails.next();
                filme.setIdadeMinima(resultqueryHomePageDetails.getString("idade"));
                imageName=resultqueryHomePageDetails.getString("nome_imagem");
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

            cstmt=con.prepareCall("{call imagem(?)}");
            cstmt.setString(1,imageName);

            if(cstmt.execute()){
                resultQueryFilmeImagem=cstmt.getResultSet();
                resultQueryFilmeImagem.next();
                Blob immAsBlob = resultQueryFilmeImagem.getBlob("imagem");
                byte[] immAsBytes = immAsBlob.getBytes(1, (int)immAsBlob.length());

                InputStream in = new ByteArrayInputStream(immAsBytes);
                try {
                    BufferedImage imgFromDb = ImageIO.read(in);
                    filme.setImagem(imgFromDb);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            //Blob immAsBlob = rs.getBlob();
            //byte[] immAsBytes = immAsBlob.getBytes(1, (int)immAsBlob.length()))


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Filme> listaFilmes(Date dia) throws SQLException {
        ArrayList<Filme> listaFilmes = new ArrayList<>();
        ResultSet resultQueryFilmes;
        System.out.println(dia);
        try {
            cstmt = con.prepareCall("{call sessoesDia(?)}");
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
            cstmt.setDate(3, dia);

            if (cstmt.execute()) {
                resultQuerySessoes = cstmt.getResultSet();
                while (resultQuerySessoes.next()) {


                    try {
                        String data = resultQuerySessoes.getString("dataHoraInicio");
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        java.util.Date date = dateFormat.parse(data);
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                        sessoes.add(new Sessao(filme,new Sala(resultQuerySessoes.getInt("numero_sala"),this),sqlDate,this));
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }


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
                    Date dataInicio=convertStringtoDatesql(resultQuerySessao.getString("dataHoraInicio"));
                    Sala sala=getSala(resultQuerySessao.getInt("numero_sala"));
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

    public Bilhete[][] getBilhetes(Sessao sessao) {
        ResultSet resultQueryLugar=null;
        ResultSet resultQueryNLinhas=null;
        ResultSet resultQueryNColunas=null;
        Bilhete[][] bilhetes= null;
        int n_sala=sessao.getSala().getNumeroSala();

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
                bilhetes = new Bilhete[nLinhas][nColunas];

            cstmt = con.prepareCall("{call bilhetes(?, ?, ?, ?)}");
            cstmt.setInt(1, n_sala);
            cstmt.setString(2, sessao.getFilme().getTitulo());
            cstmt.setInt (3, sessao.getFilme().getAno());
            cstmt.setString(4, strDay(sessao.getDataHoraInicio()));

            if (cstmt.execute()) {
                resultQueryLugar = cstmt.getResultSet();
                while (resultQueryLugar.next()) {
                    String nome = resultQueryLugar.getString("nome");
                    TipoLugar tipo=TipoLugar.valueOf(resultQueryLugar.getString("tipo"));
                    int linha =resultQueryLugar.getInt("posicao_linha");
                    int coluna =resultQueryLugar.getInt("posicao_coluna");
                    boolean ocupado=(1==resultQueryLugar.getInt("ocupado"));
                    bilhetes[linha][coluna]=new Bilhete(new Lugar(nome,tipo),sessao,ocupado,this);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bilhetes;
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

    public Date getDataHoraFim(String titulo, int ano, int nSala, Date dataHoraInicio){
        ResultSet resultQueryDaraHoraFimSessao;
        try {
            cstmt = con.prepareCall("{call sessaoDataHoraFim(?, ?, ?)}");
            cstmt.setString(1, titulo);
            cstmt.setInt(2, ano);
            cstmt.setInt(3, nSala);
            cstmt.setString(4, strDay(dataHoraInicio));

            if (cstmt.execute()) {
                resultQueryDaraHoraFimSessao = cstmt.getResultSet();
                return convertStringtoDatesql(resultQueryDaraHoraFimSessao.getString("dataHoraFim"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void concluirCompra(ArrayList<Bilhete> bilhetes){
        try {
            for (Bilhete bilhete:bilhetes) {
                cstmt = con.prepareCall("{call concluirCompra(?, ?, ?, ?, ?)}");
                cstmt.setInt(1, bilhete.getSessao().getSala().getNumeroSala());
                cstmt.setString(2, bilhete.getLugar().getNome());
                cstmt.setString(3, bilhete.getSessao().getFilme().getTitulo());
                cstmt.setInt(4, bilhete.getSessao().getFilme().getAno());
                cstmt.setString(5, strDay(bilhete.getSessao().getDataHoraInicio()));
                cstmt.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

    public ArrayList<Ator> getAtores(){
        ArrayList <Ator> atores = new ArrayList<>();
        ResultSet resultQueryAtores;

        try{
            cstmt = con.prepareCall("{call atores()}");

            if(cstmt.execute()){
                resultQueryAtores = cstmt.getResultSet();

                while(resultQueryAtores.next()){
                    atores.add(new Ator(resultQueryAtores.getString("nome")));
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return atores;
    }

    public ArrayList<Genero> getGeneros(){
        ArrayList <Genero> generos = new ArrayList<>();
        ResultSet resultQueryGeneros;

        try{
            cstmt = con.prepareCall("{call generos()}");

            if(cstmt.execute()){
                resultQueryGeneros = cstmt.getResultSet();

                while(resultQueryGeneros.next()){
                    generos.add(Genero.valueOf(resultQueryGeneros.getString("nome")));
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return generos;
    }

    public ArrayList<Realizador> getRealizadores(){
        ArrayList <Realizador> realizadores = new ArrayList<>();
        ResultSet resultQueryRealizadores;

        try{
            cstmt = con.prepareCall("{call realizadores()}");

            if(cstmt.execute()){
                resultQueryRealizadores = cstmt.getResultSet();

                while(resultQueryRealizadores.next()){
                    realizadores.add(new Realizador(resultQueryRealizadores.getString("nome")));
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return realizadores;
    }

    public ArrayList<Distribuidor> getDistribuidores(){
        ArrayList <Distribuidor> distribuidores = new ArrayList<>();
        ResultSet resultQueryDistribuidores;

        try{
            cstmt = con.prepareCall("{call distribuidores()}");

            if(cstmt.execute()){
                resultQueryDistribuidores = cstmt.getResultSet();

                while(resultQueryDistribuidores.next()){
                    distribuidores.add(new Distribuidor(resultQueryDistribuidores.getString("nome")));
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return distribuidores;
    }

    public ArrayList<Filme> getFilmes(){
        ArrayList <Filme> filmes = new ArrayList<>();
        ResultSet resultQueryFilmes;

        try{
            cstmt = con.prepareCall("{call filmes()}");

            if(cstmt.execute()){
                resultQueryFilmes = cstmt.getResultSet();

                while(resultQueryFilmes.next()){
                    filmes.add(new Filme(resultQueryFilmes.getString("nome"), resultQueryFilmes.getString("ano"), this));
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return filmes;
    }

    public ArrayList<Sessao> getSessoes(){
        ArrayList <Sessao> sessoes = new ArrayList<>();
        ResultSet resultQuerySessoes;

        try{
            cstmt = con.prepareCall("{call sessoes()}");

            if(cstmt.execute()){
                resultQuerySessoes = cstmt.getResultSet();

                while(resultQuerySessoes.next()){
                    sessoes.add(new Sessao(new Filme(resultQuerySessoes.getString("titulo_filme"),
                            resultQuerySessoes.getString("ano_filme"),this),
                            new Sala(resultQuerySessoes.getInt("numero_sala"),this),
                            resultQuerySessoes.getDate("dataHoraInicio"),this));
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sessoes;
    }

    public void closeConection(){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String strDay(Date dia){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

    public byte[] encoder(String imagePath) {
        //String base64Image = "";
        byte[] imageData={};
        File file = new File(imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            // Reading a Image file from file system
            imageData = new byte[(int) file.length()];
            imageInFile.read(imageData);
            //base64Image = Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        //return base64Image;
        return imageData;
    }

    public void uploadImagem(String fileStr) {

        try {
            cstmt = con.prepareCall("{call adicionarImagem(?,?)}");
            //String[] nomearray=fileStr.split("/");
            //String nome=nomearray[nomearray.length-1];
            //nome=nome.split(".")[0];
            String nome=fileStr/*.split(".")[0]*/;
            System.out.println(nome);
            cstmt.setString(1, nome);
            byte[] imagemByte=encoder(fileStr+".jpg");
            ByteArrayInputStream bais = new ByteArrayInputStream(imagemByte);

            cstmt.setBinaryStream(2,bais,imagemByte.length);
            cstmt.executeUpdate();
            cstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
