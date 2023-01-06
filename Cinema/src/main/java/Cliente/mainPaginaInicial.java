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


    }
}
