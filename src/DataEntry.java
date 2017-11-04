import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DataEntry{
	static Stage window;
	static Label l1,l2,l3;
	static TextField name,price,quantity;
	static Button updateButton;
	static int prod_id;
	
	public static void display() throws Exception {
		
		
		window = new Stage(); 
		window.setTitle("Update Info");
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(10);
		grid.setHgap(10);
		
		l1 = new Label("Name");
		grid.setConstraints(l1, 0, 0);
		
		l2 = new Label("Price");
		grid.setConstraints(l2, 0, 1);
		
		l3 = new Label("Quantity");
		grid.setConstraints(l3, 0, 2);
	
		name = new TextField();
		name.setMinWidth(50);
		name.setPromptText("Enter New Name");
		grid.setConstraints(name, 1, 0);
		
		price = new TextField();
		price.setMinWidth(50);
		price.setPromptText("Enter new Price");
		grid.setConstraints(price, 1, 1);
		
		quantity = new TextField();
		quantity.setMinWidth(50);
		quantity.setPromptText("Enter new Quantity");
		grid.setConstraints(quantity, 1, 2);
		
		updateButton = new Button("Update");
		updateButton.setOnAction(e -> {
			
			ProductDAO pd = new ProductDAO();
			try{
				pd.updateItem(prod_id,name.getText(),Integer.parseInt(price.getText()),Integer.parseInt(quantity.getText()));
				ProductTable.refreshButtonClicked();
			}catch(Exception pe){
				System.out.println("Problem in updation");
			}
			ConfirmBox.display("Confirm Box","Are you sure you want to update?");
			window.close();
			
		});
		grid.setConstraints(updateButton, 1 , 3);
		
		
		grid.getChildren().addAll(l1,l2,l3,name,price,quantity,updateButton);
		Scene s =  new Scene(grid,250,150);
		window.setScene(s);
		window.show();	
	}
	
	public static void setContent(String n,int p, int q) {
		
		name.setText(n);
		price.setText(Integer.toString(p));
		quantity.setText(Integer.toString(q));
	
	}
	public static void sendId(int id) {
		prod_id = id;
	}
}
