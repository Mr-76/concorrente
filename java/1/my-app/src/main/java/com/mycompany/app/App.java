package com.mycompany.app;

/**
 * Hello world!
 */
public class App {
	public static void main(String[] args) {
		// System.out.println("Hello World!");
		// MyThread my = new MyThread();
		// my.start();
		// Runnable runnable = new MyRunnable();
		// Thread thread = new Thread(runnable);
		// thread.start();

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

		// Demonstrating TwoSums
		// TwoSums twoSums = new TwoSums();
		//
		// Thread thread1 = new Thread(() -> {
		// System.out.println("Starting thread1");
		// int count = 0;
		// for (int i = 0; i < 1000000000; i++) {
		// count += 1;
		// }
		// twoSums.add(count);
		// });
		//
		// Thread thread2 = new Thread(() -> {
		// System.out.println("Starting thread1");
		// int count2 = 0;
		// for (int i = 0; i < 1000000000; i++) {
		// count2 += 1;
		// }
		// twoSums.add(count2);
		// });
		//
		// thread1.start();
		// thread2.start();
		//
		// try {
		// thread1.join();
		// thread2.join();
		// } catch (InterruptedException e) {
		// System.out.println("Thread interrupted: " + e.getMessage());
		// }
		//
		// System.out.println("count: " + twoSums.getSum1());
		int count = 0;

		for (int i = 0; i < 1000000000; i++) {
			count += 1;
		}
		for (int i = 0; i < 1000000000; i++) {
			count += 1;
		}
		System.out.println("Sequencial = " + count);
	}

	}

	class TwoSums {

		private int sum1 = 0;
		private int sum2 = 0;

		private final Object sum1Lock = new Object();
		private final Object sum2Lock = new Object();

		public void add(int val1, int val2) {
			synchronized (this.sum1Lock) {
				this.sum1 += val1;
			}
			synchronized (this.sum2Lock) {
				this.sum2 += val2;
			}
		}
	}

	class MyThread extends Thread {
		public void run() {
			System.out.println("MyThread running");
		}
	}

	class MyRunnable implements Runnable {
		public void run() {
			System.out.println("MyRunnable running");
		}
	}
}
