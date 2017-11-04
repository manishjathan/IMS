

public class Product {

	
	private int prod_id;
    private String name;
    private int price;
    private int quantity;

    public Product(){
    	this.prod_id = 0;
        this.name = "";
        this.price = 0;
        this.quantity = 0;
    }

    public Product(int prod_id,String name, int price, int quantity){
        
    	this.prod_id = prod_id;
    	this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getProd_id() {
		return prod_id;
	}

	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

 