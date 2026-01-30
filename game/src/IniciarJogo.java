import javax.swing.*;




public class IniciarJogo {


    public static void main(String []args) throws Exception{

        JFrame frame = new JFrame("Cobrona");
        frame.setSize(Jogo_Da_Cobrona.LARGURA_TELA, Jogo_Da_Cobrona.ALTURA_TELA);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Jogo_Da_Cobrona jogoDaCobrona = new Jogo_Da_Cobrona();
        frame.add(jogoDaCobrona);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        jogoDaCobrona.requestFocus();

    }
}
