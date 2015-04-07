package Server;

import java.util.ArrayList;
import java.util.Arrays;

public class BingUtil {
	
	public static final String PictureDirectory = "C:\\Users\\Prabakaran\\Desktop\\Bingshop\\Pictures\\";
	
	public ArrayList<String> toArrayListString(String string){
		return new ArrayList<String>(Arrays.asList(string.substring(1, string.length() - 1).split(", ")));
	}
	
	public final String tableList[] = new String[] {"Main Category", "Sub Category 1", "Sub Category 2", "Item", "Brand", "Item Size"};
	
	public String findParent(String child){
		if(child.equals(tableList[0]))
			return "";
		for(int i = 1; i < tableList.length; i++){
			if(child.equals(tableList[i]))
				return tableList[i-1];
		}
		return null;
	}
	
	public String findChild(String parent){
		if(parent.equals(tableList[tableList.length-1]))
			return "";
		for(int i = 1; i < tableList.length; i++){
			if(parent.equals(tableList[i]))
				return tableList[i+1];
		}
		return null;
	}
}

class SaveClient {
	public String ip, type, customerID;
}
