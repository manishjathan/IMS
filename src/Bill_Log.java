import java.sql.ResultSet;

import com.mysql.jdbc.PreparedStatement;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Bill_Log {

	static TableView<Bill> table;
	static Stage window;
	static Scene s;
	static Button Search;
	static TextField bill_id;
	static ObservableList<Bill> bills;
	
	
	@SuppressWarnings("unchecked")
	
	public static void display() throws Exception{
		
		
		window = new Stage();
		TableColumn<Bill, Integer> bill_idColumn = new TableColumn<>("Bill_id");
        bill_idColumn.setMinWidth(100);
        bill_idColumn.setCellValueFactory(new PropertyValueFactory<>("bill_id"));
       
        
        TableColumn<Bill, Integer> product_idColumn = new TableColumn<>("Prod_id");
        product_idColumn.setMinWidth(100);
        product_idColumn.setCellValueFactory(new PropertyValueFactory<>("product_id"));

        
        TableColumn<Bill, String> product_nameColumn = new TableColumn<>("Name");
        product_nameColumn.setMinWidth(100);
        product_nameColumn.setCellValueFactory(new PropertyValueFactory<>("product_name"));

        
        TableColumn<Bill, Integer> product_quantColumn = new TableColumn<>("Quantity");
        product_quantColumn.setMinWidth(100);
        product_quantColumn.setCellValueFactory(new PropertyValueFactory<>("product_quant"));
        
        TableColumn<Bill, Integer> product_priceColumn = new TableColumn<>("Price");
        product_priceColumn.setMinWidth(100);
        product_priceColumn.setCellValueFactory(new PropertyValueFactory<>("product_price"));
        
        table = new TableView<>();
        table.getColumns().addAll(bill_idColumn,product_idColumn,product_nameColumn, product_quantColumn,product_priceColumn);
	
        bill_id = new TextField();
        bill_id.setMinWidth(50);

        Search = new Button("Search");
        Search.setMinWidth(50);
        Search.setOnAction(e -> {
        	try {
        	table.getItems().clear();
        	BillDAO.setConnection();
        	Bill b[] = new Bill[10];
        	b = BillDAO.getLogDetails(Integer.parseInt(bill_id.getText()));
        	int i = 0;
        	bills = FXCollections.observableArrayList();
        		while(b[i]!=null) {
        			bills.add(new Bill(b[i].getBill_id() ,b[i].getProduct_id(), b[i].getProduct_name(), b[i].getProduct_quant(), b[i].getProduct_price()));
        			i++;
        		}
        	table.setItems(bills);
        	
        	}catch(Exception se) {
        		Alert2.display("Problem in searchButton", "Alert");
        	}
        });
        
        
        
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(bill_id,Search);
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10,10,10,10));
        
        VBox vBox = new VBox();
        vBox.getChildren().addAll(table,hBox);
        
        s = new Scene(vBox,500,400);
        table.setItems(BillDAO.getBillLogs());
        window.setScene(s);
        window.show();
	
	}
	
	

	
}
