class Person {
	private String name;
	private int age;
	private int sum = 0;

	// Constructor with arguments
	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	// Synchronized method to update the sum safely
	public synchronized void addToSum(int value) {
		sum += value;
		System.out.println(Thread.currentThread().getName() + " added " + value + ". Total sum: " + sum);
	}

	public int getSum() {
		return sum;
	}

}

public class Main {
	public static void main(String[] args) {
		Person person = new Person("Alice");

		Thread[] threads = new Thread[10]; // Array to hold thread references so u can call them later in join
							// method

		for (int i = 1; i <= 10; i++) {
			int threadId = i; // Capture loop variable for the lambda
			threads[i - 1] = new Thread(() -> {
				for (int j = 0; j < 5; j++) { // Each thread will add 5 times
					person.addToSum(threadId);
					try {
						Thread.sleep(100); // Simulate some delay
					} catch (InterruptedException e) {
						System.out.println("Thread interrupted: " + e.getMessage());
					}
				}
			}, "Thread-" + threadId);

			threads[i - 1].start(); // Start the thread
		}

		// equivalent butusing runnable
		//// //threads[i - 1] = new Thread(new Runnable() {
		// @Override
		// public void run() {
		// for (int j = 0; j < 5; j++) {
		// person.addToSum(threadId);
		// try {
		// Thread.sleep(100);
		// } catch (InterruptedException e) {
		// System.out.println("Thread interrupted: " + e.getMessage());
		// }
		// }
		// }
		// }, "Thread-" + threadId);
		//
		//
		//
		//
		//
		// OOOOOR
		// System.out.println(Thread.currentThread().getName());
		// for (int i = 0; i < 10; i++) {
		// new Thread("" + i) {
		// public void run() {
		// System.out.println("Thread: " + getName() + " running");
		// }
		// }.start();
		// }
		// now two sums at same time...
		//


		for (Thread thread : threads) {
			try {
				thread.join(); // Wait for each thread to finish
			} catch (InterruptedException e) {
				System.out.println("Thread interrupted: " + e.getMessage());
			}
		}

		System.out.println("Final sum: " + person.getSum());
	}
}
