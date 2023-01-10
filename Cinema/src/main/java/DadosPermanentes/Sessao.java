package DadosPermanentes;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Sessao {

    private Calendar dataHoraInicio;
    private Calendar dataHoraFim;
    private Filme filme;
    private Sala sala;
    private SbdHandler sbdHandler;

    public Sala getSala() {
        return sala;
    }

    public Filme getFilme() {
        return filme;
    }

    public Sessao(Filme filme, Sala sala, Calendar dataHoraInicio, SbdHandler sbdHandler) {
        this.filme=filme;
        this.sala=sala;
        this.dataHoraInicio=dataHoraInicio;
        this.sbdHandler=sbdHandler;
    }

    public Calendar getDataHoraFim() {
        if(dataHoraFim==null){
            dataHoraFim=sbdHandler.getDataHoraFim(filme.getTitulo(),filme.getAnoString(),sala.getNumeroSala()+"",dataHoraInicio);
        }
        return dataHoraFim;
    }

    @Override
    public String toString() {
        String aux="";
        aux+="Filme\n"+filme.toString();
        aux+="\nSala\n" +sala.toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        aux+="\nHora: "+sdf.format(dataHoraInicio.getTime());
    return aux;
    }

}
