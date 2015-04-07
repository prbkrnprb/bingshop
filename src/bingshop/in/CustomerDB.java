package bingshop.in;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDB extends DatabaseConn{
	
	public CustomerDB() throws Exception{
		super();
	}
	
	protected String checkUserPwd(String uName,String pwd) throws Exception{
		PreparedStatement stmt;	
		EncryptPassword e = new EncryptPassword();
		String ePWD;
		ePWD = pwd;
		//ePWD = e.encrypt(pwd);		
		String selectQuery = "select count(CustomerID),CustomerID from customer where password = ? AND (customerid = ? or email = ? or phonenumber = ?)";		
		stmt = dbConn.prepareStatement(selectQuery);			
		stmt.setString(1, ePWD);
		stmt.setString(2, uName);
		stmt.setString(3, uName);
		stmt.setString(4, uName);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		if (Integer.parseInt(rs.getString(1)) == 0)
			return "0";
		return rs.getString(2);			
	}
	
	protected String newCustomer(String firstName,String lastName,String password,String email,String phoneNumber,String validatedPhno,String validatedEmail){
		PreparedStatement stmt;
		String insertQuery;
		try{			
			insertQuery = "INSERT INTO CUSTOMER(`FirstName`,`LastName`,`Password`,`email`,`PhoneNumber`,`StartDate`,`LastActivity`,`Amount`,`Items`,`Visits`,`ValidatedPhno`,`ValidatedEmail`)VALUES(?,?,?,?,?,now(),now(),?,?,?,?,?)";
			stmt = dbConn.prepareStatement(insertQuery);
						
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			stmt.setString(3, password);
			stmt.setString(4, email);
			stmt.setDouble(5, Long.parseLong(phoneNumber));
			stmt.setDouble(6, 0.0);
			stmt.setInt(7, 0);
			stmt.setInt(8, 0);
			stmt.setInt(9, Integer.parseInt(validatedPhno));
			stmt.setInt(10, Integer.parseInt(validatedEmail));
			stmt.executeUpdate();
			stmt = dbConn.prepareStatement("SELECT CUSTOMERID from CUSTOMER WHERE EMAIL = ? AND PHONENUMBER = ?");
			stmt.setString(1, email);
			stmt.setLong(2, Long.parseLong(phoneNumber));
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				return rs.getString(1);
			}catch(SQLException e){
				System.out.println(e);
				e.printStackTrace();
				return "9";
		}	
		return "0";		
	}
	
	protected boolean checkEmail(String email){
		PreparedStatement stmt;
		try{
		stmt = dbConn.prepareStatement("SELECT EMAIL from CUSTOMER WHERE EMAIL = ?");
		stmt.setString(1, email);
		ResultSet rs = stmt.executeQuery();
		if(rs.next())
			return true;
		return false;
		}
		catch(Exception e){
			e.printStackTrace();
			return true;
		}
	}
	
	protected boolean checkPhone(String phone){
		PreparedStatement stmt;
		try{
			stmt = dbConn.prepareStatement("SELECT PHONENUMBER from CUSTOMER WHERE PHONENUMBER = ?");
			stmt.setInt(1, Integer.parseInt(phone));
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				return true;
			return false;
		}
		catch(Exception e){
			e.printStackTrace();
			return true;
		}
	}
	
	protected void newAddress(String customerID,int primaryInd,String line1,String line2,String area,String city,String state,String country,String pincode){
		PreparedStatement stmt;
		String insertQuery = "INSERT INTO CUSTOMERADDRESS(`CustomerID`,`PrimaryInd`,`Line1`,`Line2`,`Area`,`City`,`State`,`Country`,`Pincode`)VALUES(?,?,?,?,?,?,?,?,?)";
		try{
			stmt = dbConn.prepareStatement(insertQuery);
			
			stmt.setString(1, customerID);
			stmt.setInt(2, primaryInd);
			stmt.setString(3, line1);
			stmt.setString(4, line2);
			stmt.setString(5, area);
			stmt.setString(6, city);
			stmt.setString(7, state);
			stmt.setString(8, country);
			stmt.setInt(9, Integer.parseInt(pincode));
			stmt.executeUpdate();
			}catch(SQLException e){
				System.out.println(e);
				e.printStackTrace();
		}	
	}
	
	protected void newSocial(String socialId,String customerId){
		PreparedStatement stmt;
		String insertQuery;
		try{			
			insertQuery = "INSERT INTO SOCIALLOGIN (`SocialID`,`CustomerID`)VALUES(?,?)";
			stmt = dbConn.prepareStatement(insertQuery);
						
			stmt.setString(1, socialId);
			stmt.setString(2, customerId);
			stmt.executeUpdate();
			}catch(SQLException e){
				System.out.println(e);
				e.printStackTrace();
		}	
	}
	
	protected String getCustomerIdForSocialId(String socialId) throws Exception{
		PreparedStatement stmt;			
		String selectQuery = "select count(CustomerID),CustomerID from SocialLogin where SOCIALID = ?";		
		stmt = dbConn.prepareStatement(selectQuery);			
		stmt.setString(1, socialId);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		if (Integer.parseInt(rs.getString(1)) == 0)
			return "0";
		return rs.getString(2);			
	}
	
	protected int getVisits(String customerId) throws Exception{
		PreparedStatement stmt;			
		String selectQuery = "select Visits from Customer where CUSTOMERID = ?";		
		stmt = dbConn.prepareStatement(selectQuery);			
		stmt.setString(1, customerId);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		return rs.getInt(1);		
	}
	
	public int getItems(String customerId) throws Exception{
		PreparedStatement stmt;			
		String selectQuery = "select Items from Customer where CUSTOMERID = ?";		
		stmt = dbConn.prepareStatement(selectQuery);			
		stmt.setString(1, customerId);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		return rs.getInt(1);		
	}
	
	public double getAmount(String customerId) throws Exception{
		PreparedStatement stmt;			
		String selectQuery = "select Amount from Customer where CUSTOMERID = ?";		
		stmt = dbConn.prepareStatement(selectQuery);			
		stmt.setString(1, customerId);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		return rs.getDouble(1);		
	}
	
	protected void updateVisits(String customerId,int visits) throws Exception{
		PreparedStatement stmt;			
		String selectQuery = "UPDATE Customer Set Visits = ? where CUSTOMERID = ?";		
		stmt = dbConn.prepareStatement(selectQuery);			
		stmt.setInt(1, visits);			
		stmt.setString(2, customerId);
		stmt.executeUpdate();	
	}
	
	public void updateAmount(String customerId,double amount) throws Exception{
		PreparedStatement stmt;			
		String selectQuery = "UPDATE Customer Set Amount = ? where CUSTOMERID = ?";		
		stmt = dbConn.prepareStatement(selectQuery);			
		stmt.setDouble(1, amount);			
		stmt.setString(2, customerId);
		stmt.executeUpdate();	
	}
	
	public void updateItems(String customerId,int items) throws Exception{
		PreparedStatement stmt;			
		String selectQuery = "UPDATE Customer Set Items = ? where CUSTOMERID = ?";		
		stmt = dbConn.prepareStatement(selectQuery);			
		stmt.setInt(1, items);			
		stmt.setString(2, customerId);
		stmt.executeUpdate();	
	}
	
	protected void updateLastActivity(String customerId) throws Exception{
		PreparedStatement stmt;			
		String selectQuery = "UPDATE Customer Set LastActivity = now() where CUSTOMERID = ?";		
		stmt = dbConn.prepareStatement(selectQuery);	
		stmt.setString(1, customerId);
		stmt.executeUpdate();	
	}
}
