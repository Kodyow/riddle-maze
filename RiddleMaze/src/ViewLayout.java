import java.awt.Point;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Optional;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
/**
 * This class Contains and Manages all the in-game views. Also assists the controller
 * in performing interactive events and other interacted functions. Working hand in hand with
 * the Control class. All data is passed from the Control class to this class.
 * @author Kody Wiliams
 *
 */
public class ViewLayout extends BorderPane {
	/**
	 * Dialog box submit button spacing
	 */
	private static final int SPACING = 5;
	/*
	 * Stack that contains all the previous and current views
	 */
	private SceneStack myScenes;
	/**
	 * All data that coming from the model passes through this class into the view 
	 */
	private Control myController;
	/**
	 * View that displays the maze on the left side of the screen.
	 */
	private ViewMaze myMaze;
	/**
	 * view that displays the controls and player info to the right.
	 */
	private ViewControl myInterface;
	
	/**
	 * creates a new layout scene that contains all the necessary view and controller 
	 * needed for the game to function
	 * @param theMazeWidth the width of the maze
	 * @param theMazeHeight the height of the maze
	 * @param theStack the scene stack to add and remove views.
	 */
	public ViewLayout(int theMazeWidth,int theMazeHeight, SceneStack theStack) {
		super();
		myScenes = theStack;
		myController = new Control(theMazeWidth,theMazeHeight);
    	myMaze = new ViewMaze(theMazeWidth,theMazeHeight);
    	myInterface = new ViewControl();
    	Pane viewPane = new Pane(myMaze);
    	viewPane.setPrefSize(600, 600); 
    	this.setCenter(viewPane);
    	this.setRight(myInterface);
    	this.setTop(setupMenu());
    	//this.setGridLinesVisible(true);
    	Point inPlayer = myController.getPlayerLocation();
    	myMaze.setMarks(inPlayer.x,inPlayer.y,"Current");
		myInterface.getUpButton().setOnAction(event -> handleDirection("North"));
		myInterface.getDownButton().setOnAction(event -> handleDirection("South"));
		myInterface.getLeftButton().setOnAction(event -> handleDirection("West"));
		myInterface.getRightButton().setOnAction(event -> handleDirection("East"));
        addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
    		handleKey(event);
        });
        update("Welcome to the Maze!");
	}
	/**
	 * Creates a new Menu bar gui contains options like save game, exit game, and about.
	 * interactive menu bar within the game.
	 * @return the menu bar with all options and events setup.
	 */
	private MenuBar setupMenu() {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("Options");
        Menu aboutMenu = new Menu("Help");
        MenuItem aboutItem = new MenuItem("About");
        MenuItem saveItem = new MenuItem("Save");
        SeparatorMenuItem separator= new SeparatorMenuItem();
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.getItems().addAll(saveItem,separator, exitItem);
        aboutMenu.getItems().addAll(aboutItem);
        menuBar.getMenus().addAll(fileMenu,aboutMenu);
        aboutItem.setOnAction(event -> ViewAboutAlert.aboutAlert());
        saveItem.setOnAction(event -> {
            try
            {   
                FileOutputStream file = new FileOutputStream("MazeLoad.load");
                ObjectOutputStream out = new ObjectOutputStream(file);
                  
                out.writeObject(myController);
                  
                out.close();
                file.close();
                  
                System.out.println("Object has been serialized");
      
            }
              
            catch(IOException ex)
            {
                
            }
        });
        exitItem.setOnAction(event -> {
        	myScenes.removeParent();
        });
        return menuBar;
	}
	/**
	 * Creates a multiple choice pop up dialog for the user to choice from the options
	 * provided by the door object. Waits for the returns the answer which the user 
	 * to submit and returns the players choice.
	 * 
	 * @param theRiddle the Riddle to display in the dialog
	 * @param theOptions the options to display in the dialog
	 * @return the choice the the player chose forom the multiple choice options.
	 */
	private String createDialogMultipleChoice(final String theRiddle,final String[] theOptions) {
		Dialog<String> inDialog = new Dialog<String>();
		ToggleGroup inGroup = new ToggleGroup();
		inDialog.setHeaderText(theRiddle);
		inDialog.setTitle("Riddle");
		VBox inChoices = new VBox();
		inChoices.setSpacing(SPACING);
		for(String inOption : theOptions) {
			RadioButton button = new RadioButton(inOption);
			inChoices.getChildren().add(button);
			button.setToggleGroup(inGroup);
		}
		inDialog.getDialogPane().setContent(inChoices);
		ButtonType inSubmitButton = new ButtonType("Submit", ButtonData.OK_DONE);
		ButtonType inCancelButton = new ButtonType("Life -1", ButtonData.CANCEL_CLOSE);
		inDialog.getDialogPane().getButtonTypes().addAll(inSubmitButton, inCancelButton);
		
		inDialog.getDialogPane().lookupButton(inSubmitButton).disableProperty()
			.bind(Bindings.createBooleanBinding(
					() -> getSelected(inGroup).equals("No Selection"),
					inGroup.selectedToggleProperty()));
		
		inDialog.setResultConverter(dialogButton -> {
		    if (dialogButton == inSubmitButton) {
		        return getSelected(inGroup);
		    }
		    return "No Selection";
		});

		Optional<String> result = inDialog.showAndWait();
		return result.get();
	}
	
	/**
	 * creates a short answer pop up dialog displaying the riddle and an area to
	 * enter an answer. Returns the string that was entered by the user.
	 * @param theRiddle the riddle being displayed.
	 * @return Returns the string that was entered by the user.
	 */
	private String createDialogShortAnswer(final String theRiddle) { 
		String inAnswer = "No selection";
		TextInputDialog inDialog = new TextInputDialog();
		inDialog.setGraphic(null);
		inDialog.setTitle("Riddle");
		inDialog.setHeaderText(theRiddle);
		Text text = new Text(theRiddle);
		text.setWrappingWidth(100);
		inDialog.setWidth(200);
		inDialog.setContentText("Answer: ");
		((Button) inDialog.getDialogPane().lookupButton(ButtonType.OK)).setText("Submit");
		((Button) inDialog.getDialogPane().lookupButton(ButtonType.CANCEL)).setText("Life -1");
		Optional<String> result = inDialog.showAndWait();
		if (result.isPresent()) {
			inAnswer = result.get();
		}
		return inAnswer;
	}
	
	/**
	 * Helper method for the multiple choice dialog pop up. Helps with returning
	 * the selected value or checkng if a value has been selected.
	 * @param theToggle the Toggle Group that contains the radio buttons and choices.
	 * @return Returns the selected string associated with the select radio button. No Selection if no radio button is selected.
	 */
	private String getSelected(final ToggleGroup theToggle) {
		String inSelected ="No Selection";
		RadioButton selectedRadioButton = (RadioButton) theToggle.getSelectedToggle();
		if (selectedRadioButton != null) {
			inSelected = selectedRadioButton.getText();
		}
		return inSelected;
	}
	
	/**
	 * Creates a new pop up for when game ends. Pops up when player die or finds the end point.
	 * On submit the game scene is exited.
	 * @param theContent content that will be displayed.
	 * @param theEnd Header of the dialog box.
	 */
	private void endDialog(String theContent, String theEnd) {
		Alert inEnd = new Alert(AlertType.NONE, 
				theContent,
				ButtonType.OK);
		inEnd.setHeight(300);
		inEnd.setHeaderText(theEnd);
		inEnd.showAndWait();
	}
	

	/**
	 * Displays the Dialog type depending on the riddle of the door.
	 * compares the answer that the user inputed/selected and checks 
	 * if the answer is correct.
	 * @param theDoor
	 * @return boolean whether the user got the answer correct or not.
	 */
	private boolean answerRiddle(Door theDoor) {
		Riddle inRiddle = theDoor.getRiddle();
		String inAnswer = "";
		if (theDoor.getState() == false) {
			myInterface.appendLogText("Locked Door!");
			myInterface.appendLogText(inRiddle.getRiddle());
			if (inRiddle.getChoices() == null) 
			{
				inAnswer = createDialogShortAnswer(inRiddle.getRiddle());
			} 
			else 
			{
				inAnswer = createDialogMultipleChoice(inRiddle.getRiddle(),inRiddle.getChoices());
			}
		}
		return inRiddle.compareAnswer(inAnswer);
	}
	/**
	 * Helper to update the interface player information an log content.
	 * @param theAction the content that will be added to the text log.
	 */
	private void update(String theAction) {
		myInterface.appendLogText(theAction);
		myInterface.setScore(myController.getPlayerScore());
		myInterface.setLives(myController.getPlayerLives());
	}
	
	/**
	 * Handles the inputed direction from the keyboard input or move buttons.
	 * Handles part of the interaction with the door. updates values in the view.
	 * @param theDirection the value depending on the event.
	 */
	private void handleDirection(final String theDirection) {
		Door inCurrentDoor = myController.getDirection(theDirection);
		if (inCurrentDoor != null) {	
			if(answerRiddle(inCurrentDoor)) {
				inCurrentDoor.setState(true);
				myController.incrementScore();
				myInterface.appendLogText("unlocked the Door!");
			}
			Point inClearLocation = myController.getPlayerLocation();
			myMaze.setMarks(inClearLocation.x,inClearLocation.y,"");
			if(!myController.moveDirection(theDirection,inCurrentDoor)) {
				myInterface.appendLogText("OOF!!!");
			}				
			myInterface.appendLogText(myController.getPlayerString());
		} 
		else {
			myInterface.appendLogText("No Door?!?!?");
		};
		Point inCurrLocation=myController.getPlayerLocation();
		myMaze.setMarks(inCurrLocation.x,inCurrLocation.y,"Current");
		update("==========End of Action==========");
		if(myController.checkEnd()) {
			endDialog("You have found the end of the maze alive! Thank you for playing.", "Congratulations");
			myScenes.removeParent();
		} else if (myController.checkDead()) {
			endDialog("You have lost all your lives and now dead! Thank you for playing.", "Game Over");
			myScenes.removeParent();
		}
	}

	/**
	 * Set handlers for on key press
	 * @param theEvent Keyboard event.
	 */
	public void handleKey(KeyEvent theEvent) {
		
		if (theEvent.getCode() == KeyCode.D) 
		{
			handleDirection("East");
		}
		else if (theEvent.getCode() == KeyCode.A) 
		{
			handleDirection("West");
		}
		else if (theEvent.getCode() == KeyCode.W) 
		{
			handleDirection("North");
		}
		else if (theEvent.getCode() == KeyCode.S) 
		{
			handleDirection("South");
		}
		else if (theEvent.getCode() == KeyCode.F11) 
		{
			myController.setAllDoorsTrue();
		}
		else if (theEvent.getCode() == KeyCode.F12) 
		{
			Point inEndLocation = myController.getEndpointLocation();
			myMaze.setMarks(inEndLocation.x,inEndLocation.y,"End");
		}
	}

}
