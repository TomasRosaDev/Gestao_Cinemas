package Inferfaces;

import DadosPermanentes.Filme;
import DadosPermanentes.Sala;
import DadosPermanentes.SbdHandler;
import DadosPermanentes.Sessao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PanelFilmeIndiv extends JPanel {
    private Filme filme;
    private Date dia;
    private HomePage homePage;
    private  GridBagConstraints gbc;
    private JPanel butoesDasSessoes;
    public PanelFilmeIndiv(Filme filme,Date dia,HomePage homePage){
        this.filme=filme;
        this.dia=dia;
        this.homePage=homePage;
        this.setPreferredSize(new Dimension(550,210));
        //this.setBackground(Color.darkGray);
        this.setLayout(new BorderLayout());
        this.add(panelImagemFilme(),BorderLayout.WEST);
        this.add(panelCenterFilme(),BorderLayout.CENTER);
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

    public JPanel panelCenterFilme(){
        JPanel panelCentral=new JPanel();
        panelCentral.setPreferredSize(new Dimension(450,210));
        panelCentral.setLayout(new BorderLayout());
        butoesDasSessoes=panelSessoes();
        panelCentral.add(butoesDasSessoes,BorderLayout.SOUTH);
        panelCentral.add(panelCenterFilmeInfo(),BorderLayout.CENTER);
        return panelCentral;
    }

    public JPanel panelCenterFilmeInfo(){
        JPanel info=new JPanel();
        info.setLayout(new GridLayout(3,1));
        JPanel nomeButt=new JPanel();
        nomeButt.setLayout(new BorderLayout());
        JLabel nome=new JLabel(filme.getTitulo()+" ("+filme.getAnoString()+")");
        nome.setFont(new Font("Arial",Font.PLAIN, 20));
        nome.setForeground(Color.BLACK);
        nomeButt.add(nome,BorderLayout.CENTER);
        JButton infoInterface=new JButton("Informacoes>>");
        infoInterface.addActionListener(actionEvent -> homePage.detailsPage(filme,butoesDasSessoes));
        nomeButt.add(infoInterface,BorderLayout.EAST);
        JLabel idade=new JLabel("Idade Minima: "+filme.getIdadeMinima());
        JLabel generos=new JLabel("Generos: "+filme.getGenerosStr());
        info.add(nomeButt);
        info.add(idade);
        info.add(generos);
        return info;
    }

    public JPanel panelSessoes(){
        ArrayList<Sessao> sessoes=filme.getSessoes(dia);
        JPanel panelSessoes=new JPanel();
        panelSessoes.setPreferredSize(new Dimension(450,50));
        panelSessoes.setLayout(new BorderLayout());
        JPanel mainList = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        mainList.add(new JPanel(), gbc);
        panelSessoes.add(new JScrollPane(mainList/*,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED,JScrollPane.VERTICAL_SCROLLBAR_NEVER*/));
        for (Sessao sessao:sessoes) {
            gbcUpdate();
            JButton buttonSessao=new JButton("Sala: "+sessao.getSala().getNumeroSala()+"  Horario: "+sessao.getHoraInicioStr());
            buttonSessao.addActionListener(actionEvent -> homePage.sitsPage(sessao));
            mainList.add(buttonSessao,gbc,0);
            validate();
            repaint();
        }
        return panelSessoes;
    }

    private void gbcUpdate(){
        gbc = new GridBagConstraints();
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
    }
    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
}
