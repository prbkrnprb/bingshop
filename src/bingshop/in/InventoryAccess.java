package bingshop.in;

public class InventoryAccess extends InventoryDB {

	public InventoryAccess() throws Exception {
		super();
	}
	
	public String newMainCategory(String name, String description, String pictureID, String langID){
		return super.newMainCategory("0", name,description,pictureID,langID);		
	}
	
	public String newSub1Cat(String fID, String name, String description, String pictureID, String langID){
		return super.newSub1Cat("0",fID, name,description,pictureID,langID);
	}
	
	public String newSub2Cat(String fID, String name, String description, String pictureID, String langID){
		return super.newSub2Cat("0",fID, name,description,pictureID,langID);	
	}
	
	public String newItem(String fID, String name, String description, String pictureID, String langID){
		return super.newItem("0",fID, name,description,pictureID,langID);		
	}
	
	public String newBrand(String fID, String name, String description, String pictureID, String langID){
		return super.newBrand("0",fID, name,description,pictureID,langID);	
	}
	
	public String newItemSize(String fID, String name, String langID, float price, int stock){
		return super.newItemSize("0",fID, name,langID,price,stock);	
	}
	
	public String[][] getMainCategory(){
		return super.getMainCategory();
	}
	
	public String[][] getMainCategory(String cID,String name){
		return super.getMainCategory(cID,name);
	}
	
	public String[][] getMainCategory(String cID){
		return super.getMainCategory(cID);
	}
	
	public String[][] getSub1Cat(String cID, String name, String fID){
		return super.getSub1Cat(cID,name,fID);
	}
	
	public String[][] getSub1Cat(String name, String fID){
		return super.getSub1Cat(name,fID);
	}
	
	public String[][] getSub1Cat(String cID){
		return super.getSub1Cat(cID);
	}
	
	public String[][] getSub2Cat(String cID, String name, String fID){
		return super.getSub2Cat(cID,name,fID);
	}
	
	public String[][] getSub2Cat(String name, String fID){
		return super.getSub2Cat(name,fID);
	}
	
	public String[][] getSub2Cat(String cID){
		return super.getSub2Cat(cID);
	}
	
	public String[][] getItem(String cID, String name, String fID){
		return super.getItem(cID,name,fID);
	}
	
	public String[][] getItem(String name, String fID){
		return super.getItem(name,fID);
	}
	
	public String[][] getItem(String cID){
		return super.getItem(cID);
	}
	
	public String[][] getBrand(String cID, String name, String fID){
		return super.getBrand(cID,name,fID);
	}
	
	public String[][] getBrand(String name, String fID){
		return super.getBrand(name,fID);
	}
	
	public String[][] getBrand(String cID){
		return super.getBrand(cID);
	}
	
	public String[][] getItemSize(String cID, String name, String fID){
		return super.getItemSize(cID,name,fID);
	}
	
	public String[][] getItemSizeByPID(String cID){
		return super.getItemSizeByPID(cID);
	}
	
	public String[][] getItemSizeByFID(String fID){
		return super.getItemSizeByFID(fID);
	}
	
	public String getItemSizeNameByPID(String pID){
		return super.getItemSizeNameByPID(pID);
	}
	
	public String getItemNameByPID(String pID){
		return super.getItemNameByPID(pID);
	}
	
	public String getBrandNameByPID(String pID){
		return super.getBrandNameByPID(pID);
	}
	
	public Double getItemPrice(String pID){
		return super.getItemPrice(pID);
	}
}
