package bingshop.in;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;



public class UserRegisterationDB {
	
	private Connection dbConn;
	private PreparedStatement registerStatement;
	private PreparedStatement updatePhnoStatement;
	private PreparedStatement updateEMailStatement;
	private PreparedStatement checkStatement;
	private final int CUSTOMER_ID_POS = 1;
	private final int FIRST_NAME_POS = 2;
	private final int LAST_NAME_POS = 3;
	private final int PASSWORD_POS = 4;
	private final int EMAIL_POS = 5;
	private final int PHONE_NUMBER_POS = 6;
	private final int START_DATE_POS = 7;
	private final int LAST_ACTIVITY_POS = 8;
	private final int AMOUNT_POS = 9;
	private final int ITEM_POS = 10;
	private final int VISIT_POS = 11;
	private final int VALIDATE_PHNO_POS = 12;
	private final int VALIDATE_EMAIL_POS = 13;	

	public UserRegisterationDB() throws Exception {
		DatabaseConn conn = new DatabaseConn();
		dbConn = conn.dbConn;
		String insertQuery = "insert into customer (CustomerID,FirstName,LastName,Password,email,PhoneNumber,StartDate,LastActivity,Amount,Items,Visits,ValidatedPhno,ValidatedEmail) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		String phnoQuery = "update customer set ValidatedPhno = 1 where CustomerID = ?";
		String eMailQuery = "update customer set ValidatedEmail = 1 where CustomerID = ?";
		String checkQuery = "select * from customer where email = ? or PhoneNumber = ? ";
		try{
		registerStatement = dbConn.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
		updatePhnoStatement = dbConn.prepareStatement(phnoQuery);
		updateEMailStatement = dbConn.prepareStatement(eMailQuery);
		checkStatement = dbConn.prepareStatement(checkQuery);
		}
		catch(Exception e){
		e.printStackTrace();
		}
	}
	
	public int newCustomer(int cID,String fName, String lName, String pwd, String eMail, long phno, String startDate, String lastActivity, float amount, int items, int visits, int validatePhno, int validateEmail ){
		try{
		if (cID == 0)
			registerStatement.setNull(CUSTOMER_ID_POS, java.sql.Types.INTEGER);
		else
			registerStatement.setInt(CUSTOMER_ID_POS, cID);
		registerStatement.setString(FIRST_NAME_POS, fName);
		registerStatement.setString(LAST_NAME_POS, lName);
		registerStatement.setString(PASSWORD_POS, pwd);
		registerStatement.setString(EMAIL_POS, eMail);
		registerStatement.setLong(PHONE_NUMBER_POS, phno);
		if (startDate == "")
			registerStatement.setTimestamp(START_DATE_POS, new Timestamp(System.currentTimeMillis()));
		else
			registerStatement.setString(START_DATE_POS, startDate);
		if (lastActivity == "")
			registerStatement.setTimestamp(LAST_ACTIVITY_POS, new Timestamp(System.currentTimeMillis()));
		else
			registerStatement.setString(LAST_ACTIVITY_POS, lastActivity);
		registerStatement.setFloat(AMOUNT_POS, amount);
		registerStatement.setInt(ITEM_POS, items);
		registerStatement.setInt(VISIT_POS, visits);
		registerStatement.setInt(VALIDATE_PHNO_POS, validatePhno);
		registerStatement.setInt(VALIDATE_EMAIL_POS, validateEmail);
		registerStatement.executeUpdate();
		ResultSet rs = registerStatement.getGeneratedKeys();
		if(rs.next())
				return rs.getInt(1);
		}catch(SQLException e){
			e.printStackTrace();
		}	
		return 0;		
	}
	
	public void validatedPhno(int cID){
		try{
		updatePhnoStatement.setInt(1, cID);
		updatePhnoStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public void validatedEMail(int cID){
		try{
		updateEMailStatement.setInt(1, cID);
		updateEMailStatement.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public int check(String eMail, long phNo){
		try{
			checkStatement.setString(1,eMail);
			checkStatement.setLong(2, phNo);
			ResultSet rs = checkStatement.executeQuery();
			if(rs.next())
				return 1;
			}catch(SQLException e){
				e.printStackTrace();
			}
		return 0;
	}
	
}
