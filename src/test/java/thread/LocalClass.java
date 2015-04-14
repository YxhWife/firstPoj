package thread;

public class LocalClass {
	
	private static ThreadLocal<Integer> localVal = new ThreadLocal<Integer>();
	
	public LocalClass(){
		localVal.set(0);
	}
	
	public String getVal(){
		int val = localVal.get();
		localVal.set(++val);
		
		return Thread.currentThread().getName()+">>val=" + val;
	}
}
