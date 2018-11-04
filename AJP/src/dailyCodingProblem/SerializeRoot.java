package dailyCodingProblem;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;
/**
 * This problem was asked by Google.
 *
 * Given the root to a binary tree, implement serialize(root), 
 * which serializes the tree into a string, and deserialize(s), 
 * which deserializes the string back into the tree.
 *
 * For example, given the following Node class
 * 
 * class Node:
 *   def __init__(self, val, left=None, right=None):
 *      self.val = val
 *      self.left = left
 *      self.right = right
 * The following test should pass:
 * 
 * node = Node('root', Node('left', Node('left.left')), Node('right'))
 * assert deserialize(serialize(node)).left.left.val == 'left.left'
 * 
 * @author Alexander Paul
 *
 */
public class SerializeRoot {
	
	@Test
	public void test() {
		 Node root = new Node("root", new Node("left", new Node("left.left"), 
				 									   null),
				 					  new Node("right"));
		 
		 assertTrue(serialize(root).equals("root left left.left # "
				 											 + "# "
				 								   + "# "
				 							  + "right # "
		 											+ "# "));
		 String serialized = serialize(root);
		 
		 Node deserialized = deserialize(serialized);
		 
		 Node better = betterWay(serialized);
		 
		 assertTrue(deserialized.left.left.value.equals("left.left"));
		 assertTrue(better.left.left.value.equals("left.left"));

	}
	
	static class Node {
		
		String value;
		Node left;
		Node right;
		
		Node(String value, Node left, Node right){
			this.value =  value;
			this.left = left;
			this.right = right;
		}
		
		Node(String value){
			this.value =  value;
			this.left = null;
			this.right = null;
		}
		
	}
	
	/**
	 * Since you serialize the left nodes first,
	 * the better solution can also recursively call on the left nodes first to de-serialize.
	 * The split string of the serialized node contains all the left nodes in the beginning.
	 * So, iterating through the de-serialize process works properly.
	 * @param root
	 * @return
	 */
	private static String serialize(Node root) {
		StringBuilder sb = new StringBuilder();
		sb.append(root.value);
		sb.append(' ');
		sb.append(root.left == null ? "# " : serialize(root.left));
		sb.append(root.right == null ? "# " : serialize(root.right));
		return sb.toString();
	}

	/**
	 *  My way was to complex, the better solution was done in O(N) time and space.
	 * @param string the serialized version of a node
	 * @return the de-serialized node
	 */
	private static Node deserialize(String string) {
		//Used to populate a node if not null, else removed.
		var queue = new ArrayDeque<String>(Arrays.asList(string.split(" ")));
		//Pushes the last node visited to move down the tree, else pops to move back up the tree.
		var stack = new LinkedList<Node>();
		//Used to move either to the left or to the right in the tree.
		var hash = new HashMap<Node, Boolean>();
		
		Node selected = new Node(queue.poll());    
		
		hash.put(selected, false);
		
		while(!queue.isEmpty()) {
				if(queue.peek().equals("#")) {
					queue.remove();
					if(hash.get(selected) == true) selected = stack.pop(); //both complete, so move up the tree.
					else hash.put(selected, true); 						   //mark left as complete.
				}else if(hash.get(selected) == true) { //move down and to the right.
					stack.push(selected);
					selected = selected.right = new Node(queue.poll());
					hash.put(selected, false);
				}else {
					hash.put(selected, true);          //move down and to the left.
					stack.push(selected);
					selected = selected.left = new Node(queue.poll());
					hash.put(selected, false);
				}		
		}
		
		return selected;
	}
	
	/**
	 * This is done in O(N) time and space.
	 * @param string the serialized version of a node
	 * @return the de-serialized node
	 */
	private static Node betterWay(String string) {
		Iterator<String> iter = Arrays.asList(string.split(" ")).iterator();
		return help(iter);
	}
	
	private static Node help(Iterator<String> iter) {
		String val = iter.next();
		if(val.equals("#")) return null;
		
			Node node = new Node(val);
			node.left = help(iter);
			node.right = help(iter);
			return node;
		
	};
	
}
