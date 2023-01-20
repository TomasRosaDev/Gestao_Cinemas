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
        if( nome== null){
            throw new RuntimeException("Nome nulo");
        }
        Pattern pattern = Pattern.compile("(^[A-Z][1-9][0-9]{0,1}|^ND[1-9][0-9]{0,1})$");//Admite apenas formato LetraMaiuscula-Numero-Numero
        Matcher matcher = pattern.matcher(nome);
        if (matcher.find()){
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
