import java.util.ArrayList;

//This is one of the best simplest exmaple usages.....
//in u incread the number of theads it will overwtite the value of cout if u put like 8 it will mostly not do that
class MultithreadingDemo extends Thread {

	public static int count2 = 0;

	public MultithreadingDemo(int count2) {
		this.count2 = count2;
	}

	public void run() {
		try {
			// Displaying the thread that is running
			System.out.println(
					"Thread " + Thread.currentThread().getId()
							+ " is running");

			count2 += 1;
		} catch (Exception e) {
			// Throwing an exception
			System.out.println("Exception is caught");
		}
	}
}

// Main Class
public class Multithread {

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
