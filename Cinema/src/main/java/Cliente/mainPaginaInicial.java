package Cliente;

import DadosPermanentes.Filme;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class mainPaginaInicial {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        PaginaInicial pagina = new PaginaInicial();
        pagina.PaginaInicialMain();


    }
}
