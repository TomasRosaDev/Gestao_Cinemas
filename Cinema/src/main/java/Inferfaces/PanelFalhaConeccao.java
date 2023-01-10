package Inferfaces;

import javax.swing.*;
import java.awt.*;

public class PanelFalhaConeccao extends InterfaceCliente{
    Exception e;
    int tentativa;
    public PanelFalhaConeccao(Exception e, int tentativa){
        this.e=e;
        this.tentativa=tentativa;
        JLabel msg= new JLabel("falha de coneccao, tentativa: "+tentativa);
        msg.setFont(new Font("Arial",Font.PLAIN, 15));
        msg.setForeground(Color.white);
        this.add(msg);
        JLabel msg2= new JLabel(e.getMessage());
        msg2.setFont(new Font("Arial",Font.PLAIN, 15));
        msg2.setForeground(Color.white);
        this.add(msg2);
    }
}
