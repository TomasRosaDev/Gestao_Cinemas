package Inferfaces;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public abstract class InterfaceGestor extends JPanel {
    Image bgImage;

    public InterfaceGestor(){
        try{
            bgImage= ImageIO.read(new File("Cinema/src/main/java/Imagens/BackgroundNoz.png"));
        }
        catch (Exception e){
            //this.setBackground(Color.gray);
        }
        this.setPreferredSize(new Dimension(550,250));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bgImage,0,0,this);
    }
}