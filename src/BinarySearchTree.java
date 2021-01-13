import java.util.*;

class Node {
	int data;
	Node left, right;
	Stack<Integer> a=new Stack();
	Node(int d) {
		data = d;
		left = right = null;

	}
}

public class BinarySearchTree{
	static Node root;

	Node sortArray(int arr[], int start, int end) {
		if (start > end) {
			return null;
		}

		int mid = (start + end) / 2;
		Node node = new Node(arr[mid]);

		node.left = sortArray(arr, start, mid - 1);
		node.right = sortArray(arr, mid + 1, end);

		return node;
	}

	public static void main(String[] args) {
		Scanner keyboard=new Scanner(System.in);
		System.out.print("Enter number of nodes: ");
		int n = keyboard.nextInt();
		int arr[]=new int[n];

		Random num = new Random();
		for(int i=0;i<n;i++){
			arr[i]=num.nextInt(100);
		}

		Arrays.sort(arr);

		BinarySearchTree tree = new BinarySearchTree();
		root = tree.sortArray(arr, 0, n-1);
		System.out.println("Binary Search Tree of N random number: ");
		BSTPrinter.printNode(root);
		keyboard.close();

	}
}

class BSTPrinter {
	public static void printNode(Node root) {
		int maxLevel = BSTPrinter.maxLevel(root);
		printNodeInternal(Collections.singletonList(root), 1, maxLevel);
	}

	private static void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
		if (nodes.isEmpty() || BSTPrinter.CheckingAllElementsNull(nodes))
			return;

		int floor = maxLevel - level;
		int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
		int firstSpaces = (int) Math.pow(2, (floor)) - 1;
		int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

		BSTPrinter.printWhitespaces(firstSpaces);
		List<Node> newNodes = new ArrayList<Node>();
		for (Node node : nodes) {
			if (node != null) {
				System.out.print(node.data);
				newNodes.add(node.left);
				newNodes.add(node.right);
			} else {
				newNodes.add(null);
				newNodes.add(null);
				System.out.print(" ");
			}
			BSTPrinter.printWhitespaces(betweenSpaces);
		}

		System.out.println("");
		for (int i = 1; i <= endgeLines; i++) {
			for (int j = 0; j < nodes.size(); j++) {
				BSTPrinter.printWhitespaces(firstSpaces - i);
				if (nodes.get(j) == null) {
					BSTPrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
					continue;
				}
				if (nodes.get(j).left != null)
					System.out.print("/");
				else
					BSTPrinter.printWhitespaces(1);
				BSTPrinter.printWhitespaces(i + i - 1);
				if (nodes.get(j).right != null)
					System.out.print("\\");
				else
					BSTPrinter.printWhitespaces(1);
				BSTPrinter.printWhitespaces(endgeLines + endgeLines - i);
			}
			System.out.println("");
		}
		printNodeInternal(newNodes, level + 1, maxLevel);
	}

	private static void printWhitespaces(int count) {
		for (int i = 0; i < count; i++)
			System.out.print(" ");
	}

	private static int maxLevel(Node node) {
		if (node == null)
			return 0;
		return Math.max(BSTPrinter.maxLevel(node.left), BSTPrinter.maxLevel(node.right)) + 1;
	}

	private static boolean CheckingAllElementsNull(List list) {
		for (Object object : list) {
			if (object != null)
				return false;
		}
		return true;
	}
}