package DadosPermanentes;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class BilheteTest {
    @Test
    void testBilheteConstrutorValido(){
        SbdHandler sbd = new SbdHandlerFantoche();
        Sala sala= new Sala(1, sbd);
        Filme filme = new Filme("Cars", "2022", sbd);
        Lugar lugar = new Lugar("A11", TipoLugar.Normal);
        String date= "2023-01-20 15:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date data = null;
        try {
            data = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        java.sql.Date sqlDate = new java.sql.Date(data.getTime());
        Sessao sessao= new Sessao(filme, sala, sqlDate, sbd);
        Bilhete bilhete = new Bilhete(lugar, sessao, false, sbd);
        assertEquals(lugar, bilhete.getLugar(), "Lugar invalido");
        assertEquals(sessao, bilhete.getSessao(), "Sessao invalida");
        assertEquals(false, bilhete.isOcupado(), "Ocupacao invalida");
        assertNotNull(bilhete.getSbdHandler(), "Handler em falta");
    }
    @Test
    void testBilheteConstrutorHandlerInvalido(){
        SbdHandler sbd = null;
        SbdHandler sbdValido = new SbdHandlerFantoche();
        Sala sala= new Sala(1, sbdValido);
        Filme filme = new Filme("Cars", "2022", sbdValido);
        Lugar lugar = new Lugar("A11", TipoLugar.Normal);
        String date= "2023-01-20 15:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date data = null;
        try {
            data = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        java.sql.Date sqlDate = new java.sql.Date(data.getTime());
        Sessao sessao= new Sessao(filme, sala, sqlDate, sbdValido);

        Exception expectedException=new Exception("Handler invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                new Bilhete(lugar, sessao, false, sbd),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"O handler e invalido");
    }

    @Test
    void testBilheteConstrutorSessaoInvalida(){
        SbdHandler sbd = new SbdHandlerFantoche();
        Sala sala= new Sala(1, sbd);
        Filme filme = new Filme("Cars", "2022", sbd);
        Lugar lugar = new Lugar("A11", TipoLugar.Normal);
        String date= "2023-01-20 15:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date data = null;
        try {
            data = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        java.sql.Date sqlDate = new java.sql.Date(data.getTime());
        Sessao sessao= null;

        Exception expectedException=new Exception("Sessao invalida");
        Exception atualExection =assertThrows(Exception.class, () ->
                new Bilhete(lugar, sessao, false, sbd),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Sessao invalida");

    }

    @Test
    void testBilheteConstrutorLugarInvalido(){
        SbdHandler sbd = new SbdHandlerFantoche();
        Sala sala= new Sala(1, sbd);
        Filme filme = new Filme("Cars", "2022", sbd);
        Lugar lugar = null;

        String date= "2023-01-20 15:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date data = null;
        try {
            data = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        java.sql.Date sqlDate = new java.sql.Date(data.getTime());
        Sessao sessao= new Sessao(filme, sala, sqlDate, sbd);

        Exception expectedException=new Exception("Lugar invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                new Bilhete(lugar, sessao, false, sbd),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Lugar invalido");


    }
}