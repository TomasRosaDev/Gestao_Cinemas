package Inferfaces;

import DadosPermanentes.*;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BotaoLugar extends JButton {
    private Bilhete bilhete;
    private boolean escolhido;
    private String imageName;
    private InterfaceSits interfaceSits;

    public BotaoLugar(Bilhete bilhete,InterfaceSits interfaceSits){
        this.interfaceSits=interfaceSits;
        this.bilhete=bilhete;
        escolhido=false;
        verEstadoBilhete();
    }


    public void verEstadoBilhete() {
        Lugar lugar=bilhete.getLugar();
        TipoLugar tipoLugar=lugar.getTipo();
        imageName=tipoLugar.toString();
        if(tipoLugar.equals(TipoLugar.Inexistente)){
            setVisible(false);
            setEnabled(false);
        }else {
            if(bilhete.isOcupado()){
                imageName+="_Ocupado";
                //setEnabled(false);
                setContent();
            }
            else {
                setContent();
                addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        changeButtonStage();
                    }
                });
            }
        }
    }

    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    private void setContent(){
        try {
            setOpaque(false);
            setContentAreaFilled(false);
            setBorderPainted(false);
            Image image= ImageIO.read(new File("Cinema/src/main/java/Imagens/"+imageName+".png"));
            Icon i=new ImageIcon(getScaledImage(image,30,30));
            setIcon(i);
        } catch (IOException e) {
            if(bilhete.isOcupado()){
                setBackground(Color.red);
                setEnabled(false);
            }
            else{
                if (escolhido){
                    setBackground(Color.green);
                }else {
                    String tipoLugar=bilhete.getLugar().getTipo().toString();
                    if(tipoLugar.equals(TipoLugar.Normal)){
                        setBackground(new JButton().getBackground());
                    } else if (tipoLugar.equals(TipoLugar.Vip)) {
                        setBackground(Color.yellow);
                    }else {
                        setBackground(Color.blue);
                    }
                }
            }
            setText("<html>"+bilhete.getLugar().getNome()+"<html>");
        }
    }

    private void changeButtonStage(){
        escolhido=!escolhido;
        if(escolhido){
            imageName+="_Escolhido";
            interfaceSits.getBilhetesSelecionados().add(bilhete);
        }
        else {
            imageName=imageName.split("_")[0];
            interfaceSits.removeBilheteContador(bilhete);
        }
        setContent();
        interfaceSits.atualizarContLugares();
    }
}
