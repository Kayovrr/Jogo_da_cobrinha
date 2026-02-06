import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JPanel {

    private JButton botaoJogar;
    private JButton botaoScores;
    private JButton botaoSair;
    private Runnable aoClicarJogar;
    private Runnable aoClicarScores;

    public MenuPrincipal(Runnable aoClicarJogar, Runnable aoClicarScores) {
        this.aoClicarJogar = aoClicarJogar;
        this.aoClicarScores = aoClicarScores;

        this.setPreferredSize(new Dimension(Jogo_Da_Cobrona.LARGURA_TELA, Jogo_Da_Cobrona.ALTURA_TELA));
        this.setBackground(Color.black);
        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // Título
        JLabel titulo = new JLabel(":P  JOGO DA COBRONA  :P");
        titulo.setFont(new Font("Pixgrid", Font.BOLD, 50));
        titulo.setForeground(Color.green);
        gbc.gridy = 0;
        this.add(titulo, gbc);

        // Botão Jogar
        botaoJogar = criarBotao("JOGAR", new Color(45, 180, 0));
        botaoJogar.addActionListener(e -> aoClicarJogar.run());
        gbc.gridy = 1;
        this.add(botaoJogar, gbc);

        // Botão Scores
        botaoScores = criarBotao("SCORES", new Color(200, 100, 0));
        botaoScores.addActionListener(e -> aoClicarScores.run());
        gbc.gridy = 2;
        this.add(botaoScores, gbc);

        // Botão Sair
        botaoSair = criarBotao("SAIR", new Color(180, 45, 0));
        botaoSair.addActionListener(e -> System.exit(0));
        gbc.gridy = 3;
        this.add(botaoSair, gbc);
    }

    private JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Pixgrid", Font.BOLD, 24));
        botao.setBackground(cor);
        botao.setForeground(Color.white);
        botao.setFocusPainted(false);
        botao.setPreferredSize(new Dimension(250, 60));
        botao.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return botao;
    }
}
