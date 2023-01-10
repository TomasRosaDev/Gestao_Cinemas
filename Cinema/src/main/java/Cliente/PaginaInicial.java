package Cliente;

import DadosPermanentes.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaginaInicial extends JFrame {
    private final JFrame frame = new JFrame();

    private final JPanel panel = new JPanel();
    private ArrayList<Filme> arrayFilmes;

    private Lugar[][] lugares;
    private Sessao sessao;

    private Sala sala;

    private final SbdHandler db = new SbdHandler();
            public void PaginaInicialMain() throws SQLException {
                arrayFilmes = db.listaFilmes(Date.valueOf("2022-09-28"));
                panel.removeAll();
                SwingUtilities.updateComponentTreeUI(frame);
                panel.setBackground(Color.green);
                frame.setSize(600, 800);

                JPanel cinemaPanel = new JPanel();
                JLabel cinema= new JLabel("CINEMAS NOZ");
                cinema.setFont(new Font("Arial", Font.PLAIN, 40));
                cinema.setForeground(Color.white);
                cinemaPanel.setBackground(Color.gray);
                cinemaPanel.setSize(300, 100);
                cinemaPanel.add(cinema);
                panel.add(cinemaPanel);
                int y=0;
                int l=0;
                for (Filme arrayFilme : arrayFilmes) {
                    JPanel filme = new JPanel();
                    JPanel imagemFilme1 = new JPanel();
                    JLabel imagem = new JLabel("Foto");
                    // imagem.setIcon(new ImageIcon("C:\\Users\\migue\\OneDrive\\Área de Trabalho\\UNI\\1º SEMESTRE 2ºAno\\PTDA\\Cinemas_Noz\\Cinema\\src\\main\\java\\Cliente\\transferir.jpg"));
                    JPanel infoFilme1 = new JPanel();
                    JLabel titulo = new JLabel("Titulo: " + arrayFilme.getTitulo());
                    JLabel sala = new JLabel("Ano: " + arrayFilme.getAnoString());
                    JLabel idade = new JLabel("Idade Minima: " + arrayFilme.getIdade());
                    JLabel categoria = new JLabel("Genero: " + arrayFilme.getGeneros());
                    JButton info = new JButton("Informacoes ->");
                    int finalY = y;
                    int finalL= l;
                    info.addActionListener(actionEvent -> setVisible(false));
                    info.addActionListener(actionEvent -> Informacoes(finalY));//No local do zero era suposto estar i para que apontasse para o filme actual
                    filme.setLayout(new GridLayout(1, 2));
                    infoFilme1.setLayout(new GridLayout(7, 1));
                    imagemFilme1.add(imagem);
                    imagemFilme1.setBackground(Color.gray);
                    filme.add(imagemFilme1);
                    infoFilme1.add(titulo);
                    infoFilme1.add(sala);
                    infoFilme1.add(idade);
                    infoFilme1.add(categoria);
                    infoFilme1.setPreferredSize(new Dimension(280, 200));
                    String[] sessoes = db.getHorasSessoesDoFilme(arrayFilme, Date.valueOf("2022-09-28"));
                    if(sessoes.length==0){
                        infoFilme1.add(new JLabel("Sem sessoes para o dia em questao"));
                    } else {
                        for (String sessao: sessoes) {
                            String horaInicio = sessao.split(" ")[1].substring(0,5);
                            JButton buttonSessao = new JButton(horaInicio);
                            int finalL1 = finalL;
                            buttonSessao.addActionListener(actionEvent -> SalaLugares(finalY, sessoes[finalL1]));
                            infoFilme1.add(buttonSessao);
                            finalL++;
                        }
                    }
                    infoFilme1.add(info);
                    infoFilme1.setBackground(Color.gray);
                    filme.add(infoFilme1);
                    //filme.setPreferredSize(new Dimension(280, 100));
                    panel.add(filme);
                    frame.add(panel);
                    y++;
                    l=0;
                }
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null); //centrar a janela
                frame.setVisible(true);
            }

            public void Informacoes(int finalY){
                db.setFilmeDetails(arrayFilmes.get(finalY));
                panel.removeAll();
                frame.setVisible(true);
                //frame.setVisible(false);
                frame.setSize(600, 800);
                JPanel cinemaPanel = new JPanel();
                JLabel cinema= new JLabel("CINEMAS NOZ");
                cinema.setFont(new Font("Arial", Font.PLAIN, 40));
                cinema.setForeground(Color.white);
                cinemaPanel.setBackground(Color.gray);
                cinemaPanel.setSize(100, 12);
                JButton voltar = new JButton("<- Voltar");
                voltar.addActionListener(actionEvent -> {
                    try {
                        panel.removeAll();
                        this.PaginaInicialMain();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                cinemaPanel.add(cinema);
                panel.add(cinemaPanel);
                String[] resultado= arrayFilmes.get(finalY).toString().split("//");
                JPanel filme = new JPanel();
                JPanel imagemFilme1 = new JPanel();
                JLabel imagem = new JLabel("Foto");
                JPanel infoFilme1 = new JPanel();
                filme.setLayout(new GridLayout(1, 2));
                imagemFilme1.add(imagem);
                imagemFilme1.setBackground(Color.gray);
                filme.add(imagemFilme1);
                for(int i=0;i<resultado.length;i++){
                    JLabel labe=new JLabel("<html>"+resultado[i]+"<html>");
                    if(i+1 == resultado.length){
                        labe.setPreferredSize(new Dimension(280, 150));
                    }else {
                        labe.setPreferredSize(new Dimension(280, 30));
                    }
                    infoFilme1.add(labe);
                }
                infoFilme1.setBackground(Color.gray);
                infoFilme1.setPreferredSize(new Dimension(280, 500));
                filme.add(infoFilme1);

                panel.add(filme);
                frame.add(panel);
                panel.add(voltar);
                frame.setVisible(true);
            }
            public void SalaLugares(int finalY, String sessoe){
                panel.removeAll();
                frame.setVisible(true);
                sessao=db.getSessaoFilme(arrayFilmes.get(finalY), sessoe);
                sala=sessao.getSala();
                //frame.setVisible(false);
                frame.setSize(600, 800);
                JPanel panelPrincipalSala = new JPanel();
                panelPrincipalSala.setLayout(new GridLayout(2, 1));
                panelPrincipalSala.setPreferredSize(new Dimension(570, 600));
                JPanel cinemaPanel = new JPanel();
                JLabel cinema= new JLabel("CINEMAS NOZ");
                cinema.setFont(new Font("Arial", Font.PLAIN, 40));
                cinema.setForeground(Color.white);
                cinemaPanel.setBackground(Color.gray);
                cinemaPanel.setSize(100, 12);
                JButton voltar = new JButton("<- Voltar");
                voltar.addActionListener(actionEvent -> {
                    try {
                        panel.removeAll();
                        this.PaginaInicialMain();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                JPanel filme = new JPanel();
                filme.add(new JLabel(arrayFilmes.get(finalY).getTitulo()+"    Sala: "+ sala.getNumeroSala() + "    Sessao:  "+sessoe.split(" ")[1].substring(0,5)));
                filme.setPreferredSize(new Dimension(570, 30));
                JPanel ecra = new JPanel();
                ecra.add(new JLabel("ECRA"));
                ecra.setPreferredSize(new Dimension(570, 30));
                lugares = db.getLugares(String.valueOf(sala.getNumeroSala()));
                JPanel layoutSala= new JPanel();
                layoutSala.setLayout(new GridLayout(lugares.length, lugares[lugares.length-1].length));
                for (int row = 0; row < lugares.length; row++) {
                    for (int col = 0; col < lugares[row].length; col++) {
                        Lugar lugar = lugares[row][col]; //lugar atual
                        JButton button = new JButton(lugar.getNome() + " - " + lugar.getTipo()); //criacao do botao

                        boolean existeBilhete = db.getExisteBilhete(lugar.getNome(), sessao);
                        if(existeBilhete){ //se ja existir um registo de bilhete para este determinado lugar nesta determinada sessao, mostra como lugar ocupado
                            button.setBackground(Color.red);
                            button.setEnabled(false);
                        }

                        if(lugar.getTipo().equals(TipoLugar.Inexistente)){ //se o atributo tipo da relacao bilhete tiver o valor Inexistente, nao e um lugar e nao o mostramos no layout da sala
                            button.setVisible(false);
                        }

                        button.addActionListener(actionEvent -> {
                            if(button.getBackground().equals(Color.green)){ //se o lugar ja estiver selecionado (a verde), volta para a cor default de um botao
                                button.setBackground(new JButton().getBackground());
                            } else { //se o lugar nao estiver selecionado e seleciona (passa a verde)
                                button.setBackground(Color.green);
                            }
                        });

                        layoutSala.add(button);
                    }
                }
                layoutSala.setBackground(Color.gray);
                panelPrincipalSala.add(layoutSala);
                JPanel bilhetes = new JPanel();
                bilhetes.setBackground(Color.white);
                panelPrincipalSala.add(bilhetes);
                cinemaPanel.add(cinema);
                panel.add(cinemaPanel);
                panel.add(filme);
                panel.add(ecra);
                panel.add(panelPrincipalSala);
                panel.add(voltar);

                frame.add(panel);

                frame.setVisible(true);
            }
}

