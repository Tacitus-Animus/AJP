package testing;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import utils.Counter;
import utils.Collections.Tree;

public class TestingField {

	public static void main(String[] args) {

		var i = Integer.valueOf(Integer.MAX_VALUE);
		
		var l = new LinkedList<Integer>();
		var t = new TreeSet<Integer>();
		
		test("world");
		
		for(int a : new Counter(3)) {
			 System.out.println(a);
		}
		
		System.out.println(months.andThen(toDays).andThen(toHours).andThen(onlyOffTime).apply(8));
		
	}

	public static void test(String s) {
		var v = "Hi ";
		
		System.out.println(v + s);
		
	}
	
	static UnaryOperator<Integer> months = i -> i; 
	static UnaryOperator<Integer> toDays = i -> i * 30; 
	static UnaryOperator<Integer> toHours = i -> i * 24; 
	static UnaryOperator<Integer> onlyOffTime = i -> i / 3;	
	
	//static Function<Integer, Integer> days = (days) -> days;
	
	
	
}
