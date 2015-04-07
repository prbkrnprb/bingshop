package bingshop.in;

import java.util.ArrayList;

public class OrderAccess extends OrderDB {
	
	CustomerDB customerDb;

	public OrderAccess() throws Exception {
		super();
		customerDb = new CustomerDB();
	}
	
	protected String newOrder(String customerID,ArrayList<String> itemSizeID,ArrayList<String> quantity,ArrayList<Double> price){
		
		int totalItem = itemSizeID.size();
		String orderID = super.newOrder("0",customerID,totalItem,0.0,0,0.0,"",0.0);
		Double totPrice = 0.0;
		for(int i = 0;i < totalItem;i++){
			String itemID = itemSizeID.get(i);
			int quant = Integer.parseInt(quantity.get(i));
			Double indPrice = price.get(i);
			totPrice += indPrice;
			newOrderItem(orderID,itemID,quant,indPrice);
		}
		super.updateOrderPrice(orderID, totPrice);
		try {
			int oldItems = customerDb.getItems(customerID);
			double oldAmount = customerDb.getAmount(customerID);
			customerDb.updateItems(customerID, oldItems + totalItem);
			customerDb.updateAmount(customerID, oldAmount + totPrice);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in updating items & amount in Customer table. Error : " + e);
		}
		return orderID;		
		
	}
}
