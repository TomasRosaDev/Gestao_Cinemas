package DadosPermanentes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LugarTest {

    @Test
    void getNomeTipoLugar() {
        Lugar lugar = new Lugar("A11", TipoLugar.Normal);
        String expectedLugarName="A11";
        String actualLugarName = lugar.getNome();
        TipoLugar expectedTipoLugar= TipoLugar.Normal;
        TipoLugar actualTipoLugar = lugar.getTipo();
        assertEquals(expectedLugarName, actualLugarName, "Ator nao esperado");
        assertEquals(expectedTipoLugar, actualTipoLugar, "Tipo de Lugar nao esperado");
    }
    @Test
    public void testEnumTipoLugarValores() {
        TipoLugar[] values = TipoLugar.values();
        assertEquals(4, values.length);
        assertEquals(TipoLugar.Normal, values[0]);
        assertEquals(TipoLugar.Incapacitado, values[1]);
        assertEquals(TipoLugar.Vip, values[2]);
        assertEquals(TipoLugar.Inexistente, values[3]);
    }
    @Test
    void testLugarConstrutorValido(){
        String expectedLugarName="A11";
        TipoLugar expectedLugarTipo = TipoLugar.Vip;
        Lugar lugar=new Lugar("A11", TipoLugar.Vip);
        assertEquals(expectedLugarName,lugar.getNome(),"O nome e invalido");
        assertEquals(expectedLugarTipo,lugar.getTipo(),"O Tipo e invalido");
    }
    @Test
    void testLugarNomeInvalido(){
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () -> new Lugar("A111", TipoLugar.Inexistente),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }
    @Test
    void testLugarNomeVazio(){
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () -> new Lugar("      ", TipoLugar.Incapacitado),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }
    @Test
    void testLugarNomeLetraMinuscula(){
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () -> new Lugar("a22", TipoLugar.Vip),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }
    /*@Test
    void testLugarNomeLetraTipoDesconhecido(){
        Exception expectedException=new Exception("Nome invalido");
        Exception atualExection =assertThrows(Exception.class, () -> new Lugar("A22", TipoLugar.Normal),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Mensagem da exception nao esperada");
    }
       */
}