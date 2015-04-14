package thread;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MainTest {
	
	public static void main(String args[]){
		//LockTest lockTest = new LockTest();
//		BlockingQueue<String> lockTest = new ArrayBlockingQueue<String>(50);
//		LockConsume lc = new LockConsume(lockTest);
//		LockProduct lp = new LockProduct(lockTest);
//		new Thread(lc).start();
//		new Thread(lp).start();
		
		PipedInputStream pi = new PipedInputStream();
		PipedOutputStream po = new PipedOutputStream();
		PipeCustom pc = new PipeCustom(pi);
		Pipeproduct pp = new Pipeproduct(po);
		try {
			po.connect(pi);
			new Thread(pc).start();
			new Thread(pp).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
