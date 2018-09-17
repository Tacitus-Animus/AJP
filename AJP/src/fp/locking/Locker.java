package fp.locking;

import java.util.concurrent.locks.Lock;
import java.util.function.Supplier;

public class Locker {

public static <T> T runLocked(Lock lock, Supplier<T> block) {

		lock.lock();
		try {
			return block.get();
		}finally {
			lock.unlock();
		}
	
	}
public static void runLocked(Lock lock, Runnable block) {

	lock.lock();
	try {
		 block.run();
	}finally {
		lock.unlock();
	}

}
}
