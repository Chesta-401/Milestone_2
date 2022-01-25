/*
 *  Write a program that runs 5 threads, each thread randomizes a number
	between 1 and 10. The main thread waits for all the others to finish, calculates
	the sum of the numbers which were randomized and prints that sum. You will
	need to implement a Runnable class that randomizes a number and store it in a
	member field. When all the threads have done, your main program can go over
	all the objects and check the stored values in each object.
 */
package threads;

import java.util.Random;

public class MyRunnable implements Runnable{

	Random random = new Random();
	private int sum;

	private boolean isCompleted = false;
	int count = 0;



	@Override
	public void run() {
		int randomNum = random.nextInt(10) + 1;
		System.out.println("Generated number - "+randomNum);
		count++;
		sum = sum + randomNum;
		if (count == 5) {

			isCompleted = true;
			synchronized (this) {
				notifyAll();
			}
		}
	}



	public synchronized int getSum() {
		if (!isCompleted) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return sum;
	}

}


