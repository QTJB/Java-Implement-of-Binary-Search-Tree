package binary_Search_Tree;

public class BSTNode {

	int value;
	BSTNode leftNode;
	BSTNode rightNode;
	int balancedFactor;
	int size;

	BSTNode(int value) {
		this.value = value;
		leftNode = null;
		rightNode = null;
		balancedFactor = 0;
		size = 0;
	}

/////////////////////////////////     Standard Binary Search Trees   ///////////////////////////////////

	// Implementation of standard BST search
	// returns node that contains the Node;
	// If returns Null means the node is not existed
	BSTNode nodeSearch(int value) {

		BSTNode node = this;
		while (node != null) {
			if (value == node.value) {
				return node;
			} else if (value < node.value) {
				node = node.leftNode;
			} else {
				node = node.rightNode;
			}
		}
		return null;
	}

	// Implementation of standard BST insert;
	// If return false means node is already existed.
	boolean nodeInsert(int value) {

		if (this.value == value) { // if value == value means the data is existed - return false;
			return false;
		}
		if (this.value < value) {
			if (rightNode == null) {
				rightNode = new BSTNode(value);
				return true;
			} else {
				return rightNode.nodeInsert(value);
			}
		} else {
			if (leftNode == null) {
				leftNode = new BSTNode(value);
				return true;
			} else {
				return leftNode.nodeInsert(value);
			}
		}
	}

//	Implementation of standard BST delete;
	void nodeDelete(int value) {

		BSTNode root = this;
		BSTNode node = nodeSearch(value);

		if (node.leftNode == null && node.rightNode == null) { // When node is leaf.
			updateRelationship(root.parentSearch(value), node);
			node = null;
			return;
		}
		if (node.leftNode == null) { // When node has only one child - Right side.
			updateRelationship(root.parentSearch(value), node, node.rightNode);
			node = null;
			return;
		}
		if (node.rightNode == null) { // When node has only one child - Left side.
			updateRelationship(root.parentSearch(value), node, node.leftNode);
			node = null;
			return;
		}

		// When node has two child.
		// Overwrite its data with the data of the next in-order node.
		BSTNode nextNode = node.nextInOrderNode();
		// The next node in-order itself is replaced by its right child.
		updateRelationship(root.parentSearch(nextNode.value), nextNode, nextNode.rightNode);
		node.value = nextNode.value;
		nextNode = null;

	}

//////////////////////////////          Root Insertion            ////////////////////////////////////////

	// Implementation of root insert;
	// If return false means node is already existed.

	static BSTNode rootInsert(BSTNode node, int value) {
		if (node.nodeSearch(value) != null) {// key is already in the tree
			System.out.println("The tree already contains a node with the value: " + value);
			return node;
		}
		return rootInsertHelper(node, value);
	}

	// Private recursive method, called by rootInsert
	// It does all the job
	private static BSTNode rootInsertHelper(BSTNode node, int value) {
		if (node == null) {
			node = new BSTNode(value);
			return node;
		}
		if (value < node.value) {
			node.leftNode = rootInsertHelper(node.leftNode, value);
			node = rightRotation(node);
			return node;
		} else {// (key > node.key)
			node.rightNode = rootInsertHelper(node.rightNode, value);
			node = leftRotation(node);
			return node;
		}
	}

////////////////////////////////      AVL insertion and delete    //////////////////////

//	AVL insert. recursive process.
	BSTNode avlInsert(BSTNode node, int value) {
		if (node == null) {
			node = new BSTNode(value); // Finding the place to insert the node
		} else {
			if (value == node.value) {
				System.out.println("The tree already contains a node with the value: " + value);
				return node;
			}
			if (value < node.value) { // Newly node is on leftNode. -- L, therefore only two possibility LR rotation
										// or LL rotation.
				node.leftNode = avlInsert(node.leftNode, value); // recursive process.
				if (getBalancedFactor(node) == 2) { // The tree is unbalanced. Because is on left, BF only can be
													// increased.
					if (value < node.leftNode.value) { // Newly node is on leftNode. Therefore, it is LL rotation.
						node = rightRotation(node);
					} else if (value > node.leftNode.value) { // Newly node is on rightNode. Therefore, it is LR
																// rotation.
						node = lRRotation(node);
					}
				}
			} else { // Newly node is on rightNode. -- R, therefore only two possibility RR rotation
						// or RL rotation.
				node.rightNode = avlInsert(node.rightNode, value); // recursive process.
				if (getBalancedFactor(node) == -2) {
					if (getBalancedFactor(node) == -2) { // The tree is unbalanced. Because is on right, BF only can
															// be decreased.
						if (value > node.rightNode.value) { // Newly node is on rightNode. Therefore, it is RR
															// rotation.
							node = leftRotation(node);
						} else if (value < node.rightNode.value) { // Newly node is on leftNode. Therefore, it is RL
																	// rotation.
							node = rLRotation(node);
						}
					}
				}
			}

//			Updating the balance factor.	
			node.balancedFactor = getBalancedFactor(node);
		}
		return node;
	}

//	AVL delete.
	BSTNode avlDelete(BSTNode node, int value) {
		node.nodeDelete(value); // Using standard delete to delete.
		return bstAdjustment(node);
	}

//	Adjustment the tree after delete, use similar algorithm as randomizedInsert() function.
	private BSTNode bstAdjustment(BSTNode node) {
		if (node == null) {
			return null;
		} else {
			node.leftNode = bstAdjustment(node.leftNode);
			node.rightNode = bstAdjustment(node.rightNode);

			if (getBalancedFactor(node) == 2) {
				if (node.leftNode.leftNode == null) { // LR rotation.
					node = lRRotation(node);
				} else {
					node = rightRotation(node);
				}
			} else if (getBalancedFactor(node) == -2) {
				if (node.rightNode.rightNode == null) {
					node = rLRotation(node);
				} else {
					node = leftRotation(node);
				}
			}
//			Updating the balance factor.	
			node.balancedFactor = getBalancedFactor(node);
			return node;
		}
	}

//	LR rotation.
	private BSTNode lRRotation(BSTNode node) {
		node.leftNode = leftRotation(node.leftNode);
		return rightRotation(node);
	}

//	RL rotation.
	private BSTNode rLRotation(BSTNode node) {
		node.rightNode = rightRotation(node.rightNode);
		return leftRotation(node);
	}

//////////////////////////////	       Rotation function          /////////////////////////////////////

//	Right rotation function.  Node is the parent of the newly inserted node.
	static BSTNode rightRotation(BSTNode node) {

		if (node == null) {
			System.out.println("Right rotation faild, the node is not exited " + "\n");
			return null;
		}
		if (node.leftNode == null) {
			System.out.println("Right rotation faild, the node doesn't have leftNode" + "\n");
			return null;
		}

		BSTNode tempNode = node;
		node = node.leftNode;
		tempNode.leftNode = node.rightNode;
		node.rightNode = tempNode;
		System.out.println("Right rotation successful");
		return node;
	}

	// Left rotation function. Node is the parent of the newly inserted node.
	static BSTNode leftRotation(BSTNode node) {
		if (node == null) {
			System.out.println("Left rotation faild, the node is not exited" + "\n");
			return null;
		}
		if (node.rightNode == null) {
			System.out.println("Left rotation faild, the node doesn't have RightNode" + "\n");
			return null;
		}

		BSTNode tempNode = node;
		node = node.rightNode;
		tempNode.rightNode = node.leftNode;
		node.leftNode = tempNode;
		System.out.println("Left rotation successful");
		return node;
	}

/////////////////////////////          Printing nodes function      ///////////////////////////////////    

//  Print the node by Pre-order.
	void printPreorderNode() {
		System.out.print(value + " ");
		if (leftNode != null) {
			leftNode.printPreorderNode();
		}
		if (rightNode != null) {
			rightNode.printPreorderNode();
		}
	}

// Print the node by In-order.
	void printInorderNode() {
		if (leftNode != null) {
			leftNode.printInorderNode();
		}
		System.out.print(value + " ");
		if (rightNode != null) {
			rightNode.printInorderNode();
		}
	}

// Print the node by Post-order.
	void printPostorderNode() {
		if (leftNode != null) {
			leftNode.printPostorderNode();
		}
		if (rightNode != null) {
			rightNode.printPostorderNode();
		}
		System.out.print(value + " ");
	}

	void printPreorderNodeWithHeightAndBf(BSTNode node) {
		System.out.print(value + "(" + getHeight(node) + ")" + "(" + getBalancedFactor(node) + ")" + " ");
		if (leftNode != null) {
			leftNode.printPreorderNodeWithHeightAndBf(node.leftNode);
		}
		if (rightNode != null) {
			rightNode.printPreorderNodeWithHeightAndBf(node.rightNode);
		}
	}

///////////////////////////////////       All auxiliary functions.	    ///////////////////////////////////

	// Private method required by nodeDelete:
	// If the node that looking for parent is Root of hole trees; It will return the
	// root itself.
	// So check whether node is root first.
	// This function should based on the root of hole trees(root of the
	// RandomizedBst class). For example: root.parentSearch().
	// The integer value is the value of node that need to know the parent.
	// if the key is not found - returns null
	// if the key is at the root - returns the root
	// otherwise, returns the parent of the node that contains the value.
	BSTNode parentSearch(int value) {
		BSTNode parent = this;
		BSTNode node = this;

		while (node != null) {
			if (value == node.value) {
				return parent;
			} else if (value < node.value) {
				parent = node;
				node = node.leftNode;
			} else {
				parent = node;
				node = node.rightNode;
			}
		}
		return null;
	}

//    Finding the next in-order node.
//    This function is only use at node delete function. 
//    The function which calls this method decided that node must has right child.
	BSTNode nextInOrderNode() {

		BSTNode node = this.rightNode;

		while (node.leftNode != null) {
			node = node.leftNode;
		}

		return node;
	}

//	Removing the relationship to parent node.
	void updateRelationship(BSTNode parent, BSTNode child) {
		if (parent.rightNode == child) {
			parent.rightNode = null;
			return;
		} else if (parent.leftNode == child) {
			parent.leftNode = null;
			return;
		}
		return;
	}

//	Replacing the relationship to parent node.
	void updateRelationship(BSTNode parent, BSTNode perviousChild, BSTNode child) {
		if (parent.rightNode == perviousChild) {
			parent.rightNode = child;
			return;
		} else if (parent.leftNode == perviousChild) {
			parent.leftNode = child;
			return;
		}
		return;
	}

//	Setting all balanced Factor.
//	This function is modified from printPreorderNode();
	void setAllBalancedFactor() {
		balancedFactor = getBalancedFactor(this);
		if (leftNode != null) {
			leftNode.setAllBalancedFactor();
		}
		if (rightNode != null) {
			rightNode.setAllBalancedFactor();
		}
	}

//	Getting Balanced Factor.
	int getBalancedFactor(BSTNode node) {
		return getHeight(node.leftNode) - getHeight(node.rightNode);
	}

//	This function displays the height of the node to the root.
//	Sometimes the gap between the child and parent is large than 1 or -1. This phenomenon is correct.
//	Because is not standard as the high level of the hole tree. It is the height of this node to root of hole tree.
//	the getting balanced factor is relay on this function. therefore, it can get correct balanced factor.
	int getHeight(BSTNode node) {
		if (node == null) {
			return 0;
		}
		int h1 = getHeight(node.leftNode);
		int h2 = getHeight(node.rightNode);
		return h1 > h2 ? h1 + 1 : h2 + 1;
	}

//	Setting all size.
	void setAllSize() {
		size = getSize(this);
		if (leftNode != null) {
			leftNode.setAllSize();
		}
		if (rightNode != null) {
			rightNode.setAllSize();
		}
	}

//	Calculating the size.
	int getSize(BSTNode node) {

		int size = 0;

		if (node.leftNode == null && node.rightNode == null) { // node is a leaf.
			return 0;
		}
		if (node.leftNode != null) {
			size += getSize(node.leftNode);
			++size; // the left node it self is not accounting;
		}
		if (node.rightNode != null) {
			size += getSize(node.rightNode);
			++size; // the left node it self is not accounting;
		}

		return size;
	}

}
