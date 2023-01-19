package DadosPermanentes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RealizadorTest {

    @Test
    void getNomeRealizador() {
        Realizador realizador = new Realizador("Time Burton");
        String expectedRealizadorName="Time Burton";
        String actualRealizadorName = realizador.getNome();
        assertEquals(expectedRealizadorName, actualRealizadorName, "Realizador nao esperado");
    }



    @Test
    void testRealizadorConstrutorValido(){
        String expectedRealizadorName="Time Burton";
        Realizador realizador=new Realizador("Time Burton");
        assertEquals(expectedRealizadorName,realizador.getNome(),"O nome e invalido");
    }
    @Test
    void testRealizadorConstrutorInvalidoExiesteNaoLetras(){
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () -> new Realizador("Miguel Tavares123"),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }
    @Test
    void testRealizadorConstrutorInvalidoEspacos(){
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () -> new Realizador("        "),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }
    @Test
    void testRealizadorConstrutorInvalidoComprimentoExcedido(){
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () -> new Realizador("José da Silva Tavares dos Santos"),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }

    @Test
    void testRealizadorConstrutorInvalidoComprimentoZero(){
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                new Realizador(""),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }
    @Test
    void testRealizadorConstrutorInvalidoNomeNull(){
        String nomeRealizador = null;
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                new Realizador(nomeRealizador),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }

    @Test
    void testRealizadorConstrutorInvalidoNomeMinusculo(){
        String nomeRealizador = "oliver Stone";
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                new Realizador(nomeRealizador),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }

}