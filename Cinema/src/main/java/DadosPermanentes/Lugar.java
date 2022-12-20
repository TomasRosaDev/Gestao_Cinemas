package DadosPermanentes;

public class Lugar {
    // Campos
    private String posicao;
    public TipoLugar tipo;
    public enum TipoLugar{
        Normal,
        Deficiente,
        VIP
    }

    //Construtor
    public Lugar(String posicao, TipoLugar lugar){
        this.posicao = posicao;
        this.tipo = lugar;
    }

    //Getters
    public String getPosicao() {
        return this.posicao;
    }

    public TipoLugar getTipo() {
        return tipo;
    }

    public String toString(){
        return "\nPosicao: "+getPosicao()+"\nTipo: "+getTipo();
    }
}
