package perEat;

/**
 * 解决哲学家进餐问题
 * @author stephenluu
 *
 */
public class MainTest {

	public static void main(String[] args) {


        for (int i = 0; i <5; i++) {

        	Thread t = new PerThread(i);

            t.start();
		}		
	}

}