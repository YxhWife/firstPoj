package thread;

public class ThreadLocalTest {
	public static void main(String args[]){
		
		ThreadLocalTest tpt = new ThreadLocalTest();
		for(int i=0;i<3;i++){
			InnerThread it = tpt.getInnerTask();
			Thread thread = new Thread(it);
			thread.setName("Thread-"+String.valueOf(i));
			thread.start();
		}
	}
	
	private InnerThread getInnerTask(){
		return new InnerThread();
	}
	
	public class InnerThread implements Runnable{
		private LocalClass lc;
		@Override
		public void run() {
			
			lc = new LocalClass();
			for(int i=0;i<5;i++){
				System.out.println(Thread.currentThread().getName()+"******" + lc.getVal());
			}
		}
	}
}
