import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BillDAO {

	final static String url = "jdbc:mysql://localhost:3306/db";
    final static String uname = "root";
    final static String pass = "Manish_1";
    static Statement st;
    static Connection con;
    static BillDAO bd;
    static ObservableList<Bill> bills;
    
    static void setConnection(){	
		try {
    	Class.forName("com.mysql.jdbc.Driver"); 
    	con = DriverManager.getConnection(url,uname,pass); 
    	System.out.println("Connected to Bill Database");
		}catch(Exception e) {
			System.out.println("Connection Problem");
		}
		
}
    
static ObservableList<Bill> getBillLogs() throws Exception{
    	
    	bd = new BillDAO();
        bd.setConnection();
    	
    	Bill b[] = new Bill[1000];
        bills = FXCollections.observableArrayList();
        b = bd.getProd();
        int i = 0;
        while(b[i]!= null) {
        bills.add(new Bill(b[i].getBill_id(),b[i].getProduct_id(),b[i].getProduct_name(),b[i].getProduct_quant(),b[i].getProduct_price()));
        //System.out.println(b[i].getBill_id() + " " + b[i].getProduct_id() + " " + b[i].getProduct_name() + " " + b[i].getProduct_quant() + " " + b[i].getProduct_price());
        i++;
        }
        
        return bills;
    }     
    
Bill[] getProd() throws Exception{
	String query = "select * from bill";
	Bill p[] = new Bill[1000];
	st = con.createStatement();
	ResultSet rs = st.executeQuery(query);
	int i = 0;
	while(rs.next()) {	
		p[i] = new Bill(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5));
		i++;
	}
return p;
}
static void addItem(int bill_id,int prod_id,String name, int quantity, int price) throws Exception{
	
	String query = "insert into bill values(?,?,?,?,?)";
	PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
	ps.setInt(1,bill_id);
	ps.setInt(2,prod_id);
	ps.setString(3,name);
	ps.setInt(4,quantity);
	ps.setInt(5, price);
	ps.executeUpdate();
	System.out.println("Added " + name);
}

static int calcTotalPrice(int bill_id) throws Exception{
	String query = "select sum(product_price) from bill where bill_id = ?";
	PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
	ps.setInt(1, bill_id);
	ResultSet rs = ps.executeQuery();
	rs.next();
	return rs.getInt(1);
}

static void clear(int Bill_id) throws Exception{
	System.out.println(Bill_id);
	String query = "delete from bill where bill_id = ?";
	PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
	ps.setInt(1, Bill_id);
	ps.executeUpdate();
}

static void removeItem(Bill b) throws Exception{
	
	String query = "delete from bill where product_id = ?";
	PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
	ps.setInt(1, b.getProduct_id());
	ps.executeUpdate();

}

static int generateNewBill() throws Exception {
	String query = "select max(bill_id) from bill";
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery(query);
	rs.next();
	System.out.println(rs.getInt(1));
	return rs.getInt(1)+1;
}

static void insertTotalPrice(int bill_id,int total_price) throws Exception{
	String query = "insert into total_bill_price values(?,?)";
	PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
	ps.setInt(1, bill_id);
	ps.setInt(2, total_price);
	ps.executeUpdate();
	
}

static Bill[] getLogDetails(int bill_id) throws Exception {
	String query = "Select * from bill where bill_id = ?";
	Bill b[] = new Bill[100];
	int i = 0;
	System.out.println(bill_id);
	PreparedStatement ps = (PreparedStatement) BillDAO.con.prepareStatement(query);
	ps.setInt(1, bill_id);
	ResultSet rs = ps.executeQuery();
	
	while(rs.next()) {
		b[i++] = new Bill(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getInt(4),rs.getInt(5));
	}
	return b;
}	

	






}