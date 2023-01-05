package Cliente;

import DadosPermanentes.Filme;
import DadosPermanentes.SbdHandler;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;

public class mainPaginaInicial {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        PaginaInicial pagina = new PaginaInicial();
        pagina.PaginaInicial();
        /*
        Connection con;

        Statement stmt;

        ResultSet rs ;

        System.setProperty("jdbc.drivers","com.mysql.jdbc.Driver");
        Class.forName("com.mysql.cj.jdbc.Driver");

        con = DriverManager.getConnection("jdbc:mysql://estga-dev.ua.pt/PTDA_BD_04?", "PTDA_04", "Kiut684h");
        stmt = con.createStatement();
        String queryFilme="select titulo, ano from filme where data_estreia="+"'"+Date.valueOf("2022-09-28")+"'";
        if (stmt.execute(queryFilme)) {
            rs = stmt.getResultSet();
            while (rs.next()) {
                System.out.println(rs.getString("titulo"));
                System.out.println(rs.getString("ano"));
            }

        }
        con.close();
        stmt.close();
    }
    */

    }
}
