package DadosPermanentes;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FilmeTest {
    @Test
    void testFilmeConstrutorValido(){
        SbdHandler sbd = new SbdHandlerFantoche();
        String expectedFilmeTitulo="Cars";
        int expectedAno = 2023;
        Filme filme=new Filme("Cars", "2023", sbd);
        assertEquals(expectedFilmeTitulo,filme.getTitulo(),"Titulo invalido");
        assertEquals(expectedAno, filme.getAno(),"Ano invalido");
        assertNotNull(filme.getSbdHandler(), "Handler em falta");
    }
    @Test
    void testFilmeConstrutorTituloInvalido(){
        SbdHandler sbd = new SbdHandlerFantoche();
        String filmeTitulo="Cars                                                                                                     ";
        Exception expectedException=new Exception("Titulo invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                new Filme(filmeTitulo, "2023", sbd),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"O titulo e invalido");
    }
    @Test
    void testFilmeConstrutorAnoInvalido(){
        SbdHandler sbd = new SbdHandlerFantoche();
        Exception expectedException=new Exception("Ano invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                new Filme("Cars", "2025", sbd),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"O ano e invalido");
    }
    @Test
    void testFilmeConstrutorHandlerInvalido(){
        SbdHandler sbd = null;
        Exception expectedException=new Exception("Handler invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                new Filme("Cars", "2022", sbd),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"O handler e invalido");
    }

    @Test
    void testFilmeSetIdadeMaior(){
        SbdHandler sbd = new SbdHandlerFantoche();
        Filme filme=new Filme("Cars", "2022", sbd);
        Exception expectedException=new Exception("Idade minima invalida");
        Exception atualExection =assertThrows(Exception.class, () ->
                filme.setIdadeMinima(25),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Idade minima invalida");
    }
    @Test
    void testFilmeSetIdadeMenor(){
        SbdHandler sbd = new SbdHandlerFantoche();
        Filme filme=new Filme("Cars", "2022", sbd);
        Exception expectedException=new Exception("Idade minima invalida");
        Exception atualExection =assertThrows(Exception.class, () ->
                filme.setIdadeMinima(-3),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"Idade minima invalida");
    }
    @Test
    void testFilmeSetIdadeValida(){
        SbdHandler sbd = new SbdHandlerFantoche();
        Filme filme=new Filme("Cars", "2022", sbd);
        filme.setIdadeMinima(12);
        assertEquals(12,filme.getIdadeMinima(),"Idade minima invalida");
    }
    @Test
    void testFilmeSetImagemNull(){
        SbdHandler sbd = new SbdHandlerFantoche();
        Filme filme=new Filme("Cars", "2022", sbd);
        BufferedImage image=filme.getImagemAux();
        assertNull(image, "Imagem deve ser inexistente apos construtor");
        BufferedImage image1=null;
        filme.setImagem(image1);
        assertNull(filme.getImagemAux(), "Imagem deve continuar null");
    }
    @Test
    void testFilmeSetImagemValida(){
        SbdHandler sbd = new SbdHandlerFantoche();
        Filme filme=new Filme("Cars", "2022", sbd);
        File image = new File("src/main/java/Imagens/Vip.png");
        BufferedImage buffImage;
        try {
            buffImage= ImageIO.read(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        assertNotNull(buffImage,"Erro no teste");
        assertNull(filme.getImagemAux(),"A imagem deve ser inexistente apos construtor");
        filme.setImagem(buffImage);
        assertEquals(buffImage,filme.getImagemAux(), "Imagem deve continuar null");
    }

    @Test
    void testFilmeSetTituloOriginalInvalido(){
        SbdHandler sbd = new SbdHandlerFantoche();
        Filme filme=new Filme("Cars", "2023", sbd);
        String filmeTituloOriginal="Cars                                                                                                      ";
        Exception expectedException=new Exception("Titulo original invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                filme.setTituloOriginal(filmeTituloOriginal),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"O titulo original e invalido");
    }

    @Test
    void testFilmeSetTituloOriginalValido(){
        SbdHandler sbd = new SbdHandlerFantoche();
        Filme filme=new Filme("Cars", "2023", sbd);
        String filmeTituloOriginal="Carros";
        filme.setTituloOriginal(filmeTituloOriginal);
        assertEquals(filmeTituloOriginal,filme.getTituloOriginal(),"O titulo original e invalido");
    }
    @Test
    void testFilmeSetTituloOriginalNull(){
        SbdHandler sbd = new SbdHandlerFantoche();
        Filme filme=new Filme("Cars", "2023", sbd);
        assertNull(filme.getTituloOriginalAux(), "O titulo original deve ser inexistente apos construtor");
        String filmeTituloOriginal=null;
        filme.setTituloOriginal(filmeTituloOriginal);
        assertEquals(filme.getTitulo(),filme.getTituloOriginalAux(),"O titulo original quando null deve ser iguala titulo");
    }

    @Test
    void testFilmeSetGeneroInvalido(){
        SbdHandler sbd = new SbdHandlerFantoche();
        Filme filme=new Filme("Cars", "2023", sbd);
        Genero genero=null;
        ArrayList<Genero> generos= new ArrayList<>();
        generos.add(genero);
        Exception expectedException=new Exception("Genero invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                filme.setGeneros(generos),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"O genero e invalido");
    }
    @Test
    void testFilmeSetGenerosRepetidos(){
        SbdHandler sbd = new SbdHandlerFantoche();
        Filme filme=new Filme("Cars", "2023", sbd);
        ArrayList<Genero> generos= new ArrayList<>();
        ArrayList<Genero> generosEsperados= new ArrayList<>();
        generos.add(Genero.ACAO);
        generos.add(Genero.ACAO);
        generos.add(Genero.FAROESTE);
        generosEsperados.add(Genero.ACAO);
        generosEsperados.add(Genero.FAROESTE);
        filme.setGeneros(generos);
        assertEquals(generosEsperados,filme.getGeneros(),"O array de generos deve ser igual");
    }
    @Test
    void testFilmeSetGenerosValidoNull(){
        SbdHandler sbd = new SbdHandlerFantoche();
        Filme filme=new Filme("Cars", "2023", sbd);
        ArrayList<Genero> generos= new ArrayList<>();
        ArrayList<Genero> generosEsperados= new ArrayList<>();
        Genero genero=null;
        generos.add(Genero.ACAO);
        generos.add(genero);
        generosEsperados.add(Genero.ACAO);
        filme.setGeneros(generos);
        assertEquals(generosEsperados,filme.getGeneros(),"O array de generos deve ser igual");
    }
    @Test
    void testFilmeSetDataEstreiaInvalida(){
        SbdHandler sbd = new SbdHandlerFantoche();
        Filme filme=new Filme("Cars", "2023", sbd);
        Date data= Date.valueOf("2022-10-21");
        Exception expectedException=new Exception("Ano invalido");
        Exception atualExection =assertThrows(Exception.class, () ->
                filme.setDataEstreia(data),"Tipo de exception nao esperado");
        assertEquals(expectedException.getMessage(),atualExection.getMessage(),"O ano e invalido");
    }
    @Test
    void testFilmeSetDataEstreiaValido(){
        SbdHandler sbd = new SbdHandlerFantoche();
        Filme filme=new Filme("Cars", "2024", sbd);
        Date data= Date.valueOf("2024-10-21");
        filme.setDataEstreia(data);
        assertEquals(data,filme.getDataEstreiaAux(),"O ano da data de estreia e invalido");
    }
}