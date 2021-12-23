package binary_Search_Tree;

public class DriverClass {

	static BST bst = new BST();
	static BST root = new BST();
	static BST randomizedBST = new BST();

	public static void main(String[] args) {

///////////////////////////////      General setting and Print          /////////////////////////////////////
		setNode(); // Setting test data.

//////////////////////////////	Printing values that inserted to the trees. Each algorithm will print by three traversals way.    //////////////////////////////
		printInitialOrder();

//////////////////////////////    Testing each algorithm          //////////////////////////////
		testBST();
		testRootInsert();
		testAvlTree();

	}

	private static void setNode() {

//		Inserting 16 number of values including one repeat value:15.
//		Therefore, total number of available values : 15.
		addNode(-6, bst, root, randomizedBST);
		addNode(-8, bst, root, randomizedBST);
		addNode(0, bst, root, randomizedBST);
		addNode(1, bst, root, randomizedBST);
		addNode(6, bst, root, randomizedBST);
		addNode(15, bst, root, randomizedBST);
		addNode(40, bst, root, randomizedBST);
		addNode(456, bst, root, randomizedBST);
		addNode(7, bst, root, randomizedBST);
		addNode(31, bst, root, randomizedBST);
		addNode(11, bst, root, randomizedBST);
		addNode(27, bst, root, randomizedBST);
		addNode(15, bst, root, randomizedBST); // Testing whether the tree can add existed value;
		addNode(57, bst, root, randomizedBST);
		addNode(108, bst, root, randomizedBST);
		addNode(-13, bst, root, randomizedBST);

		bst.setBfAndSize();
		root.setBfAndSize();
		randomizedBST.setBfAndSize();

		System.out.println(
				"\n" + "*************************  Initial Data setting complete  *************************" + "\n");

	}

	private static void addNode(Integer value, BST... trees) {
		System.out.println("The value: " + value);
		System.out.println("Inserting by standard BST algorithm:");
		trees[0].nodeInsert(value);
		System.out.println("\n" + "Inserting by standard Root Insert algorithm:");
		trees[1].rootInsert(value);
		System.out.println("\n" + "Inserting by AVL Insert algorithm:");
		trees[2].randomizedInsert(value);
		System.out.println("--------------------------------------");
	}

	private static void printInitialOrder() {
//		Printing values that inserted to the trees. Each algorithm will print by three traversals way.
//		Printing the value with their height and balance factor by Pre-order traversal way.
		System.out.print("Standard BST algorithm:");
		bst.printAllTraversals();
		bst.printPreOrderWithHeightAndBf();

		System.out.print("Root Insert algorithm:");
		root.printAllTraversals();
		root.printPreOrderWithHeightAndBf();

		System.out.print("AVL algorithm:");
		randomizedBST.printAllTraversals();
		randomizedBST.printPreOrderWithHeightAndBf();
	}

///////////////////////////////       Standard BST operation		///////////////////////////////////
	private static void testBST() {
		System.out
				.println("\n" + "*************************  Standard BST operation  *************************" + "\n");

		// Searching function.

		bst.nodeSearch(77); // If the value is not existed.
		bst.nodeSearch(40); // If the value is not existed.

		// Removing the data.
		bst.nodeDelete(55);// If the value is not existed.

		bst.nodeDelete(-13);// If the value is existed and it is leaf.
		bst.nodeDelete(-13);// Testing whether it can delete again.
		bst.printAllTraversals();

		bst.nodeDelete(-8);// If the value is existed and it only has one child.
		bst.printAllTraversals();

		bst.nodeDelete(15);// If the value is existed and it only has two child.
		bst.printAllTraversals();

		bst.nodeDelete(-6);// If the value is existed and it is root.
		bst.printAllTraversals();

	}

///////////////////////////////              Root Insert                ///////////////////////////////////
	private static void testRootInsert() {
		System.out.println("\n" + "*************************  Root Insert operation  *************************" + "\n");

		System.out.println("Before Operation:");

		// Already using root insert during the data setting process. Here is just
		// example.

		// printing tree before insert.
		root.printAllTraversals();
		root.printPreOrderWithHeightAndBf();

		System.out.println("\n" + "Operating:" + "\n");

		root.rootInsert(-7);
		root.printAllTraversals();
		System.out.println();

		root.rootInsert(4);
		root.printAllTraversals();

		System.out.println();
	}

///////////////////////////////       AVL insert and delete		///////////////////////////////////
	private static void testAvlTree() {
		System.out.println(
				"\n" + "*************************  AVL insert and delete operation  *************************" + "\n");
		System.out.println("Before Operation:");

		// Already using AVL insert during the data setting process. Here is just
		// example.

		// printing tree before insert.
		randomizedBST.printAllTraversals();
		randomizedBST.printPreOrderWithHeightAndBf();

		System.out.println("\n" + "Operating:" + "\n");

		// Inserting by AVL insert algorithm.
		randomizedBST.nodeInsert(4);
		randomizedBST.printAllTraversals();
		randomizedBST.printPreOrderWithHeightAndBf(); // Printing the balance factor to confirm it belong to [ -1, 0,
		// 1].

		// Deleting by AVL delete algorithm.
		randomizedBST.randomizedDelete(7);
		randomizedBST.printAllTraversals();
		randomizedBST.printPreOrderWithHeightAndBf(); // Printing the balance factor to confirm it belong to [ -1, 0,
		// 1].
	}

}
