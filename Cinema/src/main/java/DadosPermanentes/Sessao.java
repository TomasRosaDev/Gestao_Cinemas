package DadosPermanentes;

import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date= dateFormat.format(dataHoraInicio).split(" ")[1].substring(0,5);
        return date;
    }


    public Sessao(Filme filme, Sala sala, Date dataHoraInicio, SbdHandler sbdHandler) {
        setFilme(filme);
        setSala(sala);
        setDataHoraInicio(dataHoraInicio);
        setSbdHandler(sbdHandler);
    }

    public Filme getFilme() {
        return filme;
    }

    public Date getDataHoraFim() {
        if(dataHoraFim==null){
            dataHoraFim=sbdHandler.getDataHoraFim(this);
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

    public void setDataHoraInicio(Date dataHoraInicio) {
        if(dataHoraInicio!=null) {
            this.dataHoraInicio=dataHoraInicio;
        }else{
            throw new RuntimeException("Data invalida");
        }
    }

    public void setDataHoraFim(Date dataHoraFim) {
        this.dataHoraFim = dataHoraFim;
    }

    public void setFilme(Filme filme) {
        if(filme!=null) {
            this.filme=filme;
        }else{
            throw new RuntimeException("Filme invalido");
        }
    }

    public void setSala(Sala sala) {
        if(sala!=null) {
            this.sala=sala;
        }else{
            throw new RuntimeException("Sala invalida");
        }
    }

    public void setSbdHandler(SbdHandler sbdHandler) {
        if(sbdHandler!=null) {
            this.sbdHandler = sbdHandler;
        }else{
            throw new RuntimeException("Handler invalido");
        }
    }
}
