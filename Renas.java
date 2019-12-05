package santaclausproblem;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Renas implements Runnable{
    Semaphore semaforoRenas;
    int numeroRena;
    
    public Renas(Semaphore semaforoRenas){
        this.semaforoRenas = semaforoRenas;
    }
    
    @Override
    public void run() {
        Random random = new Random();
        try {
            //espera um tempo aleatório até chegar das férias
            Thread.sleep(random.nextInt(500) * 20);
            //identifica que a rena chegou
            semaforoRenas.acquire();
            //atribui um número a rena que chegou
            numeroRena = (9 - semaforoRenas.availablePermits());
            System.out.println("rena: " + numeroRena + " chegou");
            //enquanto as 9 renas não chegarem, espere
            esperar();
            //vá para o trenó
            irParaOTreno();
        } catch (InterruptedException ex) {
            Logger.getLogger(Renas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized void esperar() throws InterruptedException{
        while(semaforoRenas.availablePermits() != 9){
            wait(1000);
        }
    }
    
    private void irParaOTreno(){
        System.out.println("rena: " + numeroRena + " foi para o trenó");
    }
}
