package fp.locking;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class testLocking {

	public static void main(String[] args) throws Exception {
		
		Lock lock = new ReentrantLock();	
	
		Locker.runLocked(lock, () -> System.out.println("BYE"));
		Locker.runLocked(lock, () -> System.out.println("HI"));
		
	}

}
