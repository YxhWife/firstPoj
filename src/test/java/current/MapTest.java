package current;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class MapTest implements Runnable{
	
	private MainTest mt;
	private CountDownLatch cdl;
	
	public MapTest(MainTest mt,CountDownLatch cdl) {
		this.mt = mt;
		this.cdl = cdl;
	}

	@Override
	public void run() {
		mt.process();
		cdl.countDown();
		
		//System.out.println(map.get("key"));
//		cdl.countDown();
	}
	
}
