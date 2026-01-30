import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;



public class master extends JPanel implements ActionListener, KeyListener {

    //tamanho da tela
    private final int LARGURA_TELA = 600;
    private final int ALTURA_TELA = 600;
    private final int TAMANHO_UNIDADE = 25;//tamanho de cada unidade da cobrinha
    private final int TOTAL_UNIDADES = (LARGURA_TELA * ALTURA_TELA) / (TAMANHO_UNIDADE * TAMANHO_UNIDADE);//total de unidades na tela
    private final int DELAY = 75;//velocidade do jogo




    //variaveis e arrays para armazenar as posicoes da cobrinha e outras coisas
    final int x[] = new int[TOTAL_UNIDADES];//posicoes x da cobrinha
    final int y[] = new int[TOTAL_UNIDADES];//posicoes y da cobrinha
    int bodyParts = 6;//tamanho inicial da cobrinha
    int applesEaten;//pontuacao
    int appleX;//posicao x da maca
    int appleY;//posicao y da maca
    char direction = 'D';//direcao inicial da cobrinha
    boolean running = false;//estado do jogo
    Timer timer;//timer do jogo
    Random random;//gerador de numeros aleatorios



    //construtor da classe master, onde inicia as variaveis e configurações iniciais
    master() {
        random = new Random();
        this.setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));//define o tamanho da tela
        this.setBackground(Color.black);//define a cor de fundo
        this.setFocusable(true);//define que o painel pode receber foco, parece necessario para capturar eventos de teclado
        this.addKeyListener(this);//adiciona o listener de teclado
        startGame();//inicia o jogo :)
    }

    

    //comida
    public void spawnApple() {
        //gera posicoes aleatorias para a maca
        appleX = random.nextInt((int) (LARGURA_TELA / TAMANHO_UNIDADE)) * TAMANHO_UNIDADE;
        appleY = random.nextInt((int) (ALTURA_TELA / TAMANHO_UNIDADE)) * TAMANHO_UNIDADE;
    }



    //colisao com a maca
    public boolean checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            bodyParts++;
            applesEaten++;
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



    //metodo para logica de açao do jogo, como movimentação, colisões, etc
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();//move a cobrinha
            checkApple();//verifica se a cobrinha comeu a maca
            if (checkBorderCollision()) {
                running = false;//se colidiu com a borda, o jogo termina
            }
        }
        repaint();//repaint para atualizar a tela
    }



    //metodos para capturar eventos de teclado com as teclas "wasd" ou as setas direcionais
    //verificando se a cobrinha nao esta se movendo na direcao oposta
    @Override
    public void keyPressed(KeyEvent e) {
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