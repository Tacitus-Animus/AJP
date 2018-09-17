package utils.Collections;

public class Data {
	private Data() {}
	public static <T> void swap(T a, T b){
		T temp = a;
		a = b;
		b = temp;
	}
}
