import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFx extends Application implements EventHandler<ActionEvent> {

    Button button1;
    Button button2;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Title of Window");
        button1 = new Button();
        button1.setText("1");

        button2 = new Button();
        button2.setText("2");
        //This class will handle the button events
        button1.setOnAction(e -> System.out.println("This is first Button"));
        button2.setOnAction(e -> System.out.println("This is second button"));
       
        StackPane layout = new StackPane();
        layout.getChildren().add(button1);
        layout.getChildren().add(button2);
        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //When button is clicked, handle() gets called
    //Button click is an ActionEvent (also MouseEvents, TouchEvents, etc...)
    
    

}