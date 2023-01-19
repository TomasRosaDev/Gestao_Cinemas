package DadosPermanentes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistribuidorTest {

    @Test
    void getNomeDistribuidor() {
        Distribuidor distribuidor = new Distribuidor("Cinemas Noz");
        String expectedDistribuidorName="Cinemas Noz";
        String actualDistribuidorName = distribuidor.getNome();
        assertEquals(expectedDistribuidorName, actualDistribuidorName, "Distribuidor nao esperado");
    }


    @Test
    void testDistribuidorConstrutorValido(){
        String expectedDistribuidorName="Cinemas Noz";
        Distribuidor distribuidor=new Distribuidor("Cinemas Noz");
        assertEquals(expectedDistribuidorName,distribuidor.getNome(),"O nome e invalido");
    }
    @Test
    void testDistribuidorConstrutorInvalidoExiesteNaoLetras(){
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () -> new Distribuidor("Cinemas Noz123"),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }
    @Test
    void testDistribuidorConstrutorInvalidoEspacos(){
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () -> new Distribuidor("        "),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }
    @Test
    void testDistribuidorConstrutorInvalidoComprimentoExcedido(){
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () -> new Distribuidor("Cinemas Noz Silva Castro de Almeida"),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }

    @Test
    void testDistribuidorConstrutorInvalidoComprimentoZero(){
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                new Distribuidor(""),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }
    @Test
    void testDistribuidorConstrutorInvalidoNomeNull(){
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                new Distribuidor(null),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }

    @Test
    void testDistribuidorConstrutorInvalidoNomeMinusculo(){
        String nomeDistribuidor = "oliver Stone";
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                new Distribuidor(nomeDistribuidor),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }

}