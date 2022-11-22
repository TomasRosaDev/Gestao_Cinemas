package DadosPermanentes;

public class Lugar {
    private int numero;
    private TipoLugar tipo;
    public enum TipoLugar{
        Normal,
        Deficiente,
        VIP
    }
    private String[][] posicao;
    private Sala sala;

    public Lugar(){

    }

    public int getNumero() {
        return numero;
    }

    public TipoLugar getTipo() {
        return tipo;
    }

    public Sala getSala() {
        return sala;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setTipo(TipoLugar tipo) {
        this.tipo = tipo;
    }

    public void setPosicao(String[][] posicao) {
        this.posicao = posicao;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }


}
