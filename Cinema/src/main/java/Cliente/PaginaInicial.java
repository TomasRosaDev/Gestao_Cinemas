package Cliente;

import DadosPermanentes.Filme;
import DadosPermanentes.SbdHandler;
import DadosPermanentes.Sessao;

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
                frame.setVisible(false);
                frame.setSize(500, 800);
                JPanel cinemaPanel = new JPanel();
                JLabel cinema= new JLabel("CINEMAS NOZ");
                cinema.setFont(new Font("Arial", Font.PLAIN, 40));
                cinema.setForeground(Color.white);
                cinemaPanel.setBackground(Color.gray);
                cinemaPanel.setSize(100, 12);
                cinemaPanel.add(cinema);
                panel.add(cinemaPanel);
                for(int i = 0; i < arrayFilmes.size() ; i++) {
                    JPanel filme = new JPanel();
                    JPanel imagemFilme1 = new JPanel();
                    JLabel imagem = new JLabel("Foto");
                    JPanel infoFilme1 = new JPanel();
                    JLabel titulo = new JLabel("Titulo: "+arrayFilmes.get(i).getTitulo());
                    JLabel sala = new JLabel("Ano: "+arrayFilmes.get(i).getAnoString());
                    JLabel idade = new JLabel("Idade");
                    JLabel categoria = new JLabel("categoria");
                    /*String[] sessoes = db.getHorasSessoesDoFilme(arrayFilmes.get(i), Date.valueOf("2022-09-28"));
                    for(int j = 0; j < sessoes.length ; j++) {
                        JButton butoes = new JButton(sessoes[j]);
                        infoFilme1.add(butoes);
                    }*/
                    JButton info = new JButton("Informações ->");

                    filme.setLayout(new GridLayout(1, 2));
                    infoFilme1.setLayout(new GridLayout(6, 1));

                    imagemFilme1.add(imagem);
                    filme.add(imagemFilme1);
                    infoFilme1.add(titulo);
                    infoFilme1.add(sala);
                    infoFilme1.add(idade);
                    infoFilme1.add(categoria);

                    infoFilme1.add(info);
                    filme.add(infoFilme1);
                    filme.setBackground(Color.red);
                    panel.add(filme);
                    frame.add(panel);
                    info.addActionListener(actionEvent -> this.Informacoes());
                }
                frame.setVisible(true);
            }
            public void Informacoes(){
                db.setFilmeDetails(arrayFilmes.get(0));
                //panel.removeAll();
                //frame.setVisible(false);
                frame.setSize(500, 800);
                JPanel cinemaPanel = new JPanel();
                JLabel cinema= new JLabel("CINEMAS NOZ");
                cinema.setFont(new Font("Arial", Font.PLAIN, 40));
                cinema.setForeground(Color.white);
                cinemaPanel.setBackground(Color.gray);
                cinemaPanel.setSize(100, 12);
                JButton voltar = new JButton("<- Voltar");
                voltar.addActionListener(actionEvent -> {
                    try {
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
                JLabel titulo = new JLabel("Titulo: "+ arrayFilmes.get(0).getTitulo());
                JLabel descricao = new JLabel("Descrição: "+ arrayFilmes.get(0).getDescricao());
                JLabel atores = new JLabel("Atores: "+ arrayFilmes.get(0).getAtores());
                JLabel duracao = new JLabel("Duracao: "+ arrayFilmes.get(0).getDuracao());
                JLabel pais = new JLabel("Pais: "+ arrayFilmes.get(0).getPais());
                JLabel distribuidor = new JLabel("Distribuidor: "+ arrayFilmes.get(0).getDistribuidor());

                filme.setLayout(new GridLayout(1, 2));
                infoFilme1.setLayout(new GridLayout(6, 1));

                imagemFilme1.add(imagem);
                filme.add(imagemFilme1);

                infoFilme1.add(titulo);
                infoFilme1.add(descricao);
                infoFilme1.add(atores);
                infoFilme1.add(duracao);
                infoFilme1.add(pais);
                infoFilme1.add(distribuidor);

                filme.add(infoFilme1);
                //panel.add(voltar);
                frame.setVisible(true);
            }
}

