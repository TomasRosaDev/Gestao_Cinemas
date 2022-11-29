package DadosPermanentes;

public class Lugar {
    // Campos
    private String nome;
    public TipoLugar tipo;
    public enum TipoLugar{
        Normal,
        Deficiente,
        VIP
    }

    //Construtor
    public Lugar(String nome, TipoLugar lugar){
        this.nome = nome;
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
        return "\nNome: "+getNome()+"\nTipo: "+getTipo();
    }
}
