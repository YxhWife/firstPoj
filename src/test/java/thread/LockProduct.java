package thread;

import java.util.concurrent.BlockingQueue;

public class LockProduct implements Runnable{

	//private LockTest lockTest;
	private BlockingQueue<String> lockTest;
	
	public LockProduct(BlockingQueue<String> lockTest){
		
		this.lockTest = lockTest;
 	}
	@Override
	public void run() {
		int i = 0;
		while(i<=100){
			String elem = "elem" + i;
			
			lockTest.add(elem);
			System.out.println("product:" + elem);
			i++;
			
		}
	}

}
