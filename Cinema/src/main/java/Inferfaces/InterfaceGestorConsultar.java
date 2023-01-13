package Inferfaces;

import DadosPermanentes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGestorConsultar extends InterfaceGestor{
    private HomePage homePage;
    private Ator[] atores;
    private Filme[] filme;
    private Sessao[] sessoes;
    private Sala[] salas;
    private SbdHandler sbd;
    private JLabel consul;
    private SbdHandler sb;
    public InterfaceGestorConsultar(HomePage homePage){
        this.homePage=homePage;
        this.setLayout(new BorderLayout());
        this.add(informacoes());
        this.add(ecraPrincipal());
    }
    public JPanel informacoes(){
        JPanel info=new JPanel();
        info.setPreferredSize(new Dimension(600,25));
        info.setBackground(Color.lightGray);
        info.add(new JLabel("Consultar dados"));
        return info;
    }
    public JPanel ecraPrincipal(){
        JPanel geral=new JPanel();
        geral.setOpaque(false);
        geral.add(escolha());
        geral.add(consultas());
        geral.add(butVoltar());
        return geral;
    }
    public JPanel consultas(){
        JPanel consultas=new JPanel();
        consultas.setPreferredSize(new Dimension(600,30));
        consultas.setOpaque(false);
        consul = new JLabel("");
        consultas.add(consul);
        return consultas;
    }
    public JPanel escolha(){
        JPanel geral=new JPanel();
        geral.setOpaque(false);
        String[] choices = { "Escolha uma opção...","Atores","Filmes", "Sessoes","Salas"};
        JComboBox<String> cb = new JComboBox <>(choices);
        JButton op = new JButton("OK");
        op.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cb.getItemAt(cb.getSelectedIndex()).equals("Atores")){
                    consul.setText("TODOS OS ATORES");
                }else{
                    if(cb.getItemAt(cb.getSelectedIndex()).equals("Filmes")){
                        consul.setText("TODOS OS FILMES");
                    }else{
                        if(cb.getItemAt(cb.getSelectedIndex()).equals("Sessoes")){
                            consul.setText("TODOS AS SESSOES");
                        }else{
                            consul.setText("TODOS AS SALAS");
                        }
                }
                }
                //String opca = "Selecionaste: " + cb.getItemAt(cb.getSelectedIndex());
                //consul.setText(opca);
            }
        });
        geral.add(cb);
        geral.add(op);
        return geral;
    }
    public JButton butVoltar(){
        JButton buttonVoltar=new JButton("<<Voltar");
        buttonVoltar.addActionListener(actionEvent -> homePage.gestorPage());
        return buttonVoltar;
    }
}
