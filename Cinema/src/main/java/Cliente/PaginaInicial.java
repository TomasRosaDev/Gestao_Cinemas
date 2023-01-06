package Cliente;

import DadosPermanentes.Filme;
import DadosPermanentes.SbdHandler;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaginaInicial extends JFrame {
    private final JFrame frame = new JFrame();

    private final JPanel panel = new JPanel();
    private ArrayList<Filme> arrayFilmes;

    private final SbdHandler db = new SbdHandler();
            public void PaginaInicialMain() throws SQLException {
                arrayFilmes = db.listaFilmes(Date.valueOf("2022-09-28"));

                panel.removeAll();
                frame.setVisible(true);
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
                for (Filme arrayFilme : arrayFilmes) {
                    JPanel filme = new JPanel();
                    JPanel imagemFilme1 = new JPanel();
                    JLabel imagem = new JLabel("Foto");
                    // imagem.setIcon(new ImageIcon("C:\\Users\\migue\\OneDrive\\Área de Trabalho\\UNI\\1º SEMESTRE 2ºAno\\PTDA\\Cinemas_Noz\\Cinema\\src\\main\\java\\Cliente\\transferir.jpg"));
                    JPanel infoFilme1 = new JPanel();
                    JLabel titulo = new JLabel("Titulo: " + arrayFilme.getTitulo());
                    JLabel sala = new JLabel("Ano: " + arrayFilme.getAnoString());
                    JLabel idade = new JLabel("Idade Minima: ");
                    JLabel categoria = new JLabel("Genero: " + arrayFilme.getGeneros());
                    JButton info = new JButton("Informacoes ->");
                    int finalY = y;
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
                    for (String sessoe : sessoes) {
                        infoFilme1.add(new JButton(sessoe));
                    }
                    infoFilme1.add(info);
                    infoFilme1.setBackground(Color.gray);
                    filme.add(infoFilme1);
                    //filme.setPreferredSize(new Dimension(280, 100));
                    panel.add(filme);
                    frame.add(panel);
                    y++;
                }
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
}

