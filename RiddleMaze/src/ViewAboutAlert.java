import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;

/**
 * Class contain the static GUI object that can be used throughout the
 * whole application.
 * @author Kody Williams
 *
 */
public final class ViewAboutAlert {
	/**
	 * static class that Displays a dialog box which contains information about 
	 * about the game and how the game works.
	 */
	public static void aboutAlert() {
		Alert inAbout = new Alert(AlertType.CONFIRMATION);
		inAbout.setHeaderText("Hello,\n"
				+ "Welcome to Riddle Maze!\n"
				+ "Riddle Maze is a game developed by Kody Williams and Ethan Taria.\n");
		inAbout.setTitle("About");
		TextArea content = new TextArea(
				"The Goal of the game is to find/reach the maze's end point without losing"
				+ " all your lives. To find the end of the maze you must navigate though a series"
				+ " of rooms and doors. Each door will contain a unique riddle that you will have to"
				+ " answer correctly to enter the next room. If you answer the riddle incorrectly you will"
				+ " lose one life. Watch out when you lose all your lived you will lose the game."
				+ " You are able to nevigate up/down/left/right with button. You can also use your keyboard keys WASD"
				+ " as shown below. A maze will show to the left of the controls which will show a grid of rooms."
				+ " While within the grid you will be represented as a Yellow O show your current room.\n"
				+ "Good luck on your journey to find the end of the maze.\n\n"
				+ "Controls:\n"
				+ "W = Up\n"
				+ "A = Left\n"
				+ "S = Down\n"
				+ "D = Right\n\n"
				+ "Cheats:\n"
				+ "F12 will show you the location of the end point.\n"
				+ "F11 will unlock all doors in the maze.");
		content.setEditable(false);
		content.setWrapText(true);
		inAbout.getDialogPane().setContent(content);
		inAbout.showAndWait();
	}
}
