import java.util.Scanner;

public class ThreadUI extends Thread{
    private Dado dadoExterno;
    private static boolean processando = false;

    public ThreadUI(Dado dado) {
        //System.out.println("ThreadUI criada valor: "+dado.getValor());
        this.dadoExterno = dado;
    }
    @Override
    public void run() {
        
        super.run();
        int volta = 0;
        while ( volta < 500) {
            
                processando = true;
                volta++;
                synchronized (this.dadoExterno) {
                    int v = this.dadoExterno.getValor();
                    this.dadoExterno.decrementar();
                   //if (v-1 != this.dadoExterno.getValor()) {
                       // System.out.println("ThreadUI: " + v + " -> " + this.dadoExterno.getValor());
                    //}
                }
                processando = false;
                      
        }
        processando = false;
        if (volta == 500) {
            Main.completo++;
        } else {
            Main.incompleto++;
        }
        Main.status();
    }

    @Override
    public synchronized void start() {
        // TODO Auto-generated method stub
        super.start();
    }


    
    
}
