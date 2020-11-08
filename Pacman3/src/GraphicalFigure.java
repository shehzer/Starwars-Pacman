/**
 * 
 * @author Shehzer Naumani
 *
 */
public class GraphicalFigure implements GraphicalFigureADT {
	private int id, width, height;
	private String type;
	private Location pos;
	BinarySearchTree tree;
	BinaryNode r;

	// constructor that instantiates all the attributes
	public GraphicalFigure(int id, int width, int height, String type, Location pos) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.pos = pos;
		// Have to initlize a new tree
		tree = new BinarySearchTree();
	}

	@Override // returns width
	public int getWidth() {
		return this.width;
	}

	@Override // returns height
	public int getHeight() {
		return this.height;
	}

	@Override // returns type
	public String getType() {
		return this.type;
	}

	@Override // return id
	public int getId() {
		return this.id;
	}

	@Override // position
	public Location getOffset() {
		return this.pos;
	}

	@Override // sets position
	public void setOffset(Location value) {
		this.pos = value;

	}

	@Override // sets type
	public void setType(String t) {
		this.type = t;

	}

	@Override // adds pixels essentially instantiating it.. throws exception duplicated key
				// exception
	public void addPixel(Pixel pix) throws DuplicatedKeyException {

		try {

			this.tree.put(tree.getRoot(), pix);
		} catch (Exception e) {
			throw new DuplicatedKeyException();
		}

	}

	@Override // intersection so pacman doesnt eat walls
	// returns true if they intersect
	public boolean intersects(GraphicalFigure fig) {

		/*
		 * #1 check if the bottom of the figure is higher than the offset and/or height
		 * of the figure - if so they do not intersect #2 check if the top of the figure
		 * is lower than the bottom of the figure- if so they dont intersect #3 if both
		 * the above return false - return since it doesnt intersect
		 */

		Location position = fig.getOffset();
		int X1 = this.pos.xCoord();
		int Y1 = this.pos.yCoord();
		return X1 < position.xCoord() + fig.getWidth() && X1 + this.width > position.xCoord()
				&& Y1 < position.yCoord() + fig.getHeight() && Y1 + this.height > position.yCoord();
	}

}
