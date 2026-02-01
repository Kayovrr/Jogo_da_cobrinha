import java.awt.*;

public class Maca extends Comida { // Mudança aqui!

    public Maca(int x, int y) {
        super(x, y);
    }

    @Override
    public void desenhar(Graphics g, int tamanho) {
        g.setColor(Color.red);
        g.fillOval(x, y, tamanho, tamanho);
    }
    
    // Polimorfismo de Sobrecarga (Requisito 2)
    public void setPosicao(int x, int y) {
        this.x = x;
        this.y = y;
    }
}