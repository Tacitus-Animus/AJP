package fp.TailCallRecursion;

public class TailCalls {

	public static <T> TailCall<T> call(final TailCall<T> nextCall) { return nextCall; }
	
	public static <T> TailCall<T> done(final T value){
		return new TailCall<T>() {
		
			@Override
			public boolean isComplete() {
				System.out.println("Complete.");
				return true;
			}
			
			@Override
			public T result() {
				System.out.print("Result: ");
				return value;
			}
			
			@Override
			public TailCall<T> apply() { throw new Error("Not Implemented"); }
			
		};
	}
	
}
