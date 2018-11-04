package generics.designPatterns.visitor;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TreeClient {

	public static <T> String toString(Tree<T> tree) {
		return tree.visitBy(new Tree.Visitor<T, String>() {

			public String visitLeaf(T element) {
				return element.toString();
			}

			public String visitBranch(String left, String right) {
				return "(" + left + "^" + right + ")";
			}
		});
	}
	
	public static <N extends Number> double sum(Tree<N> tree) {
		return tree.visitBy(new Tree.Visitor<N, Double>() {

			public Double visitLeaf(N element) {
				return element.doubleValue();
			}

			public Double visitBranch(Double left, Double right) {
				return left + right;
			}
		});
	}
	
	@Test
	public static void main(String[] args) {
		Tree<Integer> tree = Tree.branch(Tree.branch(Tree.leaf(1), 
												     Tree.leaf(2)), 
								         Tree.leaf(3));
		assertTrue(toString(tree).equals("((1^2)^3)"));
		assertTrue(sum(tree) == 6);
	}

}
