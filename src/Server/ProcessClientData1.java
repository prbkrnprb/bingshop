package Server;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import MyException.MyObjNotFoundException;

public class ProcessClientData1 {

    JSONObject serverDataJObj;
    JSONObject header,metaData,data;

    public ProcessClientData1(String serverData)throws MyObjNotFoundException{
        Object obj=JSONValue.parse(serverData);
        serverDataJObj = (JSONObject) obj;
        processHeader();                                                                                // mandatory data. Exception will thrown if not found
        processMetaData();
        processData();
    }

    private void processHeader(){
        try {
            header = (JSONObject) JSONValue.parse(serverDataJObj.get("header").toString());
        }catch (RuntimeException e){
            
        }
    }

    private void processMetaData()throws MyObjNotFoundException{
        try {
            metaData = (JSONObject) JSONValue.parse(serverDataJObj.get("metadata").toString());
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new MyObjNotFoundException("Meta data not found",1);
        }
    }

    private void processData(){
        try {
            data = (JSONObject) JSONValue.parse(serverDataJObj.get("data").toString());
        }catch (RuntimeException e){
        }
    }

    public String getIP() throws MyObjNotFoundException {
        String message;
        try {
            message = header.get("ip").toString();
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new MyObjNotFoundException("IP not found in header",101);
        }
        return message;
    }

    public String getClientType() throws MyObjNotFoundException{
        String message;
        try {
            message = header.get("clienttype").toString();
        }catch (RuntimeException e){
            throw new MyObjNotFoundException("Client Type not found in header",102);
        }
        return message;
    }

    public String getUser() throws MyObjNotFoundException{
        String message;
        try {
            message = metaData.get("user").toString();
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new MyObjNotFoundException("User not found in meta data",201);
        }
        return message;
    }

    public String getPwd() throws MyObjNotFoundException{
        String message;
        try {
            message = metaData.get("pwd").toString();
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new MyObjNotFoundException("Password not found in meta data",202);
        }
        return message;
    }

    public String getRequest() throws MyObjNotFoundException{
        String message;
        try {
            message = metaData.get("request").toString();
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new MyObjNotFoundException("Request not found in meta data",203);
        }
        return message;
    }

    public String getSocialId() throws MyObjNotFoundException{
        String message;
        try {
            message = metaData.get("socialid").toString();
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new MyObjNotFoundException("Social Id not found in meta data",204);
        }
        return message;
    }
    
    public String getTableName() throws MyObjNotFoundException{
        String message;
        try {
            message = data.get("tablename").toString();
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new MyObjNotFoundException("Table Name not found in data",301);
        }
        return message;
    }
    
    public String getTableKey() throws MyObjNotFoundException{
        String message;
        try {
            message = data.get("tablekey").toString();
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new MyObjNotFoundException("Table Key not found in data",302);
        }
        return message;
    }
    
    public String getCartData() throws MyObjNotFoundException{
        String message;
        try {
            message = data.get("cartdata").toString();
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new MyObjNotFoundException("Cart Data not found in data",303);
        }
        return message;
    }
    
    public ArrayList<String> getItemKeys() throws MyObjNotFoundException{
        ArrayList<String> message = new ArrayList<String>();
        JSONArray dataArray = (JSONArray) JSONValue.parse(getCartData());
        try {
        	JSONObject columnData;
        	int rowCount = dataArray.size();
            for (int i = 0; i < rowCount; i++){
                columnData = (JSONObject) JSONValue.parse(dataArray.get(i).toString());
                message.add(i, (String) columnData.get("Key"));                
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new MyObjNotFoundException("Key not found in Cart data",304);
        }
        return message;
    }
    
    public ArrayList<String> getQuantity() throws MyObjNotFoundException{
        ArrayList<String> message = new ArrayList<String>();
        JSONArray dataArray = (JSONArray) JSONValue.parse(getCartData());
        try {
        	JSONObject columnData;
        	int rowCount = dataArray.size();
            for (int i = 0; i < rowCount; i++){
                columnData = (JSONObject) JSONValue.parse(dataArray.get(i).toString());
                message.add(i, columnData.get("Quantity").toString());                
            }
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new MyObjNotFoundException("Quantity not found in Cart data",305);
        }
        return message;
    }
    
    public String getEmail() throws MyObjNotFoundException{
        String message;
        try {
            message = data.get("email").toString();
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new MyObjNotFoundException("Email Data not found in data",306);
        }
        return message;
    }
    
    public String getPhoneNumber() throws MyObjNotFoundException{
        String message;
        try {
            message = data.get("phonenumber").toString();
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new MyObjNotFoundException("Phone Number Data not found in data",307);
        }
        return message;
    }
    
    public String getNewCustomerInfo() throws MyObjNotFoundException{
        String message;
        try {
            message = data.get("customerinfo").toString();
        }catch (RuntimeException e){
            e.printStackTrace();
            throw new MyObjNotFoundException("Customer Info Data not found in data",308);
        }
        return message;
    }
}

