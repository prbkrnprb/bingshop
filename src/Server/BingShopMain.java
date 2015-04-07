package Server;

public class BingShopMain {	

	public static void main(String[] args) {
		createServer();
	}
	public static void createServer(){
		Thread bServer = new BingShopServer();
		new BingShopPicServer();
		System.out.println("hello");
		try{
			bServer.start();
		}
		catch(Exception e){
			System.out.println("Exception thrown while calling new Server" + e);
		}
	}
}
