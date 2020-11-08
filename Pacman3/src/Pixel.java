
public class Pixel {
int color;
Location p;
/**
 * constructor that initializes the new pixel w/ specified co-ordinate & color
 * @param p
 * @param color
 */
	public Pixel(Location p, int color) {
		this.p=p;
		this.color=color;
	}
	/**
	 * 
	 * @return location of pixel
	 */
	public Location getLocation() {
		return p;
	}
	/**
	 * 
	 * @return color of pixel
	 */
	public int getColor() {
		return color;
	}
	
}
