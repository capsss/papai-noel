package santaclausproblem;

import java.util.concurrent.Semaphore;

public class SantaClausProblem {
    public static void main(String[] args){
        //criação dos semáforos
        Semaphore semaforoElfos = new Semaphore(3);
        Semaphore semaforoRenas = new Semaphore(9);
        
        Thread t;
        
        //iniciando 1 Papai Noel
        t = new Thread(new PapaiNoel(semaforoRenas, semaforoElfos));
        t.start();

        //inciando 9 renas
        for(int i=0; i<9; i++){
            t = new Thread(new Renas(semaforoRenas));
            t.start();
        }
        
        //inciando 4 elfos
        for(int i=0; i<4; i++){
            t = new Thread(new Elfos(semaforoElfos));
            t.start();
        }
    }
}
