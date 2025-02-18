import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;
import java.util.stream.*;
import java.util.*;
import java.util.Random;



class MinhaFila <T> {
    private static int FILA_VAZIA_TAMANHO = 0;

    private Queue<T> queue = new ArrayDeque<>();
    private int max_size;
    private ReentrantLock lockObj = new ReentrantLock();
    private Condition producerLock = lockObj.newCondition();
    private Condition consumerLock = lockObj.newCondition();
    public MinhaFila(int n){
        max_size = n;
    }


    public void add(T element){
        lockObj.lock();
            try {
                while(max_size == queue.size()){
                    producerLock.await();
                    System.out.println("cannot enter more elemetns");
                }
                queue.add(element);
                System.out.println("A " + queue);
                consumerLock.signalAll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finally {
                lockObj.unlock();
            }


    }
    public void remove(){
        lockObj.lock();
            try {
                while(FILA_VAZIA_TAMANHO == queue.size()){
                    System.out.println("Cant put more elements....");
                    consumerLock.await();
                }
                queue.remove();
                System.out.println("R " +  queue);
                producerLock.signalAll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finally {
                lockObj.unlock();
            }


    }



    public void result(){
        System.out.println(queue);
    }

}
public class Main {
    public static void main(String[] args) throws InterruptedException {
        MinhaFila<Integer> minhaFila = new MinhaFila<>(110);
        Random rand = new Random();
        int numberOfThreads = rand.nextInt(100)/2;
        System.out.println("Number of threads " + numberOfThreads);
        ArrayList<Thread> arrayOfThreads = new ArrayList<Thread>();
        for(int i =0 ; i < numberOfThreads; i ++){
            System.out.print("Thread Creating" );
            Thread threadT = new Thread(new Runnable(){
                @Override
                public void run(){
                    Random rand = new Random();
                    int numberOfSpots = rand.nextInt(5);
                    for (int b = 0; b < numberOfSpots;b ++){
                        minhaFila.add(rand.nextInt(1000));
                    }
                }
            });
            arrayOfThreads.add(threadT);
        }
        for(int c =0 ; c < numberOfThreads; c ++){
            System.out.print("Thread Creating" );
            Thread threadB = new Thread(new Runnable(){
                @Override
                public void run(){
                        minhaFila.remove();
                }
            });
            arrayOfThreads.add(threadB);
        }

        int size = arrayOfThreads.size();
        Random rand2 = new Random();


        ArrayList<Thread> arrayOfThreadsJoin = new ArrayList<Thread>();

        for (int i = 0; i < size; i ++){
            int selected_value = rand2.nextInt(arrayOfThreads.size());
            Thread threadt = arrayOfThreads.remove(selected_value);
            threadt.start();
            arrayOfThreadsJoin.add(threadt);
        }


        for (Thread t : arrayOfThreadsJoin){
            t.join();
        }

        //verify if its empty
        if (arrayOfThreads.isEmpty()){
            System.out.println("Emptyyyyyyy");
        }else{
            ;
        }
        minhaFila.result();
    }
}