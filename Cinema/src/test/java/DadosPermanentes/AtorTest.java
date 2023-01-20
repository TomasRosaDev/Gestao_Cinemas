package DadosPermanentes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AtorTest {

    @Test
    void getNomeAtor() {
        Ator ator = new Ator("Joao Silva");
        String expectedAtorName="Joao Silva";
        String actualAtorName = ator.getNome();
        assertEquals(expectedAtorName, actualAtorName, "Ator nao esperado");
    }


    @Test
    void testAtorConstrutorValido(){
        String expectedAtorName="Antonio Bandeiras";
        Ator ator=new Ator("Antonio Bandeiras");
        assertEquals(expectedAtorName,ator.getNome(),"O nome e invalido");
    }
    @Test
    void testAtorConstrutorInvalidoExiesteNaoLetras(){
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                new Ator("Anto32nio Bandeiras"),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }
    @Test
    void testAtorConstrutorInvalidoEspacos(){
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                new Ator("        "),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }
    @Test
    void testAtorConstrutorInvalidoComprimentoExcedido(){
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                new Ator("Joao Silva Ferraira Oliveira de Jesus"),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }

    @Test
    void testAtorConstrutorInvalidoComprimentoZero(){
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                new Ator(""),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }
    @Test
    void testAtorConstrutorInvalidoNomeNull(){
        String nomeAtor = null;
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                new Ator(nomeAtor),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }

    @Test
    void testAtorConstrutorInvalidoNomeMinusculo(){
        String nomeAtor = "megan fox";
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                new Ator(nomeAtor),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }


}