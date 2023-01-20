package DadosPermanentes;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class SessaoTest {
    @Test
    void testBilheteConstrutorValido(){
        SbdHandler sbd = new SbdHandlerFantoche();
        Sala sala= new Sala(1, sbd);
        Filme filme = new Filme("Cars", "2022", sbd);
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
        assertNotNull(sessao.getSbdHandler(), "Handler em falta");
    }
    @Test
    void testSessaoConstrutorHandlerInvalido(){
        SbdHandler sbd = null;
        SbdHandler sbdValido = new SbdHandlerFantoche();
        Sala sala= new Sala(1, sbdValido);
        Filme filme = new Filme("Cars", "2022", sbdValido);
        String date= "2023-01-20 15:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date data = null;
        try {
            data = dateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        java.sql.Date sqlDate = new java.sql.Date(data.getTime());
        Exception expectedException=new Exception("Handler invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                new Sessao(filme, sala,sqlDate, sbd),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"O handler e invalido");
    }

}