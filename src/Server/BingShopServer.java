package Server;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class BingShopServer extends Thread {
	private final int port = 28738;                        					// incoming port
	private final int maxAllowable = 1000;                 					// maximum allowable clients
	public int currentClients = 0;                         					// number of clients connected currently
	
	public void run(){
		try{
			ServerSocket sSocket = new ServerSocket(port);
			while (currentClients < maxAllowable){         					// loops infinitely till the server reaches acceptable limit
				currentClients++;                          					// increments client variable
				System.out.println("Waiting for connection");
				Socket conn = sSocket.accept();            					// accepts client connection
				System.out.println("Client accepted : " + currentClients);
				Thread t = new ServerThread(conn,currentClients);     		// new server instance is created
				t.start();
			}
			sSocket.close();                               					// closes the server connection
		}
		catch(Exception e){
			System.out.println("Error while creating a new Server thread : " + e);
		}
	}
}
	
class ServerThread extends Thread{
	Socket s;
	Thread t;
	int client;
	int x=0;
	public ServerThread(Socket s,int client){
		t = new Thread("Client " + client);
		this.s = s;
		this.client = client;
	}
		
	public void run() {
		String clientData = null;
		InputStream iStream;
		BufferedReader receiveRead;	
		SaveClient saveClientData = new SaveClient();
		try {
			iStream = s.getInputStream();
			receiveRead = new BufferedReader(new InputStreamReader(iStream));
			OutputStream ostream = s.getOutputStream(); 
			PrintWriter pwrite = new PrintWriter(ostream, true);
			
			while(true){
				clientData = receiveRead.readLine();
				if (clientData == null){
					System.out.println("Client exit : " + client);
					break;
				}				
				System.out.println("Received data : " + clientData);
				ProcessClient processClient = new ProcessClient(clientData,saveClientData);
				switch(processClient.responseType) {
				case 0:
					pwrite.println(processClient.responseString);
					pwrite.flush();
					System.out.println("Server Response : " + processClient.responseString);
					saveClientData = processClient.saveClientData;
					break;
				}
			}						
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
/*
class ServerResponse extends ProcessClientData{
	public String response;
	ComposeClientResponse cResponse;
	public ServerResponse(String clientData) throws Exception{		
		super(clientData);
		response = null;
		cResponse = new ComposeClientResponse();
		start();
	}
	
	private void start() throws Exception{
		String table,key;
		switch(clientType) {                               // select path based on the client type
		case "mobile":
			switch(clientRequest){                         // select path based on the client request
			case "login":
				String customerID = loginValid();
				if (customerID == "0"){
					response = cResponse.invalidLogin();
				}
				else
					response = cResponse.validLogin(customerID);    						
				break;
				
			case "getTable":
				table = getTable();
				key = getFKey();
				if(table.equals("")){
					response = cResponse.composeTableTop();
				}
				else
					response = cResponse.composeTableData(table, key);
				break;
				
			case "getNameForKey":
				table = getTable();
				ArrayList <String> keys = getKeyArray();
				response = cResponse.composeNameForKey(table, keys);
				break;
				
			case "getPictureForKey":
				response = cResponse.composePictureData(getKey());
				break;
				
			case "checkout":
				ArrayList <String> itemSizeID = getKeyArray();
				ArrayList <String> quantity = getQuantityArray();
				String cID = getCustomerID();
				CheckoutProcess checkout = new CheckoutProcess(cID,itemSizeID,quantity);
				String orderID = checkout.orderID;
				response = cResponse.composeCheckoutResponse(orderID);
				break;
			}
			break;			
		}
	}
	
	private String loginValid() throws Exception{
		String userName = getUserName();
		String pwd = getPWD();
		UserLogin uLogin = new UserLogin();
		return uLogin.loginValid(userName, pwd);		
	}
}
*/
