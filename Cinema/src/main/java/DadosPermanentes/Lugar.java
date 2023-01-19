package DadosPermanentes;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lugar {
    // Campos
    private String nome;
    private TipoLugar tipo;

    //Construtor
    public Lugar(String posicao, TipoLugar lugar){
        setNome(posicao);
        setTipo(lugar);
    }

    //Getters.00000000000000
    public String getNome() {
        return this.nome;
    }

    public TipoLugar getTipo() {
        return tipo;
    }

    //Setters


    public void setNome(String nome) {
        Pattern pattern = Pattern.compile("^[A-Z][0-99]{0,2}$");//Admite apenas formato LetraMaiuscula-Numero-Numero
        Matcher matcher = pattern.matcher(nome);
            if (matcher.find() || nome.matches("Coluna")){
                    this.nome = nome;
            }else{
            throw new RuntimeException("Nome invalido");
        }
    }

    public void setTipo(TipoLugar tipo) {
        if (!tipo.equals(null)) {
                this.tipo = tipo;
        }else{
            throw new RuntimeException("Tipo nulo");
        }
    }

    public String toString(){
        return "\nPosicao: "+getNome()+"\nTipo: "+getTipo();
    }
}
