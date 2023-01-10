package Inferfaces;

import javax.swing.*;
import java.awt.*;

public class LoadingPanel extends InterfaceCliente{
    public LoadingPanel(){
        this.setPreferredSize(new Dimension(600,600));
        JLabel msg=new JLabel("Loading ...");
        msg.setFont(new Font("Arial",Font.PLAIN, 30));
        msg.setForeground(Color.BLACK);
        this.add(msg);
    }
}
