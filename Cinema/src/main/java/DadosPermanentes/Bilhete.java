package DadosPermanentes;

public class Bilhete {
    private Lugar lugar;
    private Sessao sessao;
    private String preco;
    private boolean ocupado;
    private SbdHandler sbdHandler;

    public Bilhete(Lugar lugar, Sessao sessao/*,boolean ocupado*/, SbdHandler sbdHandler){
        this.lugar = lugar;
        this.sessao = sessao;
        this.sbdHandler=sbdHandler;
        //this.ocupado=ocupado;
    }


    public String getPreco() {
        if(preco==null){
            preco=sbdHandler.getPrecoBilhete(lugar.getNome(),sessao);
        }
        return preco;
    }

    public String toString() {
        String auxLugarDaSessao = "";
        auxLugarDaSessao+=sessao.toString();
        auxLugarDaSessao+="\n" + lugar.toString();

        auxLugarDaSessao+= "\n" + "Sala: " + String.valueOf(sessao.getSala().getNumeroSala());
        auxLugarDaSessao+="\nPreco: " + this.preco;

        return auxLugarDaSessao;
    }

    public Lugar getLugar() {
        return lugar;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public boolean isOcupado() {
        return ocupado;
    }
}
