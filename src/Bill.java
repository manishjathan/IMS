
public class Bill {

	private int bill_id; 
	private int product_id; 
	private String product_name; 
	private int product_quant;
	private int product_price;
	
	Bill(){
		
	}
	Bill(int b_id,int p_id,String p_name,int p_q,int p_p){
		this.setBill_id(b_id);
		this.setProduct_id(p_id);
		this.setProduct_name(p_name);
		this.setProduct_price(p_p);
		this.setProduct_quant(p_q);
	}
	
	
	public int getBill_id() {
		return bill_id;
	}
	public void setBill_id(int bill_id) {
		this.bill_id = bill_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_quant() {
		return product_quant;
	}
	public void setProduct_quant(int product_quant) {
		this.product_quant = product_quant;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}

}
