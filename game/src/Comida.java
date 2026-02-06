public abstract class Comida extends ObjetoJogo {
    protected int valorPontos;

    public Comida(int x, int y) {
        super(x, y);
        this.valorPontos = 1; // Valor padrão
    }

    public int getValorPontos() {
        return valorPontos;
    }
}