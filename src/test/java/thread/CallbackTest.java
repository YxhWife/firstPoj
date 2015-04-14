package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallbackTest {
	
	public static void main(String args[]) throws InterruptedException, ExecutionException{
		
		 Callable<String> task = new Callable<String>(){
		        public String call()throws Exception{
		            return "test";
		        }
		    };
	     ExecutorService pool = Executors.newSingleThreadExecutor();
		 Future<String> future = pool.submit(task);
		 String val = future.get();
		 System.out.println(val);
		 pool.shutdown();
	}

}
