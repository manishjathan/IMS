import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu {

	static Stage window;
	static Scene s;
	static Button Products,Billing,viewLogs;
	static TextField bill_id;
	static Button Search;
	
	public static void display() {
		
		window = new Stage();
		
		Products = new Button("Products");
		Products.setMinHeight(50);
		Products.setMinWidth(200);
		Products.setOnAction( e -> {
			try {
			ProductTable.display();
			}catch(Exception pe) {
				Alert2.display("Product Window didnt open Properly","Alert");
			}
			});
		
		Billing = new Button("Billings");
		Billing.setMinHeight(50);
		Billing.setMinWidth(200);
		Billing.setOnAction( e -> {
			try {
			BillGui.display();
			
			}catch(Exception pe) {
				Alert2.display("Bill Window didnt open Properly","Alert");
			}
			});
		
		viewLogs = new Button("View Logs");
		viewLogs.setMinHeight(50);
		viewLogs.setMinWidth(200);
		viewLogs.setOnAction( e -> {
			try {
			Bill_Log.display();
			
			}catch(Exception pe) {
				Alert2.display("Bill Window didnt open Properly","Alert");
			}
			});
		
		
		
		VBox layout = new VBox();
		layout.setSpacing(20);
		layout.setPadding(new Insets(10,10,10,10));
		layout.setAlignment(Pos.CENTER);
		layout.getChildren().addAll(Products,Billing,viewLogs);
		
		s = new Scene(layout,350,250);
		window.setScene(s);
		window.setTitle("Menu");
		window.show();
	}

}
