import java.util.ArrayList;
import java.util.concurrent.*;

class SharedCount{
    int count = 0 ;
    Semaphore mutex;

    public SharedCount(int count, int semaphore_value){
        this.count = count;
        this.mutex = new Semaphore(semaphore_value);
    }

    public void increment() throws InterruptedException {
        mutex.acquire();
        count++;
        mutex.release();
    }

    public int get_count() {
        return this.count;
    }
}
public class Main {
    public static void main(String[] args) throws InterruptedException {

        SharedCount shared = new SharedCount(0,1);
        ArrayList<Thread> array_threads = new ArrayList<Thread>();
        for(int i = 0; i < 10; i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        shared.increment();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            array_threads.add(thread);
        }

        for (Thread element : array_threads){
            element.start();
        }

        for (Thread element : array_threads){
            element.join();
        }

        System.out.println("the count" + shared.get_count());
    }
}