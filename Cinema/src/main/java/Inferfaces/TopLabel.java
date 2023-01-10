package Inferfaces;

import javax.swing.*;
import java.awt.*;

public class TopLabel extends JPanel {
    public TopLabel(){
        JLabel cinema= new JLabel("CINEMAS NOZ");
        cinema.setFont(new Font("Arial",Font.PLAIN, 40));
        cinema.setForeground(Color.white);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(600, 50));
        this.setMaximumSize(new Dimension(600, 50));
        this.add(cinema);
    }
}
