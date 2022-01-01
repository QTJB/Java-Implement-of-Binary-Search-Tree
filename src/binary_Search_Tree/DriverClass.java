package binary_Search_Tree;

public class DriverClass {

	static BST bst = new BST();
	static BST rootInsert = new BST();
	static BST randomizedBST = new BST();
	static BST avlBST = new BST();

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
		addNode(-6, bst, rootInsert, randomizedBST, avlBST);
		addNode(-8, bst, rootInsert, randomizedBST, avlBST);
		addNode(0, bst, rootInsert, randomizedBST, avlBST);
		addNode(1, bst, rootInsert, randomizedBST, avlBST);
		addNode(6, bst, rootInsert, randomizedBST, avlBST);
		addNode(15, bst, rootInsert, randomizedBST, avlBST);
		addNode(40, bst, rootInsert, randomizedBST, avlBST);
		addNode(456, bst, rootInsert, randomizedBST, avlBST);
		addNode(7, bst, rootInsert, randomizedBST, avlBST);
		addNode(31, bst, rootInsert, randomizedBST, avlBST);
		addNode(11, bst, rootInsert, randomizedBST, avlBST);
		addNode(27, bst, rootInsert, randomizedBST, avlBST);
		addNode(15, bst, rootInsert, randomizedBST, avlBST); // Testing whether the tree can add existed value;
		addNode(57, bst, rootInsert, randomizedBST, avlBST);
		addNode(108, bst, rootInsert, randomizedBST, avlBST);
		addNode(-13, bst, rootInsert, randomizedBST, avlBST);

		bst.setBfAndSize();
		rootInsert.setBfAndSize();
		randomizedBST.setBfAndSize();
		avlBST.setBfAndSize();

		System.out.println(
				"\n" + "*************************  Initial Data setting complete  *************************" + "\n");

	}

	private static void addNode(int value, BST... trees) {
		System.out.println("The value: " + value);
		System.out.println("Inserting by standard BST algorithm:");
		trees[0].nodeInsert(value);
		System.out.println("\n" + "Inserting by standard Root Insert algorithm:");
		trees[1].rootInsert(value);
		System.out.println("\n" + "Inserting by randomized Root Insert algorithm:");
		trees[2].avlInsert(value);
		System.out.println("\n" + "Inserting by AVL Insert algorithm:");
		trees[3].avlInsert(value);
		System.out.println("--------------------------------------");
	}

//	Printing values that inserted to the trees. Each algorithm will print by three traversals way.
//	Printing the value with their height and balance factor by Pre-order traversal way.
	private static void printInitialOrder() {
		System.out.print("Standard BST algorithm:");
		bst.printAllTraversals();
		bst.printPreOrderWithHeightAndBf();

		System.out.print("Root Insert algorithm:");
		rootInsert.printAllTraversals();
		rootInsert.printPreOrderWithHeightAndBf();

		System.out.print("Randomized Root Insert algorithm:");
		randomizedBST.printAllTraversals();
		randomizedBST.printPreOrderWithHeightAndBf();

		System.out.print("AVL algorithm:");
		avlBST.printAllTraversals();
		avlBST.printPreOrderWithHeightAndBf();
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
		// Already using root insert during the data setting process. Here is just
		// example.
		System.out.println("\n" + "*************************  Root Insert operation  *************************" + "\n");
		System.out.println("Before Operation:");

		// printing tree before insert.
		rootInsert.printAllTraversals();
		rootInsert.printPreOrderWithHeightAndBf();

		System.out.println("\n" + "Operating:" + "\n");

		rootInsert.rootInsert(-7);
		rootInsert.printAllTraversals();
		System.out.println();

		rootInsert.rootInsert(4);
		rootInsert.printAllTraversals();

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
		avlBST.printAllTraversals();
		avlBST.printPreOrderWithHeightAndBf();

		System.out.println("\n" + "Operating:" + "\n");

		// Inserting by AVL insert algorithm.
		avlBST.nodeInsert(4);
		avlBST.printAllTraversals();
		avlBST.printPreOrderWithHeightAndBf(); // Printing the balance factor to confirm it belong to [ -1, 0,
		// 1].

		// Deleting by AVL delete algorithm.
		avlBST.avlDelete(7);
		avlBST.printAllTraversals();
		avlBST.printPreOrderWithHeightAndBf(); // Printing the balance factor to confirm it belong to [ -1, 0,
		// 1].
	}

}
