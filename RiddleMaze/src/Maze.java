import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Maze class contains a 2D array of rooms which is considered a maze.
 * tracks all information and method associated with the maze.
 * @author Kody Williams
 * @author Ethan Taira
 */
public class Maze implements Serializable{
	/**
	 * serialization ID.
	 */
	private static final long serialVersionUID = -1749450779657064670L;
	/**
	 * myGrid is a 2D array of rooms which is the array.
	 */
	private final Room[][] myGrid;
	/**
	 * myKeys is an array of primary keys for unique riddles.
	 */
	private final int[] myKeys;
	/**
	 * myWidth is the width of the maze.
	 */
	private final int myWidth;
	/**
	 * myHeight is the height of the maze.
	 */
	private final int myHeight;
	/**
	 * myDoors is the total number of doors that are in the maze.
	 */
	private final int myDoors;
	/**
	 * Constructs a new maze filled with doors and riddles and 
	 * all necessary information. 
	 * @param theWidth the width of the maze.
	 * @param theHeight the height of the maze.
	 */
	public Maze(int theWidth,int theHeight) {
		myWidth = theWidth;
		myHeight = theHeight;
		myDoors = ((myWidth-1)*myHeight)+((myHeight-1)*myWidth);
		myKeys = createKeys();
		myGrid = fillRooms(createMaze());
	}
	/**
	 * returns the Width of the Maze.
	 * @return returns the width of the maze.
	 */
	public int getWidth() {
		return myWidth;
	}
	/**
	 * returns the height of the maze
	 * @return returns the height of the maze.
	 */
	public int getHeight() {
		return myHeight;
	}
	/**
	 * returns a clone of the maze.
	 * @return returns a clone of the maze.
	 */
	public Room[][] getGrid() {
		return myGrid.clone();
	}
	/**
	 * returns the maze in the a 1D array format of rooms.
	 * allows for easy traversal of the maze.
	 * @return returns the maze in the a 1D array format of rooms
	 */
	public Room[] getMazeArray() {
		Room[] inRooms = new Room[myHeight*myWidth];
		int inArrayIndex = 0;
		for (int row = 1; row <= myHeight; row++) {
			for (int col = 1; col <= myWidth; col++) {
				inRooms[inArrayIndex] = myGrid[row][col];
				inArrayIndex ++;
			}
		}
		return inRooms;
	}
	/**
	 * Set an end point within the maze at the inputed
	 * x and y room within the maze.
	 * @param theX the column within the maze array.
	 * @param theY the row within the maze array.
	 */
	public void setEndRoom(final int theX, final int theY) {
		myGrid[theY+1][theX+1].setEndpoint(true);
	}
	/**
	 * Creates an array of riddle primary keys that resides within the database.
	 * The number of keys returned is equal to the amount of doors allowed within 
	 * the maze (myMaze).
	 * @return returns an array of riddle primary keys which is radnomized.
	 */
	private int[] createKeys() {

		String file = "jdbc:sqlite:riddles.db";
		Connection conn = null;
		int count = 0;
		try {
			String command = "SELECT count(*) FROM Riddles";
			conn = DriverManager.getConnection(file);
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(command);
			count = result.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		int[] inIntArray = IntStream.rangeClosed(1, count).toArray();
		int[] inResults = new int[myDoors];
		List<Integer> inCollection = new ArrayList<Integer>();

		for (int i : inIntArray) 
			inCollection.add(i);
		Collections.shuffle(inCollection);
		for (int i=0; i < inResults.length; i++) {
			inResults[i] = inCollection.get(i);
		}
		return inResults;
	}
	/**
	 * Creates/Fills the maze (theGrid) with rooms that has default null fields.
	 * The array returned is padded with null rooms around the actual maze.
	 * @return returns a 2D array of default rooms if null fields.
	 */
	private Room[][] createMaze() {
		Room[][] grid = new Room[myHeight + 2][myWidth + 2];
		for (int row = 1; row <= myHeight; row++) {
			for (int col = 1; col <= myWidth; col++) {
				grid[row][col] = new Room();
			}
		}
		return grid;
	}
	/**
	 * Fills the array with null fields with doors that contains a random riddle.
	 * Each filled door will lead to another room adjacent. If the adjacent index
	 * is null no door in the direction will be filled and the door field of the room.
	 * The adjacent room will contain the same door when traverse between rooms.
	 * @param theMaze a 2D array of empty/default rooms with null fields.
	 * @return returns the 2D array of rooms filled with correct doors.
	 */
	private Room[][] fillRooms(Room[][] theMaze) {
		Room[][] grid = theMaze;
		int counter = 0;
		for (int row = 1; row <= myHeight; row++) {
			for (int col = 1; col <= myWidth; col++) {
				if (grid[row - 1][col] != null) 
				{
					grid[row][col].setNorth(grid[row - 1][col].getSouth());
				}
				if (grid[row][col - 1] != null) 
				{
					grid[row][col].setWest(grid[row][col - 1].getEast());
				}
				if (grid[row + 1][col] != null) 
				{
					Riddle newRiddle = new Riddle.RiddleBuilder(myKeys[counter]).choices().build();
					grid[row][col].setSouth(new Door(newRiddle));
					counter++;
				}
				if (grid[row][col + 1] != null) 
				{
					Riddle newRiddle = new Riddle.RiddleBuilder(myKeys[counter]).choices().build();
					grid[row][col].setEast(new Door(newRiddle));
					counter++;
				}
			}
		}
		return grid;
	}
	
	@Override
	public String toString() {
		StringBuilder maze = new StringBuilder();
		for(int row = 0; row <= myHeight+1; row++) {
			for(int column = 0; column <= myWidth+1; column++) {
				if(myGrid[row][column] != null) {
					maze.append("O");
					Door East = myGrid[row][column].getEast();
					Door West = myGrid[row][column].getWest();
					Door North = myGrid[row][column].getNorth();
					Door South = myGrid[row][column].getSouth();
					if(East != null) {
						maze.append(East.getRiddle().getRiddleID());
					}
					if (West != null) {
						maze.append(West.getRiddle().getRiddleID());
					}
					if(North != null) {
						maze.append(North.getRiddle().getRiddleID());
					}
					if(South != null) {
						maze.append(South.getRiddle().getRiddleID());
					}
				}
				else {
					maze.append("X");
				}
			}
			maze.append("\n");
		}
		return maze.toString();
	}
	
}
