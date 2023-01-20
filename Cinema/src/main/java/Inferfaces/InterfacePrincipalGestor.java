package Inferfaces;

import javax.swing.*;
import java.awt.*;

public class InterfacePrincipalGestor extends InterfaceGestor{
        private HomePage homePage;
        private InterfaceGestorConsultar consultar;
        public InterfacePrincipalGestor(HomePage homePage){
            this.homePage=homePage;
            this.setLayout(new BorderLayout());
            this.add(informacoes(),BorderLayout.NORTH);
            this.add(ecraPrincipal(),BorderLayout.CENTER);
        }

        public JPanel informacoes(){
            JPanel info=new JPanel();
            info.setPreferredSize(new Dimension(600,25));
            info.setBackground(Color.lightGray);
            info.add(new JLabel("Funções Gestor"));
            return info;
        }

        public JPanel ecraPrincipal(){
            JPanel geral=new JPanel();
            geral.setOpaque(false);
            geral.add(opcoesPanel());
            geral.add(butVoltar());
            return geral;
        }

        public JPanel opcoesPanel(){
            JPanel grid= new JPanel();
            grid.setPreferredSize(new Dimension(500,300));
            grid.setLayout(new GridLayout(2,1));
            JButton consultarbutt = new JButton("Consultar");
            consultarbutt.addActionListener(actionEvent -> homePage.gestorConsultar());
            grid.add(consultarbutt);
            JButton inserirbutt =new JButton("Inserir Dados");
            inserirbutt.addActionListener(actionEvent -> homePage.gestorInserir());
            grid.add(inserirbutt);
            return grid;
        }


        public JButton butVoltar(){
            JButton buttonVoltar=new JButton("<<Voltar");
            buttonVoltar.addActionListener(actionEvent -> homePage.interfaceFilmes());
            return buttonVoltar;
        }

    }
