package binary_Search_Tree;

public class BST {
	BSTNode root;

/////////////////////////////////     Constructors function        ///////////////////////////////////
	public BST() {
		// TODO Auto-generated constructor stub
		root = null;
	}

	public BST(int value) {
		root = new BSTNode(value);
	}

	public BST(BSTNode node) {
		root = node;
	}

/////////////////////////////////     Standard Binary Search Trees   ///////////////////////////////////

	// Searching function.
	public void nodeSearch(int value) {
		if (root.nodeSearch(value) == null) {
			System.out.println("The value: " + value + " is not existed");
		} else {
			System.out.println("The value: " + value + " is found");
		}
	}

	// Insert function
	public void nodeInsert(int value) {
		if (isEmpty()) { // Checking whether root is empty.
			root = new BSTNode(value);
			System.out.println("Successful insertion");
		} else {
			if (root.nodeInsert(value)) {
				System.out.println("Successful insertion");
			} else {
				System.out.println("The tree already contains a node with the value: " + value);
			}
		}
		setBfAndSize();
	}

	public void nodeDelete(int value) {
		if (root.nodeSearch(value) == null) {
			System.out.println("The value: " + value + " is not existed");
		} else {
			root.nodeDelete(value);
			System.out.println("\n" + "The value: " + value + " is deleted");
		}
		setBfAndSize();
	}

////////////////////////////////           Root Insertion and Search         ////////////////////////// 	

	// Root insert function
	public void rootInsert(int value) {
		if (isEmpty()) { // Checking whether root is empty.
			root = new BSTNode(value);
			System.out.println("Successful insertion");
		} else {
			root = BSTNode.rootInsert(root, value);
			System.out.println("Successful Root insert if node is not existed.");
		}
		setBfAndSize();
	}

	public void rootSearch(int value) {
		if (isEmpty()) {
			System.out.println("\n" + "The value is not existed");
		} else {
			BSTNode temp = BSTNode.rootSearch(root, value);
			if (temp != null) {
				System.out.println("\n" + "The value is existed");
				root = temp;
			} else {
				System.out.println("\n" + "The value is not existed");
			}
		}
	}

/////////////////////////////       AVL insertion and delete    //////////////////////

	public void avlInsert(int value) {
		if (isEmpty()) {
			root = new BSTNode(value);
			System.out.println("Successful insertion");
		} else {
			root = root.avlInsert(root, value);
			System.out.println("Successful AVL insert if node is not existed.");
		}
	}

	public void avlDelete(int value) {
		if (root.nodeSearch(value) == null) {
			System.out.println("The value: " + value + " is not existed");
		} else {
			root = root.avlDelete(root, value);
			System.out.println("\n" + "The value: " + value + " is deleted");
		}
		setBfAndSize();
	}

/////////////////////////////              Printing nodes function    //////////////////////////       	
	public void printAllTraversals() {
		System.out.println();
		setBfAndSize();
		printPreOrder();
		printInOrder();
		printPostOrder();
	}

	public void printPreOrder() {
		System.out.println("Printing Pre-order traversals (Size:) " + (root.size + 1) + ":");
		root.printPreorderNode();
		System.out.println();
	}

	public void printInOrder() {
		System.out.println("Printing In-order traversals (Size:) " + (root.size + 1) + ":");
		root.printInorderNode();
		System.out.println();
	}

	public void printPostOrder() {
		System.out.println("Printing Post-order traversals (Size:) " + (root.size + 1) + ":");
		root.printPostorderNode();
		System.out.println();
	}

	public void printPreOrderWithHeightAndBf() {
		System.out.println(
				"Printing Pre-order traversals With Height and Balance factor: value (Height) (Balance factor)");
		root.printPreorderNodeWithHeightAndBf(root);
		System.out.println("\n");
	}

///////////////////////////       Other functions         ////////////////////////////////

//	checking whether the root is empty.
	private boolean isEmpty() {
		if (root == null) {
			return true;
		}
		return false;
	}

//	Setting balanced factor and size.
	public void setBfAndSize() {
		setBalancedFactor();
		setSize();
	}

//	Setting balanced factor.
	public void setBalancedFactor() {
		root.setAllBalancedFactor();
	}

//	Setting Size.
	public void setSize() {
		root.setAllSize();
	}
}
