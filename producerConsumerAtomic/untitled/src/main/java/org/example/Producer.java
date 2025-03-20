
package org.example;
import java.util.Random;

public class Producer extends Thread {
    private final SharedBuffer buffer;
    private final int itemCount;

    public Producer(SharedBuffer buffer, int itemCount, String name) {
        super(name);
        this.buffer = buffer;
        this.itemCount = itemCount;
    }

    public void run() {
        Random random = new Random();
        for (int i = 0; i < itemCount; i++) {
            buffer.produce(i);
        }
    }
}
