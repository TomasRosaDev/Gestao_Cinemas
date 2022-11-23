package DadosPermanentes;

public class Lugar {
    // Campos
    private String posicao;
    public TipoLugar tipo;
    private Sala sala;
    public enum TipoLugar{
        Normal,
        Deficiente,
        VIP
    }

    //Construtor
    public Lugar(Sala sala, String posicao, TipoLugar lugar){
        this.sala = sala;
        this.posicao = posicao;
        this.tipo = lugar;
    }

    //Getters
    public String getPosicao() {
        return posicao;
    }

    public TipoLugar getTipo() {
        return tipo;
    }

    public static void main(String[] args) {
        Lugar novoLugar = new Lugar(new Sala(1, 10, 10), "A1", TipoLugar.Normal);

        System.out.println(novoLugar.tipo);
    }


}
