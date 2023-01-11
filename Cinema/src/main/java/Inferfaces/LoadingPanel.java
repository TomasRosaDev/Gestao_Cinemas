package Inferfaces;

import javax.swing.*;
import java.awt.*;

public class LoadingPanel extends InterfaceCliente{
    public LoadingPanel(){
        this.setPreferredSize(new Dimension(600,600));
        this.setLayout(new BorderLayout());
        JPanel panel=new JPanel();
        JLabel msg=new JLabel("Loading ...");
        msg.setFont(new Font("Arial",Font.PLAIN, 30));
        msg.setForeground(Color.BLACK);
        panel.add(msg);
        panel.setOpaque(false);
        this.add(panel,BorderLayout.CENTER);
    }
}
