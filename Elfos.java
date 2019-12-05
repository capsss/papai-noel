package santaclausproblem;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Elfos implements Runnable{
    Semaphore semaforoElfos;
    
    public Elfos(Semaphore semaforoElfos){
        this.semaforoElfos = semaforoElfos;
    }

    @Override
    public void run() {
        Random random = new Random();
        try {
            while(true){
                //espera um número aleatório até precisar de ajuda
                Thread.sleep(random.nextInt(500) * 20);
                //identifica que o elfo precisa de ajuda
                semaforoElfos.acquire();
                System.out.println("elfo: " + Thread.currentThread().getId() + " precisa de ajuda");
                //enquanto não houverem 3 elfos precisando de ajuda, espere
                esperar();
                //consegue ajuda
                conseguirAjuda();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Elfos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public synchronized void esperar() throws InterruptedException{
        while(semaforoElfos.availablePermits() != 3){
            wait(1000);
        }
    }
    
    private void conseguirAjuda(){
        System.out.println("elfo: " + Thread.currentThread().getId() + " conseguiu ajuda");
    }
}
