
	import javafx.application.Application;
	import javafx.geometry.Insets;
	import javafx.scene.Scene;
	import javafx.scene.control.Button;
	import javafx.scene.control.CheckBox;
	import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
	import javafx.stage.Stage;

	public class ComboBoxDemo extends Application{

		Stage window;
		Button button;
		ComboBox <String>comboBox;
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			launch(args);
		}

		@Override
		public void start(Stage primaryStage) throws Exception {
			// TODO Auto-generated method stub
			window = primaryStage;
			window.setTitle("CheckBox");
			
			button = new Button("Book Tickets!");
			button.setOnAction(e -> printMovie());
			
			comboBox = new ComboBox<>();
			comboBox.getItems().addAll("GoodWill Hunting","St. Vincet","BlackHat");
			comboBox.setPromptText("What is your favourite Movie?");
			comboBox.setEditable(true);
			comboBox.setOnAction(e -> System.out.println("User Selected : " + comboBox.getValue()));
			
			GridPane layout = new GridPane();
			layout.setPadding(new Insets(20,20,20,20));
			layout.setVgap(8);
			layout.setHgap(10);
			
			GridPane.setConstraints(comboBox, 2, 1);
			GridPane.setConstraints(button, 2, 2);
			layout.getChildren().addAll(comboBox,button);
			
			
			Scene scene = new Scene(layout,300,250);
			
			window.setScene(scene);
			window.show();
		}
		

		private void printMovie() {
			System.out.println("Booked Tickets for movie " + comboBox.getValue());
		}
		
		
	}



