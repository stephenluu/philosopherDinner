package perEat;

/**
 * 哲学家线程
 * @author stephenluu
 *
 */
public class PerThread extends Thread {

	private static int[] chopstick = {1,1,1,1,1};
	private int i;
	private int n = 5;

	public PerThread(int i) {
		this.i = i;
	}

	@Override
	public void run() {

		//拿左筷子
		synchronized (chopstick) {
			while (chopstick[i] == 0) {

				try {
					chopstick.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}


			chopstick[i]--;
			System.out.println("per" + this.getName()
					+ " got left chopstick.");

			chopstick.notify();



		}

		try {
			Thread.sleep((long) (Math.random()*1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//拿右筷子
		synchronized (chopstick) {
			while (chopstick[(i + 1) % n] == 0) {

				try {
					chopstick.wait(3*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				finally{
					System.out.println(this.getName()+" waited 3s ,free left chopstick");
					chopstick[i] ++;
				}
			}


			chopstick[(i + 1) % n]--;
			System.out.println("per" + this.getName()
					+ " got right chopstick.");

			System.out.println("per" + this.getName() + " is eating...");

			chopstick[i]++;
			chopstick[(i + 1) % n]++;
			System.out.println("per"+this.getName()+" is thinking...");

			chopstick.notify();
		}

	}

}