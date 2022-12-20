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
    public ArrayList<Filme> listaFilmes (Calendar dia) {
        ArrayList<Filme> listaFilmes = new ArrayList<>();
        ResultSet queryFilmes;//Lista Filmes ativos e corresposndetes ao dia actual ou a um dia passado por parametr
        try {
            if (stmt.execute(query)) {
                queryFilmes = stmt.getResultSet();
                while (queryFilmes.next()) {
    

                    listaFilmes.add(new Filme(titulo, tituloOriginal, generos, dataEstreia, realizador,atores,));
                }
                return listaFilmes;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /*
               Statement stmt = conn.createStatement();
            ResultSet rs;
            String query = "SELECT nome FROM aluno WHERE numero = " + nAluno;

            if(stmt.execute(query)){
                rs = stmt.getResultSet();

                while (rs.next()){
                    String nome = rs.getNString("nome");
                    System.out.println(nome);
                }


            }
     */


}
