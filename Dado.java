public class Dado {
    private int valor;

    public Dado() {
        this.valor = 0;
    }

    synchronized public void incrementar() {
        this.valor++;
    }   
    // aqui temos o conceito de sobrecarga, ou seja, dois métodos com o mesmo nome, mas com parâmetros diferentes 
    // (assinatura diferente)
    public void incrementar(int valor) {
        this.valor += valor;
    }

    synchronized public void decrementar() {
        this.valor--;
    }
    public void decrementar(int valor) {
        this.valor -= valor;
    }
    public void reset() {
        this.valor = 0;
    }

    public int getValor() {
        return this.valor;
    }
    
}
