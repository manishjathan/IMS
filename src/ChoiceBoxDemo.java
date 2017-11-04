import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ChoiceBoxDemo extends Application{

	Stage window;
	Button button;
	CheckBox c1,c2;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		window = primaryStage;
		window.setTitle("CheckBox");
		
		button = new Button("Order Now!");
		c1 = new CheckBox("Btr. Chicken");
		c2 = new CheckBox("Murgh Mussallam");
		ChoiceBox <String> choiceBox= new ChoiceBox<>();
		
		choiceBox.getItems().add("Apples");
		choiceBox.getItems().add("Banana");
		choiceBox.getItems().add("NKJNsaa");
		choiceBox.getItems().add("asndjkakha");
		choiceBox.setValue("Apples");
		choiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> System.out.println(newValue));
		
		GridPane layout = new GridPane();
		layout.setPadding(new Insets(20,20,20,20));
		layout.setVgap(8);
		layout.setHgap(10);
		
		GridPane.setConstraints(choiceBox, 2, 1);
		GridPane.setConstraints(button, 2, 2);
		layout.getChildren().addAll(choiceBox,button);
		
		
		Scene scene = new Scene(layout,300,250);
		
		window.setScene(scene);
		window.show();
	}
	

}
