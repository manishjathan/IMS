import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdminPanel extends Application{
	static Stage window;
	static Scene s;
	static Label l1,l2;
	static TextField name,password;
	static Button login;
	
	 final static String url = "jdbc:mysql://localhost:3306/db";
	 final static String uname = "root";
	 final static String pass = "Manish_1";
	 static Statement st;
	 static Connection con;

	void connect() throws Exception{		
	    	Class.forName("com.mysql.jdbc.Driver"); 
	    	con = DriverManager.getConnection(url,uname,pass); 
	 }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override

	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		window = arg0;
		window.setTitle("Admin Login");
	
		//adding components
		
		l1 = new Label("UserName");
		name = new TextField();
		name.setPromptText("Enter username");
		HBox hBox1 = new HBox();
		hBox1.getChildren().addAll(l1,name);
		hBox1.setSpacing(10);
		hBox1.setAlignment(Pos.CENTER);
		
		l2 = new Label("Password");
		password = new TextField();
		password.setPromptText("Enter password");
		HBox hBox2 = new HBox();
		hBox2.getChildren().addAll(l2,password);
		hBox2.setSpacing(10);
		hBox2.setAlignment(Pos.CENTER);
		
		login = new Button("Login");
		connect();
		login.setOnAction(e -> {try{loginClicked();}catch(Exception le) {System.out.println("Problem in Login" + le);}});
		
		VBox vBox = new VBox();
		vBox.getChildren().addAll(hBox1,hBox2,login);
		vBox.setAlignment(Pos.CENTER);
		
		vBox.setSpacing(20);
		s = new Scene(vBox,300,250);
		window.setScene(s);
		window.show();
		
	}
	private static void loginClicked() throws Exception {
		String query = "select * from admin";
		st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		rs.next();
		
		
		if(name.getText().equals(rs.getString(1)) && password.getText().equals(rs.getString(2))) {
			Menu.display();
			window.close();
		}
		else {
			Alert2.display("Incorrect Username and Password", "");
		}
		
	}

}
