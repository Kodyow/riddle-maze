import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * The GUI view of the Maze holds all the tiles that will be displayed in the maze.
 * @author Kody Williams
 *
 */
public class ViewMaze extends StackPane {
	/**
	 * move the maze visual to be the center of the pane.
	 */
	private static int REMOVE_PANE_PADDING = 225;
	/**
	 * The maze view pane size width and height
	 */
	private static final int SCREEN_SIZE = 600;
	/**
	 * Used for center the pane
	 */
	private static final double SIZE_RATIO = 0.5;
	/**
	 * Size of the tiles
	 */
	private final int myTileSize;
	/**
	 * Array that contains the room display.
	 */
	private ViewRoom[][] myMazeDisplay;
	
	/**
	 * construct a new maze view
	 * @param theMazeWidth the width of the maze
	 * @param theMazeHieght the height of the maze
	 */
	public ViewMaze(int theMazeWidth,int theMazeHieght) {
		super();
		int inSize = Math.max(theMazeWidth, theMazeHieght);
		myTileSize = (SCREEN_SIZE)/(inSize);		
		myMazeDisplay = new ViewRoom[theMazeHieght][theMazeWidth];
		for (int y = 0; y < theMazeHieght; y++) {
			for (int x = 0; x < theMazeWidth; x++) {
				ViewRoom room = new ViewRoom(x,y,myTileSize,REMOVE_PANE_PADDING,REMOVE_PANE_PADDING);
				myMazeDisplay[y][x] = room;
				getChildren().add(room);
			}
		}
		setPrefSize(myTileSize*theMazeWidth, myTileSize*theMazeHieght);
		double inCenterX;
		double inCenterY;
		inCenterY = mazePostion((double) theMazeHieght/inSize);
		setTranslateY(inCenterY);
		inCenterX = mazePostion((double)theMazeWidth/inSize);
		setTranslateX(inCenterX);
		this.setBackground(new Background(new BackgroundFill(Color.GREY,CornerRadii.EMPTY,Insets.EMPTY)));
		
	}
	/**
	 * Helper method determine what position the square should be at
	 * depending on the number of rooms.
	 * @param theRatio the proportion of the tile size and maze dimension
	 * @return returns the the position of the maze.
	 */
	private double mazePostion(final double theRatio) {
		double inHalfScreenSize = SCREEN_SIZE*SIZE_RATIO;
		double inHalfMazeSize = (theRatio*SCREEN_SIZE)*SIZE_RATIO;
		return inHalfScreenSize-inHalfMazeSize;
	}
	/**
	 * sets a mark in the inputed position.
	 * @param theX the 2D array x position
	 * @param theY the 2D array y position
	 * @param theContent the mark that fills the position.
	 */
	public void setMarks(int theX, int theY,String theContent) {
		ViewRoom room = myMazeDisplay[theY][theX];
		room.setText(theContent);
	}


}

