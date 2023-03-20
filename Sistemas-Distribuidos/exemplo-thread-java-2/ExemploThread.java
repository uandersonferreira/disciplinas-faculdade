
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ExemploThread extends Thread{

    private static final int TOTAL_NUMEROS = 100_000_000;
    private static final int THREADS = 4;
    private static final int SUB_TOTAL = (int) (TOTAL_NUMEROS/THREADS);


    private Random random = new Random();

    private List<Double> numeros = new ArrayList<>(SUB_TOTAL);
    /*
     A capacidade inicial(size) padrão de um objeto ArrayList é de 10 elementos.
     */

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Iniciando. . .");
        double inicio = System.currentTimeMillis();
        var threads =  new Thread[THREADS];

        for (int i = 0; i < THREADS; i++) {
            var t = new ExemploThread();
            threads[i] = t;
           t.start();
          System.out.printf("THREADS[%d], name -> %s \n", i, t.getName());
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Finalizando. . .");
        double fim = System.currentTimeMillis();
        System.out.println("Tempo gasto Total: "+ (fim - inicio)/1000 + "s");

        
    }

    public void run(){
        // double inicio = System.currentTimeMillis();
        for (int i = 0; i < SUB_TOTAL; i++) {
           numeros.add(random.nextDouble());
        }
        // double fim = System.currentTimeMillis();
        // System.out.println("Tempo gasto: "+ (fim - inicio)/1000 + "s");
    }//method

}//class