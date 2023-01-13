package Inferfaces;

import javax.swing.*;
import java.awt.*;

public class PanelBotLabelEmpty extends BotLabel{


    public PanelBotLabelEmpty(){
        JLabel msg=new JLabel("rodape");
        msg.setFont(new Font("Arial",Font.PLAIN, 10));
        msg.setForeground(Color.white);
        this.add(msg);
    }

}
