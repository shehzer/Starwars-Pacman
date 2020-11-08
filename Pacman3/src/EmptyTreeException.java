
public class EmptyTreeException extends RuntimeException {
	  /**
	   * Sets up this exception with an appropriate message.
	   * @param collection String representing the name of the collection
	   */
	  public EmptyTreeException () {
			super("Illegal Operation on Binary Search Tree");	//Create an exception with the message "illegal operation"
		}
	}

