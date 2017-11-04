
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Alert2 {
	
	public static void display(String name, String title) {
		Stage window = new Stage();
		window.setTitle(title);
		window.initModality(Modality.APPLICATION_MODAL); //doesn't allow to interact with any other window until it is closed
//		window.setMinHeight(150);
//		window.setMinWidth(350);

		Label l = new Label();
		l.setText(name);
		Button b = new Button("Close");
		b.setOnAction(e -> window.close());
		
		VBox layout = new VBox();
		layout.getChildren().addAll(l,b);
		layout.setSpacing(10);
		layout.setAlignment(Pos.CENTER);
		
		Scene s = new Scene(layout,300,150);
		
		window.setScene(s);
		window.showAndWait();
	}
	

}
