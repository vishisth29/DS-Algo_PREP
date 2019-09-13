package Lecture17;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class BinaryTree {
	private class Node {
		int data;
		Node left;
		Node right;

		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	private Node root;
	private int size;

	public BinaryTree() {
		Scanner scn = new Scanner(System.in);
		this.root = this.takeInput(scn, null, false);
	}

	public BinaryTree(int[] pre, int[] in) {
		this.root = this.construct(pre, 0, pre.length - 1, in, 0, in.length - 1);
	}

	private Node construct(int[] pre, int psi, int pei, int[] in, int isi, int iei) {
		if (psi > pei || isi > iei) {
			return null;
		}

		Node rv = new Node(pre[psi], null, null);
		this.size++;

		int si = -1;
		for (int i = isi; i <= iei; i++) {
			if (in[i] == pre[psi]) {
				si = i;
				break;
			}
		}

		int nli = si - isi;

		rv.left = this.construct(pre, psi + 1, psi + nli, in, isi, si - 1);
		rv.right = this.construct(pre, psi + nli + 1, pei, in, si + 1, iei);

		return rv;
	}

	private Node takeInput(Scanner scn, Node parent, boolean leftOrRight) {
		if (parent == null) {
			System.out.println("Enter the data for root");
		} else {
			if (leftOrRight) {
				System.out.println("Enter the data for left child of " + parent.data);
			} else {
				System.out.println("Enter the data for right child of " + parent.data);
			}
		}

		int cdata = scn.nextInt();

		Node child = new Node(cdata, null, null);
		this.size++;

		boolean choice = false;

		System.out.println("Do you have a left child for " + child.data);
		choice = scn.nextBoolean();

		if (choice) {
			child.left = this.takeInput(scn, child, true);
		}

		System.out.println("Do you have a right child for " + child.data);
		choice = scn.nextBoolean();

		if (choice) {
			child.right = this.takeInput(scn, child, false);
		}

		return child;
	}

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

	public void display() {
		this.display(this.root);
		System.out.println("****************");
	}

	private void display(Node node) {
		if (node.left != null) {
			System.out.print(node.left.data + " => ");
		} else {
			System.out.print("END => ");
		}

		System.out.print(node.data);

		if (node.right != null) {
			System.out.print(" <= " + node.right.data);
		} else {
			System.out.print(" <= END");
		}

		System.out.println();

		if (node.left != null) {
			this.display(node.left);
		}

		if (node.right != null) {
			this.display(node.right);
		}
	}

	public int size2() {
		return this.size2(this.root);
	}

	private int size2(Node node) {
		if (node == null) {
			return 0;
		}

		int rv = 0;

		int lsize = this.size2(node.left);
		int rsize = this.size2(node.right);

		rv = lsize + rsize + 1;

		return rv;
	}

	public int min() {
		return this.min(this.root);
	}

	private int min(Node node) {
		if (node == null) {
			return Integer.MAX_VALUE;
		}

		int rv = node.data;

		int lmin = this.min(node.left);
		int rmin = this.min(node.right);

		rv = Math.min(rv, Math.min(lmin, rmin));

		return rv;
	}

	public int max() {
		return this.max(this.root);
	}

	private int max(Node node) {
		if (node == null) {
			return Integer.MIN_VALUE;
		}

		int rv = node.data;

		int lmax = this.max(node.left);
		int rmax = this.max(node.right);

		rv = Math.max(rv, Math.max(lmax, rmax));

		return rv;
	}

	public int height() {
		return this.height(this.root);
	}

	private int height(Node node) {
		if (node == null) {
			return -1;
		}

		int rv = 0;

		int lh = height(node.left);
		int rh = height(node.right);

		rv = Math.max(lh, rh) + 1;

		return rv;
	}

	public boolean find(int data) {
		return this.find(this.root, data);
	}

	private boolean find(Node node, int data) {
		if (node == null) {
			return false;
		}

		if (node.data == data) {
			return true;
		} else if (this.find(node.left, data) == true) {
			return true;
		} else if (this.find(node.right, data) == true) {
			return true;
		} else {
			return false;
		}
	}

	public void mirror() {
		this.mirror(this.root);
	}

	private void mirror(Node node) {
		if (node == null) {
			return;
		}

		this.mirror(node.left);
		this.mirror(node.right);

		Node temp = node.left;
		node.left = node.right;
		node.right = temp;
	}

	public void preOrder() {
		this.preOrder(this.root);
		System.out.println("END");
	}

	private void preOrder(Node node) {
		if (node == null) {
			return;
		}

		System.out.print(node.data + ", ");
		preOrder(node.left);
		preOrder(node.right);
	}

	public void postOrder() {
		this.postOrder(this.root);
		System.out.println("END");
	}

	private void postOrder(Node node) {
		if (node == null) {
			return;
		}

		postOrder(node.left);
		postOrder(node.right);

		System.out.print(node.data + ", ");
	}

	public void inOrder() {
		this.inOrder(this.root);
		System.out.println("END");
	}

	private void inOrder(Node node) {
		if (node == null) {
			return;
		}

		inOrder(node.left);
		System.out.print(node.data + ", ");
		inOrder(node.right);
	}

	public void levelOrder() {
		LinkedList<Node> queue = new LinkedList<>();

		queue.addLast(this.root);
		while (!queue.isEmpty()) {
			Node temp = queue.removeFirst();

			System.out.print(temp.data + ", ");

			if (temp.left != null) {
				queue.addLast(temp.left);
			}

			if (temp.right != null) {
				queue.addLast(temp.right);
			}
		}

		System.out.println("END");
	}

	public void preOrderI() {
		LinkedList<Node> stack = new LinkedList<>();

		stack.addFirst(this.root);
		while (!stack.isEmpty()) {
			Node temp = stack.removeFirst();

			System.out.print(temp.data + ", ");

			if (temp.right != null) {
				stack.addFirst(temp.right);
			}

			if (temp.left != null) {
				stack.addFirst(temp.left);
			}
		}

		System.out.println("END");
	}

	public Integer postOrderPred(int data) {
		HeapMover mover = new HeapMover();
		this.postOrderPredSucc(this.root, data, mover);

		return mover.pred == null ? null : mover.pred.data;
	}

	private void postOrderPredSucc(Node node, int data, HeapMover mover) {

		if (node == null) {
			return;
		}

		postOrderPredSucc(node.left, data, mover);
		postOrderPredSucc(node.right, data, mover);

		// work
		if (mover.item == null) {
			if (node.data == data) {
				mover.item = node;
			} else {
				mover.pred = node;
			}
		} else {
			if (mover.succ == null) {
				mover.succ = node;
			}
		}
	}

	public Integer postOrderSucc(int data) {
		HeapMover mover = new HeapMover();
		this.postOrderPredSucc(this.root, data, mover);

		return mover.succ == null ? null : mover.succ.data;
	}

	private class HeapMover {
		Node pred;
		Node item;
		Node succ;
	}

	private void multiCalculator(Node node, HeapMoverMulti multi, int level) {
		if (node == null) {
			return;
		}

		// work area

		multi.size += 1;
		multi.sum += node.data;

		if (level > multi.level) {
			multi.level = level;
		}

		if (node.data < multi.min) {
			multi.min = node.data;
		}

		if (node.data > multi.max) {
			multi.max = node.data;
		}

		multiCalculator(node.left, multi, level + 1);
		multiCalculator(node.right, multi, level + 1);
	}

	public void multiCalculator() {
		HeapMoverMulti multi = new HeapMoverMulti();

		multi.max = Integer.MIN_VALUE;
		multi.min = Integer.MAX_VALUE;

		this.multiCalculator(this.root, multi, 0);

		System.out.println(multi.size);
		System.out.println(multi.level);
		System.out.println(multi.min);
		System.out.println(multi.max);
		System.out.println(multi.sum);
	}

	private class HeapMoverMulti {
		int size;
		int sum;
		int min;
		int max;
		int level;

	}

	public int diameter() {
		return this.diameter(this.root);
	}

	private int diameter(Node node) {
		if (node == null) {
			return 0;
		}

		int lh = this.height(node.left);
		int rh = this.height(node.right);

		int ld = this.diameter(node.left);
		int rd = this.diameter(node.right);

		int f1 = ld, f2 = rd, f3 = lh + rh + 2;

		return Math.max(f1, Math.max(f2, f3));
	}

	private class DiaPair {
		int height;
		int diameter;
	}

	public int diameterBtr() {
		DiaPair rootpair = this.diameterBtr(this.root);
		return rootpair.diameter;
	}

	private DiaPair diameterBtr(Node node) {
		if (node == null) {
			DiaPair basepair = new DiaPair();

			basepair.height = -1;
			basepair.diameter = 0;

			return basepair;
		}

		DiaPair lpair = this.diameterBtr(node.left);
		DiaPair rpair = this.diameterBtr(node.right);

		DiaPair mypair = new DiaPair();

		mypair.height = Math.max(lpair.height, rpair.height) + 1;

		int f1 = lpair.diameter;
		int f2 = rpair.diameter;
		int f3 = lpair.height + rpair.height + 2;

		mypair.diameter = Math.max(f1, Math.max(f2, f3));

		return mypair;
	}

	public boolean isBST() {
		return this.isBST(this.root);
	}

	private boolean isBST(Node node) {
		if (node == null) {
			return true;
		}

		int lmax = this.max(node.left);
		if (node.data < lmax) {
			return false;
		}

		int rmin = this.min(node.right);
		if (node.data > rmin) {
			return false;
		}

		boolean isLeftBST = this.isBST(node.left);
		boolean isRightBST = this.isBST(node.right);

		return isLeftBST && isRightBST;
	}

	private class Triplet {
		boolean isBST;
		int max;
		int min;
		int size;
	}

	public boolean isBSTBtr() {
		Mover mover = new Mover();
		mover.largestBSTSize = Integer.MIN_VALUE;

		Triplet rt = this.isBSTBtr(this.root, mover);
		return rt.isBST;
	}

	private class Mover {
		int largestBSTSize;
	}

	private Triplet isBSTBtr(Node node, Mover mover) {
		if (node == null) {
			Triplet bt = new Triplet();

			bt.isBST = true;
			bt.max = Integer.MIN_VALUE;
			bt.min = Integer.MAX_VALUE;
			bt.size = 0;

			return bt;
		}

		Triplet lt = this.isBSTBtr(node.left, mover);
		Triplet rt = this.isBSTBtr(node.right, mover);
		Triplet mt = new Triplet();

		mt.size = lt.size + rt.size + 1;

		if (!lt.isBST || !rt.isBST || lt.max > node.data || rt.min < node.data) {
			mt.isBST = false;
		} else {
			mt.isBST = true;

			if (mt.size > mover.largestBSTSize) {
				mover.largestBSTSize = mt.size;
			}
		}

		mt.max = Math.max(node.data, Math.max(lt.max, rt.max));
		mt.min = Math.min(node.data, Math.min(lt.min, rt.min));

		return mt;
	}

	public boolean isBST3() {
		return this.isBST3(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private boolean isBST3(Node node, int min, int max) {
		if (node == null) {
			return true;
		}

		if (node.data <= min || node.data >= max) {
			return false;
		}

		boolean isLeftSideFine = this.isBST3(node.left, min, node.data);
		boolean isRightSideFine = this.isBST3(node.right, node.data, max);

		return isLeftSideFine && isRightSideFine;

	}

	public void levelOrderLW() {
		LinkedList<Node> pQueue = new LinkedList<>();
		LinkedList<Node> cQueue = new LinkedList<>();

		pQueue.add(this.root);

		while (!pQueue.isEmpty()) {
			Node temp = pQueue.removeFirst();

			System.out.print(temp.data + ", ");

			if (temp.left != null) {
				cQueue.add(temp.left);
			}

			if (temp.right != null) {
				cQueue.add(temp.right);
			}

			if (pQueue.isEmpty()) {
				System.out.println("END");

				pQueue = cQueue;
				cQueue = new LinkedList<>();
			}
		}
	}

	public ArrayList<LinkedList<Integer>> levelOrderAL() {
		ArrayList<LinkedList<Integer>> rv = new ArrayList<>();

		LinkedList<Node> pQueue = new LinkedList<>();
		LinkedList<Node> cQueue = new LinkedList<>();

		pQueue.add(this.root);

		LinkedList<Integer> ltba = new LinkedList<>();
		while (!pQueue.isEmpty()) {
			Node temp = pQueue.removeFirst();

			ltba.add(temp.data);

			if (temp.left != null) {
				cQueue.add(temp.left);
			}

			if (temp.right != null) {
				cQueue.add(temp.right);
			}

			if (pQueue.isEmpty()) {
				rv.add(ltba);

				pQueue = cQueue;
				cQueue = new LinkedList<>();
				ltba = new LinkedList<>();
			}
		}

		return rv;
	}

	public void levelOrderLWZZ() {
		LinkedList<Node> pQueue = new LinkedList<>();
		LinkedList<Node> cStack = new LinkedList<>();

		pQueue.add(this.root);
		boolean ltr = true;

		while (!pQueue.isEmpty()) {
			Node temp = pQueue.removeFirst();

			System.out.print(temp.data + ", ");

			if (ltr) {
				if (temp.left != null) {
					cStack.addFirst(temp.left);
				}

				if (temp.right != null) {
					cStack.addFirst(temp.right);
				}
			} else {
				if (temp.right != null) {
					cStack.addFirst(temp.right);
				}
				
				if (temp.left != null) {
					cStack.addFirst(temp.left);
				}
			}

			if (pQueue.isEmpty()) {
				System.out.println("END");

				pQueue = cStack;
				cStack = new LinkedList<>();
				ltr = !ltr;
			}
		}
	}

	int lca(int d1, int d2) {
		return this.lca(this.root, d1, d2).data;
	}

	Node lca(Node node, int d1, int d2) {
		if (node == null) {
			return null;
		}

		if (node.data == d1 || node.data == d2) {
			return node;
		}

		Node lLCA = this.lca(node.left, d1, d2);
		Node rLCA = this.lca(node.right, d1, d2);

		if (lLCA != null && rLCA != null) {
			return node;
		} else if (lLCA != null) {
			return lLCA;
		} else if (rLCA != null) {
			return rLCA;
		} else {
			return null;
		}
	}

	int printkdistanceNode(Node node, int target, int k) {
		if (node == null) {
			return -1;
		}

		if (node.data == target) {
			printkdistanceNodeDown(node, k);
			return 0;
		}

		int dl = printkdistanceNode(node.left, target, k);
		if (dl != -1) {
			if (dl + 1 == k) {
				System.out.println(node.data);
			} else {
				printkdistanceNodeDown(node.right, k - dl - 2);
			}

			return 1 + dl;
		}

		int dr = printkdistanceNode(node.right, target, k);
		if (dr != -1) {
			if (dr + 1 == k) {
				System.out.println(node.data);
			} else {
				printkdistanceNodeDown(node.left, k - dr - 2);
			}

			return 1 + dr;
		}

		return -1;
	}

	void printkdistanceNodeDown(Node node, int k) {
		if (node == null || k < 0) {
			return;
		}

		if (k == 0) {
			System.out.println(node.data);
			return;
		}

		printkdistanceNodeDown(node.left, k - 1);
		printkdistanceNodeDown(node.right, k - 1);
	}

}
