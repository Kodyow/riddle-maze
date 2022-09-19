import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

/**
 * The Panel to the right of the grid. Displays player information such as
 * player lives, score, and location. logs many of the iteration the player has
 * with the game.
 * @author Kody Williams
 *
 */
public class ViewControl extends BorderPane {
	/**
	 * Width of the view pane
	 */
	private static final int PANE_WIDTH = 300;
	/**
	 * Height of the view pane
	 */
	private static final int PANE_HEIGHT = 600;
	/**
	 * Button Size.
	 */
	private static final int BUTTON_SIZE = 60;

	/**
	 * Up button to control the up input
	 */
	private Button myUp = new Button("UP");
	/**
	 * Down button to control the down input
	 */
	private Button myDown = new Button("DOWN");
	/**
	 * Left button to control the left input
	 */
	private Button myLeft = new Button("LEFT");
	/**
	 * Right button to control the right input
	 */
	private Button myRight = new Button("RIGHT");
	/**
	 * logs many of the players interaction with the maze.
	 */
	private TextArea myLog = new TextArea();
	/**
	 * Display for the players Lives 
	 */
	private Text myLives = new Text("Lives: ");
	/**
	 * Display for the players Score
	 */
	private Text myScore = new Text("Score: ");
	/**
	 * Font of the pane.
	 */
	private Font myFont = Font.font("Lucida Sans Unicode", FontPosture.ITALIC, 15);
	
	/**
	 * constructs a new player controller.
	 */
	public ViewControl() {
		super();
		this.setPrefSize(PANE_WIDTH, PANE_HEIGHT);
		BorderPane inScoreBoard =  new BorderPane();
		FlowPane pane = new FlowPane();
		VBox vert = new VBox();
		myLives.setFont(myFont);
		myScore.setFont(myFont);
		inScoreBoard.setLeft(myLives);
		inScoreBoard.setCenter(myScore);
		inScoreBoard.setPadding(new Insets(0,5,0,5));
		
		myLog.setEditable(false);
		myLog.setFocusTraversable(false);
		myLog.setWrapText(true);
		myUp.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
		myDown.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
		myLeft.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
		myRight.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
		pane.setAlignment(Pos.CENTER);
		vert.setSpacing(BUTTON_SIZE);
		vert.getChildren().add(myUp);
		vert.getChildren().add(myDown);
		pane.getChildren().add(myLeft);
		pane.getChildren().add(vert);
		pane.getChildren().add(myRight);

		pane.setPrefHeight(PANE_WIDTH);
		this.setTop(inScoreBoard);
		this.setCenter(myLog);
		this.setBottom(pane);
		pane.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND,CornerRadii.EMPTY,Insets.EMPTY)));
	}
	/**
	 * Returns up button.
	 * @return Returns up button.
	 */
	public Button getUpButton() {
		return myUp;
	}
	/**
	 * Returns down button.
	 * @return Returns down button.
	 */
	public Button getDownButton() {
		return myDown;
	}
	/**
	 * Returns left button.
	 * @return Returns left button.
	 */
	public Button getLeftButton() {
		return myLeft;
	}
	/**
	 * Returns right button.
	 * @return Returns right button.
	 */
	public Button getRightButton() {
		return myRight;
	}
	/**
	 * sets the score text field to new value.
	 * @param theScore new score value.
	 */
	public void setScore(int theScore) {
		myScore.setText("My Score: " + theScore);
	}
	/**
	 * sets the lives text field to a new value
	 * @param theLives new lives value.
	 */
	public void setLives(int theLives) {
		myLives.setText("My Lives: " + theLives);
	}
	/**
	 * appends a new line of text to the log.
	 * @param theAppend in log input.
	 */
	public void appendLogText(String theAppend) {
		myLog.appendText(theAppend + "\n");
	}

}
