
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Grid extends Application {
	
	Stage window;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		window = primaryStage;
		window.setTitle("Using GridPane");
		
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(8);
		grid.setHgap(10);
		
		Label nameLabel = new Label("Username:");
		GridPane.setConstraints(nameLabel, 0, 2);
		
		TextField name = new TextField();
		name.setPromptText("Enter UserName");
		GridPane.setConstraints(name, 1 , 2);
		
		Label pswdLabel = new Label("Password:");
		GridPane.setConstraints(pswdLabel, 0, 3);
		
		TextField pwd = new TextField();
		pwd.setPromptText("Enter password");
		GridPane.setConstraints(pwd, 1 , 3);
		
		Button login = new Button("Login");
		GridPane.setConstraints(login, 1 , 4);
		login.setOnAction(e -> {
			boolean ans = validate(name.getText(),pwd.getText());
			System.out.println(ans);					
		});
		
		grid.getChildren().addAll(nameLabel,name,pswdLabel,pwd,login);
		Scene s = new Scene(grid,300,250);
		window.setScene(s);
		window.show();
		
	}

		private boolean validate(String name,String pwd) {
			boolean ans ; 
			ans = ((name == "Manish" && pwd == "Manish_1") ? true : false);
			return ans;
		}
	
		


}

