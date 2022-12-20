package DadosPermanentes;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class mainAuxBilhete {
    public static void main(String[] args) {
        Realizador rea=new Realizador("Steven Spilberge");
        Ator raul=new Ator("Raul Silva");
        Ator fabio=new Ator("Fabio Simoes");
        Ator tomas=new Ator("Tomas Rosa");
        Ator[] atores={raul, fabio, tomas};
        Distribuidor distribuidor=new Distribuidor("Cinemas NOZ");
        Genero[] generos={Genero.ACAO,Genero.DANCA,Genero.FAROESTE};
        String dataEstreiaAux="12/3/1815";
        String[] datas=dataEstreiaAux.split("/");
        int dia=Integer.parseInt(datas[0]);
        int mes=Integer.parseInt(datas[1]);
        int ano=Integer.parseInt(datas[2]);
        Calendar dataEstreia =new GregorianCalendar(ano, mes, dia);
        Filme filme=new Filme("Labregos in da Street", "Labregation in the Street", generos, dataEstreia, rea, atores, distribuidor, "Portugal", 360, "Labregos a fazerem um trabalho");


        Lugar l1=new Lugar("A1", Lugar.TipoLugar.Normal);
        Lugar l2=new Lugar("A2", Lugar.TipoLugar.Normal);
        Lugar l3=new Lugar("A3", Lugar.TipoLugar.Normal);
        Lugar[][] lugares={{l1,l2,l3}};
        Sala sala = new Sala(1, lugares);

        Calendar dataInicio= new GregorianCalendar(2022,11,25,23,30, 00);
        Calendar dataFim= new GregorianCalendar(2022,11,26,2,00);;
        Sessao sessao=new Sessao(filme,sala,dataInicio,dataFim);
        //System.out.println(sessao.toString());


        Bilhete obj = new Bilhete(l1, sessao, 10, Bilhete.Estado.Livre);

        System.out.println(obj.toString());
    }


}
