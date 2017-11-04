
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
	
	public class ListViewDemo extends Application{

		Stage window;
		Scene scene;
		ListView listView;
		Button button;
		 
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
			
			listView = new ListView();
			listView.getItems().addAll("GoodWill Hunting","St. Vincet","BlackHat");
			listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
			VBox layout = new VBox();
			layout.setPadding(new Insets(20,20,20,20));
			//layout.setVgap(8);
			//layout.setHgap(10);
			layout.getChildren().addAll(listView,button);
			
			
			scene = new Scene(layout,300,250);
			
			window.setScene(scene);
			window.show();
		}
		

	}





