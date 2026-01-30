import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Time;
import java.util.Random;


public class Jogo_Da_Cobrona extends JPanel {

    private static final int Largura_tela = 1300;
    private static final int Altura_tela = 750;
    private static int Tamanho_bloco = 50;
    private static final int Unidades = Largura_tela * Altura_tela / (Tamanho_bloco * Tamanho_bloco);
    private static final int Intervalo = 200;
    private static final String Nome_fonte = "Pixgrid";
    private final int[] eixoX = new int[Unidades];
    private final int[] eixoY = new int[Unidades];
    private int Corpo_cobra = 6;
    private int PontosPegos;
    private int BlocoX;
    private int BlocoY;
    private char direcao = 'D';  // W = cima, S = baixo, D = direita, A = esquerda
    private boolean estaRodadando = false;
    Time timer;
    Random random;

     Jogo_Da_Cobrona()
    {
        random = new Random();
        setPreferredSize(new Dimension(Largura_tela,Altura_tela));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new LeitorDeTeclas());
        iniciarJogo();
    }

    public void iniciarJogo(){

         criarBloco();
         estaRodadando = true;
         timer = new Time(Intervalo);

    }
    private void criarBloco(){
         BlocoX = random.nextInt(Largura_tela/Tamanho_bloco)*Tamanho_bloco;
         BlocoY = random.nextInt(Largura_tela/Tamanho_bloco)*Tamanho_bloco;
    }

    public class LeitorDeTeclas extends KeyAdapter{

    }





}
