package Inferfaces;

import Cliente.Cliente;
import Cliente.WatchDog;
import DadosPermanentes.Filme;
import DadosPermanentes.SbdHandler;
import DadosPermanentes.Sessao;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class HomePage extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private JPanel header;
    private JPanel body;
    private JPanel footer;
    private WatchDog watchDog;
    private Cliente cliente;
    private SbdHandler sbdHandler;
    private Date dia;

    public HomePage(){
        //dia=new java.sql.Date(System.currentTimeMillis());
        dia=Date.valueOf("2022-09-28");
        frame=new JFrame();
        frame.setVisible(true);
        frame.setSize(600, 750);
        frame.setResizable(false);
        panel=new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);
        panel.setPreferredSize(new Dimension(600,750));
        header=new TopLabel();
        body=new LoadingPanel();
        footer=new PanelBotLabelEmpty();
        update();
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        watchDog=new WatchDog(this,5);
        watchDog.start();
    }

    public void newCliente(SbdHandler sbdHandler) throws SQLException {
        header=new TopLabel();
        cliente =new Cliente(sbdHandler);
        this.sbdHandler=sbdHandler;
        interfaceFilmes();
    }

    public void interfaceFilmes() throws SQLException {
        ArrayList<Filme> filmes=sbdHandler.listaFilmes(dia);
        body=new PanelFilmes(filmes,dia,this);
        footer=new PanelBotLabelEmpty();
        update();
    }



    public void detailsPage(Filme filme){
        cliente.setFilme(filme);
        watchDog.turnOn();
        body=new PanelBotLabelEmpty();
        footer=new PanelBotLabelEmpty();
        update();
    }
    public void sitsPage(Sessao sessao){
        cliente.setSessao(sessao);
        watchDog.turnOn();
        body=new PanelBotLabelEmpty();
        update();
    }
    public void failConnection(Exception e,int tentativa){
        body=new PanelFalhaConeccao(e,tentativa);
        footer=new PanelBotLabelEmpty();
        update();
    }
    public void update(){
        panel.removeAll();
        panel.add(header,BorderLayout.NORTH);
        panel.add(body,BorderLayout.CENTER);
        panel.add(footer,BorderLayout.SOUTH);
        frame.add(panel);
        frame.setVisible(true);
    }

    public SbdHandler getSbdHandler(){
        return sbdHandler;
    }

    public void resetPurchase(){
        try {
            newCliente(sbdHandler);
        } catch (SQLException e) {
            watchDog.turnOn();
        }
    }
}
