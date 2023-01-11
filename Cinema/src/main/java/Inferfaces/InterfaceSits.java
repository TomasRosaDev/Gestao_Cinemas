package Inferfaces;

import DadosPermanentes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        this.lugares=sessao.getSbdHandler().getLugares(sessao.getSala().getNumeroSala()+"");
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
        grid.setPreferredSize(new Dimension(lugares.length*100,lugares[0].length*50));
        grid.setLayout(new GridLayout(lugares.length,lugares[0].length));
        for (int i = 0; i < lugares.length; i++) {
            for (int j = 0; j < lugares[0].length; j++) {
                grid.add(lugarBut(lugares[i][j]));
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

    public JButton lugarBut(Lugar lugar){
        JButton button=new JButton(lugar.getNome() +"-"+lugar.getTipo());
        if(lugar.getTipo().equals(TipoLugar.Inexistente)){
            button.setVisible(false);
        } else if (sessao.getSbdHandler().getExisteBilhete(lugar.getNome(),sessao)) {
            button.setBackground(Color.red);
            button.setEnabled(false);
        }else {
           button.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent ae) {
                   changeButtonStage(button,lugar);
               }
           });
        }
        return button;
    }

    public void changeButtonStage(JButton but,Lugar lugar){
        if(but.getBackground().equals(Color.green)){
            but.setBackground(new JButton().getBackground());
            Iterator i=lugaresSelecionados.iterator();
            while (i.hasNext()){
                Lugar lugar1= (Lugar) i.next();
                if(lugar1.getNome().equals(lugar.getNome())){
                    i.remove();
                    break;
                }
            }
        }
        else {
            but.setBackground(Color.green);
            lugaresSelecionados.add(lugar);
        }
        atualizarContLugares();
        contLug.setText(contLugares+"");
    }

    public void atualizarContLugares(){
        contLugares=lugaresSelecionados.size();
        homePage.update();
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
}
