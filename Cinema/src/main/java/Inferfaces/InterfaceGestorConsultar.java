package Inferfaces;

import DadosPermanentes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InterfaceGestorConsultar extends InterfaceGestor{
    private HomePage homePage;
    private Ator[] atores;
    private Filme[] filme;
    private Sessao[] sessoes;
    private Sala[] salas;
    private SbdHandler sbd;
    private JLabel consul;

    public InterfaceGestorConsultar(HomePage homePage, SbdHandler handler){
        this.homePage=homePage;
        this.setLayout(new BorderLayout());
        this.add(informacoes(),BorderLayout.NORTH);
        this.add(ecraPrincipal());
        this.sbd= handler;
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
        String[] choices = { "Escolha uma opção...","Generos","Atores","Realizadores","Distribuidores","Filmes", "Sessoes","Salas"};
        JComboBox<String> cb = new JComboBox <>(choices);
        JButton op = new JButton("OK");
        op.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cb.getItemAt(cb.getSelectedIndex()).equals("Generos")) {
                    consul.setText("TODOS OS GENEROS");
                    JLabel label = new JLabel(sbd.getGeneros().toString());
                    geral.add(label);

                } else if (cb.getItemAt(cb.getSelectedIndex()).equals("Atores")) {
                    consul.setText("TODOS OS ATORES");

                    JLabel label = new JLabel(sbd.getAtores().toString());
                    geral.add(label);

                } else if (cb.getItemAt(cb.getSelectedIndex()).equals("Realizadores")) {
                    //consul.setText("TODOS OS REALIZADORES");
                    ArrayList<Realizador> realizadores = sbd.getRealizadores();

                    for(Realizador realizador : realizadores){
                        consul.setText(consul.getText()+" "+realizador.getNome());
                    }

                } else if (cb.getItemAt(cb.getSelectedIndex()).equals("Distribuidores")) {
                    consul.setText("TODOS OS DISTRIBUIDORES");

                    JLabel label = new JLabel(sbd.getRealizadores().toString());
                    geral.add(label);
                } else if (cb.getItemAt(cb.getSelectedIndex()).equals("Filmes")) {
                    consul.setText("TODOS OS FILMES");

                    JLabel label = new JLabel(sbd.getRealizadores().toString());
                    geral.add(label);
                } else if (cb.getItemAt(cb.getSelectedIndex()).equals("Sessoes")) {
                    consul.setText("TODOS AS SESSOES");

                    JLabel label = new JLabel(sbd.getSessoes().toString());
                    geral.add(label);
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
