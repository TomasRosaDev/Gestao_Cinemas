package Cliente;

import DadosPermanentes.Filme;
import DadosPermanentes.SbdHandler;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaginaInicial extends JFrame {
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private ArrayList<Filme> arrayFilmes;

    private SbdHandler db = new SbdHandler();
            public void PaginaInicial() throws SQLException {
                arrayFilmes = db.listaFilmes(Date.valueOf("2022-09-28"));
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
                cinemaPanel.add(cinema);
                panel.add(cinemaPanel);
                int y=0;
                for(int i = 0; i < arrayFilmes.size() ; i++) {
                    JPanel filme = new JPanel();
                    JPanel imagemFilme1 = new JPanel();
                    JLabel imagem = new JLabel("Foto");
                    JPanel infoFilme1 = new JPanel();
                    JLabel titulo = new JLabel("Titulo: "+arrayFilmes.get(i).getTitulo());
                    JLabel sala = new JLabel("Ano: "+arrayFilmes.get(i).getAnoString());
                    JLabel idade = new JLabel("Idade Minima: ");
                    JLabel categoria = new JLabel("Genero: "+arrayFilmes.get(i).getGeneros());
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
                    infoFilme1.setPreferredSize(new Dimension(200, 100));
                    String[] sessoes = db.getHorasSessoesDoFilme(arrayFilmes.get(i), Date.valueOf("2022-09-28"));
                    for(int j = 0; j < sessoes.length ; j++) {
                        infoFilme1.add(new JButton(sessoes[j]));
                    }
                    infoFilme1.add(info);
                    infoFilme1.setBackground(Color.gray);
                    filme.add(infoFilme1);
                    panel.add(filme);
                    frame.add(panel);
                    y++;
                }
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
                        this.PaginaInicial();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                cinemaPanel.add(cinema);
                panel.add(cinemaPanel);

                JPanel filme = new JPanel();
                JPanel imagemFilme1 = new JPanel();
                JLabel imagem = new JLabel("Foto");
                JPanel infoFilme1 = new JPanel();
                JLabel titulo = new JLabel("Titulo: "+ arrayFilmes.get(finalY).getTitulo());
                JLabel descricao = new JLabel("Descricao: "+ arrayFilmes.get(finalY).getDescricao());
                JLabel atores = new JLabel("Atores: "+ arrayFilmes.get(finalY).getAtores());
                JLabel duracao = new JLabel("Duracao: "+ arrayFilmes.get(finalY).getDuracao());
                JLabel pais = new JLabel("Pais: "+ arrayFilmes.get(finalY).getPais());
                JLabel distribuidor = new JLabel("Distribuidor: "+ arrayFilmes.get(finalY).getDistribuidor());
                JLabel realizador = new JLabel("Realizador: "+arrayFilmes.get(finalY).getRealizador());
                filme.setLayout(new GridLayout(1, 2));
                infoFilme1.setLayout(new GridLayout(7, 1));

                imagemFilme1.add(imagem);
                imagemFilme1.setBackground(Color.gray);
                filme.add(imagemFilme1);
                infoFilme1.add(titulo);
                infoFilme1.add(descricao);
                infoFilme1.add(atores);
                infoFilme1.add(duracao);
                infoFilme1.add(pais);
                infoFilme1.add(distribuidor);
                infoFilme1.add(realizador);
                infoFilme1.setBackground(Color.gray);
                infoFilme1.setPreferredSize(new Dimension(280, 500));
                filme.add(infoFilme1);
                panel.add(filme);
                frame.add(panel);
                panel.add(voltar);
                System.out.println(arrayFilmes.get(finalY).getAtores());
                frame.setVisible(true);
            }
}

