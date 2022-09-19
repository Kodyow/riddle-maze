import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

/**
 * View Menu contains all the Gui Object views for the main menu
 * scene that is first presented to the user on the start of the program. 
 * @author Kody Williams
 *
 */
public class ViewMenu {
	/**
	 * Set Screen Width
	 */
	private static final int SCREEN_START_WIDTH = 900;
	/**
	 * Set Screen Height
	 */
	private static final int SCREEN_START_HEIGHT = 625;
	/**
	 * Menu Button Spacing
	 */
	private static final int BUTTON_SPACING = 20;
	/**
	 * Menu Button Size
	 */
	private static final int BUTTON_SIZE = 200;
	/**
	 * Menu Background color
	 */
	private static final Background BACKGROUD = new Background(new BackgroundFill(Color.GREY,CornerRadii.EMPTY,Insets.EMPTY));
	/**
	 * Menu Text Font
	 */
	private static final Font myFont = Font.font("Lucida Sans Unicode", FontPosture.REGULAR, 30);
	/**
	 * create maze slider size
	 */
	private static final int SLIDER_SIZE = 300;
	/**
	 * create maze max slider value
	 */
	private static final int MAZE_MAX_SIZE = 5;
	/**
	 * create maze min slider value
	 */
	private static final int MAZE_MIN_SIZE = 4;
	/**
	 * Stack that contains all the current previous/current scenes
	 */
	private SceneStack myScenes;
	/**
	 * Stage/Window for JavaFX
	 */
	private Stage myStage;
	/**
	 * construct the window and main start menu of the game.
	 * @param thePrimaryStage Pimary view of the app.
	 */
	public ViewMenu(Stage thePrimaryStage) {
    	Pane startView = mainMenu();
    	Scene startScene = new Scene(startView,SCREEN_START_WIDTH,SCREEN_START_HEIGHT);
    	myScenes = new SceneStack(startScene);
    	myStage = thePrimaryStage;
    	myStage.setTitle("Riddle Maze");
    	myStage.setScene(startScene);
    	myStage.setResizable(true);
	}
	/**
	 * creates the main menu view of the application which is first seen
	 * at the start of the program.
	 * @return main menu scene.
	 */
    private Pane mainMenu() {
    	BorderPane pane = new BorderPane();
    	pane.setBackground(BACKGROUD);
    	VBox options = new VBox();
    	Label information = new Label("Version 1.0");
    	
    	options.setSpacing(BUTTON_SPACING);
    	Button inStart = new Button("Start");
    	inStart.setOnAction(event -> createMazeDiaglog());
    	inStart.setFont(myFont);
    	inStart.setPrefWidth(BUTTON_SIZE);
    	Button inLoad = new Button("Load");
    	inLoad.setFont(myFont);
    	inLoad.setPrefWidth(BUTTON_SIZE);
    	inLoad.setOnAction(event -> {
    		Control myGame = null;
    	      try {
		          FileInputStream fileIn = new FileInputStream("MazeLoad.load");
		          ObjectInputStream in = new ObjectInputStream(fileIn);
		          myGame = (Control) in.readObject();
		          ViewLayout loadGame = new ViewLayout(myGame.getMazeWidth(),myGame.getMazeHeight(),myScenes);
		          myScenes.addParent(loadGame);
		          in.close();
		          fileIn.close();
		       } catch (IOException i) {
		          i.printStackTrace();
		          return;
		       } catch (ClassNotFoundException c) {
		          System.out.println("ViewLayout class not found");
		          c.printStackTrace();
		          return;
		       }
    	});
    	Button inAbout = new Button("About");
    	inAbout.setFont(myFont);
    	inAbout.setPrefWidth(BUTTON_SIZE);
    	inAbout.setOnAction(event -> ViewAboutAlert.aboutAlert());
    	options.getChildren().addAll(inStart,inLoad,inAbout);
    	pane.setCenter(options);
    	options.setAlignment(Pos.CENTER);
    	pane.setBottom(information);
    	information.setAlignment(Pos.BOTTOM_CENTER);
    	information.setPadding(new Insets(12,12,12,12));
    	pane.setAlignment(information, Pos.CENTER);
    	
   
    	return pane;
    	
    }
    /**
     * Creates the GUI Dialog box asking to user for the size of the maze.
     * When the user has submitted the input a new maze will be created and the
     * game will display.
     */
	private void createMazeDiaglog() {
		Alert inDialog = new Alert(AlertType.INFORMATION);
		inDialog.setTitle("Create Maze");
		inDialog.setHeaderText("Create Maze");
		inDialog.setGraphic(null);
		VBox content = new VBox();
		Label inLableWidth = new Label("Select Maze Width:");
		Slider inSizeX = new Slider(MAZE_MIN_SIZE,MAZE_MAX_SIZE,MAZE_MIN_SIZE);
		inSizeX.setPrefWidth(SLIDER_SIZE);
		inSizeX.setShowTickMarks(true);
		inSizeX.setShowTickLabels(true);
		inSizeX.setSnapToTicks(true);
		inSizeX.setMinorTickCount(0);
		inSizeX.setMajorTickUnit(1);
		
		Slider inSizeY = new Slider(MAZE_MIN_SIZE,MAZE_MAX_SIZE,MAZE_MIN_SIZE);
		Label inLableHeight = new Label("Select Maze Height:");
		inSizeY.setPrefWidth(SLIDER_SIZE);
		inSizeY.setShowTickMarks(true);
		inSizeY.setShowTickLabels(true);
		inSizeY.setSnapToTicks(true);
		inSizeY.setMinorTickCount(0);
		inSizeY.setMajorTickUnit(1);

		content.getChildren().addAll(inLableWidth,inSizeX,inLableHeight,inSizeY);

		inDialog.getDialogPane().setContent(content);

		inDialog.showAndWait()
	     .filter(response -> response == ButtonType.OK)
	     .ifPresent(response -> myScenes.addParent(new ViewLayout((int) inSizeX.getValue(),(int) inSizeY.getValue(),myScenes)));

	}
}
