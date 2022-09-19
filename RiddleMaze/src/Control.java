import java.util.Random;
import java.awt.Point;
import java.io.Serializable;
/**
 * Controller of Riddle Maze all data from the model is manipulated 
 * or passes through this class and interacts with the view.
 * @author Kody Williams
 *
 */
public class Control implements Serializable{
	
	/**
	 * serialization ID.
	 */
	private static final long serialVersionUID = -616119934401613338L;
	/**
	 * Holds the directions input text for North direction.
	 */
	private static final String NORTH = "North";
	/**
	 * Holds the directions input text for South direction.
	 */
	private static final String SOUTH = "South";
	/**
	 * Holds the directions input text for East direction.
	 */
	private static final String EAST = "East";
	/**
	 * Holds the directions input text for West direction.
	 */
	private static final String WEST = "West";
	/**
	 * number of adjacent index a player moves.
	 */
	private static final int MOVE = 1;
	/**
	 * Number of lives a player loses.
	 */
	private static final int DAMAGE = 1;
	/**
	 * Number of Points a player's score increments.
	 */
	private static final int POINT_INCREMENT = 10;
	/**
	 * Number where a player no longer has lives.
	 */
	private static final int NO_LIVES = 0;
	/**
	 * The maze for the current game.
	 */
	private final Maze myMaze;
	/**
	 * The player information of the current game.
	 */
	private Player myPlayer;
	/**
	 * Constructs a new control that contains the model attributes of player
	 * and maze. Creates and new Maze and Player objects.
	 * @param theWidth width of the maze.
	 * @param theHeight height of the maze.
	 */
	public Control(final int theWidth,final int theHeight) {
		myMaze = new Maze(theWidth,theHeight);
		myPlayer = new Player(randomLocation(theWidth),randomLocation(theHeight));
		setEndPoint(theWidth,theHeight);
	}
	/**
	 * Sets the End Point of the maze to a semi-random room within the maze.
	 * The End point will not be in the same column or in any of the adjacent
	 * rooms around the player.
	 * @param theWidth the width of the maze.
	 * @param theHeight the height of the maze.
	 */
	private final void setEndPoint(final int theWidth, final int theHeight) {
		int inPlayerX = myPlayer.getX();
		int inPlayerY = myPlayer.getY();
		int inEndX = randomLocation(theWidth);
		int inEndY = randomLocation(theHeight);
		int inIterateX = 0;
		int inIterateY = 0;
		while (inEndX == inPlayerX||inEndY == inPlayerY||around(inEndX,inEndY,inPlayerX,inPlayerY)) {
			if (inEndX == inPlayerX || around(inEndX,inEndY,inPlayerX,inPlayerY)) {
				inEndX = ((randomLocation(theWidth)+inIterateX)%theWidth);
				inIterateX++;	
			}
			if (inEndY == inPlayerY || around(inEndX,inEndY,inPlayerX,inPlayerY)) {
				inEndY = ((randomLocation(theHeight)+inIterateY)%theHeight);
				inIterateY++;
			}
		}
		myMaze.setEndRoom(inEndX,inEndY);
	}
	/**
	 * Helper function that check whether the End Point is in any of the 8 adjacent
	 * rooms around the player. If the End Point is in one of the 8 adjacent room return true.
	 * @param theEndX the current generated End Point 2D array x location
	 * @param theEndY the current generated End Point 2D array y location
	 * @param thePlayerX the Players start 2D array x location.
	 * @param thePlayerY the Players start 2D array y location.
	 * @return returns true if the End Point is within the 8 adjacent rooms.
	 */
	private boolean around(int theEndX, int theEndY, int thePlayerX, int thePlayerY) {
		boolean inPass = false;
		for (int y = thePlayerY-1; y<=thePlayerY+1;y++) {
			for (int x = thePlayerX-1; x<=thePlayerX+1;x++) {
				if(theEndX == x && theEndY == y) {
					inPass = true;
				}	
			}
		}
		return inPass;
	}
	/**
	 * generate a random integer indicating the location in the array.
	 * @param theLength upper limit of the range
	 * @return integer of within the range 0 - theLength
	 */
	private int randomLocation(int theLength) {
		Random rad = new Random();
		return rad.nextInt(theLength - 1);
	}
	/**
	 * Bases on the inputed string the function will return the door in a specific direction
	 * if the string match. option of valid inputed string include (North, South, East, West).
	 * @param theDirection inputed direction (North, South, East, West)
	 * @return the door in the input direction.
	 */
	public final Door getDirection(final String theDirection) {
		int inX = myPlayer.getX();
		int inY = myPlayer.getY();
		Room inCurrentRoom = myMaze.getGrid()[inY+1][inX+1];
		Door inCurrentDoor = null;
		if (theDirection.equalsIgnoreCase(NORTH)) 
		{
			inCurrentDoor = inCurrentRoom.getNorth();
		} 
		else if (theDirection.equalsIgnoreCase(SOUTH)) 
		{
			inCurrentDoor = inCurrentRoom.getSouth();
		} 
		else if (theDirection.equalsIgnoreCase(EAST)) {
			inCurrentDoor = inCurrentRoom.getEast();
		} 
		else if (theDirection.equalsIgnoreCase(WEST)) 
		{
			inCurrentDoor = inCurrentRoom.getWest();
		} 
		return inCurrentDoor;
	}
	/**
	 * Bases on the inputed direction and door the function will check if the door is open.
	 * If the door is open the player is moved in the indicated direction and the function 
	 * returns true. Else the Player object lose life and false is returned.
	 * @param theDirection inputed string on the direction to move.
	 * @param theDoor door that is being interacted with to check state.
	 * @return returns true if the player was moved. Else false.
	 */
	public final boolean moveDirection(final String theDirection, final Door theDoor) {
		boolean inMoved = false;
		if(theDoor.getState()) {
			movePlayer(theDirection);
			inMoved = true;
		} else {
			myPlayer.setLives(myPlayer.getLives()-DAMAGE);
		}
		return inMoved;
	}
	/**
	 * Helper Function. based on an inputed String this function will move the Player X or Y 
	 * position to an adjacent room. valid String inputs include (North, South, East, West).
	 * @param theDirection string direction for the user to move.
	 */
	private final void movePlayer(final String theDirection) {
		int inX = myPlayer.getX();
		int inY = myPlayer.getY();
		if (theDirection.equalsIgnoreCase(NORTH)) 
		{
			inY-=MOVE;
			myPlayer.setY(inY);
		} 
		else if (theDirection.equalsIgnoreCase(SOUTH)) 
		{
			inY+=MOVE;
			myPlayer.setY(inY);

		} 
		else if (theDirection.equalsIgnoreCase(EAST)) {
			inX+=MOVE;
			myPlayer.setX(inX);
		} 
		else if (theDirection.equalsIgnoreCase(WEST)) 
		{
			inX-=MOVE;
			myPlayer.setX(inX);		
		}
	}
	/**
	 * checkEnd check if the player and the End point is at the same location
	 * in the 2D array maze.
	 * @return returns true is the player X and Y is equal to the End Point X and Y
	 */
	public final boolean checkEnd() {
		boolean inEnd = false;
		int inEndX = getEndpointLocation().x;
		int inEndY = getEndpointLocation().y;
		if(inEndX == myPlayer.getX() && inEndY == myPlayer.getY()) {
			inEnd = true;
		}
		return inEnd;
	}
	/**
	 * Returns the width of the maze.
	 * @return returns the width of the maze.
	 */
	public final int getMazeWidth() {
		return myMaze.getWidth();
	}
	/**
	 * Returns the height of the maze.
	 * @return returns the height of the maze.
	 */
	public final int getMazeHeight() {
		return myMaze.getHeight();
	}
	/**
	 * returns the current number of lives the player has.
	 * @return returns the current number of lives the player has.
	 */
	public int getPlayerLives() {
		return myPlayer.getLives();
	}
	/**
	 * returns the current player score.
	 * @return returns the current player score.
	 */
	public int getPlayerScore() {
		return myPlayer.getScore();
	}
	/**
	 * returns the player current location as a point.
	 * @return returns the player current location as a point.
	 */
	public Point getPlayerLocation() {
		return new Point(myPlayer.getX(),myPlayer.getY());
	}
	/**
	 * set player X and Y location to inputed X and Y
	 * @param theX new X location for player
	 * @param theY new Y location for player
	 */
	public void setPlayerLocation(final int theX, final int theY) {
		myPlayer.setX(theX);
		myPlayer.setY(theY);
	}
	/**
	 * increment the Players score by the set amount.
	 */
	public void incrementScore() {
		myPlayer.setScore(getPlayerScore() + POINT_INCREMENT);
	}
	/**
	 * Check if the player is considered dead (player lives in <= set value)
	 * @return returns true if the player is <= set dead value.
	 */
	public final boolean checkDead() {
		boolean inEnd = false;
		if(myPlayer.getLives() <= NO_LIVES) {
			inEnd = true;
		}
		return inEnd;
	}
	/**
	 * returns the to string value of the player.
	 * @return returns player object to string.
	 */
	public final String getPlayerString() {
		return myPlayer.toString();
	}
	/**
	 * Cheat function that sets all doors in the maze to true.
	 */
	public final void setAllDoorsTrue() {
		Room[] inRooms = myMaze.getMazeArray();
		for (Room room :inRooms) {
			for (Door door : room.getDoorsArray()) {
				if (door != null) {
					door.setState(true);				
				}		
			}
		}
	}
	/**
	 * Cheat function to return the location of the End Point to the view.
	 * @return a point that contain the X and Y of the End Point.
	 */
	public final Point getEndpointLocation() {
		Room[] inRooms = myMaze.getMazeArray();
		int Location = 0;
		int inRow = 0;
		int inCol = 0;
		for (Room room :inRooms) {
			if(room.getEndpoint()) {
				break;
			}
			Location++;
		}
		inCol = Location/myMaze.getWidth();
		inRow = Location - myMaze.getHeight()*(inCol);
		return new Point(inRow,inCol);
	}
}
