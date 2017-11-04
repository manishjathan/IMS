import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProductTable{

    static Stage window;
    static TableView<Product> table;
    static TextField prod_id,name,price,quantity;
    static Button addButton,deleteButton,updateButton,refresh,alert;
    static ObservableList<Product> products;
    static ProductDAO pd;
    
    
    @SuppressWarnings("unchecked")
	public static void display() throws Exception {
        window = new Stage();
        window.setTitle("thenewboston - JavaFX");

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

        
        table = new TableView<>();
        table.setItems(getProduct());
        table.getColumns().addAll(prod_idColumn,nameColumn, priceColumn, quantityColumn);
        
        prod_id = new TextField();
        prod_id.setPromptText("Enter ProductId");
        prod_id.setMinWidth(50);
        
        name = new TextField();
        name.setPromptText("Name of Product");
        name.setMinWidth(50);
        
        price = new TextField();
        price.setPromptText("Enter Price");
        price.setMinWidth(50);
        
        quantity = new TextField();
        quantity.setPromptText("Enter Quantity");
        quantity.setMinWidth(50);
        
        addButton = new Button("AddItem");
        addButton.setMinWidth(20);
        addButton.setOnAction(e -> addButtonClicked());
        
        deleteButton = new Button("DeleteItem");
        deleteButton.setMinWidth(20);
        deleteButton.setOnAction(e -> {
        	try{
        		deleteButtonClicked();
        		}catch(Exception ae) {
        			System.out.println("Problem in deleteButtonClicked method");
        		}}
        );
        
        updateButton = new Button("UpdateItem");
        updateButton.setMinWidth(20);
        updateButton.setOnAction(e -> {
        	try{
        		updateButtonClicked();
        	}catch(Exception be) {
        		System.out.println("Problem in updateButtonClicked method");
        	}
       
        });
        
        refresh = new Button("Refresh");
        refresh.setMinWidth(20);
        refresh.setOnAction(e -> {
        	try{
        		refreshButtonClicked();
        	}catch(Exception re) {
        		System.out.println("Not able to refresh");
        	}
        
        });
      
        alert = new Button("AlertBox");
        alert.setMinWidth(20);
        alert.setOnAction(e -> alertButtonClicked());
        
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(prod_id,name,price,quantity,addButton,deleteButton,updateButton,refresh,alert);
        hBox.setSpacing(10);
     
        VBox vBox = new VBox();
        vBox.getChildren().addAll(table,hBox);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }
    
    //For setting up connection with the database
    
   
    //Get all of the products
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
    
    //AddItems
    private static void addButtonClicked() {
    	Product p = new Product();
    	p.setProd_id(Integer.parseInt(prod_id.getText()));
    	p.setName( name.getText());
    	p.setPrice(Integer.parseInt(price.getText()));
    	p.setQuantity(Integer.parseInt(quantity.getText()));
    	table.getItems().add(p);
    	
    	try {
    	pd.addItem(Integer.parseInt(prod_id.getText()),name.getText(),Integer.parseInt(price.getText()),Integer.parseInt(quantity.getText()));
    	}catch(Exception exception) {
    		System.out.println("Problem in inserting item");
    	}
    	
    	name.clear();
    	price.clear();
    	quantity.clear();
    	
    }
    
    private static void deleteButtonClicked() throws Exception{
    	
    	boolean ans = ConfirmBox.display("Confirmation Box", "Are you sure you want to delete?");
    	if(ans) {
    	ObservableList<Product> productSelected, allProducts;
    	allProducts = table.getItems();
    	productSelected = table.getSelectionModel().getSelectedItems();
    	pd.deleteItem(productSelected.get(0).getProd_id());
        allProducts.removeAll(productSelected);
    	}
    }
    
    private static void updateButtonClicked() throws Exception{
    
    	DataEntry.display();
    	ObservableList<Product> productSelected;
    	productSelected = table.getSelectionModel().getSelectedItems();
    	DataEntry.setContent(productSelected.get(0).getName(),productSelected.get(0).getPrice(),productSelected.get(0).getQuantity());
    	DataEntry.sendId(productSelected.get(0).getProd_id());
    }
     static void refreshButtonClicked() throws Exception{
    	table.setItems(getProduct());
    }
    private static void alertButtonClicked() {
    	Alert2.display("This is an alert box!", "Alert Box");
    }
    
}





 


