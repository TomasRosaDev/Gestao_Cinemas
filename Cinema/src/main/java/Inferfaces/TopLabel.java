package Inferfaces;

import javax.swing.*;
import java.awt.*;

public class TopLabel extends JPanel {
    public TopLabel(){
        JPanel cinema=new JPanel();
        cinema.setOpaque(false);
        JLabel cinemaLabel= new JLabel("CINEMAS NOZ");
        cinemaLabel.setFont(new Font("Arial",Font.PLAIN, 40));
        cinemaLabel.setForeground(Color.white);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(600, 50));
        this.setMaximumSize(new Dimension(600, 50));
        this.setLayout(new BorderLayout());
        cinema.add(cinemaLabel);
        this.add(cinema,BorderLayout.CENTER);
    }
}
