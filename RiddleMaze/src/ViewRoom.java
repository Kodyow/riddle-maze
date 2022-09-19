
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/**
 * Creates the gui view of the tiles/square representing the room.
 * @author Kody Williams
 *
 */
public class ViewRoom extends StackPane {
	/**
	 * Room outline in in the maze.
	 */
	private Rectangle myOutline;
	/**
	 * The content within the room
	 */
	private Text myContent= new Text();
	
	/**
	 * creates a new room tile at the specified location in the pane.
	 * @param theX the x location on the screen
	 * @param theY the y position on the screen
	 * @param theSize the size of the tile
	 * @param thePaddingX the x padding the center
	 * @param thePaddingY the y padding to center
	 */
	public ViewRoom(final int theX, final int theY, final int theSize, final int thePaddingX,final int thePaddingY) {
		myOutline = new Rectangle(theSize,theSize);
		myOutline.setStroke(Color.BLACK);
		myContent.setText("");
		myContent.setFont(new Font(theSize));
		myContent.setFill(Color.GOLD);
		
		getChildren().addAll(myOutline,myContent);
		setTranslateX(theX * theSize - thePaddingX);
        setTranslateY(theY * theSize - thePaddingY);
        myOutline.setFill(null);
        
	}
	/**
	 * sets the content of the room
	 * @param theContent the content that is currently in the room.
	 */
	public void setText(final String theContent) {
		if (theContent == "Current") {
			myContent.setText("O");
		} else if (theContent == "End") {
			myContent.setText("X");
		} else {
			myContent.setText("");
		}
	}
}
