package Inferfaces;

import DadosPermanentes.*;
import Gestor.Gestor;
import Cliente.Cliente;
import Cliente.WatchDog;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HomePage extends JFrame {
    private JFrame frame;
    private JPanel panel;
    private JPanel header;
    private JPanel body;
    private JPanel footer;
    private WatchDog watchDog;
    private Cliente cliente;
    private Gestor gestor;
    private SbdHandler sbdHandler;
    private Date dia;
    private JLabel hourLabel;

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
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        watchDog=new WatchDog(this,5);
        watchDog.start();
        update();
    }

    public void newCliente(SbdHandler sbdHandler) throws SQLException {
        //header=new TopLabel();
        cliente =new Cliente(sbdHandler);
        this.sbdHandler=sbdHandler;
        interfaceFilmes();
    }
    /*public void newGestor(SbdHandler sbdHandler) throws SQLException {
        header=new TopLabel();
        gestor =new Gestor(sbdHandler);
        this.sbdHandler=sbdHandler;
        gestorPage();
    }*/

    public void interfaceFilmes(){
        body=new PanelFilmes(cliente.getFilmes(dia),dia,this);
        footer=new PanelBotLabelEmpty();
        update();
    }

    public void detailsPage(Filme filme,JPanel butoesSessoes){
        cliente.setFilme(filme);
        watchDog.turnOn();
        body=new PanelInfoFilme(filme,butoesSessoes,this);
        footer=new PanelBotLabelEmpty();
        update();
    }
    public void sitsPage(Sessao sessao){
        cliente.setSessao(sessao);
        interfaceSits();
    }
    public void interfaceSits(){
        watchDog.turnOn();
        body=new InterfaceSits(cliente.getSessao(),this);
        update();
    }
    public void interfaceTiposBilhetesNew(ArrayList<Bilhete> bilhetes){
        cliente.setBilhetes(bilhetes);
        interfaceTiposBilhetes();
    }

    public void interfaceTiposBilhetes(){
        body=new InterfaceTipoBilhete(cliente.getBilhetes(),this);
        update();
    }
    public void interfaceResumoNew(JLabel[] tipoBilhetes){
        cliente.setTiposDeBilhetes(tipoBilhetes);
        interfaceResumo();
    }
    public void interfaceResumo(){
        body=new InterfaceResumo(cliente.getSessao(),cliente.getBilhetes(),cliente.getTipoDeBilhetes(),this);
        update();
    }
    public void concluirCompra(ArrayList<Bilhete> bilhetes){
        sbdHandler.concluirCompra(bilhetes);
        resetPurchase();
    }
    public void gestorPage(){
        watchDog.turnOn();
        body=new InterfacePrincipalGestor(this);
        update();
    }
    public void gestorConsultar(){
        watchDog.turnOn();
        body=new InterfaceGestorConsultar(this);
        update();
    }
    public void gestorInserir(){
        watchDog.turnOn();
        body=new InterfaceGestorInserir(this);
        update();
    }
    public void gestorEliminar(){
        watchDog.turnOn();
        body=new InterfaceGestorEliminar(this);
        update();
    }


    public void failConnection(Exception e,int tentativa){
        body=new PanelFalhaConeccao(e,tentativa);
        footer=new PanelBotLabelEmpty();
        update();
    }
    public void update(){
        watchDog.resetCont();
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
            //newGestor(sbdHandler);
        } catch (SQLException e) {
            watchDog.turnOn();
        }
    }

    public void updateClock(Date date){
        if(hourLabel==null){
            setToplabelClock(date);
        }
        else {
            hourLabel.setText(strDay(date).split(" ")[1]);
        }
    }
    public void setToplabelClock(Date date){
        TopLabelClock label2=new TopLabelClock(date);
        header=label2;
        hourLabel=label2.getHourLabel();
        update();
    }
    public String strDay(Date dia){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(dia);
    }
}
