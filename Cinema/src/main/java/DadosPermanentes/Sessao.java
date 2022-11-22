package DadosPermanentes;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Sessao {

    private Calendar horaInicio;
    private Calendar horaFim;
    private Filme filme;
    private Sala sala;

    public Sessao(Filme filme, Sala sala, Calendar horaInicio, Calendar horaFim) {
        this.filme=filme;
        this.sala=sala;
        this.horaInicio=horaInicio;
        this.horaFim = horaFim;
    }

    @Override
    public String toString() {
        String aux="";
        aux+="Filme\n"+filme.toString();
        aux+="\nSala\n " +sala.toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        aux+="\nHora: "+sdf.format(horaInicio.getTime());
    return aux;
    }
}
