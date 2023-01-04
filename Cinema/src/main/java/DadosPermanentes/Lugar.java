package DadosPermanentes;

public class Lugar {
    // Campos
    private String nome;
    private TipoLugar tipo;

    //Construtor
    public Lugar(String posicao, TipoLugar lugar){
        this.nome = posicao;
        this.tipo = lugar;
    }

    //Getters
    public String getNome() {
        return this.nome;
    }

    public TipoLugar getTipo() {
        return tipo;
    }

    public String toString(){
        return "\nPosicao: "+getNome()+"\nTipo: "+getTipo();
    }
}
