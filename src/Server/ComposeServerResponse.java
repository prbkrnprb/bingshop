package Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import bingshop.in.InventoryAccess;
import bingshop.in.PictureAccess;

@SuppressWarnings("unchecked")
public class ComposeServerResponse {
	JSONObject jHeader, jMetaData, jData;
	int rc, isMoreData, rowCount, columnCount, loginRC;
	String tableName, moreType, message, loginMessage, customerID, tableData, orderID;
	
	public ComposeServerResponse(){
	}
	
	public void setRC(int data){
		this.rc = data;
	}
	
	public void setIsMoreData(int data){
		this.isMoreData = data;
	}
	
	public void setRowCount(int data){
		this.rowCount = data;
	}
	
	public void setColumnCount(int data){
		this.columnCount = data;
	}
	
	public void setLoginRC(int data){
		this.loginRC = data;
	}
	
	public void setTableName(String data){
		this.tableName = data;
	}

	public void setMoreType(String data){
		this.moreType = data;
	}

	public void setMessage(String data){
		this.message = data;
	}
	
	public void setLoginMessage(String data){
		this.loginMessage = data;
	}
	
	public void setCustomerID(String data){
		this.customerID = data;
	}
	
	public void setOrderID(String data){
		this.orderID = data;
	}
	
	public void setTableData(String tableData[][]){
		JSONArray jTableData = new JSONArray();
		for(int i = 1; i <= rowCount; i++){
			JSONObject jTableColumn = new JSONObject();
			for(int j = 1; j <= columnCount; j++){
				jTableColumn.put("Col " + (j-1),tableData[i][j]);
			}
			jTableData.add(jTableColumn.toString());
		}
		this.tableData = jTableData.toString();
	}
	
	public void setHeader(int rc,String message){
		this.rc = rc;
		this.message = message;
	}
	
	public String forAppStart(){
		JSONObject jComplete = new JSONObject();
		
		jHeader = new JSONObject();
		jHeader.put("rc", rc);
		jHeader.put("message",message);
		jComplete.put("header", jHeader.toString());
		
		return jComplete.toString();
	}

	public String forLogin(){
		JSONObject jComplete = new JSONObject();
		
		jHeader = new JSONObject();
		jHeader.put("rc", rc);
		jHeader.put("message",message);
		jComplete.put("header", jHeader.toString());
		
		jMetaData = new JSONObject();
		jMetaData.put("more", isMoreData);
		jComplete.put("metadata", jMetaData.toString());
		
		jData = new JSONObject();
		jData.put("loginrc", loginRC);
		jData.put("loginmessage", loginMessage);
		jData.put("customerid", customerID);
		jComplete.put("data", jData.toString());
		
		return jComplete.toString();
	}	

	/*public String forPictureRequest(){
		JSONObject jComplete = new JSONObject();
		
		jHeader = new JSONObject();
		jHeader.put("rc", rc);
		jHeader.put("message",message);
		jComplete.put("header", jHeader.toString());
		
		jMetaData = new JSONObject();
		jMetaData.put("more", isMoreData);
		jMetaData.put("moretype", moreType);
		jComplete.put("metadata", jMetaData.toString());
		
		return jComplete.toString();
	}*/

	public String forTableData(){
		JSONObject jComplete = new JSONObject();
		
		jHeader = new JSONObject();
		jHeader.put("rc", rc);
		jHeader.put("message",message);
		jComplete.put("header", jHeader.toString());
		
		jMetaData = new JSONObject();
		jMetaData.put("more", isMoreData);
		jMetaData.put("row", rowCount);
		jMetaData.put("column", columnCount);
		jComplete.put("metadata", jMetaData.toString());
		
		jData = new JSONObject();
		jData.put("tabledata", tableData);
		jComplete.put("data", jData.toString());
		
		return jComplete.toString();
	}

	public String forCheckoutProcess(){
		JSONObject jComplete = new JSONObject();
		
		jHeader = new JSONObject();
		jHeader.put("rc", rc);
		jHeader.put("message",message);
		jComplete.put("header", jHeader.toString());
		
		jMetaData = new JSONObject();
		jMetaData.put("more", isMoreData);
		jComplete.put("metadata", jMetaData.toString());
		
		jData = new JSONObject();
		jData.put("orderid", orderID);
		jComplete.put("data", jData.toString());
		
		return jComplete.toString();
	}

	public String forValidateEmailPhone(int emailOTP,int phoneOTP){
		JSONObject jComplete = new JSONObject();
		
		jHeader = new JSONObject();
		jHeader.put("rc", rc);
		jHeader.put("message",message);
		jComplete.put("header", jHeader.toString());
		
		jMetaData = new JSONObject();
		jMetaData.put("more", isMoreData);
		jComplete.put("metadata", jMetaData.toString());
		
		jData = new JSONObject();
		jData.put("emailotp", emailOTP);
		jData.put("phoneotp", phoneOTP);
		jComplete.put("data", jData.toString());
		
		return jComplete.toString();
	}

	public String forNewCustomer(String customerId){
		JSONObject jComplete = new JSONObject();
		
		jHeader = new JSONObject();
		jHeader.put("rc", rc);
		jHeader.put("message",message);
		jComplete.put("header", jHeader.toString());
		
		jMetaData = new JSONObject();
		jMetaData.put("more", isMoreData);
		jComplete.put("metadata", jMetaData.toString());
		
		jData = new JSONObject();
		jData.put("customerid", customerId);
		jComplete.put("data", jData.toString());
		
		return jComplete.toString();
	}
	
	public String forError(){
		JSONObject jComplete = new JSONObject();
		
		jHeader = new JSONObject();
		jHeader.put("rc", rc);
		jHeader.put("message",message);
		jComplete.put("header", jHeader.toString());
		
		return jComplete.toString();
	}	
}
