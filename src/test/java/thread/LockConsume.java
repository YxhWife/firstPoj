package thread;

import java.util.concurrent.BlockingQueue;

public class LockConsume implements Runnable{
	
	private BlockingQueue<String> lockTest;
	public LockConsume(BlockingQueue<String> lockTest){
		
		this.lockTest = lockTest;
 	}
	
	@Override
	public void run() {
		while(true){
			try {
				String elemt = lockTest.take();
				System.out.println("consume: " + elemt);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
