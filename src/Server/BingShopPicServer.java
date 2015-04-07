package Server;


import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class BingShopPicServer implements Runnable{
	private final int port = 01126;                         					
	
	public BingShopPicServer(){
		new Thread(this,"Picture Server Thread").start();
	}
	
	public void run(){
		ServerSocket sSocket = null;
		try{
			sSocket = new ServerSocket(port);                          		
			while(true){
				System.out.println("Waiting for picture connection");
				Socket conn = sSocket.accept();            					// accepts client connection
				System.out.println("Client accepted : Picture");
				new PicServerThread(conn);	
			}                            					// closes the server connection
		}
		catch(Exception e){
			System.out.println("Error while creating a new Server thread : " + e);
		}
		try {
			sSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}   
	}
}
	
class PicServerThread implements Runnable{
	Socket s;
	Thread t;
	int x=0;
	public PicServerThread(Socket s){
		this.s = s;
		new Thread(this,"Client for Picture").start();
	}
		
	public void run() {
		String clientData = null;
		InputStream iStream;
		BufferedReader receiveRead;	
		try {
			iStream = s.getInputStream();
			receiveRead = new BufferedReader(new InputStreamReader(iStream));
			OutputStream ostream = s.getOutputStream(); 
			PrintWriter pwrite = new PrintWriter(ostream, true);
				clientData = receiveRead.readLine();
				if (clientData == null){
					System.out.println("Client picture exit : ");
				}	
				else{				
					System.out.println("Picture Received data : " + clientData);
					ProcessClient processClient = new ProcessClient(clientData);
					if(processClient.rc == 0)
						ImageIO.write(processClient.responseImage,"JPG",s.getOutputStream());
					else{
						pwrite.println("none");
						pwrite.flush();
					}
				}								
		} catch (IOException e) {
			e.printStackTrace();			
		}	
		try {
			s.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}


