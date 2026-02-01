import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;



public class Jogo_Da_Cobrona extends JPanel implements ActionListener, KeyListener {

    //tamanho da tela
    public static int LARGURA_TELA = 700;
    public static int ALTURA_TELA = 600;
    private final int TAMANHO_UNIDADE = 25;//tamanho de cada unidade da cobrinha
    private final int TOTAL_UNIDADES = (LARGURA_TELA * ALTURA_TELA) / (TAMANHO_UNIDADE * TAMANHO_UNIDADE);//total de unidades na tela
    private final int DELAY = 75;//velocidade do jogo
    public static final String NOME_FONTE = "Pixgrid";



    //variaveis e arrays para armazenar as posicoes da cobrinha e outras coisas
    final int x[] = new int[TOTAL_UNIDADES];//posicoes x da cobrinha
    final int y[] = new int[TOTAL_UNIDADES];//posicoes y da cobrinha
    int bodyParts = 6;//tamanho inicial da cobrinha
    int Pontos;//pontuacao
    private Maca maca;
    char direction = 'D';//direcao inicial da cobrinha
    boolean running = false;//estado do jogo
    Timer timer;//timer do jogo
    Random random;//gerador de numeros aleatorios
    private Scores scores;
    private Runnable aoTerminarJogo;



    //construtor da classe master, onde inicia as variaveis e configurações iniciais
    Jogo_Da_Cobrona() {
        this(null, null);
    }

    Jogo_Da_Cobrona(Scores scores, Runnable aoTerminarJogo) {
        this.scores = scores;
        this.aoTerminarJogo = aoTerminarJogo;
        random = new Random();
        this.setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));//define o tamanho da tela
        this.setBackground(Color.black);//define a cor de fundo
        this.setFocusable(true);//define que o painel pode receber foco, parece necessario para capturar eventos de teclado
        this.addKeyListener(this);//adiciona o listener de teclado
        maca = new Maca(0, 0); // Posição inicial será definida no spawnApple
        startGame();//inicia o jogo :)
    }


    //comida
    public void spawnApple() {
    int posX = random.nextInt((int) (LARGURA_TELA / TAMANHO_UNIDADE)) * TAMANHO_UNIDADE;
    int posY = random.nextInt((int) (ALTURA_TELA / TAMANHO_UNIDADE)) * TAMANHO_UNIDADE;
    maca.setPosicao(posX, posY);
}


    //colisao com a maca
    public boolean checkApple() {
    if (x[0] == maca.getX() && y[0] == maca.getY()) {
        bodyParts++;
        Pontos++;
        spawnApple();
        return true;
    }
    return false;
}


    //colisao com as bordas da tela
    public boolean checkBorderCollision() {
        if (x[0] < 0 || x[0] >= LARGURA_TELA || y[0] < 0 || y[0] >= ALTURA_TELA) {
            return true;
        }
        return false;
    }

    // Verifica se a cabeça colidiu com qualquer parte do próprio corpo, teste 
public boolean checkSelfCollision() {
    // Ignora o último segmento (a cauda)
    for (int i = 1; i < bodyParts; i++){
        if (x[0] == x[i] && y[0] == y[i]) {
            return true;
        }
    }
    return false;
}



    //movimento
    public void move() {
        //move o corpo da cobrinha
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        //move a cabeca da cobrinha na direcao atual
        switch (direction) {
            case 'E':
                x[0] = x[0] - TAMANHO_UNIDADE;
                break;
            case 'D':
                x[0] = x[0] + TAMANHO_UNIDADE;
                break;
            case 'C':
                y[0] = y[0] - TAMANHO_UNIDADE;
                break;
            case 'B':
                y[0] = y[0] + TAMANHO_UNIDADE;
                break;
        }
    }



    //metodo para iniciar o jogo
    public void startGame() {
        spawnApple();//gera a primeira maca
        running = true;//define que o jogo esta rodando
        timer = new Timer(DELAY, this);//cria o timer do jogo
        timer.start();//inicia o timer
    }



  // Este método é chamado automaticamente pelo Timer (DELAY) e implementação do teste
@Override
public void actionPerformed(ActionEvent e) {
    if (running) {
        move();           // move tudo
        checkApple();     // cresce se comer

        if (checkBorderCollision() || checkSelfCollision()) {
            running = false;
            if (scores != null) {
                scores.adicionarScore(Pontos);
            }
        }
    }
    repaint();
}


    //metodos para capturar eventos de teclado com as teclas "wasd" ou as setas direcionais
    //verificando se a cobrinha nao esta se movendo na direcao oposta
    @Override
    public void keyPressed(KeyEvent e) {
        // Se pressionou ESC e o jogo terminou, volta ao menu
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && !running) {
            if (aoTerminarJogo != null) {
                aoTerminarJogo.run();
            }
            return;
        }

        //muda a direcao da cobrinha baseado na tecla pressionada
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT://adiciona a seta para esquerda para mover para esquerda
            case KeyEvent.VK_A://adiciona a tecla "A" para mover para esquerda
                if (direction != 'D') {
                    direction = 'E';
                }
                break;
            case KeyEvent.VK_RIGHT://adiciona a seta para direita para mover para direita
            case KeyEvent.VK_D://adiciona a tecla "D" para mover para direita
                if (direction != 'E') {
                    direction = 'D';
                }
                break;
            case KeyEvent.VK_UP://adiciona a seta para cima para mover para cima
            case KeyEvent.VK_W://adiciona a tecla "W" para mover para cima
                if (direction != 'B') {
                    direction = 'C';
                }
                break;
            case KeyEvent.VK_DOWN://adiciona a seta para baixo para mover para baixo
            case KeyEvent.VK_S://adiciona a tecla "S" para mover para baixo
                if (direction != 'C') {
                    direction = 'B';
                }
                break;
        }
    }

    // dezenha o ponto na tela
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        desenharTela(g);
    }

    public void desenharTela(Graphics g) {

        if (running) {
            g.setColor(Color.red);
            maca.desenhar(g, TAMANHO_UNIDADE); // Chama o método da classe Maca

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                    g.fillRect(x[0], y[0], TAMANHO_UNIDADE, TAMANHO_UNIDADE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], TAMANHO_UNIDADE, TAMANHO_UNIDADE);
                }
            }
            g.setColor(Color.red);
            g.setFont(new Font(NOME_FONTE, Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Pontos: " + Pontos, (LARGURA_TELA - metrics.stringWidth("Pontos: " + Pontos)) / 2, g.getFont().getSize());
        } else {
            fimDeJogo(g);
        }

    }

    public void fimDeJogo(Graphics g) {
        // Se timer está rodando, para o timer
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        g.setColor(Color.red);

        // Pontuação
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
        FontMetrics fontePontuacao = getFontMetrics(g.getFont());
        g.drawString(
                "Pontos: " + Pontos,
                (LARGURA_TELA - fontePontuacao.stringWidth("Pontos: " + Pontos)) / 2,
                g.getFont().getSize()
        );

        // Texto final
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 75));
        FontMetrics fonteFinal = getFontMetrics(g.getFont());
        g.drawString(
                "Você se lascou",
                (LARGURA_TELA - fonteFinal.stringWidth("Você se lascou")) / 2,
                ALTURA_TELA / 2
        );

        // Instrução de volta ao menu
        g.setColor(Color.white);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        FontMetrics fonteInstrucao = getFontMetrics(g.getFont());
        String instrucao = "Pressione ESC para voltar ao menu";
        g.drawString(
                instrucao,
                (LARGURA_TELA - fonteInstrucao.stringWidth(instrucao)) / 2,
                ALTURA_TELA - 30
        );
    }










    //vi esses dois no codigo de inspiração, mas nao sei se vao ser uteis  :)


    //metodo para capturar evento de tecla solta
    @Override
    public void keyReleased(KeyEvent e) {

    }



    //metodo para capturar evento de tecla digitada
    @Override
    public void keyTyped(KeyEvent e) {

    }
}
