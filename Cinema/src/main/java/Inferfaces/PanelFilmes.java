package Inferfaces;

import DadosPermanentes.Filme;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;

public class PanelFilmes extends InterfaceCliente {
    private ArrayList<Filme> filmes;
    private HomePage homePage;
    private JPanel mainList;

    private  GridBagConstraints gbc;

    public PanelFilmes(ArrayList<Filme> filmes, Date dia,HomePage homePage){
        this.filmes=filmes;
        this.homePage=homePage;
        int size=filmes.size();
        if (size>0){
            testPane();
            for (Filme filme:filmes) {
                gbcUpdate();
                mainList.add(new PanelFilmeIndiv(filme,dia,homePage),gbc,0);
                validate();
                repaint();
            }
        }
        else {
            withoutFilmes();
        }
    }


    public void testPane() {
        setLayout(new BorderLayout());
        mainList = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.weighty = 1;
        mainList.add(new JPanel(), gbc);

        add(new JScrollPane(mainList));
/*
        JButton add = new JButton("Add");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel();
                panel.add(new JLabel("Hello"));
                panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
                mainList.add(panel, gbc, 0);
            }
        });

        add(add, BorderLayout.SOUTH);
*/
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    public void withoutFilmes(){

    }

    private void gbcUpdate(){
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }

}