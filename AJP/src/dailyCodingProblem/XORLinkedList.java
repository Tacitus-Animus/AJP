package dailyCodingProblem;

import java.util.ArrayList;

/**
 * This problem was asked by Google.
 * 
 * An XOR linked list is a more memory efficient doubly linked list. 
 * 
 * Instead of each node holding next and prev fields, 
 * it holds a field named both, which is an XOR of the next node and the previous node. 
 * Implement an XOR linked list; 
 * it has an add(element) which adds the element to the end, 
 * and a get(index) which returns the node at index.
 *  
 * If using a language that has no pointers (such as Python), 
 * you can assume you have access to get_pointer and dereference_pointer functions 
 * that converts between nodes and memory addresses.
 * 
 * <pre>
 * XOR   +   -
 * 	  _|_F_|_T_
 * + F_|_F_|_T_
 * - T_|_T_|_F_
 * </pre>
 * @author Alexander Paul
 *
 */
public class XORLinkedList {

	Node head, tail;
	ArrayList<Node> nodes;
	
	XORLinkedList(){
		head = tail = null;
		nodes = new ArrayList<>();
		nodes.add(head);
		nodes.add(tail);
	}
	
	static class Node{
		int value;
		int both;
		
		Node(int value){
			this.value = value;
		}
	}
	
	/*void add(int value) {
		Node newNode = new Node(value);
		if(head == null) {
			head = tail = newNode;
		}else if(tail){
			tail = newNode;
			
			
		}
	}*/
	
	
	
}
