import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CloseDemo extends Application {

    Stage window;
    Button button;
    Boolean answer;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("JavaFX - thenewboston");
        window.setOnCloseRequest(e ->closeWindow());
        button = new Button("Close the window");
        button.setOnAction(e -> closeWindow());

        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        Scene scene = new Scene(layout, 300, 250);
        window.setScene(scene);
        window.show();
    }

    private void closeWindow(){
    	answer = ConfirmBox.display("ConfirmationBox", "Are you sure you want to Exit?");
        if(answer == false) {
        	System.out.println("Doesn't want to exit");
        }
        else {
    	window.close();
        }
      }
}