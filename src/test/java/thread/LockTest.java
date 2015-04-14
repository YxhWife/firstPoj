package thread;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	
	final Lock lock = new ReentrantLock();
	Condition notFull = lock.newCondition();
	Condition notEmpty = lock.newCondition();
	
	final static List<String> list = new ArrayList<>();
	final static int LIST_SIZE = 10;
	int entityCount;
	/**
	 * 存一个数据
	 * @param entity
	 * @throws InterruptedException
	 */
	public void push(String entity) throws InterruptedException{
		lock.lock();
		try{
			if(entityCount>=LIST_SIZE){
				notFull.await();
			}
			list.add(entity);
			notEmpty.signal();
		}
		finally{
			lock.unlock();
		}
	}
	/**
	 * 取一个数据
	 * @return
	 * @throws InterruptedException
	 */
	public String pop() throws InterruptedException{
		lock.lock();
		try{
			if(list.size()<=0){
				notEmpty.await();
			}
			String elemt = list.remove(0);
			return elemt;
		}finally{
			lock.unlock();
		}
	}
	public static void main(String args[]){
		list.add("zhanglin");
		System.out.println(list.get(0));
	}

}
