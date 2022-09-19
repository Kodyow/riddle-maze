
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The Starting point of the Riddle maze application.
 * 
 * @author Kody Williams
 *
 */
public class App extends Application {

	/**
	 * Entry point of the Appication.
	 * @param args
	 */
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
    	ViewMenu mainMenu = new ViewMenu(primaryStage);
    	primaryStage.show();

    }
    

    




}
