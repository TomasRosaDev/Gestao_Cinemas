package DadosPermanentes;

public class Bilhete {
    private Lugar lugar;
    private Sessao sessao;
    private String preco;
    public Estado estado;
    private SbdHandler sbdHandler;

    public Bilhete(Lugar lugar, Sessao sessao, SbdHandler sbdHandler){
        this.lugar = lugar;
        this.sessao = sessao;
        this.sbdHandler=sbdHandler;
    }

    public Estado getEstado() {
        if(estado==null){
            estado=sbdHandler.getEstadoBilhete(lugar.getPosicao(),sessao);
        }
        return estado;
    }

    public String getPreco() {
        if(preco==null){
            preco=sbdHandler.getPrecoBilhete(lugar.getPosicao(),sessao);
        }
        return preco;
    }


    public enum Estado{
        Ocupado,
        Livre,
        Reservado
    }




    public String toString() {
        String auxLugarDaSessao = "";
        auxLugarDaSessao+=sessao.toString();
        auxLugarDaSessao+="\n" + lugar.toString();

        auxLugarDaSessao+= "\n" + "Sala: " + String.valueOf(sessao.getSala().getNumeroSala());
        auxLugarDaSessao+="\nEstado: " + estado.toString();
        auxLugarDaSessao+="\nPreco: " + this.preco;

        return auxLugarDaSessao;
    }

}
