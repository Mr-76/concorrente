import java.util.ArrayList;
//this one does the same thing BUT will sync betwee n the treads writing to the same variable
class MultithreadingDemo extends Thread {

	public static int count2 = 0;

	public MultithreadingDemo(int count2) {
	}

	public void run() {
		try {
			// Displaying the thread that is running
			System.out.println(
					"Thread " + Thread.currentThread().getId()
							+ " is running");

			this.increment();
		} catch (Exception e) {
			// Throwing an exception
			System.out.println("Exception is caught");
		}
	}

	public synchronized void increment() {
		count2 += 1;
	}
}

// Main Class
public class Multisyn {

	public static int count = 0;

	public static void main(String[] args) {
		int n = 100; // Number of threads
		ArrayList<Thread> newArray = new ArrayList<Thread>();
		for (int i = 0; i < n; i++) {
			MultithreadingDemo object2 = new MultithreadingDemo(0);
			newArray.add(object2);
			object2.start();
		}

		for (Thread tr : newArray) {
			try {
				tr.join();

			} catch (InterruptedException e) {

				System.out.println("Thread was interrupted: " + e.getMessage());
			}

		}
		System.out.println("Value of count " + MultithreadingDemo.count2);
	}
}
