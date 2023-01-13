package Inferfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGestorEliminar extends InterfaceGestor{
    private HomePage homePage;

    private JPanel elimi;

    public InterfaceGestorEliminar(HomePage homePage){
        this.homePage=homePage;
        this.setLayout(new BorderLayout());
        this.add(informacoes(),BorderLayout.NORTH);
        this.add(ecraPrincipal());

    }
    public JPanel informacoes(){
        JPanel info=new JPanel();
        info.setPreferredSize(new Dimension(600,25));
        info.setBackground(Color.lightGray);
        info.add(new JLabel("Eliminar dados"));
        return info;
    }
    public JPanel ecraPrincipal(){
        JPanel geral=new JPanel();
        geral.setOpaque(false);
        geral.add(escolha());
        geral.add(eliminar());
        geral.add(butVoltar());
        return geral;
    }
    public JPanel eliminar(){
        JPanel inserir=new JPanel();
        inserir.setPreferredSize(new Dimension(600,500));
        inserir.setOpaque(false);
        elimi = new JPanel();
        elimi.setOpaque(false);
        inserir.add(elimi);
        return inserir;
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
                    elimi.add(ator());
                    elimi.revalidate();
                    elimi.repaint();
                }else {
                    if (cb.getItemAt(cb.getSelectedIndex()).equals("Filmes")) {
                        elimi.add(filme());
                        elimi.revalidate();
                        elimi.repaint();
                    } else {
                        if (cb.getItemAt(cb.getSelectedIndex()).equals("Sessoes")) {
                            elimi.add(sessao());
                            elimi.revalidate();
                            elimi.repaint();
                        } else {
                            elimi.add(sala());
                            elimi.revalidate();
                            elimi.repaint();
                        }
                    }
                }
            }
        });
        geral.add(cb);
        geral.add(op);
        return geral;
    }
    public JPanel ator(){
        elimi.removeAll();
        JPanel sessoes = new JPanel();

        return sessoes;
    }
    public JPanel filme(){
        elimi.removeAll();
        JPanel salas = new JPanel();

        return salas;
    }
    public JPanel sessao(){
        elimi.removeAll();
        JPanel sessoes = new JPanel();

        return sessoes;
    }
    public JPanel sala(){
        elimi.removeAll();
        JPanel salas = new JPanel();

        return salas;
    }


    public JButton butVoltar(){
        JButton buttonVoltar=new JButton("<<Voltar");
        buttonVoltar.addActionListener(actionEvent -> homePage.gestorPage());
        return buttonVoltar;
    }
}
