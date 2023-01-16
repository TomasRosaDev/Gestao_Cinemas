package Inferfaces;

import DadosPermanentes.Bilhete;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InterfaceTipoBilhete extends InterfaceCliente{
    private ArrayList<Bilhete> bilhetes;
    private HomePage homePage;
    private int contLugares;
    private JLabel contLug;
    private JLabel[] contBilhetes={new JLabel("0"),new JLabel("0"),new JLabel("0")};
    JButton buttonSeguinte;
    public InterfaceTipoBilhete(ArrayList<Bilhete> bilhetes,HomePage homePage){
        this.bilhetes=bilhetes;
        this.homePage=homePage;
        contLugares=this.bilhetes.size();
        this.setLayout(new BorderLayout());
        this.add(panelTipos());
        this.add(progressPanel(),BorderLayout.SOUTH);
    }


    public JButton butVoltar(){
        JButton buttonVoltar=new JButton("<<Voltar");
        buttonVoltar.addActionListener(actionEvent -> homePage.interfaceSits());
        return buttonVoltar;
    }
    public void butSeguinte(){
        buttonSeguinte=new JButton("Seguinte>>");
        buttonSeguinte.setEnabled(false);
        buttonSeguinte.addActionListener(actionEvent -> homePage.interfaceResumoNew(contBilhetes));
    }

    public JPanel progressPanel(){
        JPanel progrPanel=new JPanel();
        progrPanel.setPreferredSize(new Dimension(500,50));
        progrPanel.setLayout(new BorderLayout());
        progrPanel.setBackground(Color.black);
        progrPanel.add(butVoltar(),BorderLayout.WEST);
        contLug=new JLabel("Lugares sem tipo: "+contLugares);
        contLug.setForeground(Color.white);
        progrPanel.add(contLug,BorderLayout.CENTER);
        butSeguinte();
        progrPanel.add(buttonSeguinte,BorderLayout.EAST);
        return progrPanel;
    }

    public JPanel panelTipos(){
        JPanel ggeral=new JPanel();
        ggeral.setOpaque(false);
        JPanel geral=new JPanel();
        geral.setLayout(new GridLayout(3,1));
        geral.setOpaque(false);
        geral.setPreferredSize(new Dimension(400,210));
        geral.add(panelTipo("Bilhetes Normais",0));
        geral.add(panelTipo("Bilhetes Crianca",1));
        geral.add(panelTipo("Bilhetes Estudante",2));
        ggeral.add(geral);
        return ggeral;
    }

    public JPanel panelTipo(String str,int indi){
        JPanel linha =new JPanel();
        linha.setLayout(new BorderLayout());
        linha.setOpaque(false);
        linha.setPreferredSize(new Dimension(400,70));
        JLabel nomeTipo=new JLabel(str);
        nomeTipo.setFont(new Font("Arial",Font.PLAIN, 20));
        linha.add(nomeTipo,BorderLayout.CENTER);
        JPanel center=new JPanel();
        center.setOpaque(false);
        center.setPreferredSize(new Dimension(210,70));
        center.setLayout(new GridLayout(1,3));
        contBilhetes[indi]=new JLabel("0");
        contBilhetes[indi].setFont(new Font("Arial",Font.PLAIN, 20));
        contBilhetes[indi].setHorizontalAlignment(SwingConstants.CENTER);
        center.add(adder(false,indi));
        center.add(contBilhetes[indi]);
        center.add(adder(true,indi));

        linha.add(center,BorderLayout.EAST);
        return linha;
    }

    public JButton adder(boolean add,int indi){
        JButton adder=new JButton();
        adder.setPreferredSize(new Dimension(70,70));
        adder.setOpaque(false);
        adder.setContentAreaFilled(false);
        String text;
        if(add){
            text=">";
            adder.addActionListener(event -> process(event, text,indi));
        }
        else {
            text="<";
            adder.addActionListener(event -> process(event, text,indi));
        }
        adder.setFont(new Font("Arial",Font.PLAIN, 20));
        adder.setText(text);

        return adder;
    }

    public void updateContador(){
        contLugares=(bilhetes.size()-(Integer.parseInt(contBilhetes[0].getText())+Integer.parseInt(contBilhetes[1].getText())+Integer.parseInt(contBilhetes[2].getText())));
        contLug.setText("Lugares sem tipo: "+contLugares);
        if (contLugares==0){
            buttonSeguinte.setEnabled(true);
        }
        else {
            buttonSeguinte.setEnabled(false);
        }
    }

    private void process(ActionEvent ae, String text,int indi) {
        int contN=Integer.parseInt(contBilhetes[indi].getText());
        int conttotal=Integer.parseInt(contLug.getText().split(" ")[3]);
        if(text.equals(">")){
            if (conttotal>0){
                contN++;
                contBilhetes[indi].setText(contN+"");
                updateContador();
            }
        }
        else if(text.equals("<")){
            if (contN>0){
                contN--;
                contBilhetes[indi].setText(contN+"");
                updateContador();
            }
        }
    }
}
