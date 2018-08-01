package fp.virtualProxy;

import java.util.function.Supplier;

//Virtual Proxy Pattern; Lazy Initialization.
public class Holder {

	private Supplier<Heavy> heavy = () -> createAndCacheHeavy();
	
	public Holder() {
		System.out.println("Holder Created...");
	}
	
	public Heavy getHeavy() {
		System.out.println("Accessing Heavy Object.");
		return heavy.get();
	}
	
	private synchronized Heavy createAndCacheHeavy() {
		
		System.out.println("Calling get() method of Supplier -> (sychronized) createAndCacheHeavy();");
		
		class HeavyFactory implements Supplier<Heavy> {
			
			private final Heavy heavyInstance = new Heavy();

			@Override
			public Heavy get() {
				System.out.println("Calling get() method of inner class HeavyFactory -> (non-sychronized) return heavyInstance;");
				return heavyInstance;
			}
		}
		
		if(!HeavyFactory.class.isInstance(heavy)) {
			System.out.println("Creating Heavy Factory...");
			heavy = new HeavyFactory();
		}
		
		return heavy.get();
	}
	
	
	
}
