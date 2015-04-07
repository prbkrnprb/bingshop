package Server;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import bingshop.in.CustomerAccess;
import bingshop.in.InventoryAccess;
import bingshop.in.OrderAccess;
import bingshop.in.PictureAccess;
import MyException.MyObjNotFoundException;

public class ProcessClient {
	
	String responseString;
	BufferedImage responseImage;
	int responseType,rc;
	static String clientType,ip;
	SaveClient saveClientData;
	
	ProcessClientData1 cData;
	public ProcessClient(String message, SaveClient saveClientData){
		try {
			cData = new ProcessClientData1(message);
			this.saveClientData = saveClientData;
			this.begin();
		} catch (MyObjNotFoundException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public ProcessClient(String message){
		try {
			cData = new ProcessClientData1(message);
			this.begin();
		} catch (MyObjNotFoundException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void begin() throws Exception{
		
		try{
			clientType = cData.getClientType();
			saveClientData.type = clientType;
		}catch(MyObjNotFoundException e){
		}
		
		switch(clientType) {                               // select path based on the client type		
		case "mobile":
			String clientRequest = cData.getRequest();
			switch(clientRequest){                         // select path based on the client request
			
			case "appstart":
				ip = cData.getIP();
				saveClientData.ip = ip;
				ProcessAppStart processAppStart = new ProcessAppStart(ip);
				responseType = 0;
				responseString = processAppStart.response();
				break;
			
			case "login":
				String user = cData.getUser();
				String pwd = cData.getPwd();
				String socialId = cData.getSocialId();
				ProcessLogin processLogin = new ProcessLogin(user,pwd,socialId);
				responseType = 0;
				responseString = processLogin.response();
				saveClientData.customerID = processLogin.customerID;
				break;
				
			case "gettable":
				String table = cData.getTableName();
				String key = cData.getTableKey();
				ProcessTableData processTableData = new ProcessTableData(table,key);
				responseType = 0;
				responseString = processTableData.response();
				break;	
				
			case "checkoutprocess":
				ArrayList<String> keys = cData.getItemKeys();
				ArrayList<String> quantity = cData.getQuantity();
				CheckoutProcess checkoutProcess = new CheckoutProcess(saveClientData.customerID,keys,quantity);
				responseType = 0;
				responseString = checkoutProcess.response();
				break;	
				
			case "validateemailphone":
				String email = cData.getEmail();
				String phone = cData.getPhoneNumber();
				ValidateEmailPhone validate = new ValidateEmailPhone(email,phone);
				responseType = 0;
				responseString = validate.response();
				break;	
				
			case "newcustomer":
				String customerInfo = cData.getNewCustomerInfo();
				NewCustomer newCustomer = new NewCustomer(customerInfo);
				responseType = 0;
				responseString = newCustomer.response();
				break;
				
			case "getpicture":
				String key1 = cData.getTableKey();
				ProcessPictureRequest processPictureRequest = new ProcessPictureRequest(key1);
				if(processPictureRequest.getRC() == 0 ){
					responseType = 1;
					responseString = processPictureRequest.response();
					responseImage = processPictureRequest.imageBuffer;
					rc = processPictureRequest.getRC();
				}
				else{
					rc = processPictureRequest.getRC();
					responseType = 0;
					responseString = processPictureRequest.response();
				}
				break;		
			}
			break;			
		}
	}
}

class ProcessAppStart{
	String ip;
	ComposeServerResponse cResponse;
	
	public ProcessAppStart(String ip){
		this.ip = ip;
		cResponse = new ComposeServerResponse();
	}
	
	public String response(){
		try {
			cResponse.setHeader(0, "Request success");
			cResponse.setIsMoreData(0);
			return cResponse.forAppStart();
		}catch (Exception e1) {
			e1.printStackTrace();
			cResponse.setMessage("Severe error Encountered" + e1);
		}
		cResponse.setRC(9);
		return cResponse.forError();			
	}
}


class ProcessLogin{
	String user,pwd,customerID,socialId;
	ComposeServerResponse cResponse;
	
	public ProcessLogin(String user, String pwd,String socialId){
		this.user = user;
		this.pwd = pwd;
		this.socialId = socialId;
		cResponse = new ComposeServerResponse();
	}
	
	public String response(){
		CustomerAccess uLogin;
		try {
			cResponse.setHeader(0, "Request success");
			cResponse.setIsMoreData(0);
			
			uLogin = new CustomerAccess();
			String customerID = uLogin.loginValid(user, pwd, socialId);
			if(customerID == "0"){
				cResponse.setLoginRC(1);
				cResponse.setLoginMessage("Invalid Login");
				cResponse.setCustomerID("0");
			}
			else{
				cResponse.setLoginRC(0);
				cResponse.setLoginMessage("Valid Login");
				cResponse.setCustomerID(customerID);
				this.customerID = customerID;
			}
			
			return cResponse.forLogin();
			
		} catch (Exception e1) {
			e1.printStackTrace();
			cResponse.setMessage("Severe error Encountered" + e1);
		}
		cResponse.setRC(9);
		return cResponse.forError();
	}	
}

class ProcessTableData{
	String table,key;
	ComposeServerResponse cResponse;
	InventoryAccess iAccess;
	final String TABLE1 = "MainCategory";
	final String TABLE2 = "Sub1Cat";
	final String TABLE3 = "Sub2Cat";
	final String TABLE4 = "Item";
	final String TABLE5 = "Brand";
	final String TABLE6 = "ItemSize";
	final String TABLE0 = "None";
	
	public ProcessTableData(String table, String key){
		this.table = table;
		this.key = key;
		cResponse = new ComposeServerResponse();
		try {
			iAccess = new InventoryAccess();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String response(){
		String tableData[][] = new String[999][99];
		switch(table){
		case TABLE1:
			tableData = iAccess.getMainCategory();
			break;
		
		case TABLE2:
			tableData = iAccess.getSub1Cat(key);
			break;
			
		case TABLE3:
			tableData = iAccess.getSub2Cat(key);
			break;
			
		case TABLE4:
			tableData = iAccess.getItem(key);
			break;
			
		case TABLE5:
			tableData = iAccess.getBrand(key);
			break;

		case TABLE6:
			tableData = iAccess.getItemSizeByFID(key);/*
			int lastCol = Integer.parseInt(tableData[1][0]);
			int lastRow = Integer.parseInt(tableData[0][0]);
			for(int i = 1; i <= lastRow; i++){
				tableData[i][lastCol + 1] = iAccess.getItemNameByPID(key);
				tableData[i][lastCol + 2] = iAccess.getBrandNameByPID(key);
			}
			lastCol +=2;
			tableData[1][0] = lastCol + "";*/
			break;
			
		default:
			cResponse.setRC(5);
			cResponse.setMessage("Table not found");
			return cResponse.forError();
		}
		cResponse.setHeader(0, "Request success");
		cResponse.setIsMoreData(0);
		cResponse.setRowCount(Integer.parseInt(tableData[0][0]));
		cResponse.setColumnCount(Integer.parseInt(tableData[1][0]));
		cResponse.setTableData(tableData);
		return cResponse.forTableData();
	}
	
}

class ProcessPictureRequest{
	String key;
	ComposeServerResponse cResponse;
	PictureAccess pAccess;
	BufferedImage imageBuffer;
	int rc;
	
	public ProcessPictureRequest(String key){
		this.key = key;
		cResponse = new ComposeServerResponse();
		try {
			pAccess = new PictureAccess(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String response(){	
		try {
			imageBuffer = pAccess.getImage();
			cResponse.setHeader(0, "Request success");
			cResponse.setIsMoreData(1);
			cResponse.setMoreType("image");
			rc = 0;
			return cResponse.forTableData();
		} catch (IOException e) {
			e.printStackTrace();
			rc = 1;
			cResponse.setMessage("Severe error Encountered" + e);
		}
		cResponse.setRC(9);
		return cResponse.forError();
	}
	
	public int getRC(){
		return rc;
	}
	
}

class CheckoutProcess extends OrderAccess{
	private String orderID;
	ComposeServerResponse cResponse;
	int rc;
	InventoryAccess iA;
	public CheckoutProcess(String customerID,ArrayList<String> itemSizeKey,ArrayList<String> quantity) throws Exception{
		ArrayList<Double> price = new ArrayList<Double>();
		iA = new InventoryAccess();
		cResponse = new ComposeServerResponse();
		for(int i=0; i<quantity.size();i++){
			Double indPrice = iA.getItemPrice(itemSizeKey.get(i));
			Double totPrice = indPrice * Integer.parseInt(quantity.get(i));
			price.add(totPrice);
		}			
		orderID = newOrder(customerID,itemSizeKey,quantity,price);			
	}
	public String response(){		
		cResponse.setHeader(0, "Request success");
		cResponse.setIsMoreData(0);
		cResponse.setOrderID(orderID);
		rc = 0;
		return cResponse.forCheckoutProcess();		
	}
	
	public int getRC(){
		return rc;
	}
}

class ValidateEmailPhone extends OrderAccess{
	private String email,phone;
	ComposeServerResponse cResponse;
	int rc;
	InventoryAccess iA;
	public ValidateEmailPhone(String email,String phone) throws Exception{	
		this.email = email;
		this.phone = phone;
		cResponse = new ComposeServerResponse();
	}
	public String response(){		
		Random r = new Random();
		int emailOTP = r.nextInt(9999 - 1001) + 1001;
		int phoneOTP = r.nextInt(9999 - 1001) + 1001;
		cResponse.setHeader(0, "Request success");
		cResponse.setIsMoreData(0);
		rc = 0;
		System.out.println("Email OTP : " + emailOTP);
		System.out.println("Phone OTP : " + phoneOTP);
		return cResponse.forValidateEmailPhone(emailOTP,phoneOTP);		
	}
	
	public int getRC(){
		return rc;
	}
}

class NewCustomer {
	private String customerInfo;
	ComposeServerResponse cResponse;
	int rc;
	CustomerAccess cA;
	public NewCustomer(String customerInfo) throws Exception{	
		this.customerInfo = customerInfo;
		cResponse = new ComposeServerResponse();
		cA = new CustomerAccess();
	}
	public String response(){			
		String customerId = cA.newCustomer(customerInfo);
		if (customerId != "0" || customerId != "9"){
			cResponse.setHeader(0, "Request success");
			cResponse.setIsMoreData(0);
			rc = 0;
			return cResponse.forNewCustomer(customerId);
		}
		cResponse.setHeader(1, "Error encountered while inserting new customer");
		cResponse.setIsMoreData(0);
		rc = 1;
		return cResponse.forError();
	}
	
	public int getRC(){
		return rc;
	}
}