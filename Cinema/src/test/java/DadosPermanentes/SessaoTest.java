package DadosPermanentes;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class SessaoTest {
    @Test
    void testSessaoConstrutorValido(){
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
    void testSessaoSetHandlerInvalido(){
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
        Sessao sessao= new Sessao(filme, sala,sqlDate, sbdValido);
        Exception expectedException=new Exception("Handler invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                sessao.setSbdHandler(sbd),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"O handler e invalido");
    }
    @Test
    void testSessaoSetFilmeInvalido(){
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
        Sessao sessao=new Sessao(filme, sala,sqlDate, sbdValido);
        Exception expectedException=new Exception("Filme invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
               sessao.setFilme(null),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"O handler e invalido");
    }
    @Test
    void testSessaoSetSalaInvalido(){
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
        Sessao sessao=new Sessao(filme, sala,sqlDate, sbdValido);
        Exception expectedException=new Exception("Sala invalida");
        Exception atualExection =assertThrows(Exception.class, () ->
                sessao.setSala(null),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"O handler e invalido");
    }
    @Test
    void testSessaoSetDataInvalido(){
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
        Sessao sessao= new Sessao(filme, sala,sqlDate, sbdValido);
        Exception expectedException=new Exception("Data invalida");
        Exception atualExection =assertThrows(Exception.class, () ->
              sessao.setDataHoraInicio(null) ,"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"O handler e invalido");
    }
    @Test
    void testSessaoSetHoraFimJaExistente(){
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
        String dateFim= "2023-01-20 17:00:00";
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date data1 = null;
        try {
            data1 = dateFormat1.parse(dateFim);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        java.sql.Date sqlDateFim = new java.sql.Date(data1.getTime());
        Sessao sessao= new Sessao(filme, sala,sqlDate, sbdValido);
        sessao.setDataHoraFim(sqlDateFim);
        assertEquals(sqlDateFim, sessao.getDataHoraFim(), "Hora invalida");
    }
    @Test
    void testSessaoSetHoraFimBaseDados(){
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
        String dateFanto= "2023-01-20 18:00:00";
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date data1 = null;
        try {
            data1 = dateFormat1.parse(dateFanto);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        java.sql.Date sqlDateExpe = new java.sql.Date(data1.getTime());
        Sessao sessao= new Sessao(filme, sala,sqlDate, sbdValido);
        assertEquals(sqlDateExpe, sessao.getDataHoraFim(), "Hora invalida");
    }


}