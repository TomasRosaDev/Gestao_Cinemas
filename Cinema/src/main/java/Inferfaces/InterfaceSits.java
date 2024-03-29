package Inferfaces;

import DadosPermanentes.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class InterfaceSits extends InterfaceCliente{

    private Sessao sessao;
    private Bilhete[][] bilhetes;
    private ArrayList<Bilhete> bilhetesSelecionados;
    private HomePage homePage;
    private int contLugares;
    private JLabel contLug;
    private JButton buttonSeguinte;
    public InterfaceSits(Sessao sessao, HomePage homePage){
        this.sessao=sessao;
        this.homePage=homePage;
        this.contLugares=0;
        this.bilhetesSelecionados= new ArrayList<>();
        this.bilhetes=sessao.getSbdHandler().getBilhetes(sessao);
        this.setLayout(new BorderLayout());
        this.add(informacoes(),BorderLayout.NORTH);
        this.add(maisEcra(),BorderLayout.CENTER);
    }

    public JPanel informacoes(){
        JPanel info=new JPanel();
        info.setPreferredSize(new Dimension(600,25));
        info.setBackground(Color.lightGray);
        info.add(new JLabel(sessao.getFilme().getTitulo()+" ("+sessao.getFilme().getAnoString()+")  Sala: "+sessao.getSala().getNumeroSala()+"  Sessao: "+sessao.getHoraInicioStr()));
        return info;
    }

    public JPanel maisEcra(){
        JPanel geral=new JPanel();
        geral.setOpaque(false);
        JPanel ecra=new JPanel();
        ecra.setPreferredSize(new Dimension(500,20));
        ecra.setBackground(Color.black);
        JLabel ecraLabel=new JLabel("ECRA");
        ecraLabel.setForeground(Color.white);
        ecra.add(ecraLabel);
        geral.add(ecra);
        geral.add(lugaresPanel());
        geral.add(progressPanel());
        return geral;
    }

    public JPanel lugaresPanel(){
        JPanel grid= new JPanel();
        int width=bilhetes[0].length;
        int height=bilhetes.length;
        grid.setPreferredSize(new Dimension(width*30,height*30));
        grid.setLayout(new GridLayout(bilhetes.length,bilhetes[0].length));
        grid.setOpaque(false);
        for (int i = 0; i < bilhetes.length; i++) {
            for (int j = 0; j < bilhetes[0].length; j++) {
                //grid.add(lugarBut(lugares[i][j]));
                grid.add(new BotaoLugar(bilhetes[i][j],this));
            }
        }
        return grid;
    }

    public JPanel progressPanel(){
        JPanel progrPanel=new JPanel();
        progrPanel.setPreferredSize(new Dimension(500,50));
        progrPanel.setLayout(new BorderLayout());
        progrPanel.setBackground(Color.black);
        progrPanel.add(butVoltar(),BorderLayout.WEST);
        contLug=new JLabel(contLugares+"");
        contLug.setForeground(Color.white);
        progrPanel.add(contLug,BorderLayout.CENTER);
        butSeguinte();
        progrPanel.add(buttonSeguinte,BorderLayout.EAST);
        return progrPanel;
    }

    public void removeBilheteContador(Bilhete bilhete){
        Iterator i=bilhetesSelecionados.iterator();
        while (i.hasNext()){
            Bilhete bilhete1= (Bilhete) i.next();
            if(bilhete1.getLugar().getNome().equals(bilhete.getLugar().getNome())){
                i.remove();
                break;
            }
        }
    }

    public void atualizarContLugares(){
        if((contLugares=bilhetesSelecionados.size())==0){
            buttonSeguinte.setEnabled(false);
        }
        else {
            buttonSeguinte.setEnabled(true);
        }
        //homePage.update();
        contLug.setText(contLugares+"");
    }

    public JButton butVoltar(){
        JButton buttonVoltar=new JButton("<<Voltar");
        buttonVoltar.addActionListener(actionEvent -> homePage.interfaceFilmes());
        return buttonVoltar;
    }

    public void butSeguinte(){
        buttonSeguinte=new JButton("Seguinte>>");
        buttonSeguinte.setEnabled(false);
        buttonSeguinte.addActionListener(actionEvent -> homePage.interfaceTiposBilhetesNew(bilhetesSelecionados));
    }

    public ArrayList<Bilhete> getBilhetesSelecionados() {
        return bilhetesSelecionados;
    }
}
