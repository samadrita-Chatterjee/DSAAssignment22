package in.ineuron.comp;


//Java code for the above approach
import java.util.*;

class Node {
int data;
Node left, right;

Node(int val)
{
	data = val;
	left = right = null;
}
}

class Main {
static Node buildTreeFromInorderPreorder(
	int inStart, int inEnd, int[] preorder,
	Map<Integer, Integer> inorderIndexMap,
	boolean[] notPossible, int preIndex)
{

	if (inStart > inEnd)
	return null;

	// build the current Node
	int rootData = preorder[preIndex];
	Node root = new Node(rootData);
	preIndex++;

	// find the node in inorderIndexMap
	if (!inorderIndexMap.containsKey(rootData)) {
	notPossible[0] = true;
	return root;
	}
	int inorderIndex = inorderIndexMap.get(rootData);
	if (!(inStart <= inorderIndex
		&& inorderIndex <= inEnd)) {
	notPossible[0] = true;
	return root;
	}

	int leftInorderStart = inStart,
	leftInorderEnd = inorderIndex - 1,
	rightInorderStart = inorderIndex + 1,
	rightInorderEnd = inEnd;

	root.left = buildTreeFromInorderPreorder(
	leftInorderStart, leftInorderEnd, preorder,
	inorderIndexMap, notPossible, preIndex);

	if (notPossible[0])
	return root;

	root.right = buildTreeFromInorderPreorder(
	rightInorderStart, rightInorderEnd, preorder,
	inorderIndexMap, notPossible, preIndex);

	return root;
}

static boolean checkPostorderCorrect(Node root,
									int[] postorder,
									int postIndex)
{
	if (root == null)
	return true;

	if (!checkPostorderCorrect(root.left, postorder,
							postIndex))
	return false;
	if (!checkPostorderCorrect(root.right, postorder,
							postIndex))
	return false;

	return (root.data == postorder[postIndex++]);
}

static boolean checktree(int[] preorder, int[] inorder,
						int[] postorder, int N)
{
	// Your code goes here
	if (N == 0)
	return true;

	Map<Integer, Integer> inorderIndexMap
	= new HashMap<>();
	for (int i = 0; i < N; i++)
	inorderIndexMap.put(inorder[i], i);

	int preIndex = 0;
	boolean[] notPossible = new boolean[] { false };

	Node root = buildTreeFromInorderPreorder(
	0, N - 1, preorder, inorderIndexMap,
	notPossible, preIndex);

	if (notPossible[0])
	return true;

	int postIndex = 0;

	return checkPostorderCorrect(root, postorder,
								postIndex);
}

public static void main(String[] args)
{
	int inOrder[] = { 4, 2, 5, 1, 3 };
	int preOrder[] = { 1, 2, 4, 5, 3 };
	int postOrder[] = { 4, 5, 2, 3, 1 };

	int len = inOrder.length;

	if (checktree(preOrder, inOrder, postOrder, len))
	System.out.println("The tree is valid");
	else
	System.out.println("The tree is not valid");
}
}


