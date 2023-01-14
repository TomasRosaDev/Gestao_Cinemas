package DadosPermanentes;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Sessao {

    private Date dataHoraInicio;
    private Date dataHoraFim;
    private Filme filme;
    private Sala sala;
    private SbdHandler sbdHandler;

    public Sala getSala() {
        return sala;
    }

    public Date getDataHoraInicio() {
        return dataHoraInicio;
    }

    public String getHoraInicioStr(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date= dateFormat.format(dataHoraInicio).split(" ")[1].substring(0,5);

        return date;
    }

    public Sessao(Filme filme, Sala sala, Date dataHoraInicio, SbdHandler sbdHandler) {
        this.filme=filme;
        this.sala=sala;
        this.dataHoraInicio=dataHoraInicio;
        this.sbdHandler=sbdHandler;
    }

    public Filme getFilme() {
        return filme;
    }

    public Date getDataHoraFim() {
        if(dataHoraFim==null){
            dataHoraFim=sbdHandler.getDataHoraFim(filme.getTitulo(),filme.getAnoString(),sala.getNumeroSala(),dataHoraInicio);
        }
        return dataHoraFim;
    }

    @Override
    public String toString() {
        String aux="";
        aux+="Filme\n"+filme.toString();
        aux+="\nSala\n" +sala.toString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        aux+="\nHora: "+sdf.format(dataHoraInicio);
    return aux;
    }

    public SbdHandler getSbdHandler() {
        return sbdHandler;
    }
}
