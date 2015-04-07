package bingshop.in;

/* Error codes
 * 
 * 0 - Unknown Error
 * 
 * 5 - SQL Error
 * 
 * 9 - Referential integrity Error 
 * 
 */

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderDB extends DatabaseConn {
	
	public OrderDB() throws Exception{
		super();
	}
	
	protected String newOrder(String orderID,String customerID,int totalItem,Double totalPrice,int ackItem,Double ackPrice,String paymentMode,Double paidAmount){
		PreparedStatement stmt;
		String insertQuery = "INSERT INTO orders (CustomerID,TotalItem,TotalPrice,OrderTime,AckItem,AckPrice,PaymentMode,PaidAmount)VALUES(?,?,?,now(),?,?,?,?)";
		try{
			stmt = dbConn.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
			/*if (orderID == "0")
				stmt.setNull(1, java.sql.Types.CHAR);
				//stmt.setString(1, "orderID");
			else
				stmt.setString(1, orderID);*/
			stmt.setString(1, customerID);
			stmt.setInt(2, totalItem);
			stmt.setDouble(3, totalPrice);
			stmt.setInt(4, ackItem);
			stmt.setDouble(5, ackPrice);
			stmt.setString(6, paymentMode);
			stmt.setDouble(7, paidAmount);
			stmt.executeUpdate();
			stmt = dbConn.prepareStatement("SELECT orderid from orders where customerid = ? order by ordertime desc");
			stmt.setString(1, customerID);
			//stmt = dbConn.prepareStatement("SELECT last_insert_id()");
			ResultSet rs = stmt.executeQuery();
			//ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next())
				return rs.getString(1);
				//return "orderID";
			}catch(SQLException e){
				System.out.println(e);
				e.printStackTrace();
				//return "5";
				return "orderID";
		}	
		return "0";		
	}
	
	protected int newOrderItem(String orderID,String itemSizeID,int quantity,Double price){
		PreparedStatement stmt;
		String insertQuery = "INSERT INTO orderitem (OrderID,ItemSizeID,Quantity,Price)VALUES(?,?,?,?)";
		try{
			stmt = dbConn.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, orderID);
			stmt.setString(2, itemSizeID);
			stmt.setInt(3, quantity);
			stmt.setDouble(4, price);
			stmt.executeUpdate();
			return 1;
			}catch(SQLException e){
				e.printStackTrace();
				return 0;
		}	
	}
	
	protected int updateOrderPrice(String orderID, Double newPrice){
		PreparedStatement stmt;
		String updateQuery = "UPDATE orders SET TotalPrice = ? WHERE OrderID = ?";
		try {
			stmt = dbConn.prepareStatement(updateQuery);
			stmt.setDouble(1, newPrice);
			stmt.setString(2, orderID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
}