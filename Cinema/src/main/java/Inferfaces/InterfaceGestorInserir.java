package Inferfaces;

import DadosPermanentes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGestorInserir extends InterfaceGestor{
    private HomePage homePage;
    /*
    private Ator[] atores;
    private Filme[] filme;
    private Sessao[] sessoes;
    private Sala[] salas;
    private SbdHandler sbd;
    */
    private JPanel inser;

    public InterfaceGestorInserir(HomePage homePage){
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
        geral.add(inserir());
        geral.add(butVoltar());
        return geral;
    }
    public JPanel inserir(){
        JPanel inserir=new JPanel();
        inserir.setPreferredSize(new Dimension(600,500));
        inserir.setOpaque(false);
        inser = new JPanel();
        inser.setOpaque(false);
        inserir.add(inser);
        return inserir;
    }
    public JPanel escolha(){
        JPanel geral=new JPanel();
        geral.setOpaque(false);
        geral.setLayout(new GridLayout(3, 1));
        geral.add(new JLabel("Escolha o que pretende visualizar:"));
        String[] choices = { "Escolha uma opção...","Atores","Filmes", "Sessoes","Salas"};
        JComboBox<String> cb = new JComboBox <>(choices);
        JButton op = new JButton("OK");

        op.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(cb.getItemAt(cb.getSelectedIndex()).equals("Atores")){
                    inser.add(ator());
                    inser.revalidate();
                    inser.repaint();
                }else {
                    if (cb.getItemAt(cb.getSelectedIndex()).equals("Filmes")) {
                        inser.add(filme());
                        inser.revalidate();
                        inser.repaint();
                    } else {
                        if (cb.getItemAt(cb.getSelectedIndex()).equals("Sessoes")) {
                            inser.add(sessao());
                            inser.revalidate();
                            inser.repaint();
                        } else {
                            inser.add(sala());
                            inser.revalidate();
                            inser.repaint();
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
        inser.removeAll();
        JPanel atores = new JPanel();
        atores.setOpaque(false);
        JLabel result= new JLabel("");
        JLabel nome = new JLabel("Nome: ");
        JTextField nomeText = new JTextField("Introduza o nomde do ator...");
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                String name= nomeText.getText();
                result.setText(name);
            }
        });
        atores.setPreferredSize(new Dimension(300, 100));
        atores.setLayout(new GridLayout(4, 1));
        atores.add(nome);
        atores.add(nomeText);
        atores.add(result);
        atores.add(ok);
        return atores;
    }
    public JPanel filme(){
        inser.removeAll();
        JPanel filmes = new JPanel();
        filmes.setOpaque(false);
        JLabel tit = new JLabel("Titulo: ");
        JTextField tittext = new JTextField("");
        JLabel titorig = new JLabel("Titulo Original: ");
        JTextField titorigtext = new JTextField("");
        JLabel ano = new JLabel("Ano: ");
        JTextField anotext = new JTextField("");
        JLabel datestreia = new JLabel("Data Estreia: ");
        JTextField datestreiatext = new JTextField("");
        JLabel desc = new JLabel("Descricao:  ");
        JTextField desctext = new JTextField("");
        JLabel duracao = new JLabel("Duracao (em minutos): ");
        JTextField duracaotext = new JTextField("");
        JLabel pais = new JLabel("Pais: ");
        JTextField paistext = new JTextField("");
        JLabel idade = new JLabel("Idade Minima: ");
        JTextField idadetext = new JTextField("");
        JLabel reali = new JLabel("Realizador: ");
        JTextField realitext = new JTextField("");
        JLabel distr = new JLabel("Distribuidor: ");
        JTextField distrtext = new JTextField("");

        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                String[] filme= {tittext.getText(), titorigtext.getText(), anotext.getText(), datestreiatext.getText(),
                        desctext.getText(), duracaotext.getText(), paistext.getText(), idadetext.getText(), realitext.getText(),
                        distrtext.getText()};
            }
        });
        filmes.setPreferredSize(new Dimension(300, 500));
        filmes.setLayout(new GridLayout(21, 1));

        filmes.add(tit);
        filmes.add(tittext);
        filmes.add(titorig);
        filmes.add(titorigtext);
        filmes.add(ano);
        filmes.add(anotext);
        filmes.add(datestreia);
        filmes.add(datestreiatext);
        filmes.add(desc);
        filmes.add(desctext);
        filmes.add(duracao);
        filmes.add(duracaotext);
        filmes.add(pais);
        filmes.add(paistext);
        filmes.add(idade);
        filmes.add(idadetext);
        filmes.add(reali);
        filmes.add(realitext);
        filmes.add(distr);
        filmes.add(distrtext);

        filmes.add(ok);
        return filmes;
    }
    public JPanel sessao(){
        inser.removeAll();
        JPanel sessoes = new JPanel();

        return sessoes;
    }
    public JPanel sala(){
        inser.removeAll();
        JPanel salas = new JPanel();

        return salas;
    }

    public JButton butVoltar(){
        JButton buttonVoltar=new JButton("<<Voltar");
        buttonVoltar.addActionListener(actionEvent -> homePage.gestorPage());
        return buttonVoltar;
    }
}
