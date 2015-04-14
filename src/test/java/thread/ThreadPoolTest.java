package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolTest {
	
	public static void main(String args[]) throws InterruptedException, ExecutionException{
		
		List<Future<String>> resultList = new ArrayList<>();
		ThreadPoolTest tpt = new ThreadPoolTest();
		ExecutorService pool = Executors.newFixedThreadPool(5);
		for(int i=0;i<10;i++){
			//pool.execute((Runnable) tpt.getInnerTask());
			Future<String> future = pool.submit(tpt.getCallTask());
			resultList.add(future);
		}
		for(Future<String> future : resultList){
			System.out.println(future.get());
		}
		pool.shutdown();
	}
	
	//Runnable测试
	public InnerTask getInnerTask(){
		return new InnerTask();
	}
	public class InnerTask implements Runnable {
		
		public void run() {
			int val = 1000000;
			while(val>0){
				val--;
			}
			System.out.println(Thread.currentThread().getName());
		}
	}
	public CallTask getCallTask(){
		return new CallTask();
	}
	
	//Callable测试
	public class CallTask implements Callable<String> {
		Random r = new Random();
		@Override
		public String call() throws Exception {
			int temp = 1000000;
			while(temp>0){
				temp--;
			}
			r.setSeed(System.currentTimeMillis());
			int rv = r.nextInt(100);
			String val =Thread.currentThread().getName() + "***" + rv;
			return val;
		}
		
	}
}


