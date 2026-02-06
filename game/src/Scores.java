import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Scores extends JPanel {

    private ArrayList<Integer> scores;
    private JButton botaoVoltar;
    private Runnable aoClicarVoltar;

    public Scores(Runnable aoClicarVoltar) {
        this.aoClicarVoltar = aoClicarVoltar;
        this.scores = new ArrayList<>();

        // Adicionar scores de exemplo
        scores.add(0);
        scores.add(0);
        scores.add(0);
        scores.add(0);
        scores.add(0);

        this.setPreferredSize(new Dimension(Jogo_Da_Cobrona.LARGURA_TELA, Jogo_Da_Cobrona.ALTURA_TELA));
        this.setBackground(Color.black);
        this.setLayout(new BorderLayout());

        // Painel de scores
        JPanel painelScores = new JPanel();
        painelScores.setBackground(Color.black);
        painelScores.setLayout(new BoxLayout(painelScores, BoxLayout.Y_AXIS));


        // Título
        JLabel titulo = new JLabel("MELHORES SCORES");
        titulo.setFont(new Font("Pixgrid", Font.BOLD, 40));
        titulo.setForeground(Color.green);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelScores.add(Box.createVerticalStrut(30));
        painelScores.add(titulo);
        painelScores.add(Box.createVerticalStrut(30));


        // Adiciona os scores
        for (int i = 0; i < scores.size(); i++) {
            JLabel score = new JLabel((i + 1) + "º - " + scores.get(i) + " pontos");
            score.setFont(new Font("Pixgrid", Font.BOLD, 24));
            score.setForeground(new Color(45, 180, 0));
            score.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelScores.add(score);
            painelScores.add(Box.createVerticalStrut(20));
        }

        this.add(painelScores, BorderLayout.CENTER);

        // Painel de botão
        JPanel painelBotao = new JPanel();
        painelBotao.setBackground(Color.black);

        botaoVoltar = new JButton("VOLTAR");
        botaoVoltar.setFont(new Font("Pixgrid", Font.BOLD, 20));
        botaoVoltar.setBackground(new Color(45, 90, 180));
        botaoVoltar.setForeground(Color.white);
        botaoVoltar.setFocusPainted(false);
        botaoVoltar.setPreferredSize(new Dimension(200, 50));
        botaoVoltar.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        botaoVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botaoVoltar.addActionListener(e -> aoClicarVoltar.run());

        painelBotao.add(botaoVoltar);
        this.add(painelBotao, BorderLayout.SOUTH);
    }

    public void adicionarScore(int score) {
        scores.add(score);
        scores.sort((a, b) -> b.compareTo(a)); // Ordena em ordem decrescente
        if (scores.size() > 5) {
            scores.remove(scores.size() - 1); // Mantém apenas os top 5
        }
        repaint();
    }
}
