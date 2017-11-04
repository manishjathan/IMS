import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BillGui{
	
	static Stage window;
	static TableView<Product> table1;
	static TableView<Bill> table2;
	static Button addButton,printBill,removeButton,newBill,clearBill;
	static Label l1,tP,bI;
	static TextField inputText,totalPrice,bill_IdText;
	static ObservableList<Product> products;
	static ObservableList<Bill> bills;
	static ProductDAO pd;
	static int bill_Id;
	
	
	


	@SuppressWarnings("unchecked")
	
	public static void display() throws Exception {
		// TODO Auto-generated method stub
		window = new Stage();
		
		/**************Table Columns for Product Display***********/
		
		TableColumn<Product, Integer> prod_idColumn = new TableColumn<>("Prod_id");
        prod_idColumn.setMinWidth(100);
        prod_idColumn.setCellValueFactory(new PropertyValueFactory<>("prod_id"));
       
        //Name column
        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(200);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Price column
        TableColumn<Product, Integer> priceColumn = new TableColumn<>("Price");
        priceColumn.setMinWidth(100);
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Quantity column
        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
        /**************Table Columns for Bill Display***********/
        
        //TableColumn<Bill, Integer> bill_idColumn = new TableColumn<>("Bill_id");
        //bill_idColumn.setMinWidth(100);
        //bill_idColumn.setCellValueFactory(new PropertyValueFactory<>("bill_id"));
       
        
        TableColumn<Bill, Integer> product_idColumn = new TableColumn<>("Prod_id");
        product_idColumn.setMinWidth(200);
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
        
        
        /**************Creating Table instances***********/
        
        table1 = new TableView<>();
        table2 = new TableView<>();
        BillDAO.setConnection();
        
        table1.setItems(getProduct());
        table1.getColumns().addAll(prod_idColumn,nameColumn, priceColumn, quantityColumn);
        table2.getColumns().addAll(product_idColumn,product_nameColumn, product_quantColumn,product_priceColumn);
        
        l1 = new Label("No. of Items : ");
        tP = new Label("Total Cost : ");
        bI = new Label("Bill ID : ");
        
        inputText = new TextField();
        inputText.setPromptText("Enter Quantity");
        inputText.setMinWidth(50);
        
        totalPrice = new TextField();
        totalPrice.setMinWidth(50);
        
        bill_IdText = new TextField();
        bill_IdText.setMinWidth(50);
        
        addButton = new Button("Add Item");
        addButton.setOnAction(e -> {
        try {
    	 //connect to bill database
        	addItemClicked();
        }catch(Exception ae) {
        	Alert2.display("Select the item and enter the quantity " , "Alert Box");
        }
        
        });
        
        printBill = new Button("Print Bill");
        printBill.setOnAction(e -> {
        	try {
        	print();
        	newBill();
        	}catch(Exception nb) {
        		
        	}
        });
        
        newBill = new Button("New Bill");
        newBill.setOnAction(e -> {
        	boolean ans = ConfirmBox.display("Confirm Action", "Do you want to print the bill?");
        	if(ans) {
             	try {
             		print();
             		newBill();
             	}catch(Exception ne) {
             		System.out.println("Problem in generating new Bill");
             	}
        	}
        	else {
        		try {
        		clearBillClicked();
        		newBill();
        		}catch(Exception ce) {
        			System.out.println("Error in clear Bill");
        		}
        	}
        });
        
        
        clearBill = new Button("Clear Bill");
        clearBill.setOnAction(e ->{
        	try {
        		clearBillClicked();	
        	}catch(Exception ce) {
        		System.out.println("Error in clear Bill");	
        	}
        });
        
        removeButton = new Button("Remove Item");
        removeButton.setOnAction(e -> {
        	try
        	{
        	deleteItemClicked();
        	}catch(Exception de) {
        		System.out.println("Problem in deleteButton");
        	}
        });
        
        HBox hBox1 = new HBox();
        hBox1.setPadding(new Insets(10,10,10,10));
        hBox1.setSpacing(10);
        hBox1.getChildren().addAll(l1,inputText,addButton);
        
        HBox hBox2 = new HBox();
        hBox2.setPadding(new Insets(10,10,10,10));
        hBox2.setSpacing(10);
        hBox2.setAlignment(Pos.CENTER);
        hBox2.getChildren().addAll(removeButton,clearBill,newBill,printBill,tP,totalPrice,bI,bill_IdText);
        
        VBox vBox1 = new VBox();
        vBox1.setPadding(new Insets(10,10,10,10));
        vBox1.setSpacing(10);
        vBox1.getChildren().addAll(table1,hBox1);
        
        VBox vBox2 = new VBox();
        vBox2.setPadding(new Insets(10,10,10,10));
        vBox2.setSpacing(10);
        vBox2.getChildren().addAll(table2,hBox2);
        
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(vBox1);
        borderPane.setCenter(vBox2);
        //borderPane.setBottom(statusbar);
        //VBox vBox = new VBox();
        //vBox.getChildren().addAll(vBox1);
       
        Scene scene = new Scene(borderPane);
        table2.getItems().clear();
        newBill();
        window.setScene(scene);
        window.show();
        
	}
	
	
	
	
	
	 private static ObservableList<Product> getProduct() throws Exception{
	    	
	    	pd = new ProductDAO();
	        pd.setConnection();
	    	
	    	Product p[] = new Product[10];
	        products = FXCollections.observableArrayList();
	        p = pd.getProd();
	        int i = 0;
	        while(p[i]!= null) {
	        products.add(new Product(p[i].getProd_id(),p[i].getName(),p[i].getPrice(),p[i].getQuantity()));
	        i++;
	        }
	        return products;
	    } 
	 
	 private static void addItemClicked() throws Exception{
		
		 Bill b = new Bill();
		 ObservableList<Product> itemSelected = table1.getSelectionModel().getSelectedItems();
		 
		 b.setBill_id(bill_Id);
		 b.setProduct_id(itemSelected.get(0).getProd_id());
		 b.setProduct_name(itemSelected.get(0).getName());
		 b.setProduct_price(itemSelected.get(0).getPrice() * Integer.parseInt(inputText.getText()));
		 b.setProduct_quant(Integer.parseInt(inputText.getText()));
		 
		 //(itemSelected.get(0).getName() + " " + itemSelected.get(0).getPrice() + " " + itemSelected.get(0).getQuantity());
		 try {	 
		if(Integer.parseInt(inputText.getText())> itemSelected.get(0).getQuantity()) {	 
			Alert2.display("Required Quantity greater than Available", "Error");
		}	 
		else {	 
		 BillDAO.addItem(bill_Id,itemSelected.get(0).getProd_id(),itemSelected.get(0).getName(),Integer.parseInt(inputText.getText()),itemSelected.get(0).getPrice() * Integer.parseInt(inputText.getText()));
		 table2.getItems().add(b);
		 totalPrice.setText(Integer.toString(BillDAO.calcTotalPrice(bill_Id)));
		 pd.updateItem(itemSelected.get(0).getProd_id(), itemSelected.get(0).getName(), itemSelected.get(0).getPrice(), itemSelected.get(0).getQuantity() - Integer.parseInt(inputText.getText()));
		 table1.setItems(getProduct());
		} 
		}catch(Exception e) {
			 System.out.println("Error : "+ e);
		 }
		 
	 }
	 
	 private static void deleteItemClicked() throws Exception{
		 
		 Bill b = new Bill();
		 ObservableList<Bill> allitems,itemSelected;
		 allitems = table2.getItems();
		 itemSelected = table2.getSelectionModel().getSelectedItems();
		 
		 
		 b.setProduct_name(itemSelected.get(0).getProduct_name()); 
		 b.setProduct_price(itemSelected.get(0).getProduct_price());
		 b.setProduct_id(itemSelected.get(0).getProduct_id());
		 b.setProduct_quant(itemSelected.get(0).getProduct_quant());
		 
		 pd.resetQuantity(b);
		 BillDAO.removeItem(b);
		 allitems.removeAll(itemSelected);
		 totalPrice.setText(Integer.toString(BillDAO.calcTotalPrice(100)));
		 table1.setItems(getProduct());
	 }
	 
	 private static void newBill() throws Exception{
		 table2.getItems().clear();
		 bill_Id = BillDAO.generateNewBill();
		 bill_IdText.setText(Integer.toString(bill_Id));
	 }
	
	 private static void clearBillClicked() throws Exception{
		table2.getItems().clear();
     	BillDAO.clear(bill_Id);   
     	bill_IdText.setText("");
     	totalPrice.setText("");
	 }
	 private static void print() throws Exception{
		Alert2.display("Printing Bill ...", "Bill");
 		table2.getItems().clear();
 		BillDAO.insertTotalPrice(bill_Id, Integer.parseInt(totalPrice.getText()));
 		bill_IdText.setText("");
      	totalPrice.setText("");
	 }
}
