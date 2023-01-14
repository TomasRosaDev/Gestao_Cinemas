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
    private Lugar[][] lugares;
    private ArrayList<Lugar> lugaresSelecionados;
    private HomePage homePage;
    private int contLugares;
    JLabel contLug;
    public InterfaceSits(Sessao sessao, HomePage homePage){
        this.sessao=sessao;
        this.homePage=homePage;
        this.contLugares=0;
        this.lugaresSelecionados= new ArrayList<>();
        this.lugares=sessao.getSbdHandler().getLugares(sessao.getSala().getNumeroSala());
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
        int width=lugares[0].length;
        int height=lugares.length;
        grid.setPreferredSize(new Dimension(width*50,height*50));
        grid.setLayout(new GridLayout(lugares.length,lugares[0].length));
        grid.setOpaque(false);
        for (int i = 0; i < lugares.length; i++) {
            for (int j = 0; j < lugares[0].length; j++) {
                //grid.add(lugarBut(lugares[i][j]));
                grid.add(new BotaoLugar(new Bilhete(lugares[i][j],sessao, sessao.getSbdHandler()),this));
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
        progrPanel.add(butSeguinte(),BorderLayout.EAST);
        return progrPanel;
    }

    public void removeLugarContador(Lugar lugar){
        Iterator i=lugaresSelecionados.iterator();
        while (i.hasNext()){
            Lugar lugar1= (Lugar) i.next();
            if(lugar1.getNome().equals(lugar.getNome())){
                i.remove();
                break;
            }
        }
    }

    public void atualizarContLugares(){
        contLugares=lugaresSelecionados.size();
        //homePage.update();
        contLug.setText(contLugares+"");
    }

    public JButton butVoltar(){
        JButton buttonVoltar=new JButton("<<Voltar");
        buttonVoltar.addActionListener(actionEvent -> homePage.interfaceFilmes());
        return buttonVoltar;
    }

    public JButton butSeguinte(){
        JButton buttonVoltar=new JButton("Seguinte>>");
        buttonVoltar.addActionListener(actionEvent -> homePage.interfaceTiposBilhetes(lugaresSelecionados,sessao));
        return buttonVoltar;
    }

    public ArrayList<Lugar> getLugaresSelecionados() {
        return lugaresSelecionados;
    }
}
