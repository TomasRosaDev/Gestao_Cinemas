package Inferfaces;

import DadosPermanentes.Filme;
import DadosPermanentes.Sessao;

import javax.swing.*;
import java.awt.*;

public class PanelInfoFilme extends InterfaceCliente{
    private Filme filme;
    private HomePage homePage;

    public PanelInfoFilme(Filme filme, JPanel butoesSessoes,HomePage homePage){
        this.filme=filme;
        this.homePage=homePage;

        JPanel panel=new JPanel();
        panel.setPreferredSize(new Dimension(600,450));
        panel.setLayout(new BorderLayout());
        panel.add(imagem(),BorderLayout.WEST);
        panel.add(infoDetail(butoesSessoes),BorderLayout.CENTER);
        add(panel);
        add(buttonVoltar());
    }

    public JPanel imagem(){
        JPanel imagem=new JPanel();
        imagem.setPreferredSize(new Dimension(300,450));
        /*try{
            filme.getImagem();
        }*/
        JLabel labelFalha=new JLabel("foto");
        labelFalha.setForeground(Color.black);
        imagem.setBackground(Color.PINK);
        imagem.add(labelFalha);
        return imagem;
    }

    public JPanel infoDetail(JPanel butoesSessoes){
        JPanel infoDetail=new JPanel();
        infoDetail.setLayout(new BorderLayout());
        infoDetail.add(butoesSessoes,BorderLayout.SOUTH);

        JPanel infoLabels=new JPanel();
        String [] infos=filme.toString().split("//");
        for (int i=0;i<infos.length;i++) {
            JLabel label=new JLabel("<html>"+infos[i]+"<html>");
            if(i==0){
                label.setFont(new Font("Arial",Font.PLAIN,20));
            }
            else {
                label.setFont(new Font("Arial",Font.PLAIN,12));
            }
            JPanel panel2=new JPanel();

            if(i==infos.length-1){
                label.setPreferredSize(new Dimension(300,150));
                panel2.setPreferredSize(new Dimension(300,150));
            }
            else {
                panel2.setPreferredSize(new Dimension(300,25));
            }
            panel2.add(label);

            infoLabels.add(panel2);
        }
        infoLabels.setPreferredSize(new Dimension(300,450));

        infoDetail.add(infoLabels,BorderLayout.CENTER);
        return infoDetail;
    }

    public JButton buttonVoltar(){
        JButton buttonVoltar=new JButton("<<Voltar");
        buttonVoltar.addActionListener(actionEvent -> homePage.interfaceFilmes());
        return buttonVoltar;
    }
}
