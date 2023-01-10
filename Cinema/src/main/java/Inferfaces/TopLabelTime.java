package Inferfaces;

import Cliente.WatchDog;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class TopLabelTime extends JPanel {
    Date date;


    public TopLabelTime(Date date){
        this.date=date;
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(600, 50));
        this.setMaximumSize(new Dimension(600, 50));

    }
}
