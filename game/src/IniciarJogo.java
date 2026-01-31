import javax.swing.*;

public class IniciarJogo {

    private static JFrame frame;
    private static JPanel painelAtual;

    public static void main(String[] args) throws Exception {
        frame = new JFrame("Cobrona");
        frame.setSize(Jogo_Da_Cobrona.LARGURA_TELA, Jogo_Da_Cobrona.ALTURA_TELA);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mostrarMenu();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void mostrarMenu() {
        Scores scores = new Scores(() -> mostrarMenu());
        
        MenuPrincipal menu = new MenuPrincipal(
            () -> iniciarJogo(scores),
            () -> mostrarScores(scores)
        );

        trocarPainel(menu);
    }

    private static void iniciarJogo(Scores scores) {
        Jogo_Da_Cobrona jogoDaCobrona = new Jogo_Da_Cobrona(scores, () -> mostrarMenu());
        trocarPainel(jogoDaCobrona);
        jogoDaCobrona.requestFocus();
    }

    private static void mostrarScores(Scores scores) {
        trocarPainel(scores);
    }

    private static void trocarPainel(JPanel novoPanel) {
        if (painelAtual != null) {
            frame.remove(painelAtual);
        }
        painelAtual = novoPanel;
        frame.add(painelAtual);
        frame.revalidate();
        frame.repaint();
    }
}
