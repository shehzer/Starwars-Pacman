/**
 * 
 * @author Shehzer Naumani
 *
 */
public class BinaryNode {

	private Pixel pixel; // Stores pixel pixel
	private BinaryNode left, right, parent; // variables to store left right and parent nodes
	

	// Constructor for creating an internal nodes
	public BinaryNode(Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent) {
		this.pixel = value;// Store passed Pixel as the pixel for this node
		this.right = right; // its right ..
		this.left = left; // and left
		this.parent = parent; // instantiate parent...
		
	}

	// Constructor to create a leaf node
	public BinaryNode() {
		this.pixel = null;
		this.left = null;
		this.right = null;
		this.parent = null;
	}

	// Method to return parent node
	public BinaryNode getParent() {
		return this.parent;
	}

	// Method to set the parent of a node
	public void setParent(BinaryNode parent) {
		this.parent = parent;
	}

	// Method to set the left child of a node
	public void setLeft(BinaryNode left) {
		this.left = left;
	}

	// Method to set the right child of a node
	public void setRight(BinaryNode right) {
		this.right = right;
	}

	// Turning a leaf into an internal node
	public void setData(Pixel pixel) {
		this.pixel=pixel;
	
	/*	left = new BinaryNode(); // Create new left leaf
		left.setParent(this); // make this the parent of it
		right = new BinaryNode(); // create new right leaf
		right.setParent(this); // make this the parent of it*/
	}

	// Method to check if the node is a leaf
	public boolean isLeaf() {
		return pixel == null; // If node has no pixel, it is a leaf
	}

	// Method to return pixel of a node
	public Pixel getData() {
		return this.pixel;
	}

	// Method to return left child
	public BinaryNode getLeft() {
		return this.left;
	}

	// Method to return right child
	public BinaryNode getRight() {
		return this.right;
	}
}
