
package org.example;
public class Consumer extends Thread {
    private final SharedBuffer buffer;
    private final int itemCount;

    public Consumer(SharedBuffer buffer, int itemCount, String name) {
        super(name);
        this.buffer = buffer;
        this.itemCount = itemCount;
    }

    public void run() {
        for (int i = 0; i < itemCount; i++) {
            buffer.consume();
        }
    }
}
