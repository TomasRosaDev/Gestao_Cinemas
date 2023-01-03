package DadosPermanentes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class Cinema {
    private SbdHandler sbdHandler;
    private ArrayList<Sala> salas;
    private ArrayList<Realizador> realizadores;
    private ArrayList<Ator> atores;
    private ArrayList<Filme> filmes;
    private ArrayList<Distribuidor> distribuidores;
    private ArrayList<Sessao> sessoes;
/*
    public Cinema(SbdHandler sbdHandler) {
        this.sbdHandler = sbdHandler;
        getSalas();
        getRealizadores();
        getAtores();
        getDistribuidores();
        getFilmes();
        getSessoes();
    }

    public void getSalas(){
        int[] queSalas = sbdHandler.getQueSalas();
        for (int i = 0; i < queSalas.length; i++) {
            int salaNum=queSalas[i];
            Sala sala1 = new Sala(salaNum, sbdHandler.getLugaresMatriz(salaNum));
            salas.add(sala1);
        }
    }

    public void getFilmes(){
        String[][] nomesAnos=sbdHandler.getQueFilmes();
        for (int i=0;i<nomesAnos.length;i++){
            String nomeFilme=nomesAnos[i][0];
            int anoFilme=Integer.parseInt(nomeFilme[i][1]);
            String[] infoFilme=sbdHandler.getInfoFilme(nomeFilme,anoFilme);
            String[] datas=infoFilme[2].split("/");
            int dia=Integer.parseInt(datas[0]);
            int mes=Integer.parseInt(datas[1]);
            int ano=Integer.parseInt(datas[2]);
            Calendar dataEstreia =new GregorianCalendar(ano, mes, dia);
            Realizador realiEspecifico=procurarRealizador(infoFilme[3]);
            Ator[] atoresEspecificos=procurarAtores(infoFilme[4].split(","));
            Distribuidor distriEspecifico=procurarDistribuidor(infoFilme[5]);
            Filme filme=new Filme(nomeFilme,infoFilme[0],Genero.infoFilme[1],dataEstreia,realiEspecifico,atoresEspecificos, distriEspecifico,infoFilme[6] , Integer.parseInt(infoFilme[7]), infoFilme[8]);
            filmes.add(filme);
        }
    }



    public void getSessoes(){
        String[][] sessaoPK=sbdHandler.getQueSessoes();
        for (int i=0;i<sessaoPK.length;i++) {
            Filme filmeEspecifico=procurarFilme(sessaoPK[i][0],sessaoPK[i][1]);
            Sala salaEspecifica=procurarSala(Integer.parseInt(sessaoPK[i][2]));

            String[] aux=sessaoPK[i][3].split("/");
            Calendar dataInicio= new GregorianCalendar(aux[0],aux[1],aux[2],aux[3],aux[4]);
            String[] aux2=sessaoPK[i][4].split("/");
            Calendar dataFim= new GregorianCalendar(aux2[0],aux2[1],aux2[2],aux2[3],aux2[4]);

            Sessao sessao=new Sessao( filmeEspecifico,salaEspecifica,dataInicio, dataFim);
            sessoes.add(sessao);
        }

    }

    public void getRealizadores() {
        String[] realizadores=sbdHandler.getRealizadores();
        for (int i=0; i<realizadores.length;i++){
            Realizador realizador=new Realizador(realizadores[i]);
            this.realizadores.add(realizador);
        }
    }

    public void getAtores(){
        String[] atores=sbdHandler.getAtores();
        for (int i=0; i<atores.length;i++){
            Ator ator=new Ator(atores[i]);
            this.atores.add(ator);
        }
    }
    public void getDistribuidores() {
        String[] distribuidores=sbdHandler.getDistribuidores();
        for (int i=0; i<distribuidores.length;i++){
            Distribuidor distribuidor=new Distribuidor(distribuidores[i]);
            this.distribuidores.add(distribuidor);
        }
    }

    public Sala procurarSala(int nSala) {
        Iterator<Sala> iterator = salas.iterator();
        while (iterator.hasNext()) {
            Sala sala = iterator.next();
            if (sala.getNumeroSala()==nSala) {
                return sala;
            }
        }
        return null;
    }

    public Realizador procurarRealizador(String realizadorInput) {
        Iterator<Realizador> iterator = realizadores.iterator();
        while (iterator.hasNext()) {
            Realizador realizador = iterator.next();
            if (realizador.getNome().equals(realizadorInput)) {
                return realizador;
            }
        }
        return null;
    }
    public Distribuidor procurarDistribuidor(String distribuidorInput) {
        Iterator<Distribuidor> iterator = distribuidores.iterator();
        while (iterator.hasNext()) {
            Distribuidor distribuidor = iterator.next();
            if (distribuidor.getNome().equals(distribuidorInput)) {
                return distribuidor;
            }
        }
        return null;
    }
    public Ator procurarAtor(String atorInput) {
        Iterator<Ator> iterator = atores.iterator();
        while (iterator.hasNext()) {
            Ator ator = iterator.next();
            if (ator.getNome().equals(atorInput)) {
                return ator;
            }
        }
        return null;
    }
    public Ator[] procurarAtores(String[] atoresInput){
        Ator[] atores;
        for (int i=0;i<atoresInput.length;i++){
            atores[i]=procurarAtor(atoresInput[i]);
        }
        return atores;
    }

    public Filme procurarFilme(String nomeFilme, String anoFilme) {
        Iterator<Filme> iterator = filmes.iterator();
        while (iterator.hasNext()) {
            Filme filme = iterator.next();
            if (filme.getTitulo().equals(nomeFilme) && filme.getAnoString().equals(anoFilme)) {
                return filme;
            }
        }
        return null;
    }

}
    /*
    Lugar[][] getLugaresMatriz(int salaNum){
        int nColunas= sbdHandler.getNumColunas(salaNum);
        int nLinhas= sbdHandler.getNumLinhas(salaNum);
        Lugar[][] lugares=new Lugar[nLinhas][nColunas];
        for(int i=0;i<nLinhas;i++){
            for(int j=0;j<nColunas;j++){
                lugares[i][j]= new Lugar(sbdHandler.getNomeLugar(salaNum,i,j), sbdHandler.getTipoLugar(salaNum,i,j));
            }
        }
        return lugares;
    }


}
    public static void main(String[] args){
        ArrayList <String> list = new ArrayList<String>();
        //Instantiating an ArrayList object
        list.add("JavaFX");
        list.add("Java");
        list.add("WebGL");
        list.add("OpenCV");
        list.add("OpenNLP");
        list.add("JOGL");
        list.add("Hadoop");
        list.add("HBase");
        list.add("Java Script");
        list.add("Flume");
        list.add("Mahout");
        list.add("Impala");
        System.out.println("Contents of the array list: ");
        for (String element : list){
            if (element.contains("Java")){
                System.out.println(element);
            }
        }
    }
     */
}