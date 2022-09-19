import java.util.Stack;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
/**
 * Holds all the scenes in a stack structure.
 * @author Kody Williams
 *
 */
public class SceneStack {
	/**
	 * stack that holds all the different main panes
	 */
	private Stack<Parent> paneStack = new Stack<Parent>();
	/**
	 * The main view scene window
	 */
	private Scene myScene;
	/**
	 * Creates a new scene stack
	 * @param theScene main view window
	 */
	public SceneStack(Scene theScene) {
		myScene = theScene;
	}
	/**
	 * Add new parent scene to a stack
	 * @param thePane the new pane to be displayed
	 */
	public void addParent(Pane thePane) {
		paneStack.add(myScene.getRoot());
		myScene.setRoot(thePane);
	}
	/**
	 * removed the current scene.
	 */
	public void removeParent() {
		myScene.setRoot(paneStack.pop());
	}
}
