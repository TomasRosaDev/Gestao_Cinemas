package Inferfaces;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TopLabelClock extends TopLabel{
    TopLabelClock(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss");
        String[]dateStr=sdf.format(date).split(" ");
        String day=dateStr[0];
        JLabel dayLabel= new JLabel(day);
        dayLabel.setFont(new Font("Arial",Font.PLAIN, 15));
        dayLabel.setForeground(Color.white);
        add(dayLabel,BorderLayout.WEST);
        String hour=dateStr[1];
        JLabel hourLabel= new JLabel(hour);
        hourLabel.setFont(new Font("Arial",Font.PLAIN, 15));
        hourLabel.setForeground(Color.white);
        add(hourLabel,BorderLayout.EAST);
    }
}
