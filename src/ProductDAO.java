import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class ProductDAO{
	    final static String url = "jdbc:mysql://localhost:3306/db";
	    final static String uname = "root";
	    final static String pass = "Manish_1";
	    static Statement st;
	    static Connection con;

	void setConnection() throws Exception{		
	    	Class.forName("com.mysql.jdbc.Driver"); 
	    	con = DriverManager.getConnection(url,uname,pass); 
	 }
	
	Product[] getProd() throws Exception{
		String query = "select * from product";
		Product p[] = new Product[10];
		st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		int i = 0;
		while(rs.next()) {	
			p[i] = new Product(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
			i++;
		}
	return p;
	}

	void addItem(int prod_id,String name, int price, int quantity) throws Exception{
		
		String query = "insert into product values (?,?,?,?)";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
		ps.setInt(1,prod_id);
		ps.setString(2, name);
		ps.setInt(3,price);
		ps.setInt(4, quantity);
		ps.executeUpdate();
	}

	void deleteItem(int prod_id) throws Exception {
	
		String query = "delete from product where prod_id = ?";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
		ps.setInt(1,prod_id);
		ps.executeUpdate();
		System.out.println("Item deleted");
	}

	void updateItem(int p_id,String n,int pr, int qn) throws Exception{
		String query = "update product set name = ?,price = ?,quantity = ? where prod_id = ?";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
		ps.setString(1,n);
		ps.setInt(2, pr);
		ps.setInt(3, qn);
		ps.setInt(4, p_id);
		ps.executeUpdate();
	}
	
	void resetQuantity(Bill b) throws Exception{
		String query1 = "update product set quantity = ? where prod_id = ?";
		String query2 = "select quantity from product where prod_id = ?";
		PreparedStatement ps1 = (PreparedStatement) con.prepareStatement(query1);
		PreparedStatement ps2 = (PreparedStatement) con.prepareStatement(query2);
		
		ps2.setInt(1, b.getProduct_id());
		ResultSet rs = ps2.executeQuery();
		rs.next();
		
		ps1.setInt(1, b.getProduct_quant() + rs.getInt(1) );
		ps1.setInt(2, b.getProduct_id());
		ps1.executeUpdate();
	}
	
}