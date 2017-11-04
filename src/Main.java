import java.awt.Label;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application{

	Stage window;
	public static void main(String args[]) {
		launch(args); //for launching the application
	}

	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		window.setTitle("Switching scenes");
		
		//create all the components
		StackPane layout1 = new StackPane();
		Scene s1 = new Scene(layout1,300,150);
		StackPane layout2 = new StackPane();
		Scene s2 = new Scene(layout2,300,150);
		//Label label1 = new Label("First Scene");
		
		Button button1 = new Button("Go to Second Scene");
		button1.setOnAction(e -> window.setScene(s2));
		Button button2 = new Button("Go to First Scene");
		button2.setOnAction(e -> window.setScene(s1));
		
		layout1.getChildren().add(button1);
		layout2.getChildren().add(button2);
		
		window.setScene(s1);
		window.show();
		
	}
}
