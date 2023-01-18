package DadosPermanentes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalaTest {

    @Test
    void testSalaConstrutorValido(){
        SbdHandler sbd = new SbdHandlerFantoche();
        int expectedSalaNum=1;
        Sala sala=new Sala(1,sbd);
        assertEquals(expectedSalaNum,sala.getNumeroSala(),"O numero e invalido");
        assertNotNull(sala.getSbdHandler(), "Handler em falta");
    }
    @Test
    void testSalaConstrutorNumeroSalaInvalido(){
        SbdHandler sbd = new SbdHandlerFantoche();
        Exception atualExection =assertThrows(Exception.class, () ->
                new Sala(-1, sbd),"Tipo de exception nao esperado");
        Exception expectedException=new Exception("Numero sala invalido");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"O numero e invalido");

    }
    @Test
    void testSalaConstrutorSalaHandlerInvalido(){
        SbdHandler sbd=null;
        Exception atualExection =assertThrows(Exception.class, () ->
                new Sala(1, sbd),"Tipo de exception nao esperado");
        Exception expectedException=new Exception("Handler invalido");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"O handler e invalido");
    }

    @Test
    void testSalaGetLugaresValido(){
        Sala sala= new Sala(1, new SbdHandlerFantoche());
        Lugar[][] lugares = sala.getLugaresAux();
        assertNull(lugares, "Lugares devem ser nulos apos construtor");
        Lugar[][] lugaresCriados=sala.getLugares();
        Lugar[][] lugaresEsperados = new Lugar[3][3];
        String[] letras= {"A","B","C"};
        for(int i=0;i<lugaresEsperados.length;i++){
            for(int j=0;j<lugaresEsperados[0].length;j++){
                assertEquals(new Lugar(letras[i]+(j+1), TipoLugar.Normal).toString(), lugaresCriados[i][j].toString(), "Lugares nao Coincidem");
            }
        }
    }

}