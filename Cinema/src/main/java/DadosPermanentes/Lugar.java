package DadosPermanentes;

public class Lugar {
    private int numero;

    enum tipo{
        Deficiente,
        Normal,
        VIP
    }
    private String[][] posicao;
    private Sala sala;


    public int getNumero() {
        return numero;
    }



    public Sala getSala() {
        return sala;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setPosicao(String[][] posicao) {
        this.posicao = posicao;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }



}
