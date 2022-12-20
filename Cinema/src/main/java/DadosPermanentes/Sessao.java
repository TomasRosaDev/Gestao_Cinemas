package DadosPermanentes;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Sessao {

    private Calendar dataHoraInicio;
    private Calendar dataHoraFim;
    private Filme filme;
    private Sala sala;

    public Sala getSala() {
        return sala;
    }

    public Sessao(Filme filme, Sala sala, Calendar dataHoraInicio, Calendar dataHoraFim) {
        this.filme=filme;
        this.sala=sala;
        this.dataHoraInicio=dataHoraInicio;
        this.dataHoraFim=dataHoraFim;
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
