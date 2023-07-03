package in.ineuron.comp;


//Java program to print root to leaf path without using
//recursion
import java.util.*;

public class PrintRoottoLeaf {

//A binary tree node structure
static class Node {
	int data;
	Node left, right;

	// fun to create a new node
	Node(int data)
	{
	this.data = data;
	this.left = this.right = null;
	}
};

//fun to check leaf node
static boolean isleafnode(Node root)
{
	return root.left == null && root.right == null;
}

static class pair {
	Node first;
	String second;
	pair(Node f, String s)
	{
	first = f;
	second = new String(s);
	}
}

//fun to print root to leaf paths without using parent
//pointers
static void printRootToLeaf(Node root)
{
	// base case
	if (root == null)
	return;

	String path = "";

	// create an empty stack to store a pair of tree
	// nodes and its path from root node.
	Stack<pair> s = new Stack<>();

	// push the root node
	s.push(new pair(root, path));

	// loop until stack becomes empty
	while (!s.isEmpty()) {

	pair it = s.peek();
	s.pop();

	root = it.first;
	path = it.second;

	// convert the curr root value to string
	String curr = (root.data) + " ";

	// add the current node to the existing path
	path += curr;

	// print the path if a node is encountered
	if (isleafnode(root))
		System.out.println(path);

	if (root.right != null)
		s.push(new pair(root.right, path));
	if (root.left != null)
		s.push(new pair(root.left, path));
	}
}

public static void main(String[] args)
{
	// create a tree
	Node root = new Node(10);
	root.left = new Node(8);
	root.right = new Node(2);
	root.left.left = new Node(3);
	root.left.right = new Node(5);
	root.right.left = new Node(2);

	printRootToLeaf(root);
}
}



