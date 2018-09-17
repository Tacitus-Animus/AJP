package fp.memoization;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;
import java.util.function.Function;

import fp.locking.Locker;

public class Memoizer {

	public static <T,R> R callMemoized(final BiFunction<Function<T, R>, T, R> profitFunct, final T length) {
		
		Function<T,R> memoized = new Function<T, R>() {

			private final Map<T, R> store = new /*Concurrent*/HashMap<>();
			
			public R apply(final T length) {
				
				//causes ConcurrentModificationException for HashMap; source code isn't clear where exception is thrown.
				//or causes IllegalStateException for ConcurrentHashMap; Source code reveals clear Contract "No Modification in method"[Paraphrased]
				//Tried Toggling between functional locks with Supplier function and traditional locks. My lack of concurrency knowledge?
				//Tried synchronized keyword in block and method form in multiple spots; again... lack of knowledge.
				
//				Lock lock = new ReentrantLock();
		
//				Locker.runLocked(lock, () -> store.computeIfAbsent(input, key -> absentfunct.apply(this, key)));

//				***********************************
				
//				lock.lock();
//				try {
//				return store.computeIfAbsent(input, key -> absentfunct.apply(this, key));
//				}finally {
//					lock.unlock();
//				}
				
				//only workaround.
				if(!store.containsKey(length)) {
					store.put(length, profitFunct.apply(this, length));				
				}
				
					return store.get(length);
				
			}
		};
		
		return memoized.apply(length);
	}

}
