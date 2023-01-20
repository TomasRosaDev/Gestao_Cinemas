package Cliente;

import DadosPermanentes.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {
    @Test
    void testeClienteConstrutorValido() {
        SbdHandler handlerTeste = new SbdHandlerFantoche();
        Cliente clienteTest = new Cliente(handlerTeste);
        assertNotNull(clienteTest, "SBDHandler invalido");
    }

    @Test
    void testeClienteConstrutorInvalido() {
        SbdHandler handlerTeste = new SbdHandlerFantoche();
        Cliente clienteTest = null;
        assertNull(clienteTest, "SBDHandler deveria ser null");
    }

    @Test
    void testeClienteDespesaValida() {
        SbdHandler handlerTeste = new SbdHandlerFantoche();
        Cliente clienteTest = new Cliente(handlerTeste);
        clienteTest.setDespeza(20F);
        Float valorTeste = 20F;
        assertEquals(clienteTest.getDespeza(), valorTeste, "Valor deveria ser igual");
    }

    @Test
    void testeClienteDespesaInvalida() {
        SbdHandler handlerTeste = new SbdHandlerFantoche();
        Cliente clienteTest = new Cliente(handlerTeste);
        clienteTest.setDespeza(null);
        Float valorTeste = null;
        assertEquals(clienteTest.getDespeza(), valorTeste, "Valor deveria ser nulo");
    }

    @Test
    void testeClienteSessaoValida() {
        SbdHandler handlerTeste = new SbdHandlerFantoche();
        Sala sala = new Sala(1, handlerTeste);
        Filme filme = new Filme("Cars", "2022", handlerTeste);
        String date = "2023-01-20 15:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date data = null;
        try {
            data = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        java.sql.Date sqlDate = new java.sql.Date(data.getTime());
        Cliente clienteTest = new Cliente(handlerTeste);
        Sessao sessaoTeste = new Sessao(filme, sala, sqlDate, handlerTeste);
        assertNotNull(sessaoTeste, "Sessao nula");
    }

    @Test
    void testeClienteSessaoInvalida() {
        Sessao sessaoTeste = null;
        assertNull(sessaoTeste, "Sessao deveria ser nula");
    }

    @Test
    void testeClienteBilheteValido() {
        SbdHandler handlerTeste = new SbdHandlerFantoche();
        Sala sala = new Sala(1, handlerTeste);
        Filme filme = new Filme("Cars", "2022", handlerTeste);
        Lugar lugar = new Lugar("A11", TipoLugar.Normal);
        String date = "2023-01-20 15:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date data = null;
        try {
            data = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        java.sql.Date sqlDate = new java.sql.Date(data.getTime());
        Sessao sessao = new Sessao(filme, sala, sqlDate, handlerTeste);
        Bilhete bilheteTeste = new Bilhete(lugar, sessao, false, handlerTeste);
        Cliente clienteTest = new Cliente(handlerTeste);
        ArrayList<Bilhete> bilhetesList = new ArrayList<>();
        bilhetesList.add(bilheteTeste);
        clienteTest.setBilhetes(bilhetesList);
        assertNotNull(clienteTest.getBilhetes(), "Lista de bilhetes nao deveria ser null");
    }

    @Test
    void testeClienteBilheteInvalido() {
        SbdHandler handlerTeste = new SbdHandlerFantoche();
        Cliente clienteTest = new Cliente(handlerTeste);
        ArrayList<Bilhete> bilhetesList = null;
        clienteTest.setBilhetes(bilhetesList);
        assertNull(clienteTest.getBilhetes(), "Lista de bilhetes deveria ser null");
    }

    @Test
    void testeClienteFilmeValido() {
        SbdHandler handlerTeste = new SbdHandlerFantoche();
        Cliente clienteTest = new Cliente(handlerTeste);
        Filme filme = new Filme("Cars", "2023", handlerTeste);
        clienteTest.setFilme(filme);
        assertEquals(clienteTest.getFilme(), filme, "Filme nao atribuido");
    }

    @Test
    void testeClienteFilmeInvalido() {
        SbdHandler handlerTeste = new SbdHandlerFantoche();
        Cliente clienteTest = new Cliente(handlerTeste);
        Filme filme = null;
        clienteTest.setFilme(filme);
        assertNull(clienteTest.getFilme(), "Filme deveria ser nulo");
    }

    @Test
    void testeClienteArrayFilmeValido() {
        SbdHandler handlerTeste = new SbdHandlerFantoche();
        Cliente clienteTest = new Cliente(handlerTeste);
        ArrayList<Filme> filmes = new ArrayList<>();
        Date data = Date.valueOf("2023-01-20");
        Filme filme = new Filme("Cars", "2023", handlerTeste);
        Filme filme2 = new Filme("Carss", "2022", handlerTeste);
        filmes.add(filme);
        filmes.add(filme2);
        clienteTest.setFilmesArray(filmes);
        clienteTest.setDia(data);
        assertEquals(filmes, clienteTest.getFilmes(data), "Filmes nao correspondem");
    }

    @Test
    void testeClienteArrayFilmeDataNula() {
        SbdHandler handlerTeste = new SbdHandlerFantoche();
        Cliente clienteTest = new Cliente(handlerTeste);
        ArrayList<Filme> filmes = new ArrayList<>();
        Date data = Date.valueOf("2023-01-20");
        Filme filme = new Filme("Cars", "2023", handlerTeste);
        Filme filme2 = new Filme("Carss", "2022", handlerTeste);
        filmes.add(0, filme);
        filmes.add(1, filme2);
        ArrayList<Filme> filmesNew = clienteTest.getFilmes(data);
        for (int i = 0; i<2; i++) {
            assertEquals(filmes.get(0).getTitulo(), filmesNew.get(0).getTitulo(), "Filmes nao correspondem");
        }
    }

}
