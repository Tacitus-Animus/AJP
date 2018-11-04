package dailyCodingProblem;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This problem was asked by Google.
 *
 * A unival tree (which stands for "universal value") is a tree where all nodes under it have the same value.
 *
 * Given the root to a binary tree, count the number of unival subtrees.
 *
 * For example, the following tree has 5 unival subtrees:
 *
 *   0
 *  / \
 * 1   0
 *    / \
 *   1   0 
 *  / \
 * 1   1
 *
 * Took me 6 days... The psuedo code solution sort of pushed me in the right direction on the fourth day.
 * Watching CS Do-Jo helped a bit more on the fifth day;
 * I partially watched the video, since I still wanted to finish the rest by myself.
 *
 * @author Alexander Paul
 *
 */
class UnivalSubTrees {
	
	@Test
	void testThree() {
		/*
		 * Result: 3
		 *    0
		 *   / \
		 *  0   0
		 *     / \
		 *    0   0
		 *    	   \
		 *          1
		 */
		Node three = new Node(false, new Node(false), 
					                 new Node(false, new Node(false), 	
					                    		     new Node(false, null,
					                    		        		     new Node(false))));	
		assertTrue(unival_count(three) == 3);
	}
	
	@Test
	void testFour() {
		/*
		 * Result: 4
		 *    0
		 *   / \
		 *  0   0
		 *     / \
		 *    0   0
		 *    	   
		 *        
		 */
		Node four = new Node(false, new Node(false), 
					                 new Node(false, new Node(false), 	
					                    		     new Node(false)));	
		
		assertTrue(unival_count(four) == 4);
	}
	
	@Test
	public void testFive() {
		/*
		 * Result: 5
		 *    0
		 *   / \
		 *  0   0
		 *     / \
		 *    1   0
		 *   / \	   
		 *  1   1     
		 */
		Node five = new Node(false, new Node(true), 
					                new Node(false, new Node(true, new Node(true),
					                		 					   new Node(true)), 	
					                    		    new Node(false)));	
		
		assertTrue(unival_count(five) == 5);
	}
	
	@Test
	public void testOne() {
		/*
		 * Result: 1
		 *    0
		 *     \
		 *      0
		 *       \
		 *        0     
		 */
		Node one = new Node(false, null, 
								   new Node(false, null,
										   		   new Node(true)));			
		assertTrue(unival_count(one) == 1);
	}
	
	/**
	 * Delegates a helper method to get unival count.
	 * @param node to find unival counts for.
	 * @return the number of univals.
	 */
	private int unival_count(Node node) {
		return is_unival(node, null)[0];
	}

	/**
	 * Helper method that caches count and is-unival flags in an <code>int[]</code>.
	 * Recursively climbs down once and counts on the way back up.
	 * On every step back up, checks unival flags to decide on extra counts.
	 * Caches a node as a unival candidate of it's children have same values.
	 * The origin is checked at sub-squint levels so not to count extra subtrees as univals on the climb back up.
	 * @param origin is the node that becomes a unival candidate.
	 * @param node to be checked of univals.
	 * @return the number of univals in node.
	 */
	static int[] is_unival(Node node, Node origin) {
		//0: count 1: unival flag; mostly for leafs.
		int[] cache = new int[] {0,0};
		
		//If left and right nodes aren't null and have equal values, becomes a unival candidate.
		//if origin is null, that means there have been no previous unival candidates.
		//so we mark this node as an unival origin.
		//if this sub tree doesn't have equal values, origin is marked null to signify unival is lost.
		if(node.l != null && node.l.v == node.v && node.r != null && node.r.v == node.v) {
			cache[1] = 1;
			if(origin == null) origin = node;
		}else origin = null;

		//didn't want to worry about nullpointers.
		int[] left = node.l == null ? new int[] {0,0} : is_unival(node.l, origin);
		int[] right = node.r == null ? new int[] {0,0} : is_unival(node.r, origin);
	
		//Found a leaf node. Technically a unival sub-tree; return with a count of one and is-unival flag true.
		if(left[0] == 0 && right[0] == 0) return new int[] {1,1};
		
		//If unival candidates return with their own is-unival flag as true, 
		//plus, if we're at the origin of a unival, then we have completely climbed down and up successfully; count goes up.
		//Else, candidate loses.
		if(cache[1] == 1 && left[1] == 1 && right[1] == 1) {
			if(origin == node) cache[0] += 1;
		}else cache[1] = 0;
		
		//add up leaf and unival subtree counts.
		cache[0] += left[0] + right[0];
		
		return cache;
	}
	
	static class Node {
		boolean v;
		Node l;
		Node r;
		Node(boolean v){
			this.v = v;
		}
		Node(boolean v, Node l, Node r){
			this.v = v;
			this.l = l;
			this.r = r;
		}
	}

}
