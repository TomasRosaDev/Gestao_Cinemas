/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DadosPermanentes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jorge Vieira
 */
public class Distribuidor {
    private String nome;

    public Distribuidor(String nome) {
        setNome(nome);
    }

    public void setNome(String nome) {
        if (nome!=null && nome.matches("^[A-Z][A-z\\s]{0,29}$")){
            this.nome = nome;
        }else{
            throw new RuntimeException("Nome invalido");
        }
    }

    public String getNome() {
        return nome;
    }
    
    
}
