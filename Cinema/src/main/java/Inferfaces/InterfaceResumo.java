package Inferfaces;

import DadosPermanentes.Bilhete;
import DadosPermanentes.Filme;
import DadosPermanentes.Sessao;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.util.ArrayList;

public class InterfaceResumo extends InterfaceCliente{
    private Sessao sessao;
    private Filme filme;
    private ArrayList<Bilhete> bilhetes;
    private HomePage homePage;
    private JLabel[] tipoBilhetes;
    public InterfaceResumo(Sessao sessao,ArrayList<Bilhete> bilhetes,JLabel[] tipoBilhetes,HomePage homePage){
        this.sessao=sessao;
        this.filme=sessao.getFilme();
        this.bilhetes=bilhetes;
        this.tipoBilhetes=tipoBilhetes;
        this.homePage=homePage;
        this.setLayout(new BorderLayout());
        this.add(filmeDetailsprev(),BorderLayout.CENTER);
        this.add(footer(),BorderLayout.SOUTH);
    }

    public JPanel filmeDetailsprev(){
        JPanel filmeDetails=new JPanel();
        filmeDetails.setOpaque(false);
        filmeDetails.add(filmeDetails());
        filmeDetails.add(bilhetesPreco());
        return filmeDetails;
    }

    public JPanel filmeDetails(){
        JPanel filmeDetails=new JPanel();
        filmeDetails.setPreferredSize(new Dimension(550,210));
        filmeDetails.setLayout(new BorderLayout());
        filmeDetails.add(panelImagemFilme(),BorderLayout.WEST);
        filmeDetails.add(panelCenterFilmeInfo(),BorderLayout.CENTER);
        return filmeDetails;
    }
    public JPanel panelImagemFilme(){
        JPanel panelImagem=new JPanel();
        panelImagem.setPreferredSize(new Dimension(140,210));
        try{
            panelImagem.add(new JLabel(new ImageIcon(getScaledImage(filme.getImagem(),140,210))));
        }catch (Exception e){
            panelImagem.setBackground(Color.PINK);
            JLabel imagemLabel=new JLabel("Imagem Filme");
            imagemLabel.setForeground(Color.GRAY);
            imagemLabel.setFont(new Font("Arial",Font.PLAIN,10));
            panelImagem.add(imagemLabel);
        }
        return panelImagem;
    }

    public JPanel panelCenterFilmeInfo(){
        JPanel info=new JPanel();
        info.setPreferredSize(new Dimension(450,210));
        info.setLayout(new GridLayout(4,1));
        JLabel nomeButt=new JLabel(filme.getTitulo()+" ("+filme.getAnoString()+")");
        nomeButt.setFont(new Font("Arial",Font.PLAIN, 20));
        nomeButt.setForeground(Color.BLACK);
        JLabel idade=new JLabel("Idade Minima: "+filme.getIdadeMinima());
        JLabel generos=new JLabel("Generos: "+filme.getGenerosStr());
        JLabel sessaoLabel=new JLabel("Sala: "+sessao.getSala().getNumeroSala()+"  Horario: "+sessao.getHoraInicioStr());
        info.add(nomeButt);
        info.add(idade);
        info.add(generos);
        info.add(sessaoLabel);
        return info;
    }


    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    public JButton butVoltar(){
        JButton buttonVoltar=new JButton("<<Voltar");
        buttonVoltar.addActionListener(actionEvent -> homePage.interfaceTiposBilhetes());
        return buttonVoltar;
    }
    public JButton butSeguinte(){
        JButton buttonSeguinte=new JButton("Concluir>>");
        buttonSeguinte.addActionListener(actionEvent -> homePage.concluirCompra());
        return buttonSeguinte;
    }
    public JPanel footer(){
        JPanel progrPanel=new JPanel();
        progrPanel.setPreferredSize(new Dimension(500,50));
        progrPanel.setLayout(new BorderLayout());
        progrPanel.setBackground(Color.black);
        progrPanel.add(butVoltar(),BorderLayout.WEST);
        JLabel msg=new JLabel("Verifique a sua compra");
        msg.setForeground(Color.white);
        progrPanel.add(msg,BorderLayout.CENTER);
        progrPanel.add(butSeguinte(),BorderLayout.EAST);
        return progrPanel;
    }
    public JPanel bilhetesPreco(){
        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(4,1));
        float despesa=0.0f;
        for (int i = 0; i < 3; i++) {
            int cont=Integer.parseInt(tipoBilhetes[i].getText());
            if (cont>0){
                String text="";
                float preco;
                if(i==0){
                    text="Bilhetes Normais: ";
                    preco=5.6f;
                }
                else if(i==1){
                    text="Bilhetes Crianca: ";
                    preco=2.3f;
                }else {
                    text="Bilhetes Estudante: ";
                    preco=4f;
                }
                float preco2=preco*cont;
                despesa+=preco2;
                JLabel panelTipo=new JLabel(text+cont+" * "+preco+"EU = "+preco2+"EU");
                panel.add(panelTipo);
            }
        }
        JLabel despesaLabel=new JLabel("Despesa Total: "+despesa+"EU");
        panel.add(despesaLabel);
        return panel;
    }
}
