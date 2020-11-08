
public class Location {
private int x, y;  //int variables for x and y co-ordinates of the object
/**
 * constructor, sets object's x and y to the x and y passed by constructor
 * @param x
 * @param y
 */
	public Location(int x, int y) {
		this.x=x;
		this.y=y;
	}
	/**
	 * returns x co-ordinate of object
	 * @return
	 */
	public int xCoord() {
		return x;
	}
	/**
	 * returns y co-ordinate of object
	 * @return
	 */
	public int yCoord() {
		return y;
	}
	/**
	 * compares x and y co-ordinates with the previous x' and y' positions
	 * @param p
	 * @return
	 */
	public int compareTo(Location p) {
		int lX = p.xCoord(), lY = p.yCoord();
		if(x == lX && y == lY) {
			return 0;
		}
		else if( x<lX || (x==lX)&& y<lY) {
			return -1;
		}
		else 
			return 1;
	}
}
