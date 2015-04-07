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

public class InventoryDB extends DatabaseConn {
	
	public InventoryDB() throws Exception{
		super();
	}
	
	protected String newMainCategory(String cID,String name, String description, String pictureID, String langID){
		PreparedStatement stmt;
		String insertQuery = "insert into MainCat (CategoryID,Name,Description,PictureID,LangID) values (?,?,?,?,?)";
		try{
			stmt = dbConn.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
			if (cID == "0")
				stmt.setNull(1, java.sql.Types.CHAR);
			else
				stmt.setString(1, cID);
			stmt.setString(2, name);
			stmt.setString(3, description);
			stmt.setString(4, pictureID);
			stmt.setString(5, langID);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next())
					return rs.getString(1);
			}catch(SQLException e){
				e.printStackTrace();
				return "5";
		}	
		return "0";		
	}
	
	protected String newSub1Cat(String cID, String fID, String name, String description, String pictureID, String langID){
		PreparedStatement stmt;
		String insertQuery = "insert into sub1Cat (CategoryID,MCatID,Name,Description,PictureID,LangID) values (?,?,?,?,?,?)";
		try{
			stmt = dbConn.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
			if (cID == "0")
				stmt.setNull(1, java.sql.Types.CHAR);
			else
				stmt.setString(1, cID);
			stmt.setString(2, fID);
			stmt.setString(3, name);
			stmt.setString(4, description);
			stmt.setString(5, pictureID);
			stmt.setString(6, langID);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next())
					return rs.getString(1);
			}catch(SQLException e){
				e.printStackTrace();
				return "5";
		}	
		return "0";		
	}
	
	protected String newSub2Cat(String cID, String fID, String name, String description, String pictureID, String langID){
		PreparedStatement stmt;
		String insertQuery = "insert into sub2Cat (CategoryID,S1CatID,Name,Description,PictureID,LangID) values (?,?,?,?,?,?)";
		try{
			stmt = dbConn.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
			if (cID == "0")
				stmt.setNull(1, java.sql.Types.CHAR);
			else
				stmt.setString(1, cID);
			stmt.setString(2, fID);
			stmt.setString(3, name);
			stmt.setString(4, description);
			stmt.setString(5, pictureID);
			stmt.setString(6, langID);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next())
					return rs.getString(1);
			}catch(SQLException e){
				e.printStackTrace();
				return "5";
		}	
		return "0";		
	}
	
	protected String newItem(String cID, String fID, String name, String description, String pictureID, String langID){
		PreparedStatement stmt;
		String insertQuery = "insert into item (ItemID,S2CatID,Name,Description,PictureID,LangID) values (?,?,?,?,?,?)";
		try{
			stmt = dbConn.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
			if (cID == "0")
				stmt.setNull(1, java.sql.Types.CHAR);
			else
				stmt.setString(1, cID);
			stmt.setString(2, fID);
			stmt.setString(3, name);
			stmt.setString(4, description);
			stmt.setString(5, pictureID);
			stmt.setString(6, langID);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next())
					return rs.getString(1);
			}catch(SQLException e){
				e.printStackTrace();
				return "5";
		}	
		return "0";		
	}
	
	protected String newBrand(String cID, String fID, String name, String description, String pictureID, String langID){
		PreparedStatement stmt;
		String insertQuery = "insert into brand (BrandID,ItemID,Name,Description,PictureID,LangID) values (?,?,?,?,?,?)";
		try{
			stmt = dbConn.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
			if (cID == "0")
				stmt.setNull(1, java.sql.Types.CHAR);
			else
				stmt.setString(1, cID);
			stmt.setString(2, fID);
			stmt.setString(3, name);
			stmt.setString(4, description);
			stmt.setString(5, pictureID);
			stmt.setString(6, langID);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next())
					return rs.getString(1);
			}catch(SQLException e){
				e.printStackTrace();
				return "5";
		}	
		return "0";		
	}
	
	protected String newItemSize(String cID, String fID, String name, String langID, float price, int stock){
		PreparedStatement stmt;
		String insertQuery = "insert into itemsize (ItemSizeID,BrandID,Name,LangID,Price,Stock) values (?,?,?,?,?,?)";
		try{
			stmt = dbConn.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
			if (cID == "0")
				stmt.setNull(1, java.sql.Types.CHAR);
			else
				stmt.setString(1, cID);
			stmt.setString(2, fID);
			stmt.setString(3, name);
			stmt.setString(4, langID);
			stmt.setFloat(5, price);
			stmt.setInt(6, stock);
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next())
					return rs.getString(1);
			}catch(SQLException e){
				e.printStackTrace();
				return "5";
		}	
		return "0";		
	}
	
	protected String[][] getMainCategory(){
		PreparedStatement getStatement;
		String result[][] = new String[999][9];
		String selectQuery = "SELECT CATEGORYID, NAME, DESCRIPTION, PICTUREID, LANGID FROM MAINCAT ORDER BY NAME ASC";
		
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		ResultSet rs = getStatement.executeQuery();
		int i = 1;
		//rs.next();
		while(rs.next()){
			result[i][1] = rs.getString(1);
			result[i][2] = rs.getString(2);
			result[i][3] = rs.getString(3);
			result[i][4] = rs.getString(4);
			result[i][5] = rs.getString(5);
			i = i + 1;			
		}
		i = i - 1;
		result[0][0] = Integer.toString(i);
		result[0][1] = "categoryid";
		result[0][2] = "name";
		result[0][3] = "description";
		result[0][4] = "pictureid";
		result[0][5] = "langid";
		result[1][0] = "5";
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	protected String[][] getMainCategory(String cID,String name){
		PreparedStatement getStatement;
		String result[][] = new String[999][9];
		String selectQuery = "SELECT CATEGORYID, NAME, DESCRIPTION, PICTUREID, LANGID FROM MAINCAT WHERE CATEGORYID = ? OR NAME = ? ORDER BY NAME ASC";
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		getStatement.setString(1, cID);
		getStatement.setString(2, name);
		ResultSet rs = getStatement.executeQuery();
		int i = 1;
		while(rs.next()){
			result[i][1] = rs.getString(1);
			result[i][2] = rs.getString(2);
			result[i][3] = rs.getString(3);
			result[i][4] = rs.getString(4);
			result[i][5] = rs.getString(5);
			i = i + 1;			
		}
		i = i - 1;
		result[0][0] = Integer.toString(i);
		result[0][1] = "categoryid";
		result[0][2] = "name";
		result[0][3] = "description";
		result[0][4] = "pictureid";
		result[0][5] = "langid";
		result[1][0] = "5";
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	protected String[][] getMainCategory(String cID){
		PreparedStatement getStatement;
		String result[][] = new String[999][9];
		String selectQuery = "SELECT CATEGORYID, NAME, DESCRIPTION, PICTUREID, LANGID FROM MAINCAT WHERE CATEGORYID = ? ORDER BY NAME ASC";
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		getStatement.setString(1, cID);
		ResultSet rs = getStatement.executeQuery();
		int i = 1;
		while(rs.next()){
			result[i][1] = rs.getString(1);
			result[i][2] = rs.getString(2);
			result[i][3] = rs.getString(3);
			result[i][4] = rs.getString(4);
			result[i][5] = rs.getString(5);
			i = i + 1;			
		}
		i = i - 1;
		result[0][0] = Integer.toString(i);
		result[0][1] = "categoryid";
		result[0][2] = "name";
		result[0][3] = "description";
		result[0][4] = "pictureid";
		result[0][5] = "langid";
		result[1][0] = "5";
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	protected String[][] getSub1Cat(String cID, String name, String fID){
		PreparedStatement getStatement;
		String result[][] = new String[999][9];
		String selectQuery = "SELECT CATEGORYID, MCATID, NAME, DESCRIPTION, PICTUREID, LANGID FROM SUB1CAT WHERE CATEGORYID = ? OR (NAME = ? AND MCATID = ?) ORDER BY NAME ASC";
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		getStatement.setString(1, cID);
		getStatement.setString(2, name);
		getStatement.setString(3, fID);
		ResultSet rs = getStatement.executeQuery();
		int i = 1;
		while(rs.next()){
			result[i][1] = rs.getString(1);
			result[i][2] = rs.getString(2);
			result[i][3] = rs.getString(3);
			result[i][4] = rs.getString(4);
			result[i][5] = rs.getString(5);
			result[i][6] = rs.getString(6);
			i = i + 1;			
		}
		i = i - 1;
		result[0][0] = Integer.toString(i);
		result[0][1] = "categoryid";
		result[0][2] = "mcatid";
		result[0][3] = "name";
		result[0][4] = "description";
		result[0][5] = "pictureid";
		result[0][6] = "langid";
		result[1][0] = "6";
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	protected String[][] getSub1Cat(String name, String fID){
		PreparedStatement getStatement;
		String result[][] = new String[999][9];
		String selectQuery = "SELECT CATEGORYID, MCATID, NAME, DESCRIPTION, PICTUREID, LANGID FROM SUB1CAT WHERE NAME = ? AND MCATID = ? ORDER BY NAME ASC";
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		getStatement.setString(1, name);
		getStatement.setString(2, fID);
		ResultSet rs = getStatement.executeQuery();
		int i = 1;
		while(rs.next()){
			result[i][1] = rs.getString(1);
			result[i][2] = rs.getString(2);
			result[i][3] = rs.getString(3);
			result[i][4] = rs.getString(4);
			result[i][5] = rs.getString(5);
			result[i][6] = rs.getString(6);
			i = i + 1;			
		}
		i = i - 1;
		result[0][0] = Integer.toString(i);
		result[0][1] = "categoryid";
		result[0][2] = "mcatid";
		result[0][3] = "name";
		result[0][4] = "description";
		result[0][5] = "pictureid";
		result[0][6] = "langid";
		result[1][0] = "6";
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	protected String[][] getSub1Cat(String cID){
		PreparedStatement getStatement;
		String result[][] = new String[999][9];
		String selectQuery = "SELECT CATEGORYID, NAME, DESCRIPTION, PICTUREID, LANGID FROM SUB1CAT WHERE MCATID = ? ORDER BY NAME ASC";
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		getStatement.setString(1, cID);
		ResultSet rs = getStatement.executeQuery();
		int i = 1;
		while(rs.next()){
			result[i][1] = rs.getString(1);
			result[i][2] = rs.getString(2);
			result[i][3] = rs.getString(3);
			result[i][4] = rs.getString(4);
			result[i][5] = rs.getString(5);
			i = i + 1;			
		}
		i = i - 1;
		result[0][0] = Integer.toString(i);
		result[0][1] = "categoryid";
		result[0][2] = "name";
		result[0][3] = "description";
		result[0][4] = "pictureid";
		result[0][5] = "langid";
		result[1][0] = "5";
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	protected String[][] getSub2Cat(String cID, String name, String fID){
		PreparedStatement getStatement;
		String result[][] = new String[999][9];
		String selectQuery = "SELECT CATEGORYID, S1CATID, NAME, DESCRIPTION, PICTUREID, LANGID FROM SUB2CAT WHERE CATEGORYID = ? OR (NAME = ? AND S1CATID = ?) ORDER BY NAME ASC";
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		getStatement.setString(1, cID);
		getStatement.setString(2, name);
		getStatement.setString(3, fID);
		ResultSet rs = getStatement.executeQuery();
		int i = 1;
		while(rs.next()){
			result[i][1] = rs.getString(1);
			result[i][2] = rs.getString(2);
			result[i][3] = rs.getString(3);
			result[i][4] = rs.getString(4);
			result[i][5] = rs.getString(5);
			result[i][6] = rs.getString(6);
			i = i + 1;			
		}
		i = i - 1;
		result[0][0] = Integer.toString(i);
		result[0][1] = "categoryid";
		result[0][2] = "sub1catid";
		result[0][3] = "name";
		result[0][4] = "description";
		result[0][5] = "pictureid";
		result[0][6] = "langid";
		result[1][0] = "6";
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	protected String[][] getSub2Cat(String name, String fID){
		PreparedStatement getStatement;
		String result[][] = new String[999][9];
		String selectQuery = "SELECT CATEGORYID, S1CATID, NAME, DESCRIPTION, PICTUREID, LANGID FROM SUB2CAT WHERE NAME = ? AND S1CATID = ? ORDER BY NAME ASC";
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		getStatement.setString(1, name);
		getStatement.setString(2, fID);
		ResultSet rs = getStatement.executeQuery();
		int i = 1;
		while(rs.next()){
			result[i][1] = rs.getString(1);
			result[i][2] = rs.getString(2);
			result[i][3] = rs.getString(3);
			result[i][4] = rs.getString(4);
			result[i][5] = rs.getString(5);
			result[i][6] = rs.getString(6);
			i = i + 1;			
		}
		i = i - 1;
		result[0][0] = Integer.toString(i);
		result[0][1] = "categoryid";
		result[0][2] = "sub1catid";
		result[0][3] = "name";
		result[0][4] = "description";
		result[0][5] = "pictureid";
		result[0][6] = "langid";
		result[1][0] = "6";
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	protected String[][] getSub2Cat(String cID){
		PreparedStatement getStatement;
		String result[][] = new String[999][9];
		String selectQuery = "SELECT CATEGORYID, NAME, DESCRIPTION, PICTUREID, LANGID FROM SUB2CAT WHERE S1CATID = ? ORDER BY NAME ASC";
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		getStatement.setString(1, cID);
		ResultSet rs = getStatement.executeQuery();
		int i = 1;
		while(rs.next()){
			result[i][1] = rs.getString(1);
			result[i][2] = rs.getString(2);
			result[i][3] = rs.getString(3);
			result[i][4] = rs.getString(4);
			result[i][5] = rs.getString(5);
			i = i + 1;			
		}
		i = i - 1;
		result[0][0] = Integer.toString(i);
		result[0][1] = "categoryid";
		result[0][2] = "name";
		result[0][3] = "description";
		result[0][4] = "pictureid";
		result[0][5] = "langid";
		result[1][0] = "5";
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	protected String[][] getItem(String cID, String name, String fID){
		PreparedStatement getStatement;
		String result[][] = new String[999][9];
		String selectQuery = "SELECT ITEMID, S2CATID, NAME, DESCRIPTION, PICTUREID, LANGID FROM ITEM WHERE ITEMID = ? OR (NAME = ? AND S2CATID = ?) ORDER BY NAME ASC";
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		getStatement.setString(1, cID);
		getStatement.setString(2, name);
		getStatement.setString(3, fID);
		ResultSet rs = getStatement.executeQuery();
		int i = 1;
		while(rs.next()){
			result[i][1] = rs.getString(1);
			result[i][2] = rs.getString(2);
			result[i][3] = rs.getString(3);
			result[i][4] = rs.getString(4);
			result[i][5] = rs.getString(5);
			result[i][6] = rs.getString(6);
			i = i + 1;			
		}
		i = i - 1;
		result[0][0] = Integer.toString(i);
		result[0][1] = "itemid";
		result[0][2] = "sub2catid";
		result[0][3] = "name";
		result[0][4] = "description";
		result[0][5] = "pictureid";
		result[0][6] = "langid";
		result[1][0] = "6";
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	protected String[][] getItem(String name, String fID){
		PreparedStatement getStatement;
		String result[][] = new String[999][9];
		String selectQuery = "SELECT ITEMID, S2CATID, NAME, DESCRIPTION, PICTUREID, LANGID FROM ITEM WHERE NAME = ? AND S2CATID = ? ORDER BY NAME ASC";
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		getStatement.setString(1, name);
		getStatement.setString(2, fID);
		ResultSet rs = getStatement.executeQuery();
		int i = 1;
		while(rs.next()){
			result[i][1] = rs.getString(1);
			result[i][2] = rs.getString(2);
			result[i][3] = rs.getString(3);
			result[i][4] = rs.getString(4);
			result[i][5] = rs.getString(5);
			result[i][6] = rs.getString(6);
			i = i + 1;			
		}
		i = i - 1;
		result[0][0] = Integer.toString(i);
		result[0][1] = "itemid";
		result[0][2] = "sub2catid";
		result[0][3] = "name";
		result[0][4] = "description";
		result[0][5] = "pictureid";
		result[0][6] = "langid";
		result[1][0] = "6";
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	protected String[][] getItem(String cID){
		PreparedStatement getStatement;
		String result[][] = new String[999][9];
		String selectQuery = "SELECT ITEMID, NAME, DESCRIPTION, PICTUREID, LANGID FROM ITEM WHERE S2CATID = ? ORDER BY NAME ASC";
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		getStatement.setString(1, cID);
		ResultSet rs = getStatement.executeQuery();
		int i = 1;
		while(rs.next()){
			result[i][1] = rs.getString(1);
			result[i][2] = rs.getString(2);
			result[i][3] = rs.getString(3);
			result[i][4] = rs.getString(4);
			result[i][5] = rs.getString(5);
			i = i + 1;			
		}
		i = i - 1;
		result[0][0] = Integer.toString(i);
		result[0][1] = "itemid";
		result[0][2] = "name";
		result[0][3] = "description";
		result[0][4] = "pictureid";
		result[0][5] = "langid";
		result[1][0] = "5";
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	protected String getItemNameByPID(String cID){
		PreparedStatement getStatement;
		String selectQuery = "SELECT NAME FROM ITEM WHERE ITEMID = ?";
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		getStatement.setString(1, cID);
		ResultSet rs = getStatement.executeQuery();
		if(rs.next()){
			return rs.getString(1);
		}else{
			return "0";
		}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return "0";
	}
	
	protected String[][] getBrand(String cID, String name, String fID){
		PreparedStatement getStatement;
		String result[][] = new String[999][9];
		String selectQuery = "SELECT BRANDID, ITEMID, NAME, DESCRIPTION, PICTUREID, LANGID FROM BRAND WHERE BRANDID = ? OR (NAME = ? AND ITEMID = ?) ORDER BY NAME ASC";
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		getStatement.setString(1, cID);
		getStatement.setString(2, name);
		getStatement.setString(3, fID);
		ResultSet rs = getStatement.executeQuery();
		int i = 1;
		while(rs.next()){
			result[i][1] = rs.getString(1);
			result[i][2] = rs.getString(2);
			result[i][3] = rs.getString(3);
			result[i][4] = rs.getString(4);
			result[i][5] = rs.getString(5);
			result[i][6] = rs.getString(6);
			i = i + 1;			
		}
		i = i - 1;
		result[0][0] = Integer.toString(i);
		result[0][1] = "itemid";
		result[0][2] = "brandid";
		result[0][3] = "name";
		result[0][4] = "description";
		result[0][5] = "pictureid";
		result[0][6] = "langid";
		result[1][0] = "6";
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	protected String[][] getBrand(String name, String fID){
		PreparedStatement getStatement;
		String result[][] = new String[999][9];
		String selectQuery = "SELECT BRANDID, ITEMID, NAME, DESCRIPTION, PICTUREID, LANGID FROM BRAND WHERE NAME = ? AND ITEMID = ?) ORDER BY NAME ASC";
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		getStatement.setString(1, name);
		getStatement.setString(2, fID);
		ResultSet rs = getStatement.executeQuery();
		int i = 1;
		while(rs.next()){
			result[i][1] = rs.getString(1);
			result[i][2] = rs.getString(2);
			result[i][3] = rs.getString(3);
			result[i][4] = rs.getString(4);
			result[i][5] = rs.getString(5);
			result[i][6] = rs.getString(6);
			i = i + 1;			
		}
		i = i - 1;
		result[0][0] = Integer.toString(i);
		result[0][1] = "itemid";
		result[0][2] = "brandid";
		result[0][3] = "name";
		result[0][4] = "description";
		result[0][5] = "pictureid";
		result[0][6] = "langid";
		result[1][0] = "6";
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	protected String[][] getBrand(String cID){
		PreparedStatement getStatement;
		String result[][] = new String[999][9];
		String selectQuery = "SELECT BRANDID, NAME, DESCRIPTION, PICTUREID, LANGID FROM BRAND WHERE ITEMID = ? ORDER BY NAME ASC";
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		getStatement.setString(1, cID);
		ResultSet rs = getStatement.executeQuery();
		int i = 1;
		while(rs.next()){
			result[i][1] = rs.getString(1);
			result[i][2] = rs.getString(2);
			result[i][3] = rs.getString(3);
			result[i][4] = rs.getString(4);
			result[i][5] = rs.getString(5);
			i = i + 1;			
		}
		i = i - 1;
		result[0][0] = Integer.toString(i);
		result[0][1] = "itemid";
		result[0][2] = "name";
		result[0][3] = "description";
		result[0][4] = "pictureid";
		result[0][5] = "langid";
		result[1][0] = "5";
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	protected String getBrandNameByPID(String cID){
		PreparedStatement getStatement;
		String selectQuery = "SELECT NAME FROM BRAND WHERE BRANDID = ?";
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		getStatement.setString(1, cID);
		ResultSet rs = getStatement.executeQuery();
		if(rs.next()){
			return rs.getString(1);
		}else{
			return "0";
		}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return "0";
	}
	
	protected String[][] getItemSize(String cID, String name, String fID){
		PreparedStatement getStatement;
		String result[][] = new String[999][9];
		String selectQuery = "SELECT ITEMSIZEID, BRANDID, NAME, LANGID, PRICE, STOCK FROM ITEMSIZE WHERE ITEMSIZEID = ? OR (NAME = ? AND BRANDID = ?) ORDER BY NAME ASC";
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		getStatement.setString(1, cID);
		getStatement.setString(2, name);
		getStatement.setString(3, fID);
		ResultSet rs = getStatement.executeQuery();
		int i = 1;
		while(rs.next()){
			result[i][1] = rs.getString(1);
			result[i][2] = rs.getString(2);
			result[i][3] = rs.getString(3);
			result[i][4] = rs.getString(4);
			result[i][5] = rs.getString(5);
			result[i][6] = rs.getString(6);
			i = i + 1;			
		}
		i = i - 1;
		result[0][0] = Integer.toString(i);
		result[0][1] = "itemsizeid";
		result[0][2] = "brandid";
		result[0][3] = "name";
		result[0][4] = "langid";
		result[0][5] = "price";
		result[0][6] = "stock";
		result[1][0] = "6";
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	protected String[][] getItemSizeByPID(String cID){
		PreparedStatement getStatement;
		String result[][] = new String[999][9];
		String selectQuery = "SELECT ITEMSIZEID, BRANDID, NAME, LANGID, PRICE, STOCK FROM ITEMSIZE WHERE ITEMSIZEID = ? ORDER BY NAME ASC";
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		getStatement.setString(1, cID);
		ResultSet rs = getStatement.executeQuery();
		int i = 1;
		while(rs.next()){
			result[i][1] = rs.getString(1);
			result[i][2] = rs.getString(2);
			result[i][3] = rs.getString(3);
			result[i][4] = rs.getString(4);
			result[i][5] = rs.getString(5);
			result[i][6] = rs.getString(6);
			i = i + 1;			
		}
		i = i - 1;
		result[0][0] = Integer.toString(i);
		result[0][1] = "itemsizeid";
		result[0][2] = "brandid";
		result[0][3] = "name";
		result[0][4] = "langid";
		result[0][5] = "price";
		result[0][6] = "stock";
		result[1][0] = "6";
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	protected String[][] getItemSizeByFID(String fID){
		PreparedStatement getStatement;
		String result[][] = new String[999][9];
		String selectQuery = "SELECT ITEMSIZEID, NAME, LANGID, PRICE, STOCK FROM ITEMSIZE WHERE BRANDID = ? ORDER BY NAME ASC";
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		getStatement.setString(1, fID);
		ResultSet rs = getStatement.executeQuery();
		int i = 1;
		while(rs.next()){
			result[i][1] = rs.getString(1);
			result[i][2] = rs.getString(2);
			result[i][3] = rs.getString(3);
			result[i][4] = rs.getString(4);
			result[i][5] = rs.getString(5);
			i = i + 1;			
		}
		i = i - 1;
		result[0][0] = Integer.toString(i);
		result[0][1] = "itemsizeid";
		result[0][2] = "name";
		result[0][3] = "langid";
		result[0][4] = "price";
		result[0][5] = "stock";
		result[1][0] = "5";
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
	
	protected String getItemSizeNameByPID(String pID){
		PreparedStatement getStatement;
		String result = "";
		String selectQuery = "SELECT NAME FROM ITEMSIZE WHERE ITEMSIZEID = ?";
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		getStatement.setString(1, pID);
		ResultSet rs = getStatement.executeQuery();
		while(rs.next()){
			result = rs.getString(1);		
		}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}	
	
	protected Double getItemPrice(String pID){
		PreparedStatement getStatement;
		String result = "999.99";
		String selectQuery = "SELECT PRICE FROM ITEMSIZE WHERE ITEMSIZEID = ?";
		try{
		getStatement = dbConn.prepareStatement(selectQuery);
		getStatement.setString(1, pID);
		ResultSet rs = getStatement.executeQuery();
		while(rs.next()){
			result = rs.getString(1);		
		}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return Double.parseDouble(result);
	}
	
}

	

