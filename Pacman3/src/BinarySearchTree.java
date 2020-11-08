/**
 * 
 * @author Shehzer Naumani
 *
 */

public class BinarySearchTree implements BinarySearchTreeADT {

	private int size; // private variable for size of tree
	private BinaryNode root;// Stores the root of the tree
	BinaryNode parent;// stores the parent
	BinaryNode right, left;

	// constructor to create tree

	public BinarySearchTree() {
		this.root = new BinaryNode();
		size = 0; // Initially set size to 0 so that we can add onto the tree
	}

	// Method to get and return a Location within the Tree
	public Pixel get(BinaryNode r, Location key) {
		BinaryNode node = nodefinder(r, key); //find the node
		if (node != null) { //if the node is in the tree return it else return null
			return node.getData();
		}

		return null;
		/*
		 * if (isEmpty()) return null; // if tree is empty return int node =
		 * root.getData().getLocation().compareTo(key); // else compare the current
		 * location to key if (node == 0) return root.getData(); // if its a match
		 * return it else if (node < 0) // if its less than then check the right return
		 * nodefinderRec(root.getRight(), key).getData(); else return
		 * nodefinderRec(root.getLeft(), key).getData();
		 */
	}

	// Method to put a new entry into the tree
	public void put(BinaryNode r, Pixel data) throws DuplicatedKeyException {
		if (r.isLeaf()) {
			r.setData(data); // if its a leaf we want to start by insert key into r
			r.setLeft(new BinaryNode(null, null, null, r)); // make r the parent of its left
			r.setRight(new BinaryNode(null, null, null, r)); // make r the parent of its right
		} else {
			// get the location of the data and compare it to the the location of r
			switch (data.getLocation().compareTo(r.getData().getLocation())) {
			case 1:
				put(r.getRight(), data); // (i.e. result <0)
				break;
			case -1:
				put(r.getLeft(), data); // i.e result > 0
				break;
			default:
				throw new DuplicatedKeyException(); // else that means !r.isleaf
			}
		}
	}

	public void remove(BinaryNode r, Location key) throws InexistentKeyException {

		r = nodefinder(r, key); // Find the node with the location key and set it to r

		if (r == null || r.isLeaf()) // If you cannot find r in the tree or it is a leaf
			throw new InexistentKeyException();

		else if (!r.isLeaf()) { //if r is not a leaf
			if (r.getRight().isLeaf() && r.getLeft().isLeaf()) // If both children are leafs-> leaf
			{
				parent = r.getParent(); //make parent the 'parent' of r
				BinaryNode c = r.getLeft().isLeaf() ? r.getRight() : r.getLeft(); //create a child
				c.setParent(parent); //set the parent of r as the parent of c
				if (r == root) //if r is equal to the root then set the roots data as c's data
					root.setData(c.getData());

				else {
					if (parent.getRight() == r) //if parents right is equal to r
						parent.setRight(c); //set the c as its right
					else
						parent.setLeft(c); //else set the left as its left
				}
			}

			/*
			 * For internal nodes find smallest node in the right subtree of r, and change
			 * the data in r to this new node's data. We must then remove this new node from
			 * the tree so that we do not have duplicates.
			 */
			else {
				BinaryNode s = smallestNode(r.getRight());
				r.setData(s.getData());
				remove(s, s.getData().getLocation()); //recursive call
			}
		}
	}

	// Method to return the successor
	public Pixel successor(BinaryNode r, Location key) {
		BinaryNode find = nodefinder(r, key); //find the node with given key
		if (find != null) { //if its in the tree
			if (!find.getRight().isLeaf()) { //find's right is not a leaf
				return smallest(find.getRight()); //return the smallest in its right subtree
			} else {
				BinaryNode parent = find.getParent(); //else create a parent and make it the found nodes parent
				while (find.getParent() != null && isRC(find)) { //as long as find's parent is not null and it is the right child
					find = parent; //make find = to the parent
					parent = find.getParent(); //make parent- the parent of find
				}
				if (find == root) { //if find is the root just return the data
					return find.getData();
				}
				return parent.getData(); // return the parents data
			}
		} else {
			return r.getData(); //else return the binarynode r's data
		}
	}

	/*
	 * Gets the predecessor of the given key starting at the given node Returns the
	 * Pixel that represents the predecessor of the key
	 * 	Same steps as successor except we find the LARGEST in the left sub tree
	 * and all steps are using the left except the right (thats why i didnt comment)
	 */
	@Override
	public Pixel predecessor(BinaryNode r, Location key) {
		BinaryNode pred = nodefinder(r, key);
		if (pred != null) {
			if (!pred.getLeft().isLeaf()) {
				return largest(pred.getLeft());
			} else {
				BinaryNode parent = pred.getParent();
				while (pred.getParent() != null && pred == parent.getLeft()) {
					pred = parent;
					parent = pred.getParent();
				}
				if (pred == root) {
					return pred.getData();
				}
				return parent.getData();
			}
		} else {
			return r.getData();
		}
	}
//returns the smallest pixel 
	public Pixel smallest(BinaryNode r) throws EmptyTreeException {

		BinaryNode comparison = smallestNode(r);
		if (comparison != null) { //while the smallestnode is not null
			return comparison.getData(); //return it
		}
		throw new EmptyTreeException(); //else throw exception
	}

	/*
	 * Gets the largest value from the given node Returns the Pixel representing the
	 * largest value in the tree Throws EmptyTreeException if the tree is empty
	 */
	@Override
	public Pixel largest(BinaryNode r) throws EmptyTreeException {
		BinaryNode compare = biggestNode(r);
		if (compare != null) {
			return compare.getData();
		}
		throw new EmptyTreeException();
	}

	// Private Method to get and return a specific node
	private BinaryNode nodefinder(BinaryNode r, Location k) {
		if (r.isLeaf())
			return null; //if r is a leaf return null
		switch (k.compareTo(r.getData().getLocation())) { //else use the location of k to compare different cases
		case 1:
			return nodefinder(r.getRight(), k); //ie. r<0
		case -1:
			return nodefinder(r.getLeft(), k); //ie r>0
		case 0:
			return r; //else return r
		}
		return null; //if its not any of the above its not in the tree

	}

	// Private Method to return the smallest key
	private BinaryNode smallestNode(BinaryNode r) {

		while (!r.getLeft().isLeaf()) // While the left child of r is not a leaf
			r = r.getLeft(); // Set r to the left child

		return r;
	}

	// Private method to return the largest key
	private BinaryNode biggestNode(BinaryNode r) {

		while (!r.getRight().isLeaf()) // While the right child of r is not a leaf
			r = r.getRight(); // Set r to the right child

		return r; // return r

	}

	@Override // method to get root
	public BinaryNode getRoot() {
		return root;
	}

	// private method to check if node r is the right child of its parent
	private boolean isRC(BinaryNode r) {
		BinaryNode parent = r.getParent();
		if (r == parent.getRight())
			return true;
		else
			return false;

	}
}
