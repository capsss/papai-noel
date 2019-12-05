package santaclausproblem;

import java.util.concurrent.Semaphore;

public class PapaiNoel implements Runnable{
    Semaphore semaforoRenas;
    Semaphore semaforoElfos;
    
    public PapaiNoel(Semaphore semaforoRenas, Semaphore semaforoElfos){
        this.semaforoRenas = semaforoRenas;
        this.semaforoElfos = semaforoElfos;
    }

    @Override
    public void run() {
        while(true){
            //se as 9 renas já chegaram
            if(semaforoRenas.availablePermits() == 0){
                //acorda o Papai Noel para preparar o trenó
                prepararTreno();
            } else {
                //se 3 elfos precisam de ajuda
                if(semaforoElfos.availablePermits() == 0) {
                    //acorda o Papai Noel para ajudar
                    ajudarElfos();
                }
            }
        }
    }
    
    private synchronized void prepararTreno(){
        System.out.println("papai noel arrumou o trenó para as renas");
        semaforoRenas.release(9);
        notifyAll();
    }
    
    private synchronized void ajudarElfos(){
        System.out.println("papai noel ajudou os elfos");
        semaforoElfos.release(3);
        notifyAll();
    }
}
