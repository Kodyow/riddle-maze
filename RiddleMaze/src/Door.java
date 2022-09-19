import java.io.Serializable;

/**
 * Door class represents a single door object within the maze.
 * They are located between rooms within the maze.
 * Doors hold a single reference to a riddle and a state. 
 * The state of the door can be open or closed.
 * 
 * @author Ethan Taira
 * @author Kody Williams
 */
public final class Door implements Serializable{
	/**
	 * serialization ID.
	 */
	private static final long serialVersionUID = 1968133965617375864L;
	/**
	 * State indicated whether the door is open or closed. (false is closed)
	 */
	private boolean myState;
	/**
	 * Riddle contains the information about the riddle associated with the door
	 */
	private final Riddle myRiddle;
	/**
	 * Constructs a new door object with a riddle and a closed door.
	 * @param theRiddle unique riddle that belong to the door.
	 */
	public Door(final Riddle theRiddle) {
		myState = false;
		myRiddle = theRiddle;
	}
	/**
	 * set the current state of the Door either open or closed
	 * @param theNewState state to change the door to.
	 */
	public final void setState(final boolean theNewState) {
		myState = theNewState;
	}
	/**
	 * returns the current state of the door.
	 * @return returns the current state of the door.
	 */
	public final boolean getState() {
		return myState;
	}
	/**
	 * returns the unique riddle associated with the door.
	 * @return returns the unique riddle associated with the door.
	 */
	public final Riddle getRiddle() {
		return myRiddle;
	}	
}
