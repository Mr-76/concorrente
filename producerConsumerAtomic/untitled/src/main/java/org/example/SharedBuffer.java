package org.example;
import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


public class SharedBuffer {
    private final Queue<Integer> buffer = new LinkedList<>();
    private final int capacity;
    private final AtomicBoolean lock = new AtomicBoolean(false);
    private final AtomicInteger count = new AtomicInteger(0);


    public SharedBuffer(int capacity) {
        this.capacity = capacity;
    }

    public void produce(int item) {
        if (lock.compareAndSet(false,true)){
            if (count.get() < capacity){
                buffer.add(item);
                count.incrementAndGet();
            }else{
                lock.set(false);
            }
        }
    }

    public int consume() {
        if (lock.compareAndSet(false,true)){
            if (count.get() > 0){//tem mais que 0 pode consumir
                int item = buffer.remove();
                count.decrementAndGet();
                lock.set(false);

                return item;
            }else{//ta zerado saia
                lock.set(false);
            }
        }else{
            ;
        }

        return 0;
    }

    public int getSize() {
        return 0;
    }
}
