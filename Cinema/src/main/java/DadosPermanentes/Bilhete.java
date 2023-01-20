package DadosPermanentes;

public class Bilhete {
    private Lugar lugar;
    private Sessao sessao;
    private String preco;
    private boolean ocupado;
    private SbdHandler sbdHandler;

    public Bilhete(Lugar lugar, Sessao sessao,boolean ocupado, SbdHandler sbdHandler){
        setLugar(lugar);
        setSessao(sessao);
        setSbdHandler(sbdHandler);
        setOcupado(ocupado);
    }


    public String getPreco() {
        if(preco==null){
            preco= String.valueOf(sbdHandler.getPrecoBilhete(lugar.getNome()));
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

    public Sessao getSessao() {
        return sessao;
    }

    public SbdHandler getSbdHandler() {
        return sbdHandler;
    }
    public void setSbdHandler(SbdHandler sbdHandler) {
        if (sbdHandler != null) {
            this.sbdHandler = sbdHandler;
        } else {
            throw new RuntimeException("Handler invalido");
        }
    }

    public void setSessao(Sessao sessao) {
        if(sessao != null) {
            this.sessao = sessao;
        }else {
            throw new RuntimeException("Sessao invalida");
        }
    }

    private void setLugar(Lugar lugar) {
        if(lugar != null) {
            this.lugar = lugar;
        }else {
            throw new RuntimeException("Lugar invalido");
        }
    }
}
