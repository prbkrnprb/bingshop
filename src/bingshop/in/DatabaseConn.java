package bingshop.in;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConn {
	
	protected Connection dbConn;
	
	public DatabaseConn() throws Exception{
		String dbPath="jdbc:mysql://127.11.128.2:3306/";
		String dbName="bing_shop";
		String driver="com.mysql.jdbc.Driver";
		String dbUserName="root";
		String dbPwd="me7Lolita";
		dbConn=null;
		Class.forName(driver).newInstance();
		dbConn=DriverManager.getConnection(dbPath+dbName,dbUserName,dbPwd);	
	}
}
