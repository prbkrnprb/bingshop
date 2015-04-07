package bingshop.in;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class CustomerAccess extends CustomerDB{
	public CustomerAccess() throws Exception{
		super();
	}
	
	public String newCustomer(String customerValues){
		JSONObject customer;
		Object obj = JSONValue.parse(customerValues);
		customer = (JSONObject) obj;
		String customerId;
		customerId = super.newCustomer((String)customer.get("firstname"),(String)customer.get("lastname"),(String)customer.get("password"),(String)customer.get("email"),(String)customer.get("phonenumber"),customer.get("validatedphone").toString(),customer.get("validatedemail").toString());
		if(customerId != "0" || customerId != "9"){
			newAddress(customerId,1,(String)customer.get("line1"),(String)customer.get("line2"),(String)customer.get("area"),(String)customer.get("city"),(String)customer.get("state"),(String)customer.get("country"),(String)customer.get("pincode"));
			String socialId = customer.get("socialid").toString();
			if(socialId != "0"){
				newSocial(socialId,customerId);
			}
		}		
		return customerId;
	}
	
	public String loginValid(String uName,String pwd,String socialId) throws Exception{
		String customerId;
		int visits;
		if(socialId == ""){
			customerId = checkUserPwd(uName,pwd);
			visits = getVisits(customerId);
			updateVisits(customerId,visits + 1);
			updateLastActivity(customerId);
			return customerId;
		}
		else{
			customerId = getCustomerIdForSocialId(socialId);
			if(customerId != "0"){
				visits = getVisits(customerId);
				updateVisits(customerId,visits + 1);
				updateLastActivity(customerId);
				return checkUserPwd(customerId,pwd);
			}
		}
		return "0";
	}
}
