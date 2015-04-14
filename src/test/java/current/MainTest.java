package current;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.CDL;

public class MainTest {

	private ConcurrentMap<String,Object> map = new ConcurrentHashMap<>();
	private AtomicInteger val = new AtomicInteger();
	
	public AtomicInteger getVal(){
		return this.val;
	}
	
	public ConcurrentMap<String,Object> getMap(){
		return this.map;
	}
	
	public void process(){
		Integer temp = val.incrementAndGet();
		String key = Thread.currentThread().getName();
		map.put(key, temp);
		
	}
	public static void main(String args[]) throws InterruptedException{

		CountDownLatch cdl = new CountDownLatch(5);
		MainTest mt = new MainTest();
		for(int i=0;i<5;i++){
			
			Thread t = new Thread(new MapTest(mt,cdl));
			String tName = "thread_"+i;
			t.setName(tName);
			t.start();
			cdl.countDown();
		}
		System.out.println("Main: "+mt.getVal());
		cdl.await();
		Map<String, Object> map = mt.getMap();
		for(String key : map.keySet()){
			System.out.println("key="+key+"***"+"val="+map.get(key));
		}
	}
}
