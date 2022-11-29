package DadosPermanentes;

public class LugarDaSessao {
    private Lugar lugar;
    private Sessao sessao;

    private int preco;

    public Estado getEstado() {
        return estado;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        if(preco>=0){
            this.preco = preco;
        }
        else{
            throw new IllegalArgumentException("O preco tem de ser maior ou igual a 0.");
        }
    }

    public enum Estado{
        Ocupado,
        Livre,
        Reservado
    }

    public Estado estado;

    public LugarDaSessao(Lugar lugar, Sessao sessao, int preco, Estado estado){
        this.lugar = lugar;
        this.sessao = sessao;
        this.preco = preco;
        this.estado = estado;
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
