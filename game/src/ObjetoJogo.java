import java.awt.Graphics;

public abstract class ObjetoJogo {
    protected int x;
    protected int y;

    // Construtor necessário para a hierarquia funcionar
    public ObjetoJogo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void desenhar(Graphics g, int tamanho);

    public int getX() { return x; }
    public int getY() { return y; }
}