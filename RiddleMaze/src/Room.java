import java.io.Serializable;

/**
 * Room class represents a room with in the Maze. Each room 
 * contains 4 doors leading in different directions. If the 
 * door is null there is no door in that directions. This class
 * does not contain a constructor and defaults to null.
 * 
 * @author Kody Williams
 * @author Ethan Taira
 */
public final class Room implements Serializable{
	/**
	 * serialization ID.
	 */
	private static final long serialVersionUID = -7536674267169126684L;
	/**
	 * Total number of door within all rooms.
	 */
	private static final int NUMBER_DOORS = 4;
	/**
	 * boolean indicating whether this room is the end point.
	 */
	private boolean myEndpoint;
	/**
	 * Door that leads north or up.
	 */
	private Door myNorth;
	/**
	 * Door that leads East or right.
	 */
	private Door myEast;
	/**
	 * Door that leads south or down.
	 */
	private Door mySouth;	
	/**
	 * Door that leads west or left.
	 */
	private Door myWest;
	
	/**
	 * returns a boolean indicating whether the room is the end point. 
	 * (true mean the room is the end)
	 * @return returns a boolean indicating if the room is the end.
	 */
	public final boolean getEndpoint() {
		return myEndpoint;
	}
	
	/**
	 * set the room to be considered an end point within the maze.
	 * @param theEnd sets an end point of the maze.
	 */
	public final void setEndpoint(final boolean theEnd) {
		myEndpoint = theEnd;
	}
	/**
	 * returns the north/up door for the room.
	 * @return returns the north/up door for the room.
	 */
	public final Door getNorth() {
		return myNorth;
	}
	/**
	 * sets the north/up door of the room.
	 * @param a Door to set the door to the north/up.
	 */
	public final void setNorth(final Door theDoor) {
		myNorth = theDoor;
	}
	/**
	 * returns the east/right door for the room.
	 * @return returns the east/right door for the room.
	 */
	public final Door getEast() {
		return myEast;
	}
	/**
	 * sets the east/right door of the room.
	 * @param a Door to set the door to the east/right.
	 */
	public final void setEast(final Door theDoor) {
		myEast = theDoor;
	}	
	/**
	 * returns the south/down door for the room.
	 * @return returns the south/down door for this room.
	 */
	public final Door getSouth() {
		return mySouth;
	}
	/**
	 * sets the south/down door of the room.
	 * @param a Door to set the door to the south/down.
	 */
	public final void setSouth(final Door theDoor) {
		mySouth = theDoor;
	}
	/**
	 * returns the west/left door for the room.
	 * @return returns the west/left door for the room.
	 */
	public final Door getWest() {
		return myWest;
	}
	/**
	 * sets the west/left door of the room.
	 * @param a Door to set the door to the west/left.
	 */
	public final void setWest(final Door theDoor) {
		myWest = theDoor;
	}
	/**
	 * returns all rooms as array for iterating over all the doors
	 * within the room.
	 * @return returns an array with all the doors in the room.
	 */
	public final Door[] getDoorsArray() {
		Door[] inDoors = new Door[NUMBER_DOORS];
		inDoors[0] = myNorth;
		inDoors[1] = mySouth;
		inDoors[2] = myEast;
		inDoors[3] = myWest;
		return inDoors;
	}
	
}