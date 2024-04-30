import java.util.Scanner;

public class Main {
    public static int completo = 0;
    public static int incompleto = 0;
    public static void main(String[] args) {
        Dado dado = new Dado();
       
        
        ThreadUI[] threadUI = new ThreadUI[400000];
        for (int i = 0; i < 400000; i++) {
            threadUI[i] = new ThreadUI(dado);
        }
        for (int i = 0; i < 40000; i++) {
                
                threadUI[i].start();
            
        }
        
       
       //for(int i = 0; i < 500; i++) {
       //     dado.incrementar();
       //     System.out.println("Valor do dado (Main): " + dado.getValor());
       // }
        System.out.println("valor final: " + dado.getValor());

    }
    public static void status() {
        if (completo + incompleto == 400000) {
            System.out.println("completo: " + completo + " incompleto: " + incompleto);
        }
    }
}