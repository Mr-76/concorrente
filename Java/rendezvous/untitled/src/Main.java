import java.util.ArrayList;
import java.util.concurrent.*;

class SharedCount2 extends Thread{
    Semaphore semaphoreA;
    Semaphore semaphoreB;


    public SharedCount2(Semaphore semaphorea,Semaphore semaphoreb,String thread_name){
        super(thread_name);
        this.semaphoreA = semaphorea;
        this.semaphoreB = semaphoreb;
    }


    @Override
    public void run(){
        System.out.println("Statement B1");
        semaphoreA.release();
        try {
            semaphoreB.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Statement B2");
    }

}
class SharedCount1 extends Thread{
    Semaphore semaphoreA;
    Semaphore semaphoreB;


    public SharedCount1(Semaphore semaphorea,Semaphore semaphoreb,String thread_name){
        super(thread_name);
        this.semaphoreA = semaphorea;
        this.semaphoreB = semaphoreb;
    }


    @Override
    public void run(){
        System.out.println("Statement A1");
        semaphoreB.release();
        try {
            semaphoreA.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Statement A2");
    }

}
public class Main {
    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphorea = new Semaphore(0);
        Semaphore semaphoreb = new Semaphore(0);
        SharedCount1 shared1 = new SharedCount1(semaphorea,semaphoreb,"this is A");
        SharedCount2 shared2 = new SharedCount2(semaphorea,semaphoreb,"this is B");

        shared2.start();
        shared1.start();

        shared1.join();
        shared2.join();


        System.out.println("end");
    }
}